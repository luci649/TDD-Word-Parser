package analyser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

/**
 * A kind of {@link BaseAnalyser} that identifies whether words are present
 * within a specified dictionary.
 * 
 * @author mdixon
 */
public class DictionaryAnalyser extends BaseAnalyser {

	/**
	 * The set of words that represents the dictionary of known words.
	 */
	private Set<String> dictionary = new HashSet<String>();	//create the appropriate collection instance

	/**
	 * The set of unknown words found during the most recent analysis.
	 */
	private Set<String> unknownWords = new HashSet<String>();	//create the appropriate collection instance

	/**
	 * The set of known words found during the most recent analysis.
	 */
	private Set<String> knownWords = new HashSet<String>();	//create the appropriate collection instance

	////////////////////////////////////////////////////////////////////

	
	/**
	 * Adds words contained with the specified file into the dictionary of known
	 * words.
	 * 
	 * Each individual word should be on a separate line within the file, and should
	 * not contain any spaces.
	 * 
	 * Blank lines are ignored.
	 * 
	 * Any space before or after a word is trimmed.
	 * 
	 * The words are always stored as lower-case, even if they are upper-case within
	 * the file.
	 * 
	 * note: this appends to the dictionary, with any existing content remaining.
	 * 
	 * @param filename the name of the file containing the words to be added to the dictionary.
	 * @throws IOException if the named file could not be found and loaded.
	 */
	public void addToDictionary(String filename) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(filename));
		
		// read the next line from the file
		String nextLine = reader.readLine();	

		while (nextLine != null) {
			//trim white space from the next line and change the next line to be lower case
			nextLine = nextLine.toLowerCase().trim(); 
			//add the next line to the dictionary (unless it is an empty line)
			if(nextLine.length() >= 1){
				dictionary.add(nextLine);
			}
			//read the next line from the file
			nextLine = reader.readLine();
		}

		reader.close();
	}
	
	@Override
	public void performAnalysis(String filename) throws IOException {

		//clear existing known and unknown word collections.
		knownWords.clear();
		unknownWords.clear();
		
		selectInputFile(filename);	// select the file to be analysed

		String nextWord = readNextWord();

		// process all available words
		while (nextWord != null) {

			//identify whether word is within the dictionary
			// if it is then record as a known word, otherwise record as an unknown word.
			if(dictionary.contains(nextWord)) {
				knownWords.add(nextWord);
			}else {
				unknownWords.add(nextWord);
			}
			nextWord = readNextWord();
		}
	}

	@Override
	public void generateReport(PrintStream out) {
		
		generateHeader(out);
	
		out.println("The dictionary word count is " + dictionary.size());
		out.println("The number of words not present in the dictionary is " + unknownWords.size());
		out.println("The number of words present in the dictionary is " + knownWords.size());
	}

	/**
	 * Clears the current dictionary contents.
	 */
	public void clearDictionary() {

		dictionary.clear();  //clear the dictionary contents
	}
	
	/**
	 * 
	 * @return the set of words that represents the dictionary of known words.
	 */
	public Set<String> getDictionary() {
				
		return dictionary;	//return appropriate attribute
	}

	/**
	 * 
	 * @return the set of known words found during the most recent analysis.
	 */
	public Set<String> getKnownWords() {
		
		return knownWords;  //return appropriate attribute	
	}

	/**
	 * 
	 * @return the set of unknown words found during the most recent analysis.
	 */
	public Set<String> getUnknownWords() {

		return unknownWords;	//return appropriate attribute
	}

	//////////////////////////////////////////////////////////////////

	/**
	 * Constructor
	 */
	public DictionaryAnalyser() {

		super("Dictionary Analyser", "checks for words which are present within a dictionary of known words");
	}
}
