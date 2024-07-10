package analyser;

import java.io.IOException;

/**
 * An interface that defines method(s) to be implemented by text analysers.
 * 
 * @author mdixon
 */
public interface Analyser {

	/**
	 * Performs an analysis of the text within the given file.
	 * 
	 * Each time an analysis is performed any results from previous calls are removed, and do not form part of the generated results.
	 * 
	 * @param filename the name of the text file to be analysed
	 * @throws IOException if an IO error occurs
	 */
	void performAnalysis(String filename) throws IOException;
}
