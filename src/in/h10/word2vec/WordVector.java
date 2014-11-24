package in.h10.word2vec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *Vector representation of word.
 *If word vector not available then {@link #getWordPosition()} return {@link #NOT_PRESENT}
 *@author kamal
 *@see Word2vec
 *@see Word2vec#getWordVector(String)
 */
public class WordVector {
	/**
	 * If word vector not present
	 */
	public static final int NOT_PRESENT = -1;
	private int wordPosition = -1;
	private List<String> words;
	private String word;
	private List<Double> distance;
	public WordVector(String word){
		this.word = word;
		words = new ArrayList<>();
		distance = new ArrayList<>();
	}
	
	/**
	 * true If vector not found in vocabulary
	 * @return true If vector not found in vocabulary
	 */
	public boolean isNotPresent() {
		return this.wordPosition == WordVector.NOT_PRESENT;
	}
	/**
	 * Word 
	 * @return word 
	 */
	public String getWord() {
		return this.word;
	}
	/**
	 * position in vocabulary</br>
	 * If word vector not available then return {@link #NOT_PRESENT}
	 * @return word position in vocabulary
	 */
	public int getWordPosition() {
		return wordPosition;
	}
	/**
	 * sets word position in vocabulary</br>
	 *If word vector not available then set {@link #NOT_PRESENT}
	 * @param wordPosition word position in vocabulary
	 */
	public void setWordPosition(int wordPosition) {
		this.wordPosition = wordPosition;
	}
	/**
	 * add word and it distance from {@link #getWord() word}
	 * @param word word
	 * @param dis distance of word
	 */
	public void addWord(String word, double dis) {
		this.words.add(word);
		this.distance.add(dis);
	}
	/**
	 * Word to cosine distance map
	 * @return Map of word to distance
	 * @see #getWordsList()
	 */
	public Map<String, Double> getWordsDistanceMap() {
		HashMap<String, Double> vector = new HashMap<>();
		for(int i=0;i<words.size();i++) {
			vector.put(words.get(i), distance.get(i));
		}
		return vector;
	}
	/**
	 * List of words.
	 * List will be in decreasing order of distance.
	 * @return List of words 
	 * @see #getWordsDistanceMap()
	 */
	public List<String> getWordsList() {
		return this.words;
	}
	
	@Override
	public String toString(){
		return "word position in vocabulary of " + word + 
				" = " + this.wordPosition + " word vector :" + this.words
				+ " , distance :" + this.distance;
	}
	
}
