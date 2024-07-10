package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import analyser.DictionaryAnalyser;

/**
 * Tests the {@link DictionaryAnalyser} class.
 * 
 * 
 * NOTE: DO NOT CHANGE THE CONTENTS OF THIS FILE.
 * 
 * @author mdixon
 */
class PART2_DictionaryAnalyserTest {

	/**
	 * The {@link DictionaryAnalyser} instance created prior to each test.
	 */
	private DictionaryAnalyser an;

	@BeforeEach
	void setUpBeforeEach() throws Exception {

		an = new DictionaryAnalyser();
	}

	@Test
	void testAddToDictionary() {

		assertEquals(0, an.getDictionary().size(), "Dictionary should be empty before words are added");

		assertThrows(IOException.class, () -> { an.addToDictionary("non-existent-dixt.txt"); }, "Exception reporting IO error should be thrown");
		
		try {
			// add file contents to the dictionary
			an.addToDictionary("testFiles/dict1.txt");

		} catch (IOException e) {

			fail("Unexpected IO exception : " + e.getMessage());
		}

		assertEquals(7, an.getDictionary().size(), "Dictionary does not contain correct number of added words");
	}

	@Test
	void testAddToDictionaryBlankLines() {

		assertEquals(0, an.getDictionary().size(), "Dictionary should be empty before words are added");

		try {
			// add a file with various blank lines and white space
			an.addToDictionary("testFiles/dict2.txt");

		} catch (IOException e) {

			fail("Unexpected IO exception : " + e.getMessage());
		}

		assertFalse(an.getDictionary().contains(""), "Blank lines should not be stored in the dictionary");
		assertEquals(8, an.getDictionary().size(),
				"Dictionary does not contain correct word count, empty lines not stripped?");
	}

	@Test
	void testAddToDictionaryWhitespace() {

		assertEquals(0, an.getDictionary().size(), "Dictionary should be empty before words are added");

		try {
			// add a file with various blank lines and white space
			an.addToDictionary("testFiles/dict2.txt");

		} catch (IOException e) {

			fail("Unexpected IO exception : " + e.getMessage());
		}

		assertTrue(an.getDictionary().contains("whitespace"),
				"Dictionary does not contain correct word, whitespace not trimmed?");
		assertTrue(an.getDictionary().contains("a"),
				"Dictionary does not contain correct word, whitespace not trimmed?");
	}

	@Test
	void testAddToDictionaryMixedCase() {

		assertEquals(0, an.getDictionary().size(), "Dictionary should be empty before words are added");

		try {
			// add a file with upper and lower case words
			an.addToDictionary("testFiles/dict3.txt");

		} catch (IOException e) {

			fail("Unexpected IO exception : " + e.getMessage());
		}

		assertTrue(an.getDictionary().contains("police"),
				"Dictionary does not contained expected word, not stored as lower-case?");
		assertFalse(an.getDictionary().contains("POLICE"),
				"Dictionary contains unexpected word, not stored as lower-case?");
		assertTrue(an.getDictionary().contains("word"),
				"Dictionary does not contained expected word, not stored as lower-case?");
		assertFalse(an.getDictionary().contains("Word"),
				"Dictionary contains unexpected word, not stored as lower-case?");
	}

	@Test
	void testAddToDictionaryMulti() {

		assertEquals(0, an.getDictionary().size(), "Dictionary should be empty before words are added");

		try {
			// add a file with upper and lower case words
			an.addToDictionary("testFiles/dict1.txt");
			assertEquals(7, an.getDictionary().size(), "Dictionary should contain correct word count");

			an.addToDictionary("testFiles/dict1.txt");
			assertEquals(7, an.getDictionary().size(), "Dictionary word count should not change when same file added");

			an.addToDictionary("testFiles/dict2.txt");
			assertEquals(11, an.getDictionary().size(),
					"Dictionary word count should increase when more than one file added");

			an.addToDictionary("testFiles/dict3.txt");
			assertEquals(21, an.getDictionary().size(),
					"Dictionary word count should increase when more than one file added");

		} catch (IOException e) {

			fail("Unexpected IO exception : " + e.getMessage());
		}

	}

