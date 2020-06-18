import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner in=new Scanner(System.in);
        System.out.println("Do you want to play a new game,yes or no(play saved game)?");
        String yesOrNo=in.nextLine();
        if(yesOrNo.equalsIgnoreCase("yes")){
        System.out.println("please enter your name");
        String name=in.nextLine();
        System.out.println("please enter your password");
        String password=in.nextLine();
        System.out.println("What scale do you want to play, 3(3*3),4(4*4),5(5*5),6(6*6) or 7(7*7)?");
        int scale=Integer.parseInt(in.nextLine());
        System.out.println("What mode do you want to play, CvC(Computer vs computer),CvP(Computer vs people) or PvP(People vs people)?");
        String mode=in.nextLine();




        if(mode.equalsIgnoreCase("CvP")){
            System.out.println("What difficulty do you want to play, easy or hard?");
            String difficulty=in.nextLine();
            Storage storage=new Storage(name,password,mode,scale,difficulty);
            System.out.println("Do you want to play first, yes or no?");
            String play=in.nextLine();
            int Play=play.equalsIgnoreCase("yes")?-5:5;
            ComputerVsHuman.CvP(scale,difficulty,name,null,null,Play);
        }
        if (mode.equalsIgnoreCase("Cvc")){
            System.out.println("What difficulty do you want to play, easy or hard?");
            String difficulty=in.nextLine();
            Storage storage=new Storage(name,password,mode,scale,difficulty);

            ComputerVsComputer.computerVsCom(scale,difficulty,name,null,null);
        }
        if (mode.equalsIgnoreCase("pvp")){

            Storage storage=new Storage(name,password,mode,scale,"no difficulty");
            PeopleVsPeople.PvP(scale,name,null,null);
        }

        }
       else if(yesOrNo.equalsIgnoreCase("no")) {
            System.out.println("please enter your name");
            String name=in.nextLine();
            System.out.println("please enter your password to enter the game");
            String password=in.nextLine();
            Storage storage=new Storage(password,name);
            ArrayList<String>stringArrayList=storage.read(storage.getFileName());
            int scale=Integer.parseInt(stringArrayList.get(1));
            String difficulty=stringArrayList.get(3);
            ArrayList<String[]>edgeList=new ArrayList<>();
            for (int i = 4; i < stringArrayList.size(); i++) {
                edgeList.add(stringArrayList.get(i).split(" "));
            }
            int[]whoDidit=new int[edgeList.size()];
            int[]whichEdge=new int[edgeList.size()];
            for (int i = 0; i < whoDidit.length; i++) {
                whoDidit[i]=Integer.parseInt(edgeList.get(i)[0]);
            }
            for (int i = 0; i < whichEdge.length; i++) {
                whichEdge[i]=Integer.parseInt(edgeList.get(i)[1]);
            }
            if (password.equals(stringArrayList.get(0))){
                if (stringArrayList.get(2).equalsIgnoreCase("cvp")){
                    ComputerVsHuman.CvP(scale,difficulty,name,whoDidit,whichEdge,0);
                }
                if (stringArrayList.get(2).equalsIgnoreCase("cvc")){
                    ComputerVsComputer.computerVsCom(scale,difficulty,name,whoDidit,whichEdge);
                }
                if (stringArrayList.get(2).equalsIgnoreCase("pvp")){
                    PeopleVsPeople.PvP(scale,name,whoDidit,whichEdge);
                }
            }
            else System.out.println("Password is not correct.");
        }
        else System.out.println("Invalid input");
    }
}
