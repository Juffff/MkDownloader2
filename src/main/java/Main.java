import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) throws IOException {
        File f = new File("urlList.lst");
        ArrayList<String> urlList = new urlListParser2(f).getUrlArrayList();


        System.out.println("Input path to save photos\n\r");
        String path = new BufferedReader(new InputStreamReader(System.in)).readLine();
        for (int i = 0; i <urlList.size() ; i++) {
            System.out.println(i);
            String url = urlList.get(i);
            Downloader downloader = new Downloader();
            downloader.setPath(path);
            downloader.setUrl(url);
            downloader.download();
        }
    }
}

/*\keytool -import -alias gp -keystore /Library/Java/JavaVirtualMachines/jdk1.8.0_102.jdk/Contents/Home/jre/lib/security/cacerts -file /Users/Juff/Downloads/gp.crt
* You will be asked for password which default is changeit

*/