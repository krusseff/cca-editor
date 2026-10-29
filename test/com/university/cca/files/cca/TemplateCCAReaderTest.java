package com.university.cca.files.cca;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class TemplateCCAReaderTest {

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	// --- readTemplate(filePath) ---

	@Test
	public void testReadTemplate_singleLineTemplate() throws IOException {
		File templateFile = createTemplateFile("AMBIENT_SENDER[AMBIENT_RECEIPIENT::send(SEND_PARAMETERS).0]");

		String content = TemplateCCAReader.readTemplate(templateFile.getAbsolutePath());

		assertNotNull(content);
		assertTrue(content.contains("AMBIENT_SENDER"));
		assertTrue(content.contains("AMBIENT_RECEIPIENT"));
		assertTrue(content.contains("SEND_PARAMETERS"));
	}

	@Test
	public void testReadTemplate_multiLineTemplate() throws IOException {
		String template = "AMBIENT_SENDER[\n  AMBIENT_RECEIPIENT::send(SEND_PARAMETERS).0\n  | AMBIENT_RECEIPIENT::recv(RECEIVE_PARAMETERS).0\n]";
		File templateFile = createTemplateFile(template);

		String content = TemplateCCAReader.readTemplate(templateFile.getAbsolutePath());

		assertTrue(content.contains("AMBIENT_SENDER"));
		assertTrue(content.contains("send(SEND_PARAMETERS)"));
		assertTrue(content.contains("recv(RECEIVE_PARAMETERS)"));
	}

	@Test
	public void testReadTemplate_delimiterTemplate() throws IOException {
		File templateFile = createTemplateFile("|");

		String content = TemplateCCAReader.readTemplate(templateFile.getAbsolutePath());

		assertTrue(content.contains("|"));
	}

	@Test
	public void testReadTemplate_emptyFile() throws IOException {
		File templateFile = tempFolder.newFile("empty_template.cca");

		String content = TemplateCCAReader.readTemplate(templateFile.getAbsolutePath());

		assertNotNull(content);
		assertEquals("", content);
	}

	@Test
	public void testReadTemplate_createsFileIfNotExists() {
		String filePath = new File(tempFolder.getRoot(), "auto_created.cca").getAbsolutePath();

		String content = TemplateCCAReader.readTemplate(filePath);

		assertNotNull(content);
		assertTrue(new File(filePath).exists());
	}

	@Test
	public void testReadTemplate_exchangeMessagesTemplate() throws IOException {
		String template = "AMBIENT_SENDER[\n  MESSAGES_PLACEHOLDER\n]";
		File templateFile = createTemplateFile(template);

		String content = TemplateCCAReader.readTemplate(templateFile.getAbsolutePath());

		assertTrue(content.contains("MESSAGES_PLACEHOLDER"));
	}

	@Test
	public void testReadTemplate_preservesNewlines() throws IOException {
		String template = "line1\nline2\nline3";
		File templateFile = createTemplateFile(template);

		String content = TemplateCCAReader.readTemplate(templateFile.getAbsolutePath());

		assertTrue(content.contains("line1"));
		assertTrue(content.contains("line2"));
		assertTrue(content.contains("line3"));
	}

	// --- private constructor (coverage) ---

	@Test
	public void testPrivateConstructor() throws Exception {
		Constructor<TemplateCCAReader> constructor =
				TemplateCCAReader.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		assertNotNull(constructor.newInstance());
	}

	// --- helpers ---

	private File createTemplateFile(String content) throws IOException {
		File file = tempFolder.newFile("template.cca");
		try (FileWriter writer = new FileWriter(file)) {
			writer.write(content);
		}
		return file;
	}
}
