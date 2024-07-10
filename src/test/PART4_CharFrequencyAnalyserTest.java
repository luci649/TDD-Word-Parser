package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import analyser.CharFrequencyAnalyser;

/**
 * Tests the {@link CharFrequencyAnalyser} class.
 * 
 * 
 * NOTE: DO NOT CHANGE THE CONTENTS OF THIS FILE.
 * 
 * @author mdixon
 */
class PART4_CharFrequencyAnalyserTest {

	/**
	 * The {@link CharFrequencyAnalyser} instance created prior to each test.
	 */
	private CharFrequencyAnalyser an;
	
	@BeforeEach
	void setUpBeforeEach() throws Exception {
		
		an = new CharFrequencyAnalyser();
	}
	
	@Test
	void testGetCountOf() {
				
		try {
			an.performAnalysis("testFiles/text1.txt");
			
		} catch (IOException e) {

			fail("Unexpected IO exception : " + e.getMessage());
		}
		
		assertEquals(6, an.getCountOf('a'), "Incorrect count of present character");
		assertEquals(2, an.getCountOf('b'), "Incorrect count of present character");
		assertEquals(3, an.getCountOf('c'), "Incorrect count of present character");
		
	}
	
	@Test
	void testGetCountOfNonExistentChars() {
		
		assertEquals(0, an.getCountOf('a'), "Non-zero count of character before analysis");
		
		try {
			an.performAnalysis("testFiles/text3.txt");
			
		} catch (IOException e) {

			fail("Unexpected IO exception : " + e.getMessage());
		}
		
		assertEquals(0, an.getCountOf(null), "Non-zero count of null character");
		assertEquals(0, an.getCountOf('%'), "Non-zero count of missing character");
		
		assertNotEquals(0, an.getUniqueCharCount(), "Some characters should be present");
	}
	
	
	@Test
	void testMostPopularChar() {
		
		assertNull(an.getMostPopularChar(), "Most popular characters should be empty before analysis");

		try {
			an.performAnalysis("testFiles/emma.txt");

		} catch (IOException e) {

			fail("Unexpected IO exception : " + e.getMessage());
		}

		assertEquals('e', an.getMostPopularChar(), "Incorrect most popular character identified");
	}
	
	@Test
	void testMostPopularCharFirst() {
		
		assertNull(an.getMostPopularChar(), "Most popular characters should be empty before analysis");

		try {
			an.performAnalysis("testFiles/text3.txt");

		} catch (IOException e) {

			fail("Unexpected IO exception : " + e.getMessage());
		}

		assertEquals('w', an.getMostPopularChar(), "Incorrect most popular character identified, words not stored in an ordered collection?");
	}
	
	@Test
	void testMostPopularCharCount() {
		
		assertEquals(0, an.getMostPopularCharCount(), "Zero most popular character count should exist before analysis");

		try {
			an.performAnalysis("testFiles/emma.txt");

		} catch (IOException e) {

			fail("Unexpected IO exception : " + e.getMessage());
		}

		assertEquals(87838, an.getMostPopularCharCount(), "Incorrect count of the most popular character identified");
	}
	

	@Test
	void testUniqueCharCount() {
		
		assertEquals(0, an.getUniqueCharCount(), "Zero unique character count should exist before analysis");

		try {
			an.performAnalysis("testFiles/emma.txt");
			assertEquals(27, an.getUniqueCharCount(), "Incorrect unique count returned");
			
			an.performAnalysis("testFiles/text1.txt");
			assertEquals(19, an.getUniqueCharCount(), "Incorrect unique count returned after second analysis, character occurrence collection not cleared?");

		} catch (IOException e) {

			fail("Unexpected IO exception : " + e.getMessage());
		}		
	}
	
	@Test
	void testVowelCount() {
		
		assertEquals(0, an.getVowelCount(), "Zero vowel count should exist before analysis");

		try {
			an.performAnalysis("testFiles/emma.txt");
			assertEquals(266641, an.getVowelCount(), "Incorrect vowel count returned");
			
			an.performAnalysis("testFiles/text1.txt");
			assertEquals(38, an.getVowelCount(), "Incorrect vowel count returned after second analysis, count or character occurrence collection not cleared?");

		} catch (IOException e) {

			fail("Unexpected IO exception : " + e.getMessage());
		}		
	}
	
	@Test
	void testNonVowelCount() {
		
		assertEquals(0, an.getNonVowelCount(), "Zero non-vowel count should exist before analysis");

		try {
			an.performAnalysis("testFiles/emma.txt");
			assertEquals(433634, an.getNonVowelCount(), "Incorrect non-vowel count returned");
			
			an.performAnalysis("testFiles/text1.txt");
			assertEquals(60, an.getNonVowelCount(), "Incorrect non-vowel count returned after second analysis, count or character occurrence collection not cleared?");

		} catch (IOException e) {

			fail("Unexpected IO exception : " + e.getMessage());
		}		
	}

	@Test
	void testSingleCharacterWordCount() {
		
		assertEquals(0, an.getSingleCharacterWordCount(), "Zero single-character word count should exist before analysis");

		try {
			an.performAnalysis("testFiles/emma.txt");
			assertEquals(7430, an.getSingleCharacterWordCount(), "Incorrect single-character word count returned");
			
			an.performAnalysis("testFiles/text1.txt");
			assertEquals(1, an.getSingleCharacterWordCount(), "Incorrect single-character word count returned after second analysis, count or character occurrence collection not cleared?");

			an.performAnalysis("testFiles/text2.txt");
			assertEquals(0, an.getSingleCharacterWordCount(), "Incorrect single-character word count returned after third analysis, count or character occurrence collection not cleared?");

			
		} catch (IOException e) {

			fail("Unexpected IO exception : " + e.getMessage());
		}		
	}
	
	@Test
	void testMultiCharacterWordCount() {
		
		assertEquals(0, an.getMultiCharacterWordCount(), "Zero multi-character word count should exist before analysis");

		try {
			an.performAnalysis("testFiles/emma.txt");
			assertEquals(157612, an.getMultiCharacterWordCount(), "Incorrect multi-character word count returned");
			
			an.performAnalysis("testFiles/text1.txt");
			assertEquals(22, an.getMultiCharacterWordCount(), "Incorrect multi-character word count returned after second analysis, count or character occurrence collection not cleared?");

			an.performAnalysis("testFiles/text2.txt");
			assertEquals(27, an.getMultiCharacterWordCount(), "Incorrect multi-character word count returned after third analysis, count or character occurrence collection not cleared?");

			
		} catch (IOException e) {

			fail("Unexpected IO exception : " + e.getMessage());
		}		
	}
}
