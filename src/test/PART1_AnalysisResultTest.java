package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import analyser.AnalysisResult;

/**
 * Tests the {@link AnalysisResult} class.
 * 
 * 
 * NOTE: DO NOT CHANGE THE CONTENTS OF THIS FILE.
 * 
 * @author mdixon
 */
class PART1_AnalysisResultTest {

	/**
	 * The {@link AnalysisResult} instance created prior to each test.
	 */
	private AnalysisResult ar;
	
	@BeforeEach
	void setUpBeforeEach() throws Exception {
		
		ar = new AnalysisResult();
	}
	
	@Test
	void testGetTotalChars() {
		assertEquals(0, ar.getTotalChars(), "Should have zero character count before words recorded");
		
		ar.recordWord("A");
		assertEquals(1, ar.getTotalChars(), "Should have 1 character count when single character word recorded");
		
		ar.recordWord("B");
		assertEquals(2, ar.getTotalChars(), "Should have 2 character count when single character word recorded twice");
		
		ar.recordWord("London");
		assertEquals(8, ar.getTotalChars(), "Should have appropriate characters when multiple words recorded");
		
		ar.recordWord("Town");
		assertEquals(12, ar.getTotalChars(), "Should have appropriate characters when multiple words recorded");
	}
	
	@Test
	void testGetWordCount() {
		assertEquals(0, ar.getWordCount(), "Should have zero word count before words recorded");
		
		ar.recordWord("A");
		assertEquals(1, ar.getWordCount(), "Should have 1 word count when single character word recorded");
		
		ar.recordWord("B");
		assertEquals(2, ar.getWordCount(), "Should have 2 word count when single character word recorded twice");
		
		ar.recordWord("London");
		assertEquals(3, ar.getWordCount(), "Should have appropriate increasing word count as words recorded");
		
		ar.recordWord("Town");
		assertEquals(4, ar.getWordCount(), "Should have appropriate increasing word count as words recorded");
		
		ar.recordWord("is");
		assertEquals(5, ar.getWordCount(), "Should have appropriate increasing word count as words recorded");
		
		ar.recordWord("Busy");
		assertEquals(6, ar.getWordCount(), "Should have appropriate increasing word count as words recorded");
	}
		
	@Test
	void testGetLongestWord() {
		assertEquals("",ar.getLongestWord(), "Should have empty longest word before words recorded");
		
		ar.recordWord("A");
		assertEquals("A", ar.getLongestWord(), "Should have matching longest word when single character word recorded");
		
		ar.recordWord("Robot");
		assertEquals("Robot", ar.getLongestWord(), "Should have matching longest word when multi-character word recorded");
		
		ar.recordWord("Work");
		assertEquals("Robot", ar.getLongestWord(), "Should have unchanged longest word when lower-character word recorded");
		
		ar.recordWord("Shy");
		assertEquals("Robot", ar.getLongestWord(), "Should have unchanged longest word when lower-character word recorded");
		
		ar.recordWord("Shout");
		assertEquals("Robot", ar.getLongestWord(), "Should have unchanged longest word when equal length character word recorded");
		
		ar.recordWord("Person");
		assertEquals("Person", ar.getLongestWord(), "Should have matching longest word when longer word recorded");
		
		ar.recordWord("Spy");
		assertEquals("Person", ar.getLongestWord(), "Should have unchanged longest word when lower-character word recorded");
		
		ar.recordWord("");
		assertEquals("Person", ar.getLongestWord(), "Should have unchanged longest word when empty-character word recorded");
		
		ar.recordWord(null);
		assertEquals("Person", ar.getLongestWord(), "Should have unchanged longest word when null word recorded");
	}
	
	@Test
	void testGetShortestWord() {
		assertEquals("",ar.getShortestWord(), "Should have empty shortest word before words recorded");
				
		ar.recordWord("Robot");
		assertEquals("Robot", ar.getShortestWord(), "Should have matching shortest word when multi-character word recorded");
		
		ar.recordWord("Working");
		assertEquals("Robot", ar.getShortestWord(), "Should have unchanged shortest word when higher-character word recorded");
		
		ar.recordWord("Android");
		assertEquals("Robot", ar.getShortestWord(), "Should have unchanged shortest word when higher-character word recorded");
		
		ar.recordWord("Shout");
		assertEquals("Robot", ar.getShortestWord(), "Should have unchanged shortest word when equal length character word recorded");
				
		ar.recordWord("Spy");
		assertEquals("Spy", ar.getShortestWord(), "Should have updated shortest word when lower-character word recorded");
		
		ar.recordWord("Sat");
		assertEquals("Spy", ar.getShortestWord(), "Should have unchanged shortest word when equal length character word recorded");
		
		ar.recordWord("");
		assertEquals("Spy", ar.getShortestWord(), "Should have unchanged shortest word when empty-character word recorded");
		
		ar.recordWord(null);
		assertEquals("Spy", ar.getShortestWord(), "Should have unchanged shortest word when null word recorded");
	}
	
