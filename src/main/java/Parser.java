import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private StringBuilder html;
    private String id;
    private ArrayList<String> imgUrlSrcList = new ArrayList<>();
    private String color;

    public void setHTML(StringBuilder html) {
        this.html = html;
    }

    public void parse() {

        String htmlString = html.toString();

        Document document = Jsoup.parse(htmlString);


        //getID
        try {

            this.id = document.getElementsByClass("pd_sku-code").html().replace("Код товара:","").replaceAll("<!--.*-->","").replaceAll(" ","").replaceAll("\\D", "");

        }catch (IndexOutOfBoundsException e){
            this.id = "noId";
        }

        //getColor

/*

        String s1 = document.html().replaceAll("\n","");
        int colorBeginIndex = s1.indexOf("Цвет:");
        try {
            s1=s1.substring(colorBeginIndex,colorBeginIndex+200).split("-->")[1].split("<")[0].replace(" ","");
        } catch (StringIndexOutOfBoundsException e){
            System.out.println(document.html().replaceAll("\n",""));
            System.out.println("EXCEPTION!");
            s1=null;
        }



   *//*     Pattern p1 = Pattern.compile("<!-- react-text: 109 -->(.*)<!.*data-reactid");
        Matcher m1 = p1.matcher(s1);


        while (m1.find()){
            this.color = m1.group(1).split("<")[0].replace(" ","");
        }*//*
        this.color = s1;
        System.out.println(this.color);*/


        //GetImages
        //QUERY SELECTOR
        Object[] imgUrlClassArray = document.select("img[src$=.jpg]").toArray();


        for (Object o : imgUrlClassArray) {
            String s = o.toString();
            Pattern p = Pattern.compile("src.*?(h.*?)\"");
            Matcher m = p.matcher(s);

            while (m.find()){
                this.imgUrlSrcList.add(m.group(1).replace("93x129","0x0"));
            }
        }

    }

    public String getId() {
        return id;
    }

    public ArrayList<String> getImgUrlSrcList() {
        return imgUrlSrcList;
    }

    public String getColor(){
        return this.color;
    }
}
