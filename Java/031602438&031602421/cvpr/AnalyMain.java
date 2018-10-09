import com.google.gson.Gson;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.*;
import java.util.*;

import static java.io.FileDescriptor.out;

public class AnalyMain {


    public static void main(String[] args){
        Analy analy = new Analy();
        //按顺序执行 读csv -> 然后匹配
        analy.readCsv();
        analy.matchCsv();
        ArrayList<Paper> papers = analy.getPapers();
        Gson gson = new Gson();
        List<String> author = new ArrayList<>();
        for(Paper p:papers){
            for(Author a:p.getAuthors()){
                author.add(gson.toJson(a));
            }
        }
        System.out.println(gson.toJson(author));
    }
}

