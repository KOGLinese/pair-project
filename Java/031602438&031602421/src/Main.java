import java.io.File;

public class Main{
    public static void main(String[] args) {
        String inputFile = null;
        String outputFile = null;
        int weight = 0;
        int m = 1;
        int n = 10;
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
                        weight=0;
                    }else{
                        weight=1;
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

        Tools tool = new Tools();
        FileRead fileRead = new FileRead();
        fileRead.Input(new File(inputFile));


        int characters = 0;
        int wordCounts = 0;
        //权重为true title：1 abstract：0 权重为false title：0 abstract：0
        for(String title: fileRead.getTitles()) {

            characters += title.length();
            wordCounts += tool.CountforWord(title,weight);
            tool.CountforPhrase(title,m,weight);

        }
        for(String abs: fileRead.getAbstracts()){

            characters += abs.length();
            wordCounts += tool.CountforWord(abs,0);
            tool.CountforPhrase(abs,m,0);

        }

        fileRead.Output(characters,wordCounts,fileRead.getTitles().size()*2,tool.getSortList(),outputFile,n);
    }
}