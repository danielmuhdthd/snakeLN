package de.thdeg.snake.highScore;

import java.io.*;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("SpellCheckingInspection")
public class HighscoreIO {

    public String refreshHighscore(String difficulty, int score){
        List<Integer> scores = new ArrayList<>();

        String path = Paths.get(System.getenv("APPDATA"), "Snake").toString();
        String filepath = path + "\\" + difficulty + ".snake";

        createDirectoryIfNotExists(path);

        readFileIfExists(scores, filepath);

        scores.add(score);
        Collections.sort(scores);
        Collections.reverse(scores);

        writeFile(scores, filepath);

        StringBuilder ret = new StringBuilder("Highscores for ")
                .append(difficulty).append(":")
                .append(System.lineSeparator());

        List<Integer> subList = scores.stream().limit(10).collect(Collectors.toList());
        for (int i = 0; i < subList.size(); i++) {
            ret.append(i+1).append(":\t").append(subList.get(i)).append(System.lineSeparator());
        }
        return ret.toString();
    }

    private void writeFile(List<Integer> scores, String path) {
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
            bufferedWriter.write(scores.stream().limit(10).map(Object::toString).collect(Collectors.joining(";")));
            bufferedWriter.close();
        }catch(Exception c){
            System.err.println("The highscore file could not be written! "+c.getMessage());
        }
    }

    private void readFileIfExists(List<Integer> scores, String path) {
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            scores.addAll(Arrays.stream(bufferedReader.readLine().split(";")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList()));
            bufferedReader.close();
        } catch(Exception ignored){}
    }

    private void createDirectoryIfNotExists(String path) {
        File theDir = new File(path);
        if (!theDir.exists()){
            //noinspection ResultOfMethodCallIgnored
            theDir.mkdirs();
        }
    }
}
