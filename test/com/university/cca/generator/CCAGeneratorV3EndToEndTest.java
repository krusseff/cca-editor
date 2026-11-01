package com.university.cca.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.university.cca.entities.Ambient;
import com.university.cca.entities.Message;
import com.university.cca.enums.AmbientType;
import com.university.cca.enums.CommunicationType;
import com.university.cca.interpreter.CcaInterpreter;
import com.university.cca.util.CcaPlExecutor;

/**
 * End-to-end tests for CCA Generator V3 with interpreter verification.
 *
 * These tests simulate the creation of ambients and messages (as the GUI would),
 * use CCA Generator V3 to produce ccaPL code, write it to a temporary .cca file,
 * and then execute it using both the ccaPL JAR executor and the integrated
 * interpreter to verify that the generated code is valid and produces the
 * expected communication patterns.
 *
 * Test scenarios are inspired by real .cca files found in the cca-prior-work folder:
 * - Simple sibling communication (like scenario.cca, my_simple_scenario.cca)
 * - Parent-child communication (like cca_generator_version_2_parent_child_hierarchy.cca)
 * - Forwarding chains with mixed hierarchy (like SmartCity.cca, school_scenario.cca)
 *
 * @author Konstantin Rusev
 * @version 1.0
 */
public class CCAGeneratorV3EndToEndTest {

	private File tempCcaFile;

	@BeforeEach
	public void setUp() throws IOException {
		tempCcaFile = File.createTempFile("cca_v3_e2e_test_", ".cca");
	}

	@AfterEach
	public void tearDown() {
		if (tempCcaFile != null && tempCcaFile.exists()) {
			tempCcaFile.delete();
		}
	}

	// =========================================================================
	// Scenario 1: Simple sibling communication (like my_simple_scenario_1.cca)
	// Reference: PA <-> Museum (sibling send/recv)
	// =========================================================================

	@Test
	public void testScenario_simpleSibling_generatesValidCcaPL() throws Exception {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("PA", "City", true),
			createAmbient("Museum", "City", true)
		);
		List<Message> messages = Arrays.asList(
			new Message("PA", "Museum", "null", "null", "museum_status"),
			new Message("Museum", "PA", "null", "museum_status", "museum_open")
		);

		String generated = generateFullCcaPL(ambients, messages);

