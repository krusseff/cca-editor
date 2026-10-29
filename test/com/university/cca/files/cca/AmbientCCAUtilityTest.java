package com.university.cca.files.cca;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class AmbientCCAUtilityTest {

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	// --- createFileIfDoesNotExist ---

	@Test
	public void testCreateFileIfDoesNotExist_createsNewFile() {
		String filePath = new File(tempFolder.getRoot(), "new_file.cca").getAbsolutePath();
		assertFalse(new File(filePath).exists());

		AmbientCCAUtility.createFileIfDoesNotExist(filePath);

		assertTrue(new File(filePath).exists());
	}

	@Test
	public void testCreateFileIfDoesNotExist_existingFileNotOverwritten() throws IOException {
		File existing = tempFolder.newFile("existing.cca");
		assertTrue(existing.exists());

		AmbientCCAUtility.createFileIfDoesNotExist(existing.getAbsolutePath());

		assertTrue(existing.exists());
	}

	// --- isFileCreated(filePath) ---

	@Test
	public void testIsFileCreated_existingFile() throws IOException {
		File file = tempFolder.newFile("test.cca");

		assertTrue(AmbientCCAUtility.isFileCreated(file.getAbsolutePath()));
	}

	@Test
	public void testIsFileCreated_nonExistingFile() {
		String fakePath = new File(tempFolder.getRoot(), "nonexistent.cca").getAbsolutePath();

		assertFalse(AmbientCCAUtility.isFileCreated(fakePath));
	}
}
