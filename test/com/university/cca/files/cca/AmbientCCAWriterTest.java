package com.university.cca.files.cca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Files;
import java.nio.file.Path;

public class AmbientCCAWriterTest {

	@TempDir
	Path tempFolder;

	// --- write(filePath, data) ---

	@Test
	public void testWrite_createsFileAndWritesData() {
		File ccaFile = new File(tempFolder.toFile(), "output.cca");

		AmbientCCAWriter.write(ccaFile.getAbsolutePath(), "n[m::send(hello).0]");

		assertTrue(ccaFile.exists());
		assertTrue(ccaFile.length() > 0);
	}

	@Test
	public void testWrite_contentMatchesInput() throws IOException {
		File ccaFile = new File(tempFolder.toFile(), "scenario.cca");
		String ccaCode = "n[m::send(hello).0] | m[n::recv(x).0]";

		AmbientCCAWriter.write(ccaFile.getAbsolutePath(), ccaCode);

		String content = new String(Files.readAllBytes(Paths.get(ccaFile.getAbsolutePath())));
		assertTrue(content.contains(ccaCode));
	}

	@Test
	public void testWrite_overwritesPreviousContent() throws IOException {
		File ccaFile = new File(tempFolder.toFile(), "overwrite.cca");

		AmbientCCAWriter.write(ccaFile.getAbsolutePath(), "first_content");
		AmbientCCAWriter.write(ccaFile.getAbsolutePath(), "second_content");

		String content = new String(Files.readAllBytes(Paths.get(ccaFile.getAbsolutePath())));
		assertTrue(content.contains("second_content"));
	}

	@Test
	public void testWrite_emptyData() {
		File ccaFile = new File(tempFolder.toFile(), "empty.cca");

		AmbientCCAWriter.write(ccaFile.getAbsolutePath(), "");

		assertTrue(ccaFile.exists());
	}

	@Test
	public void testWrite_multilineData() throws IOException {
		File ccaFile = new File(tempFolder.toFile(), "multiline.cca");
		String ccaCode = "BEGIN_DECLS\n  mode random\nEND_DECLS\nn[0]";

		AmbientCCAWriter.write(ccaFile.getAbsolutePath(), ccaCode);

		String content = new String(Files.readAllBytes(Paths.get(ccaFile.getAbsolutePath())));
		assertTrue(content.contains("BEGIN_DECLS"));
		assertTrue(content.contains("mode random"));
	}

	// --- write + read round-trip ---

	@Test
	public void testWriteAndRead_roundTrip() throws Exception {
		File ccaFile = new File(tempFolder.toFile(), "roundtrip.cca");
		String ccaCode = "n[m::send(hello).0] | m[n::recv(x).0]";

		AmbientCCAWriter.write(ccaFile.getAbsolutePath(), ccaCode);
		String readBack = AmbientCCAReader.read(ccaFile.getAbsolutePath());

		assertEquals(ccaCode, readBack);
	}

	// --- error path ---

	@Test
	public void testWrite_throwsOnDirectoryPath() throws IOException {
		File dir = Files.createDirectories(tempFolder.resolve("dir_as_file.cca")).toFile();

		assertThrows(RuntimeException.class, () ->
			AmbientCCAWriter.write(dir.getAbsolutePath(), "data")
		);
	}

	// --- private constructor (coverage) ---

	@Test
	public void testPrivateConstructor() throws Exception {
		Constructor<AmbientCCAWriter> constructor =
				AmbientCCAWriter.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		assertNotNull(constructor.newInstance());
	}
}
