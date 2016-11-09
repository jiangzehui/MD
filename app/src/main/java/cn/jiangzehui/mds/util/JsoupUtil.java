package cn.jiangzehui.mds.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import cn.jiangzehui.mds.model.Gif;

/**
 * Created by quxianglin on 16/11/9.
 */
public class JsoupUtil {

    public static ArrayList<String> lists = new ArrayList<>();


    public static ArrayList<Gif> getGif(String urls) {
        ArrayList<Gif> list = new ArrayList<>();
        Document doc = null;
        try {
            doc = Jsoup.parse(new URL(urls), 5000);
            Elements es_item = doc.getElementsByClass("item");
            for (int i = 0; i < es_item.size(); i++) {
                Element et = es_item.get(i).getElementsByTag("h3").first();
                if (et != null) {
                    String title = et.getElementsByTag("b").text();
                    String url = es_item.get(i).select("img").first().attr("src");
                    list.add(new Gif(title, url));

                }

            }


            Elements es_page = doc.getElementsByClass("page").first().getElementsByTag("select").first().getElementsByTag("option");
            for (int i = 0; i < es_page.size(); i++) {
                Element et = es_page.get(i);
                if (et != null) {
                    lists.add(et.attr("value"));

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
