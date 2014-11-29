package in.h10.main;


import in.h10.restserver.RestServer;
import in.h10.word2vec.Word2vec;

import java.io.File;
import java.io.IOException;

public class Server {
    public static void main(String[] args) throws IOException {
        Word2vec w2v = new Word2vec(new File("word2vec/vectors.bin"));
        RestServer restServer = RestServer.getInstance();
        restServer.start(w2v);
    }
}
