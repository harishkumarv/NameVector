package in.h10.word2vec;

import java.io.File;
import java.io.IOException;

/**
 * Provide abstract interface to train word to vector.
 * {@link #train} will do the training and store 
 * output data into {@link #getOutputFile() output file}
 * @author kamal
 *
 */
public abstract class AbstractTrainWordToVector {
	/**
	 * input file used for training
	 */
	protected File inputFile;
	/**
	 * Output file where result will be stored
	 */
	protected File outputFile;
	/**
	 *
	 * @param inputFile input file for training.
	 * @param outputFile output file where training results will be stored
	 */
	protected AbstractTrainWordToVector(File inputFile, File outputFile) {
		this.inputFile = inputFile;
		this.outputFile = outputFile;
	}
	/**
	 * Training file
	 * @return file that is used for training.
	 */
	public File getInputFile(){
		return this.inputFile;
	}
	/**
	 * Output File
	 * @return output file where training results will be stored
	 */
	public File getOutputFile(){
		return this.outputFile;
	}
	/**
	 * Input File for training.
	 * @param file Input File
	 */
	public void setInputFile(File file) {
		this.inputFile  = file;
	}
	
	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}
	
	
	/**
	 * train based on {@link #getInputFile() input file}  
	 * and store the result in {@link #getOutputFile() output file}
	 *@throws IOException In case of any IO Error
	 */
	public abstract void train() throws IOException;
	
	
}
