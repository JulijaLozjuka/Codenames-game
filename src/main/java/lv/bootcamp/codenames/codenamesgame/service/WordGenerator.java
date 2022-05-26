package lv.bootcamp.codenames.codenamesgame.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WordGenerator {

    public List<String> generateWords(){
        List<String> listOfWords = new ArrayList<>();
        List<String> listOf25Words = new ArrayList<>();
        try (
                BufferedReader bufReader = new BufferedReader(new
                        FileReader("GameWords.txt"))) {
            String line = bufReader.readLine();
            while (line != null) {
                listOfWords.add(line);
                line = bufReader.readLine();
            }
            bufReader.close();
            for (int i = 0; i < 25; i++) {
                int index = (int) (Math.random() * listOfWords.size());
                listOf25Words.add(listOfWords.get(index));
                listOfWords.remove(index);

            }
        }
        catch (
                IOException e) {
            System.out.println("List of words not found");
        }

        return listOf25Words;
    }

}
