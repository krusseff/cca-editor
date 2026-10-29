package com.university.cca.files.cca;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.university.cca.exceptions.FileReadFailedException;

public class AmbientCCAReaderTest {

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

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
		File ccaFile = tempFolder.newFile("empty.cca");

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

	@Test(expected = FileNotFoundException.class)
	public void testRead_nonExistentFile() throws Exception {
		String fakePath = new File(tempFolder.getRoot(), "nonexistent.cca").getAbsolutePath();

		AmbientCCAReader.read(fakePath);
	}

	// --- error path ---

	@Test(expected = FileReadFailedException.class)
	public void testRead_throwsFileReadFailedOnDirectory() throws Exception {
		File dir = tempFolder.newFolder("dir_not_file");

		AmbientCCAReader.read(dir.getAbsolutePath());
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
		File file = tempFolder.newFile("test.cca");
		try (FileWriter writer = new FileWriter(file)) {
			writer.write(content);
		}
		return file;
	}
}
