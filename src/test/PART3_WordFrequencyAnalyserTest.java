package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import analyser.WordFrequencyAnalyser;

/**
 * Tests the {@link WordFrequencyAnalyser} class.
 * 
 * 
 * NOTE: DO NOT CHANGE THE CONTENTS OF THIS FILE.
 * 
 * @author mdixon
 */
class PART3_WordFrequencyAnalyserTest {

	/**
	 * The {@link WordFrequencyAnalyser} instance created prior to each test.
	 */
	private WordFrequencyAnalyser an;
	
	@BeforeEach
	void setUpBeforeEach() throws Exception {
		
		an = new WordFrequencyAnalyser();
	}
	
	@Test
	void testGetCountOf() {
		
		try {
			an.performAnalysis("testFiles/text1.txt");
			
		} catch (IOException e) {

			fail("Unexpected IO exception : " + e.getMessage());
		}
		
		assertEquals(3, an.getCountOf("words"), "Incorrect count of present word");
		assertEquals(3, an.getCountOf("some"), "Incorrect count of present word");
		assertEquals(2, an.getCountOf("will"), "Incorrect count of present word");
	}
	
	@Test
	void testGetCountOfNonExistentWords() {
		
		assertEquals(0, an.getCountOf(""), "Non-zero count of word before analysis");
		
		try {
			an.performAnalysis("testFiles/text3.txt");
			
		} catch (IOException e) {

			fail("Unexpected IO exception : " + e.getMessage());
		}
		
		assertEquals(0, an.getCountOf(""), "Non-zero count of empty word");
		assertEquals(0, an.getCountOf(null), "Non-zero count of null word");
		assertEquals(0, an.getCountOf("missing"), "Non-zero count of missing word");
		
		assertNotEquals(0, an.getUniqueWordCount(), "Some words should be present");

	}
	
	
	@Test
	void testMostPopularWord() {
		
		assertTrue(an.getMostPopularWord().isEmpty(), "Most popular words should be empty before analysis");

		try {
			an.performAnalysis("testFiles/emma.txt");

		} catch (IOException e) {

			fail("Unexpected IO exception : " + e.getMessage());
		}

		assertEquals("the", an.getMostPopularWord(), "Incorrect most popular word identified");
	}
	
	@Test
	void testMostPopularWordFirst() {
		
		assertTrue(an.getMostPopularWord().isEmpty(), "Most popular words should be empty before analysis");

		try {
			an.performAnalysis("testFiles/text3.txt");

		} catch (IOException e) {

			fail("Unexpected IO exception : " + e.getMessage());
		}

		assertEquals("word", an.getMostPopularWord(), "Incorrect most popular word identified, words not stored in an ordered collection?");
	}
	
	@Test
	void testMostPopularWordCount() {
		
		assertEquals(0, an.getMostPopularWordCount(), "Zero most popular word count should exist before analysis");

		try {
			an.performAnalysis("testFiles/emma.txt");

		} catch (IOException e) {

			fail("Unexpected IO exception : " + e.getMessage());
		}

		assertEquals(5377, an.getMostPopularWordCount(), "Incorrect count of the most popular word identified");
	}
	
	@Test
	void testLeastPopularWord() {
		
		assertTrue(an.getLeastPopularWord().isEmpty(), "Least popular words should be empty before analysis");

		try {
			an.performAnalysis("testFiles/emma.txt");

		} catch (IOException e) {

			fail("Unexpected IO exception : " + e.getMessage());
		}

		assertEquals("title", an.getLeastPopularWord(), "Incorrect least popular word identified");
	}
	
	@Test
	void testLeastPopularWordFirst() {
		
		assertTrue(an.getLeastPopularWord().isEmpty(), "Least popular words should be empty before analysis");

		try {
			an.performAnalysis("testFiles/text3.txt");

		} catch (IOException e) {

			fail("Unexpected IO exception : " + e.getMessage());
		}

		assertEquals("word", an.getLeastPopularWord(), "Incorrect least popular word identified, words not stored in an ordered collection?");
	}
	
	@Test
	void testLeastPopularWordCount() {
		
		assertEquals(0, an.getLeastPopularWordCount(), "Zero least popular word count should exist before analysis");

		try {
			an.performAnalysis("testFiles/emma.txt");

		} catch (IOException e) {

			fail("Unexpected IO exception : " + e.getMessage());
		}

		assertEquals(1, an.getLeastPopularWordCount(), "Incorrect count of the least popular word identified");
	}

	@Test
	void testUniqueWordCount() {
		
		assertEquals(0, an.getUniqueWordCount(), "Zero unique word count should exist before analysis");

		try {
			an.performAnalysis("testFiles/emma.txt");
			assertEquals(7503, an.getUniqueWordCount(), "Incorrect unique count returned");
			
			an.performAnalysis("testFiles/text1.txt");
			assertEquals(16, an.getUniqueWordCount(), "Incorrect unique count returned after second analysis, word occurrence collection not cleared?");

		} catch (IOException e) {

			fail("Unexpected IO exception : " + e.getMessage());
		}		
	}
	
	@Test
	void testAllAnalysis() {
		
		assertTrue(an.getMostPopularWord().isEmpty(), "Most popular words should be empty before analysis");
		assertTrue(an.getLeastPopularWord().isEmpty(), "Least popular words should be empty before analysis");
		assertEquals(0, an.getMostPopularWordCount(), "Zero most popular word count should exist before analysis");
		assertEquals(0, an.getLeastPopularWordCount(), "Zero least popular word count should exist before analysis");
		assertEquals(0, an.getUniqueWordCount(), "Zero unique word count should exist before analysis");
		assertEquals(0, an.getCountOf(""), "Non-zero count of word before analysis");
		
		try {
			an.performAnalysis("testFiles/pride-and-prejudice.txt");
			
			assertEquals(53, an.getCountOf("pride"), "Incorrect count of word");
			assertEquals("the", an.getMostPopularWord(), "Incorrect most popular word identified");
			assertEquals(4507, an.getMostPopularWordCount(), "Incorrect count of the most popular word identified");
			assertEquals("author", an.getLeastPopularWord(), "Incorrect least popular word identified");
			assertEquals(1, an.getLeastPopularWordCount(), "Incorrect count of the least popular word identified");
			assertEquals(6659, an.getUniqueWordCount(), "Incorrect unique count returned");
			
			an.performAnalysis("testFiles/text2.txt");
			assertEquals(0, an.getCountOf("pride"), "Incorrect count of missing word, word occurrence collection not cleared?");
			assertEquals("to", an.getMostPopularWord(), "Incorrect most popular word identified");
			assertEquals(3, an.getMostPopularWordCount(), "Incorrect count of the most popular word identified");
			assertEquals(3, an.getCountOf("to"), "Incorrect count of word");
			assertEquals("alternative", an.getLeastPopularWord(), "Incorrect least popular word identified");
			assertEquals(1, an.getLeastPopularWordCount(), "Incorrect count of the least popular word identified");
			assertEquals(20, an.getUniqueWordCount(), "Incorrect unique count returned after second analysis, word occurrence collection not cleared?");
			
		} catch (IOException e) {

			fail("Unexpected IO exception : " + e.getMessage());
		}	
	}
	
}
