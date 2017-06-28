package concordance;

import java.util.ArrayList;

public class WordData {
	protected int count;
	protected ArrayList<Integer> sentences;
	
	public WordData() {
		this.count = 0;
		this.sentences = new ArrayList<Integer>();
	}
}
