package com.university.cca.files.cca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Files;
import java.nio.file.Path;

import com.university.cca.exceptions.FileReadFailedException;

public class AmbientCCAReaderTest {

	@TempDir
	Path tempFolder;

	// --- read(filePath) ---

	@Test
	public void testRead_singleLine() throws Exception {
		File ccaFile = createCcaFile("n[m::send(hello).0]");

		String content = AmbientCCAReader.read(ccaFile.getAbsolutePath());

		assertEquals("n[m::send(hello).0]", content);
	}

	@Test
	public void testRead_multipleLines() throws Exception {
		File ccaFile = createCcaFile("n[m::send(hello).0]\n| m[n::recv(x).0]");

		String content = AmbientCCAReader.read(ccaFile.getAbsolutePath());

		assertNotNull(content);
		assertTrue(content.contains("n[m::send(hello).0]"));
		assertTrue(content.contains("m[n::recv(x).0]"));
	}

	@Test
	public void testRead_emptyFile() throws Exception {
		File ccaFile = Files.createFile(tempFolder.resolve("empty.cca")).toFile();

		String content = AmbientCCAReader.read(ccaFile.getAbsolutePath());

		assertNotNull(content);
		assertEquals("", content);
	}

	@Test
	public void testRead_ccaWithDeclarations() throws Exception {
		String cca = "BEGIN_DECLS\n  def has(n) = { somewhere (this | n[true] | true) }\n  mode random\nEND_DECLS\nn[0]";
		File ccaFile = createCcaFile(cca);

		String content = AmbientCCAReader.read(ccaFile.getAbsolutePath());

		assertTrue(content.contains("BEGIN_DECLS"));
		assertTrue(content.contains("has(n)"));
		assertTrue(content.contains("END_DECLS"));
	}

	@Test
	public void testRead_nonExistentFile() {
		String fakePath = new File(tempFolder.toFile(), "nonexistent.cca").getAbsolutePath();

		assertThrows(FileNotFoundException.class, () ->
			AmbientCCAReader.read(fakePath)
		);
	}

	// --- error path ---

	@Test
	public void testRead_throwsOnDirectoryPath() throws Exception {
		File dir = Files.createDirectories(tempFolder.resolve("dir_not_file")).toFile();
		try {
			AmbientCCAReader.read(dir.getAbsolutePath());
			fail("Expected an exception when reading a directory");
		} catch (FileReadFailedException e) {
			// Windows: Files.lines() throws IOException, caught and wrapped
		} catch (RuntimeException e) {
			// Linux: Files.lines() throws UncheckedIOException (extends RuntimeException)
		}
	}

	// --- private constructor (coverage) ---

	@Test
	public void testPrivateConstructor() throws Exception {
		Constructor<AmbientCCAReader> constructor =
				AmbientCCAReader.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		assertNotNull(constructor.newInstance());
	}

	// --- helpers ---

	private File createCcaFile(String content) throws IOException {
		File file = Files.createFile(tempFolder.resolve("test.cca")).toFile();
		try (FileWriter writer = new FileWriter(file)) {
			writer.write(content);
		}
		return file;
	}
}
