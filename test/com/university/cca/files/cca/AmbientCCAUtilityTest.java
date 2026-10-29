package com.university.cca.files.cca;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Files;
import java.nio.file.Path;

public class AmbientCCAUtilityTest {

	@TempDir
	Path tempFolder;

	// --- createFileIfDoesNotExist ---

	@Test
	public void testCreateFileIfDoesNotExist_createsNewFile() {
		String filePath = new File(tempFolder.toFile(), "new_file.cca").getAbsolutePath();
		assertFalse(new File(filePath).exists());

		AmbientCCAUtility.createFileIfDoesNotExist(filePath);

		assertTrue(new File(filePath).exists());
	}

	@Test
	public void testCreateFileIfDoesNotExist_existingFileNotOverwritten() throws IOException {
		File existing = Files.createFile(tempFolder.resolve("existing.cca")).toFile();
		assertTrue(existing.exists());

		AmbientCCAUtility.createFileIfDoesNotExist(existing.getAbsolutePath());

		assertTrue(existing.exists());
	}

	// --- isFileCreated(filePath) ---

	@Test
	public void testIsFileCreated_existingFile() throws IOException {
		File file = Files.createFile(tempFolder.resolve("test.cca")).toFile();

		assertTrue(AmbientCCAUtility.isFileCreated(file.getAbsolutePath()));
	}

	@Test
	public void testIsFileCreated_nonExistingFile() {
		String fakePath = new File(tempFolder.toFile(), "nonexistent.cca").getAbsolutePath();

		assertFalse(AmbientCCAUtility.isFileCreated(fakePath));
	}

	// --- createFileIfDoesNotExist error path ---

	@Test
	public void testCreateFileIfDoesNotExist_throwsOnInvalidPath() {
		String invalidPath = new File(tempFolder.toFile(),
				"nonexistent_dir/nested/deep/file.cca").getAbsolutePath();

		assertThrows(RuntimeException.class, () ->
			AmbientCCAUtility.createFileIfDoesNotExist(invalidPath)
		);
	}

	// --- private constructor (coverage) ---

	@Test
	public void testPrivateConstructor() throws Exception {
		Constructor<AmbientCCAUtility> constructor =
				AmbientCCAUtility.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		assertNotNull(constructor.newInstance());
	}
}
