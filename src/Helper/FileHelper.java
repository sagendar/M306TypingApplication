package Helper;

import Model.Word;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Jethif on 31/10/2017.
 */
public class FileHelper {

    public ArrayList<Word> readFile(){
        ArrayList<Word> words = new ArrayList<>();
        try {
            String line = "";
            String cvsSplitBy = ";";
            boolean first = true;
            try (BufferedReader br = new BufferedReader(new FileReader("files/words.csv"))) {
                while ((line = br.readLine()) != null) {
                    if(first) {
                        first = false;
                        continue;
                    }
                    String[] fileInfo = line.split(cvsSplitBy);

                    //get Information from file like this and save in a variable. Then return or place as class member.
                    Word word = new Word(Integer.parseInt(fileInfo[0]), fileInfo[1], fileInfo[2]);
                    words.add(word);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return words;
    }


    public void writeFile(ArrayList<Word> words) {
        String delimiter = ";";
        try {
            // if you want to only append the new values the filewrite needs the option true instead of true. with false it will rewrite the whole file
            FileWriter writer = new FileWriter("files/words.csv",false);
            // This is an option which would require that you send the whole arraylist of objects which have been already inside and you just added/edited/removed the whole list and now save it again
            for(Word word : words) {
                writer.append(word.getId()+delimiter+word.getWord()+delimiter+word.getLanguage()+delimiter + "\r\n");
            }
            writer.close();


        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
    }
}
