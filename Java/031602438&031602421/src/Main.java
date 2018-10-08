import java.io.File;

public class Main{
    public static void main(String[] args) {
        FileRead fileRead = new FileRead();
        File file = new File("E:\\SpringDemo\\pair-project\\Java\\031602438&031602421\\result.txt");
        fileRead.Input(file);
        for(String title: fileRead.getTitles()){
            System.out.println(title);
        }
    }
}