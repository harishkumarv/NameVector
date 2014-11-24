package in.h10.word2vec;

import java.io.*;

/**
 * Provide implementation of <a href = "https://code.google.com/p/word2vec/">word2vec</a>.
 * needs "word2vec" folder from <a href = "https://code.google.com/p/word2vec/">word2vec</a> inside root directory of project.
 * @author kamal
 *
 */
public class TrainWord2vec extends AbstractTrainWordToVector {
	private Process process;
	private String comm  = "word2vec/./word2vec -train %s -output %s -cbow 0 -size 200 -window 5" +
			" -negative 0 -hs 1 -sample 1e-3 -threads 12 -binary 1";
	/**
	 * Build Word2vec trainer
	 * @param inputFile Input file for training 
	 * @param outputFile Output file
	 */
	public TrainWord2vec(File inputFile, File outputFile) {
		super(inputFile, outputFile); 
		
		System.out.println(comm);
	}
	
	
	/**
	 * Run word2vec process train.
	 * It will kill opened process after training.
	 */
	@Override
	public void train() throws IOException {
        String cmd = String.format(comm, inputFile.getPath(), outputFile.getPath());
        process = Runtime.getRuntime().exec(cmd);

        InputStream in = process.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line = "";
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println("complete");
        in.close();
        process.destroy();
    }
	
	public static void main(String args[]) throws IOException {
		TrainWord2vec  trainWord2vec = new TrainWord2vec(new File("word2vec/text8"), new File("output.out"));
		trainWord2vec.train();
	}
}
