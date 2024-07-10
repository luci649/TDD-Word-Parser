package analyser;

/**
 * Stores result information related to the analysis of text.
 * 
 * @author mdixon
 */
public class AnalysisResult {

	//add missing attributes (use UML model to identify these)
	private int resetCount = 0;
	private int totalChars = 0;
	private int wordCount = 0;
	private String longestWord = "";
	private String shortestWord = "";
	private String lastWord = "";
	////////////////////////////////////////////////////////////

	/**
	 * Records a word, using the information given to calculate analysis results.
	 * 
	 * Any whitespace is trimmed from either side of the word prior to it being
	 * recorded.
	 * 
	 * @param word the word to be recorded (null or empty words are ignored).
	 */
	public void recordWord(String word) {

		//ensure word is not null or empty
		if(word != null && word != "") {
			//remove any whitespace
			//store the word in the last word attribute
			lastWord = word.trim();
			//increment the word count attribute
			wordCount++;
			if(shortestWord == "" && longestWord == "") {
				shortestWord = lastWord;
				longestWord = lastWord;
			//check if word is the longest so far, if so record in appropriate attribute
			}else if(lastWord.length() > longestWord.length()) {
				longestWord = lastWord;
			//check if word is the shortest so far, if so record in appropriate attribute
			}else if(lastWord.length() < shortestWord.length()) {
				shortestWord = lastWord;
			}
			//add length of word to the total character count attribute
			totalChars = totalChars + lastWord.length();
		}
	}

	/**
	 * @return total number of characters recorded.
	 */
	public int getTotalChars() {

		return totalChars; //return correct attribute
	}

	/**
	 * @return total number of words recorded.
	 */
	public int getWordCount() {

		return wordCount; //return correct attribute
	}

	/**
	 * @return the number of times {@link #reset()} has been called.
	 */
	public int getResetCount() {

		return resetCount; //return correct attribute
	}

	/**
	 * Gets the longest word recorded.
	 * 
	 * note: If multiple longest recorded words contain the same number of
	 * characters, then the first one recorded is returned.
	 * 
	 * @return the longest recorded word
	 */
	public String getLongestWord() {

		return longestWord; //return correct attribute
	}

	/**
	 * Gets the shortest word recorded.
	 * 
	 * note: If multiple shortest recorded words contain the same number of
	 * characters, then the first one recorded is returned.
	 * 
	 * @return the shortest recorded word
	 */
	public String getShortestWord() {

		return shortestWord; //return correct attribute
	}

	/**
	 * Gets the most recently recorded word.
	 * 
	 * @return the most recently recorded word.
	 */
	public String getLastWord() {

		return lastWord; //return correct attribute
	}

	/**
	 * Calculates and returns the average length of all recorded words.
	 * 
	 * @return the average length of all recorded words. This will be 0.0 if no
	 *         words have been recorded.
	 */
	public double getAveWordLength() {

		//calculate average and return
		if(wordCount > 0.0) {
			return (double)totalChars/wordCount;
		}
		return 0.0;
	}

	/**
	 * Resets the analysis results back to the initial state, and increments the
	 * reset count as returned by {@link #getResetCount()}.
	 */
	public void reset() {

		//reset appropriate attributes, and increment the reset count
		 resetCount++;
		 totalChars = 0;
		 wordCount = 0;
		 longestWord = "";
		 shortestWord = "";
		 lastWord = ""; 
	}
}
