package com.university.cca.files.cca;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class AmbientCCAWriterTest {

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	// --- write(filePath, data) ---

	@Test
	public void testWrite_createsFileAndWritesData() {
		File ccaFile = new File(tempFolder.getRoot(), "output.cca");

		AmbientCCAWriter.write(ccaFile.getAbsolutePath(), "n[m::send(hello).0]");

		assertTrue(ccaFile.exists());
		assertTrue(ccaFile.length() > 0);
	}

	@Test
	public void testWrite_contentMatchesInput() throws IOException {
		File ccaFile = new File(tempFolder.getRoot(), "scenario.cca");
		String ccaCode = "n[m::send(hello).0] | m[n::recv(x).0]";

		AmbientCCAWriter.write(ccaFile.getAbsolutePath(), ccaCode);

		String content = new String(Files.readAllBytes(Paths.get(ccaFile.getAbsolutePath())));
		assertTrue(content.contains(ccaCode));
	}

	@Test
	public void testWrite_overwritesPreviousContent() throws IOException {
		File ccaFile = new File(tempFolder.getRoot(), "overwrite.cca");

		AmbientCCAWriter.write(ccaFile.getAbsolutePath(), "first_content");
		AmbientCCAWriter.write(ccaFile.getAbsolutePath(), "second_content");

		String content = new String(Files.readAllBytes(Paths.get(ccaFile.getAbsolutePath())));
		assertTrue(content.contains("second_content"));
	}

	@Test
	public void testWrite_emptyData() {
		File ccaFile = new File(tempFolder.getRoot(), "empty.cca");

		AmbientCCAWriter.write(ccaFile.getAbsolutePath(), "");

		assertTrue(ccaFile.exists());
	}

	@Test
	public void testWrite_multilineData() throws IOException {
		File ccaFile = new File(tempFolder.getRoot(), "multiline.cca");
		String ccaCode = "BEGIN_DECLS\n  mode random\nEND_DECLS\nn[0]";

		AmbientCCAWriter.write(ccaFile.getAbsolutePath(), ccaCode);

		String content = new String(Files.readAllBytes(Paths.get(ccaFile.getAbsolutePath())));
		assertTrue(content.contains("BEGIN_DECLS"));
		assertTrue(content.contains("mode random"));
	}

	// --- write + read round-trip ---

	@Test
	public void testWriteAndRead_roundTrip() throws Exception {
		File ccaFile = new File(tempFolder.getRoot(), "roundtrip.cca");
		String ccaCode = "n[m::send(hello).0] | m[n::recv(x).0]";

		AmbientCCAWriter.write(ccaFile.getAbsolutePath(), ccaCode);
		String readBack = AmbientCCAReader.read(ccaFile.getAbsolutePath());

		assertEquals(ccaCode, readBack);
	}
}
