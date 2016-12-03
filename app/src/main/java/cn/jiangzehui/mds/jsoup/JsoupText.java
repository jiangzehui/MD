package cn.jiangzehui.mds.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import cn.jiangzehui.mds.model.Gif;
import cn.jiangzehui.mds.model.Ip;
import cn.jiangzehui.mds.model.Video;
import cn.jiangzehui.mds.util.JsoupUtil;

/**
 * Created by jiangzehui on 16/11/9.
 */
public class JsoupText {

    public static String url = "http://www.zbjuran.com/dongtai/";
    public static String urls = "http://www.zbjuran.com/gaoxiaoshipin/";


    public static void main(String[] str) throws IOException {

        Document doc = Jsoup.parse(new URL(urls), 5000);
        //获取页数
        Elements es_page = doc.getElementsByClass("page").first().getElementsByTag("select").first().getElementsByTag("option");

        for (int i = 0; i < es_page.size(); i++) {
            Element et = es_page.get(i);
            if (et != null) {
                System.out.println(et.attr("value"));
            }

        }
//
//
//        //Video
//        ArrayList<Video> list = new ArrayList<>();
//        // Document doc = Jsoup.parse(new URL(url), 5000);
//        Elements es_item = doc.getElementsByClass("item");
//        for (int i = 0; i < es_item.size(); i++) {
//            Element et = es_item.get(i).getElementsByTag("h3").first();
//            if (et != null) {
//                String title = et.getElementsByTag("b").text();
//                String img = es_item.get(i).select("img").first().attr("src");
//                String url = es_item.get(i).getElementsByClass("read").first().attr("href");
//                Document docs = Jsoup.parse(new URL(Ip.url + url), 5000);
//                String urls = docs.getElementsByTag("iframe").attr("src");
//                list.add(new Video(title, img, urls));
//
//            }
//
//        }
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i).toString());
//        }


        //GIF
//        ArrayList<Gif> list = new ArrayList<>();
//        // Document doc = Jsoup.parse(new URL(url), 5000);
//        Elements es_item = doc.getElementsByClass("item");
//        for (int i = 0; i < es_item.size(); i++) {
//            Element et = es_item.get(i).getElementsByTag("h3").first();
//            if (et != null) {
//                String title = et.getElementsByTag("b").text();
//                String img = es_item.get(i).select("img").first().attr("src");
//                String url = es_item.get(i).getElementsByClass("read").first().attr("href");
//                list.add(new Gif(title, url));
//
//            }
//
//        }
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i).toString());
//        }


    }


//    Document doc = Jsoup.parse(url, 5000);
//
//    Elements es_cnames = doc.getElementsByClass("area");
//    Element e1 = es_cnames.get(8);
//    Elements e11=e1.getElementsByClass("cname");
//    Elements e12=e1.getElementsByClass("weather");
//    Elements e13=e1.getElementsByClass("temp");
//    for (int i = 0; i <e11.size(); i++) {
//        Element e_name = e11.get(i);
//        String cname = e_name.child(0).text();
//        String weather = e12.get(i).text();
//        String temp = e13.get(i).text();
//        System.out.println(cname+"\t\t"+weather+"\t\t"+temp);
//    }
}
