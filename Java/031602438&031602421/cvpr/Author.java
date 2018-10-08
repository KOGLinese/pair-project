public class  Author{
    String name;
    String belong;
    public Author(){
    }
    public Author(String name,String belong){
        this.name = name;
        this.belong = belong;
    }
    public String getBelong() {
        return belong;
    }

    public String getName() {
        return name;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public void setName(String name) {
        this.name = name;
    }
}
