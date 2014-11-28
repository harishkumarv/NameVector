package in.h10.word2vec;

import in.h10.restserver.RestServer;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *Provide implementation of <a href = "https://code.google.com/p/word2vec/">word2vec</a>.
 *Call ./distance and return vector of given word with cosine distance. 
 *needs "truck" folder from word2vec inside root directory of project.
 * @author kamal
 * @see TrainWord2vec
 *
 */
public class Word2vec extends AbstractWordToVector {
	private Process process;
	private String cmd = "word2vec/distance %s";
	private BufferedReader br;
	private PrintStream ps;
	private final int noOfClose = 40;//word2vec return no of close word 
	/**
	 * {@link TrainWord2vec#getOutputFile() Output file} from {@link TrainWord2vec} should be given as trained file.
	 * Or externally trained vector file can be given as input.
	 * @param trainedFile Input File of vector predication
	 * @throws IOException
	 */
	public Word2vec(File trainedFile) throws IOException {
		super(trainedFile);
		this.init();
	}

	private void init() throws IOException {
		cmd = String.format(cmd, "word2vec/vectors.bin");
		process = Runtime.getRuntime().exec(cmd);
		InputStream in = process.getInputStream();
		br = new BufferedReader(new InputStreamReader(in));
		ps = new PrintStream(process.getOutputStream());

	}
	/**
	 * @exception IOException In case of error in reading data from process output Stream.
	 */
	@Override
	public WordVector getWordVector(String word) throws IOException {
		//long time  = System.currentTimeMillis();
		String line = "";
		//System.out.println(br.readLine());
		WordVector wv = new WordVector(word);
		//ps.flush();
		//ps.println();
		//System.out.println(br.readLine());
		ps.flush();
		ps.println(word);
		ps.flush();
		br.readLine();
		try {
			String ss = br.readLine();
			int index = Integer.parseInt(ss.split(": ")[2]);
			br.readLine();
			if( index != -1) {
				int a = index;
				wv.setWordPosition(a);
				br.readLine();
				br.readLine();
				for(int i=0;i< this.noOfClose;i++) {
					line = br.readLine();
					Scanner scanner = new Scanner(line);
					wv.addWord(scanner.next(), scanner.nextDouble());
					scanner.close();

				}
			}
		}catch(NullPointerException  | InputMismatchException  | IOException n ) {
			throw new IOException("Error in reading data" + n);
		}

		//System.out.println("Time taken =" + (System.currentTimeMillis() - time));
		return wv;
	}

	/**
	 * Kill Process
	 * @see {@link #isKilled()}
	 */
	public void kill() {
		process.destroy();
	}

	/**
	 * If process is killed 
	 * @return true if Killed.
	 * @see {@link #kill()}
	 */
	public boolean isKilled() {
		boolean r = true;
		try {
			process.exitValue();
		}catch(IllegalThreadStateException e) {
			r = false;
		}

		return r;
	}


	public static void main(String args[]) throws IOException, InterruptedException {
		Word2vec w2v = new Word2vec(new File("word2vec/vectors.bin"));
		//long t = System.currentTimeMillis();
		System.out.println(w2v.getWordVector("especially"));
		w2v.getWordVector("custom");
		w2v.getWordVector("custom");
		RestServer restServer = RestServer.getInstance();
		restServer.start(w2v);
		//TODO fix word 
		/*for(int i =0 ;i < 2000000;i++) {
			w2v.getWordVector("hello");
		}*/
		/*
		//System.out.println("Complete");
		Thread.sleep(100000);
		//System.out.println("starting again.....");
		for(int i =0 ;i < 2;i++) {
			w2v.getWordVector("hello");
		}
		//System.out.println(w2v.getWordVector("hi"));*/
		//System.out.println((System.currentTimeMillis() - t ));
	}

}
