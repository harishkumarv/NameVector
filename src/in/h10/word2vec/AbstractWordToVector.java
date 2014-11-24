package in.h10.word2vec;

import java.io.File;
import java.io.IOException;
/**
 * Provide method to return word vector of particular word.
 * Read trained file
 * @author kamal
 * @see TrainWordToVector
 *
 */
public abstract class AbstractWordToVector {
	/**
	 * File that is used to generate vector for word
	 */
	protected File trainedFile;
	/**
	 * 
	 * @param trainedFile File that is used to generate vector for word
	 */
	protected AbstractWordToVector(File trainedFile) {
		this.trainedFile = trainedFile;
	}
	
	/**
	 * return with closed words(vector) of any word
	 * @param word word for which we need vector.
	 * @return closed words vector of any word
	 * @throws IOException In case of IO errors
	 */
	public abstract WordVector getWordVector(String word) throws IOException;
	
}
