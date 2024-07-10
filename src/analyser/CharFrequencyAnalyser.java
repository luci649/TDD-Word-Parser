package analyser;

import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * A kind of {@link BaseAnalyser} that counts the number of unique individual
 * character occurrences within the text.
 * 
 * @author mdixon
 */
public class CharFrequencyAnalyser extends BaseAnalyser {

	/**
	 * The collection containing each found character, mapped to the occurrence
	 * count.
	 * 
	 * This is a linked hash map so the order in which the characters are added is
	 * maintained.
	 */
	private Map<Character, Integer> charCounts = new LinkedHashMap<Character, Integer>(); // create the appropriate collection instance

	// add missing attributes (see UML model).
	private int vowelCount, singleCharCount;
	//////////////////////////////////////////////////////////////////

	@Override
	public void performAnalysis(String filename) throws IOException {

		// clear map contents and re-init other attributes.
		charCounts.clear();
		vowelCount = 0;
		singleCharCount = 0;

		selectInputFile(filename); // select the file to be analysed

		String nextWord = readNextWord();

		// process all available words
		while (nextWord != null) {

			// extract each character from the next word, and add to the occurrence map
			for (int i = 0; i < nextWord.length(); i++) {
				
				// get char at position 'i' from the next word
				// Check if present in the map, and increment occurrence count		
				if(charCounts.containsKey(nextWord.charAt(i))) {
					charCounts.put(nextWord.charAt(i), charCounts.get(nextWord.charAt(i)) + 1);
				}else {
					charCounts.put(nextWord.charAt(i), 1);	
				}
				// check if vowel, if so increment correct attribute
				if(nextWord.charAt(i) == 'a' || nextWord.charAt(i) == 'e' || nextWord.charAt(i) == 'i' || nextWord.charAt(i) == 'o' || nextWord.charAt(i) == 'u') {
					vowelCount++;
				}
			}

			// if word length is 1, then increment attribute that counts single
			// character words.
			if(nextWord.length() == 1) {
				singleCharCount++;
			}
			nextWord = readNextWord();
		}
	}

	@Override
	public void generateReport(PrintStream out) {

		generateHeader(out);

		out.println("Most popular character is '" + getMostPopularChar() + "' with an occurrence count of "
				+ getMostPopularCharCount());
		out.println("Unique character count is " + getUniqueCharCount());
	}

	/**
	 * Gets the most popular character of the most recent analysis.
	 * 
	 * If multiple characters have the same number of occurrences, then the first of
	 * these recorded should be returned.
	 * 
	 * @return the most popular character of the most recent analysis, this will be
	 *         null an analysis is yet to be performed.
	 */
	public Character getMostPopularChar() {

		// find the most popular character
		Character character = null;
		int max = 0;
		// if highest occurrence count so far, record the character.
		for(Entry<Character, Integer> entry : charCounts.entrySet()) {
			
			if(entry.getValue() > max) {
				max = entry.getValue();
				character = entry.getKey();
			}
		}
		return character;
	}

	/**
	 * Gets the number of times the most popular character(s) appeared within the
	 * most recent analysis.
	 * 
	 * @return the number of times the most popular character(s) appeared, 0 if an
	 *         analysis is yet to be performed.
	 */
	public int getMostPopularCharCount() {

		// find the most popular character count
		if (getMostPopularChar() == null) {
			return 0;
		}else {
		return charCounts.get(getMostPopularChar());
		}
	}

	/**
	 * Gets the number of unique characters within the analysed text.
	 * 
	 * @return the number of unique characters analysed.
	 */
	public int getUniqueCharCount() {

		return charCounts.size(); // return size of the map
	}

	/**
	 * Gets the total number of characters which are vowels within the analysed
	 * text.
	 * 
	 * @return the total number of characters which are vowels
	 */
	public int getVowelCount() {

		return vowelCount; // return appropriate attribute
	}

	/**
	 * Gets the total number of characters which are not vowels within the analysed
	 * text.
	 * 
	 * @return the total number of characters which are not vowels
	 */
	public int getNonVowelCount() {

		// calc result and return (hint: can use getResult().getTotalChars() to get total char count).
		return getResult().getTotalChars() - getVowelCount();
	}

	/**
	 * Gets the total number of single character words present within the analysed
	 * text.
	 * 
	 * @return the total number of single character words
	 */
	public int getSingleCharacterWordCount() {

		return singleCharCount; // return appropriate attribute
	}

	/**
	 * Gets the total number of multi-character words present within the analysed
	 * text.
	 * 
	 * @return the total number of multi-character words
	 */
	public int getMultiCharacterWordCount() {

		// calc result and return (hint: can use getResult().getWordCount() to get total word count).
		return getResult().getWordCount() - getSingleCharacterWordCount();
	}

	/**
	 * Gets the number of times the given character occurred in the most recent
	 * analysis.
	 * 
	 * @param character the character for which the occurrence count is required.
	 * @return the number of times the given character appeared, 0 if it did not
	 *         ever appear.
	 */
	public int getCountOf(Character character) {

		if(charCounts.containsKey(character)) {
			return charCounts.get(character);
		}else {
		return 0; // lookup the character in the map and return the associated count value (if any).
		}
	}

	/**
	 * Constructor
	 */
	public CharFrequencyAnalyser() {

		super("Character Frequency Analyser", "counts the number of unique character occurrences within the text");
	}

}
