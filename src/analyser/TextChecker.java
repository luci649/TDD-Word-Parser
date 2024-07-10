package analyser;

import java.io.IOException;

/**
 * Simple example driver class.
 * 
 * @author mdixon
 *
 */
public class TextChecker {

	public static void main(String[] args) throws IOException {

		DictionaryAnalyser dictAnalyser = new DictionaryAnalyser();
		WordFrequencyAnalyser freqAnalyser = new WordFrequencyAnalyser();
		CharFrequencyAnalyser charAnalyser = new CharFrequencyAnalyser();
				
		dictAnalyser.addToDictionary("testFiles/dict1.txt");
		dictAnalyser.performAnalysis("testFiles/emma.txt");
		dictAnalyser.generateReport(System.out);
		
		freqAnalyser.performAnalysis("testFiles/emma.txt");
		freqAnalyser.generateReport(System.out);
		
		charAnalyser.performAnalysis("testFiles/emma.txt");
		charAnalyser.generateReport(System.out);
	}

}
