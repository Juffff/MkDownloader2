import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class urlListParser2 {

    private File file;
    private ArrayList<String> urlList = new ArrayList<>();

    public urlListParser2(File file) {
        this.file = file;
    }

    public ArrayList<String> getUrlArrayList() {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            while (bufferedReader.ready()) {
                sb.append(bufferedReader.readLine());
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] stringArray =  sb.toString().split(Pattern.quote("https"));
        for(int i=1;i<stringArray.length; i++){
            if(!urlList.contains(stringArray[i])){urlList.add("https"+stringArray[i]);}
        }
        return urlList;
    }


}
