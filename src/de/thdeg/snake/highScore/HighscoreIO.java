package de.thdeg.snake.highScore;

import java.io.*;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles the file input and output and refreshes the highscore list
 */
@SuppressWarnings("SpellCheckingInspection")
public class HighscoreIO {

    /**
     * Refreshes the highscore list and saves it to %appdata%\Snake\'difficulty'.snake
     * @param difficulty the difficulty on which the highscore is archieved
     * @param score the score  which is archieved
     * @return returns an display string of the highscore on the current difficulty pretty printed to show in an
     * message dialog
     */
    public String refreshHighscore(String difficulty, int score){

        String path = Paths.get(getAppdataDir(), "Snake").toString();
        String filepath = path + "\\" + difficulty + ".snake";

        createDirectoryIfNotExists(path);

        List<Integer> scores = new ArrayList<>(readFileIfExists(filepath));

        scores.add(score);
        Collections.sort(scores);
        Collections.reverse(scores);

        writeFile(scores, filepath);

        return getPrettyPrintedScoreList(difficulty, scores);
    }

    /**
     * Gets an Appdata equivalent path for non-windows systems
     * @return returns an Appdata equivalent path for non-windows systems
     */
    private String getAppdataDir() {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().contains("windows")) {
            return System.getenv("APPDATA");
        } else {
            return System.getProperty("user.home");
        }
    }

    /**
     * Creates the directory, if it doesn't exist yet
     * @param path Path of the directory which needs to be created (without file name)
     */
    private void createDirectoryIfNotExists(String path) {
        File theDir = new File(path);
        if (!theDir.exists()){
            //noinspection ResultOfMethodCallIgnored
            theDir.mkdirs();
        }
    }

    /**
     * Reads the file, if it exists. Does nothing, if file doesn't exist
     * @param path Path of the file to read
     * @return returns a list of scores read form the file (may be empty)
     */
    private List<Integer> readFileIfExists(String path) {
        List<Integer> ret = new ArrayList<>();
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            ret.addAll(Arrays.stream(bufferedReader.readLine().split(";")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList()));
            bufferedReader.close();
        } catch(Exception ignored){}
        return ret;
    }


    /**
     * Writes the file of the highscore for the current difficulty to %appdata%\Snake\'difficulty'.snake
     * @param scores SORTED list of scores as integer, which will be written in an csv formated file
     * @param path Path of the file %appdata%\Snake\'difficulty'.snake
     */
    private void writeFile(List<Integer> scores, String path) {
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
            bufferedWriter.write(scores.stream().limit(10).map(Object::toString).collect(Collectors.joining(";")));
            bufferedWriter.close();
        }catch(Exception c){
            System.err.println("The highscore file could not be written! "+c.getMessage());
        }
    }

    /**
     * Calculates a pretty printed string for a message box to show in
     * @param difficulty Difficulty chosen by the user
     * @param scores List of scores to be printed
     * @return returns a pretty printed string for a message box to show in
     */
    private String getPrettyPrintedScoreList(String difficulty, List<Integer> scores) {
        StringBuilder ret = new StringBuilder("Highscores for ")
                .append(difficulty).append(":")
                .append(System.lineSeparator());

        List<Integer> subList = scores.stream().limit(10).collect(Collectors.toList());
        for (int i = 0; i < subList.size(); i++) {
            String colonSpaces = i==9?":    ": ":     ";
            ret.append(i+1).append(colonSpaces).append(subList.get(i)).append(System.lineSeparator());
        }
        return ret.toString();
    }
}
