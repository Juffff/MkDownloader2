import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;


public class ConformityParser {

    private File file;
    private HashMap<String, String> map = new HashMap<>();


    public ConformityParser(File file) {
        this.file = file;

    }

    public HashMap<String, String> getMap() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(file));
        while (reader.ready()){
            String s = reader.readLine();
            map.put(s.split("#")[0],s.split("#")[1].replaceAll("/","_"));
        }

        return map;
    }


}
