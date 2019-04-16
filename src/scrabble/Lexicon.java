package scrabble;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Lexicon {
    private Set<String> allWords;

    public Lexicon() {
        allWords = new HashSet<>();
        try(BufferedReader reader =
                    new BufferedReader(new FileReader("data/scrabble.txt"))) {
            String word;
            while((word = reader.readLine()) != null) {
                allWords.add(word);
            }

        } catch(IOException ioe) {
            // squash
        }
    }

    public boolean contains(String word) {
        return allWords.contains(word.toUpperCase());
    }
}
