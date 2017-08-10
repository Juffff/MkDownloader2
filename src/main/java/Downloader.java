import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;


public class Downloader {
    private String url;
    private File path;
    private StringBuilder sb = new StringBuilder();
    private HashMap<String, String> conformityMap = new HashMap<>();
    private File colorConformityFile;


    public void setUrl(String url) {
        this.url = url;
    }

    public void setPath(String path) {
        this.path = new File(path);


    }

    public void download() throws IOException {
       /* colorConformityFile = new File(path + "/colorConformity.txt");


        if (!colorConformityFile.exists()) {

            colorConformityFile.createNewFile();

        }*/

        File dir = path;
        if (!dir.exists()) {
            dir.mkdir();
        }


        File conformityFile = new File("conformity.lst");
        ConformityParser conformityParser = new ConformityParser(conformityFile);
        conformityMap = conformityParser.getMap();


        //Get product ID
        String productID = url;
        productID = productID.replace("https://modnakasta.ua/product/", "").split("/")[0];
        System.out.println(productID);


        //Connect
        URL link = new URL(url);
        URLConnection connection = link.openConnection();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            //Read
            while (reader.ready()) {
                String s = reader.readLine();
                sb.append(s);
            }

            //Parse
            Parser parser = new Parser();
            parser.setHTML(sb);


            parser.parse();

/*
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(colorConformityFile, true));
        bufferedWriter.write(conformityMap.get(productID) + "#" + parser.getColor());
        bufferedWriter.newLine();
        bufferedWriter.close();*/


            //String id = parser.getId();
            ArrayList<String> urlList = parser.getImgUrlSrcList();

            //Download


            for (int i = 0; i < urlList.size(); i++) {
                URL downloadLink = new URL(urlList.get(i));
                System.out.println(downloadLink);
                URLConnection downloadConnection = downloadLink.openConnection();
                String filename = "";

                if (conformityMap.containsKey(productID)) {
                    filename = path + File.separator + conformityMap.get(productID) + "#" + (i + 1) + ".jpg";
                } else {
                    filename = path + File.separator + productID + "#" + (i + 1) + ".jpg";
                }


                File f = new File(filename);
                try {

                    f.createNewFile();
                } catch (IOException x) {
                    x.printStackTrace();
                }

                try {
                    BufferedInputStream bis = new BufferedInputStream(downloadConnection.getInputStream());
                    FileOutputStream fos = new FileOutputStream(f);
                    int ch;
                    while ((ch = bis.read()) != -1) {
                        fos.write(ch);
                    }
                    bis.close();
                    fos.flush();
                    fos.close();
                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