	@Test
	void testRecordWord() {
		
		assertEquals("",ar.getLastWord(), "Should have empty last word before words recorded");
		
		ar.recordWord("Timmy");
		assertEquals("Timmy", ar.getLastWord(), "Last recorded word should always match");
		
		ar.recordWord("Peach");
		assertEquals("Peach", ar.getLastWord(), "Last recorded word should always match");
		
		ar.recordWord("Jimmy");
		assertEquals("Jimmy", ar.getLastWord(), "Last recorded word should always match");
		
		ar.recordWord("");
		assertNotEquals("", ar.getLastWord(), "Empty words should not be recorded");
		assertEquals("Jimmy", ar.getLastWord(), "Empty words should not update last recorded word");
		
		ar.recordWord(null);
		assertNotEquals("", ar.getLastWord(), "null words should not be recorded");
		assertEquals("Jimmy", ar.getLastWord(), "null words should not update last recorded word");
		
		ar.recordWord("Fozzie");
		assertEquals("Fozzie", ar.getLastWord(), "Last recorded word should always match");
	}

	@Test
	void testRecordTrimmedWord() {
		
		ar.recordWord(" Timmy ");
		assertEquals("Timmy", ar.getLastWord(), "Last recorded word should be trimmed before being recorded");
		
		ar.recordWord("Apple            ");
		assertEquals("Apple", ar.getLastWord(), "Last recorded word should be trimmed before being recorded");
		
		ar.recordWord("           Water");
		assertEquals("Water", ar.getLastWord(), "Last recorded word should be trimmed before being recorded");
		
		assertEquals(15, ar.getTotalChars(), "The character count should be based on the actual words recorded (after trimming)");
	}
	
	@Test
	void testGetAveWordLength() {
		
		assertEquals(0.0, ar.getAveWordLength(), "Should have average zero word length before words recorded");
		
		ar.recordWord("test");
		assertEquals(4.0, ar.getAveWordLength(), "Should have matching average word length when only one word recorded");
		
		ar.recordWord("test");
		assertEquals(4.0, ar.getAveWordLength(), "Should have matching average word length when same word recorded more than once");
		
		ar.recordWord("seventy");
		assertEquals(5.0, ar.getAveWordLength(), "Should have average word length when same word recorded more than once");
		
		ar.recordWord("subsquently");
		assertEquals(6.5, ar.getAveWordLength(), "Should have non-integer based average");
		
		ar.recordWord("urgent");
		assertEquals(6.4, ar.getAveWordLength(), "Should have non-integer based average");
	}

	@Test
	void testReset() {
		
		ar.recordWord("some");
		ar.recordWord("words");
		ar.recordWord("recorded");
		assertEquals(3, ar.getWordCount(), "Should have word count of three before reset");
		
		ar.reset();	// perform reset
		
		assertEquals(0, ar.getTotalChars(), "Should have zero character count after reset");
		assertEquals(0, ar.getWordCount(), "Should have zero word count after reset");
		assertEquals("",ar.getLongestWord(), "Should have empty longest word after reset");
		assertEquals("",ar.getLastWord(), "Should have empty last after reset");
		assertEquals(0.0, ar.getAveWordLength(), "Should have average zero word length after reset");
		
		ar.reset();	// perform reset again
		assertEquals(0, ar.getTotalChars(), "Multiple resets should not change state");
		assertEquals(0, ar.getWordCount(), "Multiple resets should not change state");
		assertEquals("",ar.getLongestWord(), "Multiple resets should not change state");
		assertEquals("",ar.getLastWord(), "Multiple resets should not change state");
		assertEquals(0.0, ar.getAveWordLength(), "Multiple resets should not change state");
	}
	
	@Test
	void testResetCount() {
		
		assertEquals(0, ar.getResetCount(), "Should have zero resets before use");
		
		ar.reset();	// perform reset
		assertEquals(1, ar.getResetCount(), "Should have one reset when first reset");
		
		ar.reset();
		ar.reset();
		ar.reset();
		assertEquals(4, ar.getResetCount(), "Should have correct reset count after multiple resets");
		
		ar.recordWord("Still");
		ar.recordWord("Water");
		ar.getLastWord();
		ar.getTotalChars();
		ar.getWordCount();
		ar.getLongestWord();
		ar.getAveWordLength();
		
		assertEquals(4, ar.getResetCount(), "Should have unchanged reset count after unrelated calls");
		ar.reset();
		assertEquals(5, ar.getResetCount(), "Should have increased reset count after extra reset");
	}
	
	@Test
	void testAllCalls() {
		
		String word = "Thunder";
		int wordLen = word.length();
		
		int repCount = 10;
		
		for(int i = 0; i < repCount; i++) {
			ar.recordWord("   " + word + "   ");
			ar.recordWord(null);
			ar.recordWord("");
			
			assertEquals(word, ar.getLastWord(), "Last recorded word should always match");
			assertEquals(i + 1, ar.getWordCount(), "Should have increasing word count after each word recorded");
			assertEquals(wordLen * (i + 1), ar.getTotalChars(), "Should have correct character count after each word recorded");
		}
		
		assertEquals(wordLen * repCount, ar.getTotalChars(), "Should have correct character count after multiple words recorded");
		assertEquals(repCount, ar.getWordCount(), "Should have correct word count after multiple words recorded");
		assertEquals(word,ar.getLongestWord(), "Should have unchanged longest word after multiple words recorded");
		assertEquals(word,ar.getShortestWord(), "Should have unchanged shortest word after multiple words recorded");
		assertEquals((double)wordLen, ar.getAveWordLength(), "Should have consistent average word length after multiple words recorded");
		assertEquals(word, ar.getLastWord(), "Last recorded word should always match");
		
		assertEquals(0, ar.getResetCount(), "Should have zero resets before reset");
		ar.reset();
		assertEquals(1, ar.getResetCount(), "Should have one reset when first reset");
		ar.reset();
		assertEquals(2, ar.getResetCount(), "Should have two resets when reset twice");
	}

}
