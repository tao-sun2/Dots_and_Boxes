import java.io.*;
import java.util.ArrayList;

public class Storage {
    private int scale;
    private String mode;
    private String password;
    private String fileName;
    private String gameDifficulty;
    public String getMode() {
        return mode;
    }

    public Storage(String password, String fileName) {
        this.password = password;
        this.fileName = String.format("%s.txt",fileName);
    }

    public void setModel(String model) {
        this.mode = mode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }



    public Storage(String fileName,String code,String model,int scale,String gameDifficulty) throws IOException {
        this.scale=scale;
    this.password =code;
    this.mode=model;
    this.fileName = String.format("%s.txt",fileName);
    this.gameDifficulty=gameDifficulty;
    write(password);
    write(Integer.toString(scale));
    write(mode);
    write(gameDifficulty);


    }


    public void write(String information)throws IOException {
        BufferedWriter in = new BufferedWriter(new FileWriter(fileName, true));
        in.write(information);

        in.newLine();
        in.close();
    }


    public static void write(String fileName,int whoDidIt,int whichEdge)throws IOException {
        BufferedWriter in = new BufferedWriter(new FileWriter(fileName, true));
       in.write(whoDidIt+" "+whichEdge);
        in.newLine();
        in.close();
    }
    public static void writeBlank(String fileName)throws IOException {
        BufferedWriter in = new BufferedWriter(new FileWriter(fileName, true));
        in.write("\n");
        in.newLine();
        in.close();
    }

    public static ArrayList<String> read(String fileName)throws IOException{
        BufferedReader out = new BufferedReader(new FileReader(fileName));
        String s = out.readLine();
       ArrayList<String>edges=new ArrayList<>();
        while (s!=null){
            edges.add(s);
            s=out.readLine();
        }
        return edges;

//        while (s != null) {
//            s += out.readLine();
////            edge=s.split(" ");
////            for (int i = 0; i <edge.length ; i++) {
////                int a =Integer.parseInt(edge[i]);
////            }
////            s=out.readLine();
//        }return s;

//        out.close();
    }
}