		assertNotNull(generated, "Generated ccaPL should not be null");
		assertTrue(generated.contains("Museum::send(museum_status).0"), "PA should send to sibling Museum");
		assertTrue(generated.contains("Museum::recv(museum_status,museum_open).0"), "PA should recv from Museum");
		assertTrue(generated.contains("PA::recv(museum_status)."), "Museum should recv from PA");
		assertTrue(generated.contains("PA::send(museum_status,museum_open).0"), "Museum should send to PA");
	}

	@Test
	public void testScenario_simpleSibling_executesViaIntegratedInterpreter() throws Exception {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("PA", "City", true),
			createAmbient("Museum", "City", true)
		);
		List<Message> messages = Arrays.asList(
			new Message("PA", "Museum", "null", "null", "museum_status"),
			new Message("Museum", "PA", "null", "museum_status", "museum_open")
		);

		String generated = generateFullCcaPL(ambients, messages);
		writeCcaFile(generated);

		String output = CcaInterpreter.execute(tempCcaFile.getAbsolutePath());

		assertNotNull(output, "Interpreter output should not be null");
		assertTrue(output.contains("CCA program parsed successfully"),
				"Program should parse successfully");
		assertTrue(output.contains("--->"),
				"Output should contain reduction steps");
		assertTrue(output.contains("Sibling to sibling:"),
				"Output should contain sibling-to-sibling communication");
		assertTrue(output.contains("PA ===(museum_status)===> Museum"),
				"Output should show PA sending museum_status to Museum");
		assertTrue(output.contains("Museum ===(museum_status,museum_open)===> PA"),
				"Output should show Museum responding to PA");
	}

	@Test
	public void testScenario_simpleSibling_executesViaJar() throws Exception {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("PA", "City", true),
			createAmbient("Museum", "City", true)
		);
		List<Message> messages = Arrays.asList(
			new Message("PA", "Museum", "null", "null", "museum_status"),
			new Message("Museum", "PA", "null", "museum_status", "museum_open")
		);

		String generated = generateFullCcaPL(ambients, messages);
		writeCcaFile(generated);

		CcaPlExecutor.ExecutionResult result = CcaPlExecutor.execute(tempCcaFile.getAbsolutePath());

		assertTrue(result.isSuccess(), "JAR execution should be successful");
		assertTrue(result.hasOutput(), "JAR execution should produce output");
		assertTrue(result.getOutput().contains("Sibling to sibling:"),
				"JAR output should contain sibling communication");
		assertTrue(result.getOutput().contains("PA ===(museum_status)===> Museum"),
				"JAR output should show PA to Museum message");
	}

	// =========================================================================
	// Scenario 2: Multiple siblings (like scenario.cca — Hotel mediating)
	// Reference: PA <-> Hotel <-> Museum, PA <-> Hotel <-> BS
	// =========================================================================

	@Test
	public void testScenario_multipleSiblings_generatesValidCcaPL() throws Exception {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("PA", "City", true),
			createAmbient("Hotel", "City", true),
			createAmbient("Museum", "City", true),
			createAmbient("BS", "City", true)
		);
		List<Message> messages = Arrays.asList(
			new Message("PA", "Hotel", "null", "null", "museum_status"),
			new Message("Hotel", "PA", "null", "museum_status", "Museum_open"),
			new Message("Hotel", "Museum", "null", "null", "worktime"),
			new Message("Museum", "Hotel", "null", "worktime", "open"),
			new Message("Hotel", "BS", "null", "null", "timetable"),
			new Message("BS", "Hotel", "null", "timetable", "bus_schedule")
		);

		String generated = generateFullCcaPL(ambients, messages);

		assertNotNull(generated);
		assertTrue(generated.contains("PA["), "Should contain PA ambient block");
		assertTrue(generated.contains("Hotel["), "Should contain Hotel ambient block");
		assertTrue(generated.contains("Museum["), "Should contain Museum ambient block");
		assertTrue(generated.contains("BS["), "Should contain BS ambient block");
		assertTrue(generated.contains("Hotel::send(museum_status).0"), "PA should send to Hotel");
		assertTrue(generated.contains("Museum::send(worktime).0"), "Hotel should send to Museum");
		assertTrue(generated.contains("BS::send(timetable).0"), "Hotel should send to BS");
	}

	@Test
	public void testScenario_multipleSiblings_allSiblingCommunicationViaInterpreter() throws Exception {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("PA", "City", true),
			createAmbient("Hotel", "City", true),
			createAmbient("Museum", "City", true),
			createAmbient("BS", "City", true)
		);
		List<Message> messages = Arrays.asList(
			new Message("PA", "Hotel", "null", "null", "museum_status"),
			new Message("Hotel", "PA", "null", "museum_status", "Museum_open"),
			new Message("Hotel", "Museum", "null", "null", "worktime"),
			new Message("Museum", "Hotel", "null", "worktime", "open"),
			new Message("Hotel", "BS", "null", "null", "timetable"),
			new Message("BS", "Hotel", "null", "timetable", "bus_schedule")
		);

		String generated = generateFullCcaPL(ambients, messages);
		writeCcaFile(generated);

		String output = CcaInterpreter.execute(tempCcaFile.getAbsolutePath());

		assertTrue(output.contains("CCA program parsed successfully"));
		assertTrue(output.contains("Sibling to sibling:"));

		int siblingCount = countOccurrences(output, "Sibling to sibling:");
		assertEquals(6, siblingCount,
				"Should have exactly 6 sibling communication events (3 pairs of send/recv)");
	}

	// =========================================================================
	// Scenario 3: Parent-to-child communication
	// Reference: cca_generator_version_2_parent_child_hierarchy.cca
	// PLOVDIV_UNIVERSITY -> FMI (parent sends to child)
	// =========================================================================

	@Test
	public void testScenario_parentToChild_generatesCorrectSpecifiers() throws Exception {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("PLOVDIV_UNIVERSITY", "City", true),
			createAmbient("FMI", "PLOVDIV_UNIVERSITY", true)
		);
		List<Message> messages = Arrays.asList(
			new Message("PLOVDIV_UNIVERSITY", "FMI", "null", "null", "FMI_rooms"),
			new Message("FMI", "PLOVDIV_UNIVERSITY", "null", "FMI_rooms", "room_543")
		);

		String generated = generateFullCcaPL(ambients, messages);

		assertTrue(generated.contains("FMI#send(FMI_rooms).0"),
				"Parent should use # to send to child");
		assertTrue(generated.contains("FMI#recv(FMI_rooms,room_543).0"),
				"Parent should use # to recv from child");
		assertTrue(generated.contains("FMI["),
				"FMI should be nested inside PLOVDIV_UNIVERSITY block");
		assertTrue(generated.contains("@recv(FMI_rooms)."),
				"Child should use @ to recv from parent");
		assertTrue(generated.contains("@send(FMI_rooms,room_543).0"),
				"Child should use @ to send to parent");
		assertFalse(generated.contains("FMI::"),
				"Should NOT use :: for parent-child communication");
	}

	@Test
	public void testScenario_parentToChild_executesViaInterpreter() throws Exception {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("PLOVDIV_UNIVERSITY", "City", true),
			createAmbient("FMI", "PLOVDIV_UNIVERSITY", true)
		);
		List<Message> messages = Arrays.asList(
			new Message("PLOVDIV_UNIVERSITY", "FMI", "null", "null", "FMI_rooms"),
			new Message("FMI", "PLOVDIV_UNIVERSITY", "null", "FMI_rooms", "room_543")
		);

		String generated = generateFullCcaPL(ambients, messages);
		writeCcaFile(generated);

		String output = CcaInterpreter.execute(tempCcaFile.getAbsolutePath());

		assertTrue(output.contains("CCA program parsed successfully"));
		assertTrue(output.contains("Parent to child:"),
				"Output should contain parent-to-child communication");
		assertTrue(output.contains("Child to parent:"),
				"Output should contain child-to-parent communication");
		assertTrue(output.contains("PLOVDIV_UNIVERSITY ===(FMI_rooms)===> FMI"),
				"Output should show parent sending to child");
		assertTrue(output.contains("FMI ===(FMI_rooms,room_543)===> PLOVDIV_UNIVERSITY"),
				"Output should show child responding to parent");
	}

	@Test
	public void testScenario_parentToChild_executesViaJar() throws Exception {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("PLOVDIV_UNIVERSITY", "City", true),
			createAmbient("FMI", "PLOVDIV_UNIVERSITY", true)
		);
		List<Message> messages = Arrays.asList(
			new Message("PLOVDIV_UNIVERSITY", "FMI", "null", "null", "FMI_rooms"),
			new Message("FMI", "PLOVDIV_UNIVERSITY", "null", "FMI_rooms", "room_543")
		);

		String generated = generateFullCcaPL(ambients, messages);
		writeCcaFile(generated);

		CcaPlExecutor.ExecutionResult result = CcaPlExecutor.execute(tempCcaFile.getAbsolutePath());

		assertTrue(result.isSuccess(), "JAR execution should succeed");
		assertTrue(result.getOutput().contains("Parent to child:"),
				"JAR output should contain parent-to-child communication");
		assertTrue(result.getOutput().contains("Child to parent:"),
				"JAR output should contain child-to-parent communication");
	}

	// =========================================================================
	// Scenario 4: Child-to-parent communication (child initiates)
	// FMI (child) sends report to PLOVDIV_UNIVERSITY (parent)
	// =========================================================================

	@Test
	public void testScenario_childToParent_childInitiates() throws Exception {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("University", "City", true),
			createAmbient("Department", "University", true)
		);
		List<Message> messages = Arrays.asList(
			new Message("Department", "University", "null", "null", "report"),
			new Message("University", "Department", "null", "report", "acknowledged")
		);

		String generated = generateFullCcaPL(ambients, messages);

		assertTrue(generated.contains("University["), "Should have University block");
		assertTrue(generated.contains("Department["), "Department should be nested");
		assertTrue(generated.contains("@send(report).0"),
				"Child initiator should use @ to send to parent");
		assertTrue(generated.contains("@recv(report,acknowledged).0"),
				"Child initiator should use @ to recv from parent");
		assertTrue(generated.contains("Department#recv(report)."),
				"Parent responder should use # to recv from child");
		assertTrue(generated.contains("Department#send(report,acknowledged).0"),
				"Parent responder should use # to send to child");
	}

	@Test
	public void testScenario_childToParent_executesViaInterpreter() throws Exception {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("University", "City", true),
			createAmbient("Department", "University", true)
		);
		List<Message> messages = Arrays.asList(
			new Message("Department", "University", "null", "null", "report"),
			new Message("University", "Department", "null", "report", "acknowledged")
		);

		String generated = generateFullCcaPL(ambients, messages);
		writeCcaFile(generated);

		String output = CcaInterpreter.execute(tempCcaFile.getAbsolutePath());

		assertTrue(output.contains("CCA program parsed successfully"));
		assertTrue(output.contains("Child to parent:"),
				"Output should show child-to-parent direction");
		assertTrue(output.contains("Parent to child:"),
				"Output should show parent-to-child response");
		assertTrue(output.contains("Department ===(report)===> University"),
				"Output should show Department sending report to University");
		assertTrue(output.contains("University ===(report,acknowledged)===> Department"),
				"Output should show University acknowledging to Department");
	}

	// =========================================================================
	// Scenario 5: Mixed sibling + parent-child (like ambients.cca in cca-editor)
	// BUS_STATION <-> TAXI (sibling) + PLOVDIV_UNIVERSITY -> FMI (parent-child)
	// + STUDENT_PA <-> PLOVDIV_UNIVERSITY (sibling, with forwarding)
	// =========================================================================

	@Test
	public void testScenario_mixedSiblingAndParentChild_generatesValidCcaPL() throws Exception {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("BUS_STATION", "City", true),
			createAmbient("TAXI", "City", true),
			createAmbient("PLOVDIV_UNIVERSITY", "City", true),
			createAmbient("FMI", "PLOVDIV_UNIVERSITY", true),
			createAmbient("STUDENT_PA", "City", true)
		);

		List<Message> messages = Arrays.asList(
			new Message("BUS_STATION", "TAXI", "null", "null", "are_you_free"),
			new Message("TAXI", "BUS_STATION", "null", "are_you_free", "yes_I_am"),
			new Message("STUDENT_PA", "PLOVDIV_UNIVERSITY", "null", "null", "FMI_rooms"),
			new Message("PLOVDIV_UNIVERSITY", "FMI", "STUDENT_PA", "null", "FMI_rooms_from_uni_to_fmi"),
			new Message("FMI", "PLOVDIV_UNIVERSITY", "null", "FMI_rooms_from_uni_to_fmi", "fmi_to_uni_543"),
			new Message("PLOVDIV_UNIVERSITY", "STUDENT_PA", "null", "FMI_rooms", "fmi_to_uni_543")
		);

		String generated = generateFullCcaPL(ambients, messages);

		// Sibling: BUS_STATION <-> TAXI
		assertTrue(generated.contains("BUS_STATION["), "Should have BUS_STATION block");
		assertTrue(generated.contains("TAXI::send(are_you_free).0"), "BUS_STATION sends to sibling TAXI");
		assertTrue(generated.contains("TAXI::recv(are_you_free,yes_I_am).0"), "BUS_STATION recvs from sibling TAXI");
		assertTrue(generated.contains("TAXI["), "Should have TAXI block");
		assertTrue(generated.contains("BUS_STATION::recv(are_you_free)."), "TAXI recvs from BUS_STATION");
		assertTrue(generated.contains("BUS_STATION::send(are_you_free,yes_I_am).0"), "TAXI responds to BUS_STATION");

		// Forwarding: STUDENT_PA -> PLOVDIV_UNIVERSITY -> FMI -> PLOVDIV_UNIVERSITY -> STUDENT_PA
		assertTrue(generated.contains("PLOVDIV_UNIVERSITY["), "Should have PLOVDIV_UNIVERSITY block");
		assertTrue(generated.contains("FMI["), "FMI should be nested inside PLOVDIV_UNIVERSITY");
		assertTrue(generated.contains("FMI#send(FMI_rooms_from_uni_to_fmi)."),
				"Relay step 2: University forwards to child FMI with #");
		assertTrue(generated.contains("FMI#recv(FMI_rooms_from_uni_to_fmi,fmi_to_uni_543)."),
				"Relay step 3: University recvs from child FMI with #");
		assertTrue(generated.contains("STUDENT_PA::recv(FMI_rooms)."),
				"Relay step 1: University recvs from sibling STUDENT_PA with ::");
		assertTrue(generated.contains("STUDENT_PA::send(FMI_rooms,fmi_to_uni_543).0"),
				"Relay step 4: University sends back to sibling STUDENT_PA with ::");

		// Child side: FMI uses @
		assertTrue(generated.contains("@recv(FMI_rooms_from_uni_to_fmi)."), "FMI uses @ to recv from parent");
		assertTrue(generated.contains("@send(FMI_rooms_from_uni_to_fmi,fmi_to_uni_543).0"), "FMI uses @ to send to parent");

		// STUDENT_PA: simple initiator
		assertTrue(generated.contains("STUDENT_PA["), "Should have STUDENT_PA block");
		assertTrue(generated.contains("PLOVDIV_UNIVERSITY::send(FMI_rooms).0"), "STUDENT_PA sends to sibling University");
		assertTrue(generated.contains("PLOVDIV_UNIVERSITY::recv(FMI_rooms,fmi_to_uni_543).0"),
				"STUDENT_PA recvs from sibling University");
	}

	@Test
	public void testScenario_mixedSiblingAndParentChild_executesViaInterpreter() throws Exception {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("BUS_STATION", "City", true),
			createAmbient("TAXI", "City", true),
			createAmbient("PLOVDIV_UNIVERSITY", "City", true),
			createAmbient("FMI", "PLOVDIV_UNIVERSITY", true),
			createAmbient("STUDENT_PA", "City", true)
		);

		List<Message> messages = Arrays.asList(
			new Message("BUS_STATION", "TAXI", "null", "null", "are_you_free"),
			new Message("TAXI", "BUS_STATION", "null", "are_you_free", "yes_I_am"),
			new Message("STUDENT_PA", "PLOVDIV_UNIVERSITY", "null", "null", "FMI_rooms"),
			new Message("PLOVDIV_UNIVERSITY", "FMI", "STUDENT_PA", "null", "FMI_rooms_from_uni_to_fmi"),
			new Message("FMI", "PLOVDIV_UNIVERSITY", "null", "FMI_rooms_from_uni_to_fmi", "fmi_to_uni_543"),
			new Message("PLOVDIV_UNIVERSITY", "STUDENT_PA", "null", "FMI_rooms", "fmi_to_uni_543")
		);

		String generated = generateFullCcaPL(ambients, messages);
		writeCcaFile(generated);

		String output = CcaInterpreter.execute(tempCcaFile.getAbsolutePath());

		assertTrue(output.contains("CCA program parsed successfully"));

		// All three communication types should be present
		assertTrue(output.contains("Sibling to sibling:"),
				"Output should contain sibling-to-sibling communication");
		assertTrue(output.contains("Parent to child:"),
				"Output should contain parent-to-child communication");
		assertTrue(output.contains("Child to parent:"),
				"Output should contain child-to-parent communication");

		// Verify specific message exchanges
		assertTrue(output.contains("BUS_STATION ===(are_you_free)===> TAXI"),
				"Output should show BUS_STATION to TAXI sibling message");
		assertTrue(output.contains("TAXI ===(are_you_free,yes_I_am)===> BUS_STATION"),
				"Output should show TAXI response to BUS_STATION");
		assertTrue(output.contains("PLOVDIV_UNIVERSITY ===(FMI_rooms_from_uni_to_fmi)===> FMI"),
				"Output should show parent-to-child message");
		assertTrue(output.contains("FMI ===(FMI_rooms_from_uni_to_fmi,fmi_to_uni_543)===> PLOVDIV_UNIVERSITY"),
				"Output should show child-to-parent response");
	}

	@Test
	public void testScenario_mixedSiblingAndParentChild_executesViaJar() throws Exception {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("BUS_STATION", "City", true),
			createAmbient("TAXI", "City", true),
			createAmbient("PLOVDIV_UNIVERSITY", "City", true),
			createAmbient("FMI", "PLOVDIV_UNIVERSITY", true),
			createAmbient("STUDENT_PA", "City", true)
		);

		List<Message> messages = Arrays.asList(
			new Message("BUS_STATION", "TAXI", "null", "null", "are_you_free"),
			new Message("TAXI", "BUS_STATION", "null", "are_you_free", "yes_I_am"),
			new Message("STUDENT_PA", "PLOVDIV_UNIVERSITY", "null", "null", "FMI_rooms"),
			new Message("PLOVDIV_UNIVERSITY", "FMI", "STUDENT_PA", "null", "FMI_rooms_from_uni_to_fmi"),
			new Message("FMI", "PLOVDIV_UNIVERSITY", "null", "FMI_rooms_from_uni_to_fmi", "fmi_to_uni_543"),
			new Message("PLOVDIV_UNIVERSITY", "STUDENT_PA", "null", "FMI_rooms", "fmi_to_uni_543")
		);

		String generated = generateFullCcaPL(ambients, messages);
		writeCcaFile(generated);

		CcaPlExecutor.ExecutionResult result = CcaPlExecutor.execute(tempCcaFile.getAbsolutePath());

		assertTrue(result.isSuccess(), "JAR execution should succeed for mixed scenario");
		assertTrue(result.getOutput().contains("Sibling to sibling:"));
		assertTrue(result.getOutput().contains("Parent to child:"));
		assertTrue(result.getOutput().contains("Child to parent:"));
	}

	// =========================================================================
	// Scenario 6: JAR and Integrated interpreter produce consistent output
	// =========================================================================

	@Test
	public void testScenario_jarAndIntegratedProduceSameReductionCount() throws Exception {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("PA", "City", true),
			createAmbient("Museum", "City", true)
		);
		List<Message> messages = Arrays.asList(
			new Message("PA", "Museum", "null", "null", "visit"),
			new Message("Museum", "PA", "null", "visit", "welcome")
		);

		String generated = generateFullCcaPL(ambients, messages);
		writeCcaFile(generated);

		CcaPlExecutor.ExecutionResult jarResult = CcaPlExecutor.execute(tempCcaFile.getAbsolutePath());
		String integratedOutput = CcaInterpreter.execute(tempCcaFile.getAbsolutePath());

		assertTrue(jarResult.isSuccess(), "JAR execution should succeed");

		int jarReductions = countOccurrences(jarResult.getOutput(), "--->");
		int intReductions = countOccurrences(integratedOutput, "--->");

		assertEquals(jarReductions, intReductions,
				"JAR and integrated interpreter should produce the same number of reduction steps");
		assertTrue(jarReductions > 0, "Should have at least one reduction step");
	}

	// =========================================================================
	// Scenario 7: IoT-inspired scenario (like school_scenario.cca)
	// Sensors (siblings) -> Gateway (parent) -> Child networks
	// =========================================================================

	@Test
	public void testScenario_iotSensorsWithParentGateway_generatesValidCcaPL() throws Exception {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("SmartHome", "", true),
			createAmbient("IoT1", "SmartHome", true),
			createAmbient("IoT2", "SmartHome", true),
			createAmbient("Gateway", "SmartHome", true),
			createAmbient("Processor", "Gateway", true)
		);
		List<Message> messages = Arrays.asList(
			new Message("IoT1", "Gateway", "null", "null", "temperature_high"),
			new Message("Gateway", "IoT1", "null", "temperature_high", "alert_sent"),
			new Message("IoT2", "Gateway", "null", "null", "humidity_low"),
			new Message("Gateway", "IoT2", "null", "humidity_low", "noted"),
			new Message("Gateway", "Processor", "null", "null", "analyze_data"),
			new Message("Processor", "Gateway", "null", "analyze_data", "analysis_done")
		);

		String generated = generateFullCcaPL(ambients, messages);

		// IoT sensors use :: to talk to sibling Gateway
		assertTrue(generated.contains("Gateway::send(temperature_high).0"),
				"IoT1 should use :: to send to sibling Gateway");
		assertTrue(generated.contains("Gateway::send(humidity_low).0"),
				"IoT2 should use :: to send to sibling Gateway");
		assertTrue(generated.contains("IoT1::recv(temperature_high)."),
				"Gateway should use :: to recv from sibling IoT1");
		assertTrue(generated.contains("IoT2::recv(humidity_low)."),
				"Gateway should use :: to recv from sibling IoT2");

		// Gateway uses # to talk to child Processor
		assertTrue(generated.contains("Processor#send(analyze_data).0"),
				"Gateway should use # to send to child Processor");
		assertTrue(generated.contains("Processor#recv(analyze_data,analysis_done).0"),
				"Gateway should use # to recv from child Processor");

		// Processor uses @ to talk to parent Gateway
		assertTrue(generated.contains("@recv(analyze_data)."),
				"Processor should use @ to recv from parent Gateway");
		assertTrue(generated.contains("@send(analyze_data,analysis_done).0"),
				"Processor should use @ to send to parent Gateway");

		// Processor is nested inside Gateway
		assertTrue(generated.contains("Processor["), "Processor should be nested inside Gateway");
	}

	@Test
	public void testScenario_iotSensorsWithParentGateway_executesViaInterpreter() throws Exception {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("SmartHome", "", true),
			createAmbient("IoT1", "SmartHome", true),
			createAmbient("IoT2", "SmartHome", true),
			createAmbient("Gateway", "SmartHome", true),
			createAmbient("Processor", "Gateway", true)
		);
		List<Message> messages = Arrays.asList(
			new Message("IoT1", "Gateway", "null", "null", "temperature_high"),
			new Message("Gateway", "IoT1", "null", "temperature_high", "alert_sent"),
			new Message("IoT2", "Gateway", "null", "null", "humidity_low"),
			new Message("Gateway", "IoT2", "null", "humidity_low", "noted"),
			new Message("Gateway", "Processor", "null", "null", "analyze_data"),
			new Message("Processor", "Gateway", "null", "analyze_data", "analysis_done")
		);

		String generated = generateFullCcaPL(ambients, messages);
		writeCcaFile(generated);

		String output = CcaInterpreter.execute(tempCcaFile.getAbsolutePath());

		assertTrue(output.contains("CCA program parsed successfully"));
		assertTrue(output.contains("Sibling to sibling:"),
				"IoT-to-Gateway should be sibling communication");
		assertTrue(output.contains("Parent to child:"),
				"Gateway-to-Processor should be parent-to-child");
		assertTrue(output.contains("Child to parent:"),
				"Processor-to-Gateway should be child-to-parent");
	}

	// =========================================================================
	// Scenario 8: Forwarding chain A -> B -> C -> B -> A (all siblings)
	// =========================================================================

	@Test
	public void testScenario_forwardingChainAllSiblings_generatesRelaySequence() throws Exception {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("Tourist", "City", true),
			createAmbient("InfoCenter", "City", true),
			createAmbient("Museum", "City", true)
		);
		List<Message> messages = Arrays.asList(
			new Message("Tourist", "InfoCenter", "null", "null", "visit_museum"),
			new Message("InfoCenter", "Museum", "Tourist", "null", "check_availability"),
			new Message("Museum", "InfoCenter", "null", "check_availability", "available"),
			new Message("InfoCenter", "Tourist", "null", "visit_museum", "available")
		);

		String generated = generateFullCcaPL(ambients, messages);

		// InfoCenter should have a 4-step relay
		assertTrue(generated.contains("Tourist::recv(visit_museum)."),
				"Relay step 1: recv from Tourist");
		assertTrue(generated.contains("Museum::send(check_availability)."),
				"Relay step 2: send to Museum");
		assertTrue(generated.contains("Museum::recv(check_availability,available)."),
				"Relay step 3: recv from Museum");
		assertTrue(generated.contains("Tourist::send(visit_museum,available).0"),
				"Relay step 4: send back to Tourist");

		// Tourist: normal initiator
		assertTrue(generated.contains("InfoCenter::send(visit_museum).0"),
				"Tourist should send to InfoCenter");
		assertTrue(generated.contains("InfoCenter::recv(visit_museum,available).0"),
				"Tourist should recv from InfoCenter");

		// Museum: normal responder
		assertTrue(generated.contains("InfoCenter::recv(check_availability)."),
				"Museum should recv from InfoCenter");
		assertTrue(generated.contains("InfoCenter::send(check_availability,available).0"),
				"Museum should respond to InfoCenter");
	}

	@Test
	public void testScenario_forwardingChainAllSiblings_executesViaInterpreter() throws Exception {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("Tourist", "City", true),
			createAmbient("InfoCenter", "City", true),
			createAmbient("Museum", "City", true)
		);
		List<Message> messages = Arrays.asList(
			new Message("Tourist", "InfoCenter", "null", "null", "visit_museum"),
			new Message("InfoCenter", "Museum", "Tourist", "null", "check_availability"),
			new Message("Museum", "InfoCenter", "null", "check_availability", "available"),
			new Message("InfoCenter", "Tourist", "null", "visit_museum", "available")
		);

		String generated = generateFullCcaPL(ambients, messages);
		writeCcaFile(generated);

		String output = CcaInterpreter.execute(tempCcaFile.getAbsolutePath());

		assertTrue(output.contains("CCA program parsed successfully"));
		assertTrue(output.contains("Sibling to sibling:"));

		int reductions = countOccurrences(output, "--->");
		assertTrue(reductions >= 4,
				"Forwarding chain should produce at least 4 reduction steps");
	}

	// =========================================================================
	// Scenario 9: Forwarding chain with parent-child nesting
	// STUDENT_PA (sibling) -> University (relay) -> FMI (child of University)
	// This is the exact scenario from cca_generator_version_2_parent_child_hierarchy.cca
	// =========================================================================

	@Test
	public void testScenario_forwardingWithParentChild_matchesReferenceFile() throws Exception {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("STUDENT_PA", "City", true),
			createAmbient("PLOVDIV_UNIVERSITY", "City", true),
			createAmbient("FMI", "PLOVDIV_UNIVERSITY", true)
		);
		List<Message> messages = Arrays.asList(
			new Message("STUDENT_PA", "PLOVDIV_UNIVERSITY", "null", "null", "FMI_rooms"),
			new Message("PLOVDIV_UNIVERSITY", "FMI", "STUDENT_PA", "null", "FMI_rooms_from_uni_to_fmi"),
			new Message("FMI", "PLOVDIV_UNIVERSITY", "null", "FMI_rooms_from_uni_to_fmi", "fmi_to_uni_543"),
			new Message("PLOVDIV_UNIVERSITY", "STUDENT_PA", "null", "FMI_rooms", "fmi_to_uni_543")
		);

		String generated = generateFullCcaPL(ambients, messages);
		writeCcaFile(generated);

		String output = CcaInterpreter.execute(tempCcaFile.getAbsolutePath());

		assertTrue(output.contains("CCA program parsed successfully"));

		// Verify exactly the same messages as the reference file produces
		assertTrue(output.contains("STUDENT_PA ===(FMI_rooms)===> PLOVDIV_UNIVERSITY"),
				"Should show STUDENT_PA sending FMI_rooms to PLOVDIV_UNIVERSITY");
		assertTrue(output.contains("PLOVDIV_UNIVERSITY ===(FMI_rooms_from_uni_to_fmi)===> FMI"),
				"Should show parent-to-child forwarding");
		assertTrue(output.contains("FMI ===(FMI_rooms_from_uni_to_fmi,fmi_to_uni_543)===> PLOVDIV_UNIVERSITY"),
				"Should show child-to-parent response");
		assertTrue(output.contains("PLOVDIV_UNIVERSITY ===(FMI_rooms,fmi_to_uni_543)===> STUDENT_PA"),
				"Should show University forwarding response back to STUDENT_PA");
	}

	@Test
	public void testScenario_forwardingWithParentChild_matchesReferenceViaJar() throws Exception {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("STUDENT_PA", "City", true),
			createAmbient("PLOVDIV_UNIVERSITY", "City", true),
			createAmbient("FMI", "PLOVDIV_UNIVERSITY", true)
		);
		List<Message> messages = Arrays.asList(
			new Message("STUDENT_PA", "PLOVDIV_UNIVERSITY", "null", "null", "FMI_rooms"),
			new Message("PLOVDIV_UNIVERSITY", "FMI", "STUDENT_PA", "null", "FMI_rooms_from_uni_to_fmi"),
			new Message("FMI", "PLOVDIV_UNIVERSITY", "null", "FMI_rooms_from_uni_to_fmi", "fmi_to_uni_543"),
			new Message("PLOVDIV_UNIVERSITY", "STUDENT_PA", "null", "FMI_rooms", "fmi_to_uni_543")
		);

		String generated = generateFullCcaPL(ambients, messages);
		writeCcaFile(generated);

		CcaPlExecutor.ExecutionResult result = CcaPlExecutor.execute(tempCcaFile.getAbsolutePath());

		assertTrue(result.isSuccess(), "JAR execution should succeed for forwarding scenario");
		assertTrue(result.getOutput().contains("Sibling to sibling:"));
		assertTrue(result.getOutput().contains("Parent to child:"));
		assertTrue(result.getOutput().contains("Child to parent:"));
	}

	// =========================================================================
	// Scenario 10: Communication type detection verification
	// Verify that detectCommunicationType works correctly for all cases
	// =========================================================================

	@Test
	public void testCommunicationTypeDetection_siblingPair() {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("Hospital", "City", true),
			createAmbient("Pharmacy", "City", true)
		);

		CommunicationType type = GeneratorUtil.detectCommunicationType("Hospital", "Pharmacy", ambients);
		assertEquals(CommunicationType.SIBLING, type, "Same-parent ambients should be siblings");
	}

	@Test
	public void testCommunicationTypeDetection_parentToChild() {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("University", "City", true),
			createAmbient("FMI", "University", true)
		);

		CommunicationType type = GeneratorUtil.detectCommunicationType("University", "FMI", ambients);
		assertEquals(CommunicationType.PARENT_TO_CHILD, type, "University -> FMI should be parent-to-child");
	}

	@Test
	public void testCommunicationTypeDetection_childToParent() {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("University", "City", true),
			createAmbient("FMI", "University", true)
		);

		CommunicationType type = GeneratorUtil.detectCommunicationType("FMI", "University", ambients);
		assertEquals(CommunicationType.CHILD_TO_PARENT, type, "FMI -> University should be child-to-parent");
	}

	@Test
	public void testCommunicationTypeDetection_unsupported() {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("Country", "", true),
			createAmbient("CityA", "Country", true),
			createAmbient("CityB", "Country", true),
			createAmbient("HospitalA", "CityA", true),
			createAmbient("PharmacyB", "CityB", true)
		);

		CommunicationType type = GeneratorUtil.detectCommunicationType("HospitalA", "PharmacyB", ambients);
		assertEquals(CommunicationType.UNSUPPORTED, type,
				"Ambients with different parents (not direct parent-child) should be unsupported");
	}

	// =========================================================================
	// Scenario 11: Verify generated output matches the existing ambients.cca
	// file structure that is already tested by CcaInterpreterTest
	// =========================================================================

	@Test
	public void testScenario_fullEditorScenario_producesExactlySixReductionSteps() throws Exception {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("BUS_STATION", "City", true),
			createAmbient("TAXI", "City", true),
			createAmbient("PLOVDIV_UNIVERSITY", "City", true),
			createAmbient("FMI", "PLOVDIV_UNIVERSITY", true),
			createAmbient("STUDENT_PA", "City", true)
		);

		List<Message> messages = Arrays.asList(
			new Message("BUS_STATION", "TAXI", "null", "null", "are_you_free"),
			new Message("TAXI", "BUS_STATION", "null", "are_you_free", "yes_I_am"),
			new Message("STUDENT_PA", "PLOVDIV_UNIVERSITY", "null", "null", "FMI_rooms"),
			new Message("PLOVDIV_UNIVERSITY", "FMI", "STUDENT_PA", "null", "FMI_rooms_from_uni_to_fmi"),
			new Message("FMI", "PLOVDIV_UNIVERSITY", "null", "FMI_rooms_from_uni_to_fmi", "fmi_to_uni_543"),
			new Message("PLOVDIV_UNIVERSITY", "STUDENT_PA", "null", "FMI_rooms", "fmi_to_uni_543")
		);

		String generated = generateFullCcaPL(ambients, messages);
		writeCcaFile(generated);

		String output = CcaInterpreter.execute(tempCcaFile.getAbsolutePath());

		int reductionCount = countOccurrences(output, "--->");
		assertEquals(6, reductionCount,
				"Full editor scenario should produce exactly 6 reduction steps " +
				"(matching the existing ambients.cca reference)");
	}

	// =========================================================================
	// Scenario 12: Nested children are correctly excluded from top-level
	// =========================================================================

	@Test
	public void testScenario_nestedChildrenExcludedFromTopLevel() throws Exception {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("Parent", "City", true),
			createAmbient("Child", "Parent", true),
			createAmbient("Sibling", "City", true)
		);
		List<Message> messages = Arrays.asList(
			new Message("Parent", "Child", "null", "null", "hello"),
			new Message("Child", "Parent", "null", "hello", "world"),
			new Message("Parent", "Sibling", "null", "null", "greeting"),
			new Message("Sibling", "Parent", "null", "greeting", "hi")
		);

		Set<String> nestedChildren = CCAGeneratorV3.findNestedChildren(messages, ambients);
		assertTrue(nestedChildren.contains("Child"), "Child should be detected as nested");
		assertFalse(nestedChildren.contains("Sibling"), "Sibling should not be nested");
		assertFalse(nestedChildren.contains("Parent"), "Parent should not be nested");

		List<String> topLevel = CCAGeneratorV3.findTopLevelAmbients(messages, nestedChildren);
		assertTrue(topLevel.contains("Parent"), "Parent should be top-level");
		assertTrue(topLevel.contains("Sibling"), "Sibling should be top-level");
		assertFalse(topLevel.contains("Child"), "Child should NOT be top-level");
	}

	// =========================================================================
	// Scenario 13: Multiple children nested under same parent
	// =========================================================================

	@Test
	public void testScenario_multipleChildrenUnderSameParent() throws Exception {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("University", "City", true),
			createAmbient("FMI", "University", true),
			createAmbient("FII", "University", true)
		);
		List<Message> messages = Arrays.asList(
			new Message("University", "FMI", "null", "null", "schedule"),
			new Message("FMI", "University", "null", "schedule", "confirmed"),
			new Message("University", "FII", "null", "null", "grades"),
			new Message("FII", "University", "null", "grades", "uploaded")
		);

		String generated = generateFullCcaPL(ambients, messages);

		assertTrue(generated.contains("University["), "Should have University block");
		assertTrue(generated.contains("FMI["), "FMI should be nested inside University");
		assertTrue(generated.contains("FII["), "FII should be nested inside University");
		assertTrue(generated.contains("FMI#send(schedule).0"), "University sends to FMI via #");
		assertTrue(generated.contains("FII#send(grades).0"), "University sends to FII via #");
	}

	@Test
	public void testScenario_multipleChildrenUnderSameParent_executesViaInterpreter() throws Exception {
		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("University", "City", true),
			createAmbient("FMI", "University", true),
			createAmbient("FII", "University", true)
		);
		List<Message> messages = Arrays.asList(
			new Message("University", "FMI", "null", "null", "schedule"),
			new Message("FMI", "University", "null", "schedule", "confirmed"),
			new Message("University", "FII", "null", "null", "grades"),
			new Message("FII", "University", "null", "grades", "uploaded")
		);

		String generated = generateFullCcaPL(ambients, messages);
		writeCcaFile(generated);

		String output = CcaInterpreter.execute(tempCcaFile.getAbsolutePath());

		assertTrue(output.contains("CCA program parsed successfully"));
		assertTrue(output.contains("Parent to child:"));
		assertTrue(output.contains("Child to parent:"));

		int parentToChildCount = countOccurrences(output, "Parent to child:");
		int childToParentCount = countOccurrences(output, "Child to parent:");
		assertEquals(2, parentToChildCount,
				"Should have 2 parent-to-child communications (FMI + FII)");
		assertEquals(2, childToParentCount,
				"Should have 2 child-to-parent communications (FMI + FII responses)");
	}

	// =========================================================================
	// Scenario 14: Verify prior-work reference file executes identically
	// Runs the cca_generator_version_2_parent_child_hierarchy.cca from prior work
	// and compares output patterns with our generated version
	// =========================================================================

	@Test
	public void testScenario_generatedMatchesPriorWorkReferenceFile() throws Exception {
		String priorWorkFile = "cca-prior-work/ССА модели Глушкова/CCAPL/Sevilia/CCA/"
				+ "cca_generator_version_2_parent_child_hierarchy.cca";

		File refFile = new File(priorWorkFile);
		if (!refFile.exists()) {
			return;
		}

		String priorOutput = CcaInterpreter.execute(priorWorkFile);

		List<Ambient> ambients = Arrays.asList(
			createAmbient("City", "", true),
			createAmbient("STUDENT_PA", "City", true),
			createAmbient("PLOVDIV_UNIVERSITY", "City", true),
			createAmbient("FMI", "PLOVDIV_UNIVERSITY", true)
		);
		List<Message> messages = Arrays.asList(
			new Message("STUDENT_PA", "PLOVDIV_UNIVERSITY", "null", "null",
					"Where_is_my_lecture"),
			new Message("PLOVDIV_UNIVERSITY", "FMI", "STUDENT_PA", "null",
					"Where_is_the_Artificial_Intelligence_lecture"),
			new Message("FMI", "PLOVDIV_UNIVERSITY", "null",
					"Where_is_the_Artificial_Intelligence_lecture",
					"The_AI_lecture_is_in_hall_422"),
			new Message("PLOVDIV_UNIVERSITY", "STUDENT_PA", "null",
					"Where_is_my_lecture",
					"Your_lecture_is_in_hall_422")
		);

		String generated = generateFullCcaPL(ambients, messages);
		writeCcaFile(generated);

		String generatedOutput = CcaInterpreter.execute(tempCcaFile.getAbsolutePath());

		// Both should parse and execute successfully
		assertTrue(priorOutput.contains("CCA program parsed successfully"),
				"Prior work file should parse successfully");
		assertTrue(generatedOutput.contains("CCA program parsed successfully"),
				"Generated file should parse successfully");

		// Both should have the same number of reduction steps
		int priorReductions = countOccurrences(priorOutput, "--->");
		int generatedReductions = countOccurrences(generatedOutput, "--->");
		assertEquals(priorReductions, generatedReductions,
				"Generated and prior-work reference should have the same reduction count");

		// Both should have all three communication types
		assertTrue(generatedOutput.contains("Sibling to sibling:"),
				"Generated output should have sibling communication like reference");
		assertTrue(generatedOutput.contains("Parent to child:"),
				"Generated output should have parent-to-child like reference");
		assertTrue(generatedOutput.contains("Child to parent:"),
				"Generated output should have child-to-parent like reference");
	}

	// =========================================================================
	// Scenario 15: passMessageTo detection across all scenarios
	// =========================================================================

	@Test
	public void testPassMessageTo_isCorrectlyIdentifiedAsForwarder() {
		Message normalInit = new Message("A", "B", "null", "null", "hello");
		Message normalResp = new Message("B", "A", "null", "hello", "world");
		Message forwarding = new Message("B", "C", "A", "null", "forwarded");

		assertTrue(GeneratorUtil.isInitiator("A", normalInit),
				"A sending with no respondTo should be initiator");
		assertTrue(GeneratorUtil.isResponder("B", normalResp),
				"B responding with respondTo should be responder");
		assertTrue(GeneratorUtil.isForwarder("B", forwarding),
				"B with passMsgTo=A and no respondTo should be forwarder");

		assertFalse(GeneratorUtil.isForwarder("A", normalInit),
				"Normal initiator should not be forwarder");
		assertFalse(GeneratorUtil.isForwarder("B", normalResp),
				"Normal responder should not be forwarder");
	}

	// =========================================================================
	// Helper methods
	// =========================================================================

	/**
	 * Generates full ccaPL code from ambients and messages by invoking
	 * the same logic as CCAGeneratorV3.generate() but without CSV file I/O.
	 */
	private String generateFullCcaPL(List<Ambient> ambients, List<Message> messages)
			throws Exception {

		List<Message> activeMessages = GeneratorUtil.getMessagesActiveAmbients(messages, ambients);

		Set<String> nestedChildren = CCAGeneratorV3.findNestedChildren(activeMessages, ambients);
		Map<String, List<String>> parentToChildren = CCAGeneratorV3.buildParentToChildrenMap(nestedChildren, ambients);
		List<String> topLevel = CCAGeneratorV3.findTopLevelAmbients(activeMessages, nestedChildren);

		List<String> blocks = new java.util.ArrayList<>();
		for (String ambientName : topLevel) {
			String block = CCAGeneratorV3.generateAmbientBlock(
				ambientName, activeMessages, parentToChildren, ambients, 0
			);
			if (block != null && !block.trim().isEmpty()) {
				blocks.add(block);
			}
		}

		return String.join("\n|\n", blocks);
	}

	private void writeCcaFile(String content) throws IOException {
		Files.write(tempCcaFile.toPath(), content.getBytes(java.nio.charset.StandardCharsets.UTF_8));
	}

	private Ambient createAmbient(String name, String parent, boolean isActive) {
		Ambient ambient = new Ambient();
		ambient.setName(name);
		ambient.setLocation("Loc");
		ambient.setLatitude("42.0");
		ambient.setLongitude("23.0");
		ambient.setStaticAmbient(true);
		ambient.setActiveAmbient(isActive);
		ambient.setParentAmbient(parent);
		ambient.setAmbientType(AmbientType.CITY);
		return ambient;
	}

	private int countOccurrences(String text, String pattern) {
		int count = 0;
		int index = 0;
		while ((index = text.indexOf(pattern, index)) != -1) {
			count++;
			index += pattern.length();
		}
		return count;
	}
}