	@Test
	void testClearDictionary() {

		assertEquals(0, an.getDictionary().size(), "Dictionary should be empty before words are added");

		an.clearDictionary();

		assertEquals(0, an.getDictionary().size(), "Dictionary should be empty once cleared");

		try {
			// add file contents to the dictionary
			an.addToDictionary("testFiles/dict1.txt");

			assertFalse(an.getDictionary().isEmpty(), "Dictionary should contain some words prior to being cleared");

		} catch (IOException e) {

			fail("Unexpected IO exception : " + e.getMessage());
		}

		an.clearDictionary();

		assertEquals(0, an.getDictionary().size(), "Dictionary should be empty once cleared");

		an.clearDictionary();
		an.clearDictionary();
		an.clearDictionary();
		assertEquals(0, an.getDictionary().size(), "Dictionary should be empty once cleared");
	}

	@Test
	void testKnownWords() {

		assertEquals(0, an.getKnownWords().size(), "Some known words found before use");

		try {
			// try analysis when dictionary is empty, should be no know words.
			an.performAnalysis("testFiles/text1.txt");
			assertEquals(0, an.getKnownWords().size(), "Some known words found before dictionary added?");

			// add file contents to the dictionary
			an.addToDictionary("testFiles/dict1.txt");
			assertEquals(0, an.getKnownWords().size(), "Some known words found before analysis performed?");

			an.performAnalysis("testFiles/text1.txt");

		} catch (IOException e) {

			fail("Unexpected IO exception : " + e.getMessage());
		}

		assertEquals(4, an.getKnownWords().size(), "Incorrect number of known words found");

		String msg = "Dictionary does not contain expected word, not added to known set?";

		assertTrue(an.getKnownWords().contains("some"), msg);
		assertTrue(an.getKnownWords().contains("dictionary"), msg);
		assertTrue(an.getKnownWords().contains("words"), msg);
		assertTrue(an.getKnownWords().contains("to"), msg);
	}	
	
	@Test
	void testUnknownWords() {

		assertEquals(0, an.getUnknownWords().size(), "Some unknown words found before use");

		try {
			// try analysis when dictionary is empty, should be all unknown words.
			an.performAnalysis("testFiles/text1.txt");
			assertEquals(16, an.getUnknownWords().size(), "All words should be unknown before dictionary added?");

			// add file contents to the dictionary
			an.addToDictionary("testFiles/dict1.txt");

			an.performAnalysis("testFiles/text1.txt");

		} catch (IOException e) {

			fail("Unexpected IO exception : " + e.getMessage());
		}

		assertEquals(12, an.getUnknownWords().size(), "Incorrect number of unknown words found");

		String msg = "Found word that should be unknown, not added to unknown set?";

		assertTrue(an.getUnknownWords().contains("analysed"), msg);
		assertTrue(an.getUnknownWords().contains("a"), msg);
		assertTrue(an.getUnknownWords().contains("be"), msg);
		assertTrue(an.getUnknownWords().contains("will"), msg);
		assertTrue(an.getUnknownWords().contains("in"), msg);
		assertTrue(an.getUnknownWords().contains("occur"), msg);
		assertTrue(an.getUnknownWords().contains("multiple"), msg);
		assertTrue(an.getUnknownWords().contains("these"), msg);
		assertTrue(an.getUnknownWords().contains("times"), msg);
		assertTrue(an.getUnknownWords().contains("are"), msg);
		assertTrue(an.getUnknownWords().contains("and"), msg);
		assertTrue(an.getUnknownWords().contains("of"), msg);

	}
	
	@Test
	void testKnownWordsReset() {

		assertEquals(0, an.getKnownWords().size(), "Some known words found before use");

		try {
			// add file contents to the dictionary
			an.addToDictionary("testFiles/dict1.txt");
			an.performAnalysis("testFiles/text1.txt");
			
			assertEquals(4, an.getKnownWords().size(), "Incorrect number of known words found");
			
			an.performAnalysis("testFiles/text2.txt");
			assertEquals(5, an.getKnownWords().size(), "Incorrect number of known words found, known words not reset before each analysis?");

		} catch (IOException e) {

			fail("Unexpected IO exception : " + e.getMessage());
		}
	}
	
	@Test
	void testUnKnownWordsReset() {

		assertEquals(0, an.getUnknownWords().size(), "Some unknown words found before use");

		try {
			// add file contents to the dictionary
			an.addToDictionary("testFiles/dict1.txt");
			an.performAnalysis("testFiles/text1.txt");
			
			assertEquals(12, an.getUnknownWords().size(), "Incorrect number of unknown words found");
			
			an.performAnalysis("testFiles/text2.txt");
			assertEquals(15, an.getUnknownWords().size(), "Incorrect number of unknown words found, unknown words not reset before each analysis?");

		} catch (IOException e) {

			fail("Unexpected IO exception : " + e.getMessage());
		}
	}
}
