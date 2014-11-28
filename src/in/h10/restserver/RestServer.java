package in.h10.restserver;
import in.h10.word2vec.Word2vec;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static spark.Spark.*;
import com.google.gson.Gson;

/**
 * Created by harish on 28/11/14.
 * A singleton class.
 * Its a simple http server.
 */
public class RestServer {
    private static RestServer ref = new RestServer();

    public static RestServer getInstance() {
        return ref;
    }

    private RestServer() {
    }

    public void start(Word2vec w2v) {
        get("/names", (req, res) -> {
            String word = req.queryParams("q"), response;
            List <String> wordList;
            Iterator <String> iterator;
            JsonResponse jsonResponse = new JsonResponse();
            Gson gson = new Gson();

            if (word != null) {
                jsonResponse.q = word;
                try {
                    int i=0;
                    wordList = w2v.getWordVector(word).getWordsList();
                    jsonResponse.words = new String[wordList.size()];
                    iterator = wordList.iterator();

                    while (iterator.hasNext()) {
                        jsonResponse.words[i++] = iterator.next();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                jsonResponse.error = "Specify the q parameter";
            }

            response = gson.toJson(jsonResponse);
            return response;
        });
    }
}
