package cn.jiangzehui.mds.util;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import cn.jiangzehui.mds.model.Gif;
import cn.jiangzehui.mds.model.Ip;
import cn.jiangzehui.mds.model.Video;

/**
 * Created by jiangzehui on 16/11/9.
 */
public class JsoupUtil {

    public static ArrayList<String> list_dongtai = new ArrayList<>();
    public static ArrayList<String> list_xiegif = new ArrayList<>();
    public static ArrayList<String> list_gaoxiao = new ArrayList<>();


    public static int getListSize(int type) {
        switch (type) {
            case 0:
                return list_dongtai.size();

            case 1:
                return list_xiegif.size();
            case 2:
                return list_gaoxiao.size();

            default:
                return 0;

        }
    }

    public static String getListItem(int position, int type) {
        switch (type) {
            case 0:
                return list_dongtai.get(position);

            case 1:
                return list_xiegif.get(position);
            case 2:
                return list_gaoxiao.get(position);
            default:
                return "";

        }
    }

    /**
     * 抓取网页动态图
     *
     * @param urls
     * @param type
     * @return
     */
    public static ArrayList<Gif> getGif(String urls, int type) {


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
                    Log.i("jsoup", title + "\t\t" + url + "\n");
                    list.add(new Gif(title, url));

                }

            }
            Elements es_page = doc.getElementsByClass("page").first().getElementsByTag("select").first().getElementsByTag("option");
            for (int i = 0; i < es_page.size(); i++) {
                Element et = es_page.get(i);
                if (et != null) {
                    switch (type) {
                        case 0:
                            list_dongtai.add(et.attr("value"));
                            break;
                        case 1:
                            list_xiegif.add(et.attr("value"));
                            break;
                        case 2:
                            list_gaoxiao.add(et.attr("value"));
                            break;
                    }


                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


}
