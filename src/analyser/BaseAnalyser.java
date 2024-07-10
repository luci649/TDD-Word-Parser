package analyser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An abstract class that defines the primary methods of a textual analyser.
 * 
 * This is designed to be extended by concrete classes that provide specific
 * types of analysis.
 * 
 * @author mdixon
 */
abstract class BaseAnalyser implements Analyser {

	/**
	 * Stores the result of the most recent analysis.
	 */
	private final AnalysisResult result = new AnalysisResult();

	/**
	 * The name of the analyser.
	 */
	private final String name;

	/**
	 * The description of the analyser.
	 */
	private final String descr;

	/**
	 * The name of the text file that is being analysed.
	 */
	private String currentFilename = "";

	/**
	 * The reader used to read text from the current file.
	 */
	private BufferedReader reader;

	/**
	 * The most recently read line of words, split into an array.
	 */
	private String[] words;

	/**
	 * The index of the next word to be read from the words stored within the
	 * {@link #words} array.
	 */
	private int nextWord;

	/**
	 * Used to match patterns of numerical digits within accessed words.
	 */
	private final Matcher numMatcher;

	//////////////////////////////////////////////////////////

	/**
	 * Generates a report header to the given output.
	 * 
	 * @param out the output to which to generate the header.
	 */
	void generateHeader(PrintStream out) {

		out.println(
				"=======================================================================================================================");
		out.println("Analysis performed by " + name + ", which " + descr);
		out.println("The file analysed was '" + currentFilename + "'");
		out.println("---------------------------------------------------------------------");

		out.println("Total number of words is      : " + result.getWordCount());
		out.println("Total number of characters is : " + result.getTotalChars());
		out.println("Average words length is       : " + result.getAveWordLength());
		out.println("The longest word is           : " + result.getLongestWord());
		out.println("The shortest word is          : " + result.getShortestWord());
		out.println("---------------------------------------------------------------------");
	}

	/**
	 * Selects the text file to be be analysed.
	 * 
	 * @param filename the name of the file.
	 * @throws FileNotFoundException if the file could not be found
	 */
	void selectInputFile(String filename) throws FileNotFoundException {

		result.reset(); // ensure result instance is reset, since selecting a new input file.

		reader = new BufferedReader(new FileReader(filename));

		currentFilename = filename;
	}

	/**
	 * Reads the next word from the file being analysed.
	 * 
	 * @return the next word (always lower-case), null if no further words exist.
	 * @throws IOException
	 */
	String readNextWord() throws IOException {

		String word = "";

		do {
			while (words == null || nextWord >= words.length) {
				// a new line needs reading from the input file
				String nextLine = (reader != null) ? reader.readLine() : null;

				if (nextLine == null) {
					// end of file
					reader.close();
					reader = null;
					words = null;

					return null;
				}

				// split the line into an array of individual words
				
				words = nextLine.split("[\\W]"); // \\W represents a non-word character

				nextWord = 0;
			}

			// next word available to be read from the array
			word = words[nextWord++];

			// remove any word that contains digits, by testing if the word matches a
			// pattern containing digits.
			numMatcher.reset(word);

			if (numMatcher.find())
				word = ""; // word contained digits, so ignore this word

		} while (word.isEmpty());	// loop until valid non-empty word found

		word = word.toLowerCase(); // ensure word always returned as lower case.

		result.recordWord(word); // record the returned word within the result instance

		return word;
	}

	//////////////////////////////////////////////////////////

	/**
	 * Gets the result of the most recent analysis.
	 * 
	 * @return the {@link AnalysisResult} of the most recent analysis.
	 */
	public AnalysisResult getResult() {

		return result;
	}

	/**
	 * Generates a report to the given output.
	 * 
	 * @param out the {@link PrintStream} output to which to generate the report.
	 */
	abstract public void generateReport(PrintStream out);

	//////////////////////////////////////////////////////////

	/**
	 * Constructor (not public since this class cannot be directly instantiated)
	 * 
	 * @param name  the name of the analyser.
	 * @param descr the description of the analyser.
	 */
	BaseAnalyser(String name, String descr) {

		this.name = name;
		this.descr = descr;

		// Create a pattern matcher, that can be used to identify words containing digits
		Pattern pattern = Pattern.compile("[0-9]+");
		numMatcher = pattern.matcher(""); // init to empty string, reset when used.
	}

}
