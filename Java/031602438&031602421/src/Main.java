import java.io.File;

public class Main{
    public static void main(String[] args) {
        String inputFile = null;
        String outputFile = null;
        boolean weight = false;
        int m = 0;
        int n = 0;
        for(int i=0;i<args.length;i++){
            switch(args[i]){
                case "-i":
                    inputFile = args[++i];
                    break;
                case "-o":
                    outputFile = args[++i];
                    break;
                case "-w":
                    if(args[++i].equals("0")){
                        weight=false;
                    }else{
                        weight=true;
                    }
                    break;
                case "-m":
                    m = Integer.parseInt(args[++i]);
                    break;
                case "-n":
                    n = Integer.parseInt(args[++i]);
                    break;
            }
        }
        System.out.println("读入的文件是"+inputFile);
        System.out.println("输出的文件是"+outputFile);
        System.out.println("权重为"+weight);
        System.out.println("m="+m);
        System.out.println("n="+n);
//        FileRead fileRead = new FileRead();
//        File file = new File("E:\\SpringDemo\\pair-project\\Java\\031602438&031602421\\result.txt");
//        fileRead.Input(file);
//        for(String title: fileRead.getTitles()){
//            System.out.println(title);
//        }
    }
}