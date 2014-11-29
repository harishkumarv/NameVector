NameVector
==========
A name finder using machine learning techniques

#Links
[word2vec](https://code.google.com/p/word2vec/)
[spark](http://sparkjava.com/documentation.html)

#Dependencies
* jdk-8
* Gson
* spark

#Installation

```bash
cd word2vec
make
chmod +x *.sh
```

#Word2vec
##Training
```bash
word2vec/./word2vec -train <<input_file>> -output <<output_file>> -cbow 0 -size 200 -window 5" +
  			" -negative 0 -hs 1 -sample 1e-3 -threads 12 -binary 1
```
##Distance
```bash
distance <<Trained_model_file>>
```