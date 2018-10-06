import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;


public class SpiderMain {


    public static void main(String[] args) {
        StringBuilder t = new StringBuilder();
        try {
            Document document = Jsoup.connect("http://openaccess.thecvf.com/CVPR2018.py").get();
            Elements ptitles = document.getElementsByClass("ptitle");
            ArrayList<String> links = new ArrayList<>();
            String link = "http://openaccess.thecvf.com/";
            StringBuilder str = new StringBuilder();  // 用来拼接 url字符串 http://openaccess.thecvf.com/content_cvpr_2018/html/Das_Embodied_Question_Answering_CVPR_2018_paper.html
            for(Element ptitle: ptitles){
                str.append(link);
                str.append(ptitle.getElementsByTag("a").attr("href"));
                links.add(str.toString());
                // 清空StringBuilder
                str.delete(0,str.length());
            }

            int i = 0 ;
            // div id = papertitle 标题； div id = abstract 摘要
            //System.out.println(links.size());
            for(String paper: links){
                Document doc = Jsoup.connect(paper).get();
                String title = doc.select("div#papertitle").first().text();
                String abstr = doc.select("div#abstract").first().text();
                t.append(i);
                t.append("\r\n");
                t.append("Title: ");
                t.append(title);
                t.append("\r\n");
                t.append("Abstract: ");
                t.append(abstr);

                if(i<links.size()-1){
                    t.append("\r\n");
                    t.append("\r\n");
                    t.append("\r\n");
                }
                System.out.println(i);
                //System.out.println("Title:" + title);
                //System.out.println("Abstract:" + abstr);
                //System.out.println("=================================");
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("爬虫结束，开始输出！");
        try{
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("result.txt"));

            bos.write(t.toString().getBytes());
            bos.close();
            System.out.println("输出完毕！");

        }catch (Exception e){
            System.out.println("文本写出失败！");
            System.out.println(e.getMessage());
        }
    }
}
