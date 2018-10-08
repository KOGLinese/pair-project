import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AnalyMain {

    public static void main(String[] args){
        ArrayList<Paper> papers = new ArrayList<Paper>(); //论文列表
        StringBuilder str = new StringBuilder();
        String title = null;
        String type = null;
        String authorStr = null;
        try{
            BufferedReader reader = new BufferedReader(new FileReader("E:\\SpringDemo\\pair-project\\Java\\031602438&031602421\\cvpr\\cvpr2018.csv"));
            String line = null;
            reader.readLine(); // 去掉标题行(第一行)
            // 手动拼接字符串，清洗数据
            int count = 0; // 用来标记

            while((line=reader.readLine())!=null){
                count = 0;

                for(int i = 0;i<line.length();i++){
                    if(line.charAt(i)==','){
                        count++;
                        if(count == 1){
                            str.delete(0,str.length());
                        }else if(count == 2){
                            str.delete(0,1); // 删除开头那个逗号
                            type = str.toString();
                            str.delete(0,str.length());
                        }else if(count == 3){
                            str.delete(0,1);
                            title = str.toString().replaceAll("\"","");
                            str.delete(0,str.length());
                        }else{

                        }
                    }
                    str.append(line.charAt(i)); //拼接字符串
                }
                str.delete(0,1);

                authorStr = str.toString().replaceAll("\"","");
                List<Author> authors = new ArrayList();
                String name = null;
                String belong = null;
                str.delete(0,str.length());
                //分割名单，读取人名和归属地
                for(int j=0;j<authorStr.length();j++){
                    if(authorStr.charAt(j)==','){
                        if(str.toString().charAt(0)==' '){
                            str.delete(0,1);
                        }
                        name = str.toString();
                        str.delete(0,str.length());
                    }else if(authorStr.charAt(j)==';'){
                        belong = str.toString();
                        str.delete(0,str.length());
                        Author author = new Author(name,belong);
                        authors.add(author);
                    }else{
                        str.append(authorStr.charAt(j));
                    }
                }
                // 多执行一次
                belong = str.toString();
                str.delete(0, str.length());
                Author author = new Author(name, belong);
                authors.add(author);

                Paper paper = new Paper();
                paper.setTitle(title);
                paper.setAuthors(authors);
                paper.setType(type);
                //System.out.println(type);
                //System.out.println(title);
                //System.out.println(authors);
                papers.add(paper);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        // 开始匹配
            File file = new File("E:\\SpringDemo\\pair-project\\Java\\031602438&031602421\\cvpr\\SpiderResult.txt");
            FileRead fileRead = new FileRead();
            fileRead.Input(file);
            ArrayList<String> titles = fileRead.getTitles();

            for(int i = 0;i<papers.size();i++){
                for(int j = 0;j<titles.size();j++){
                   if(papers.get(i).getTitle().equals(titles.get(j).replaceAll("\n",""))){
                       papers.get(i).setAbstracts(fileRead.getAbstracts().get(j));
                       papers.get(i).setPdf_link(fileRead.getPDFLink().get(j));
                   }
                }
            }
            StringBuilder ans = new StringBuilder();
            int i = 0;
            for(Paper p:papers){
                ans.append(i++);
                ans.append("\n");
                ans.append("Title: ");
                ans.append(p.getTitle());
                ans.append("\n");
                ans.append("Type: ");
                ans.append(p.getType());
                ans.append("\n");
                if(p.getAbstracts()!=null){
                    ans.append("Abstracts: ");
                    ans.append(p.getAbstracts());
                }
                if(p.getAuthors()!=null){
                    ans.append("Authors: ");
                    ans.append(p.getAuthor());
                    ans.append("\n");
                }
                if(p.getPdf_link()!=null){
                    ans.append("PDF_Link: ");
                    ans.append(p.getPdf_link());
                    ans.append("\n");
                }
                System.out.println(ans);

                ans.delete(0,ans.length());

            }
    }
}
