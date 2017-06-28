package concordance;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer.PTBTokenizerFactory;

public class ConcordBuilder {

	/*Method to check the frequency of words in sentences
	  Iterate through the list of words.
	  Add frequency of words and sentence occurences */
	private TreeMap<String, WordData> processSentences(DocumentPreprocessor dp) {
		String str;
		WordData temp;
		int sentenceCount = 0;
		TreeMap<String, WordData> concordance = new TreeMap<String, WordData>();
		for (List<HasWord> sentence : dp) {
			sentenceCount++;

			for (HasWord word : sentence) {
				str = word.toString().toLowerCase();
				if (!(checkCharacters(str))) {
					if (concordance.containsKey(str)) {
						temp = concordance.get(str);
					} else {
						temp = new WordData();
						concordance.put(str, temp);

					}
					temp.count++;
					temp.sentences.add(sentenceCount);
				}
			}
		}
		return concordance;
	}

	//Method to check if a sting contains only special characters
	protected boolean checkCharacters(String str) {
		return str.matches("[^a-z A-Z0-9]");
	}

	// Method to print the final Tree Map generated from input
	private void printMap(TreeMap<String, WordData> concordance) {
		String key;
		WordData val;
		ArrayList<Integer> sentences;
		for (Map.Entry<String, WordData> entry : concordance.entrySet()) {
			key = entry.getKey();
			val = entry.getValue();
			sentences = val.sentences;
			String sentenceList = sentences.stream().map(i -> i.toString()).collect(Collectors.joining(", "));
			System.out.println(key + " -> " + val.count + " : " + sentenceList);

		}
	}

	protected TreeMap<String, WordData> parser(BufferedReader br) {
		// Stanford NLP Tokenizer for splitting text into sentences and words
		DocumentPreprocessor dp = new DocumentPreprocessor(br);
		PTBTokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizerFactory
				.newCoreLabelTokenizerFactory("normalizeParentheses=false, normalizeOtherBrackets=false, latexQuotes=false");
		dp.setTokenizerFactory(tokenizerFactory);

		return processSentences(dp);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConcordBuilder cb = new ConcordBuilder();
		try {
			// Read file from a given path using BufferedReader
			File file = new File("C://Users/Vineeth/Desktop/input.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			TreeMap<String, WordData> concordance = cb.parser(br);
			cb.printMap(concordance);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
