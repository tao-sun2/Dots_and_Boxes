import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ComputerVsHuman {
    public static void CvP(int scale,String difficulty,String fileName,int[]whoDidIt,int[]whichEdge,int Play) throws IOException {

        DotsBoxes game = new DotsBoxes(600, 600, scale);
        Game gameTest = new Game(scale);
        gameTest.initiatingMatrix(scale);
        game.setFileName(fileName);
//        Storage storage=new Storage()
        int gameRun = Play;
        game.setCurrentColor(Play==5? Color.BLUE:Color.RED);
        game.setWhoDidIt(Play);
        game.paint();
       if(whoDidIt!=null){
           for (int i = 0; i < whoDidIt.length; i++) {
            try {
                gameRun=game.ComputerRecover(gameTest,whoDidIt[i],whichEdge[i]);
                Thread.sleep(2);
                game.paint();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
       }
       while (true) {
           while (gameRun==0|gameRun==-5){
                gameRun=game.update(gameTest, -5);
                game.paint();
                gameTest.paint();
            }
            if(gameRun==5)  {
              if(difficulty.equalsIgnoreCase("easy"))  gameRun=game.ComputerUpdate(gameTest,5,Machine.randomMove(game.getEdges()));
                else gameRun=game.ComputerUpdate(gameTest,5,Machine.smarterMove(game.getEdges(),gameTest.getMatrix(),scale));
            game.paint();
            gameTest.paint();
            }
            try
            {Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            if (gameRun == -1) {
                break;
            }
            game.paint();
            gameTest.paint();
        }

        if (gameTest.getYourMark() > Math.pow(scale-1,2) / 2) {
            JOptionPane.showMessageDialog(null, String.format("Red:Blue=%d : %d\nRed wins!",gameTest.getYourMark(),(int)Math.pow(scale-1,2)-gameTest.getYourMark()), "Game Result", JOptionPane.INFORMATION_MESSAGE);
        } else if (gameTest.getYourMark() == Math.pow(scale-1,2)/ 2) {
            JOptionPane.showMessageDialog(null, String.format("Red:Blue=%d : %d\nIt's a draw.",gameTest.getYourMark(),(int)Math.pow(scale-1,2)-gameTest.getYourMark()), "Game Result", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, String.format("Red:Blue=%d : %d\nBlue wins!",gameTest.getYourMark(),(int)Math.pow(scale-1,2)-gameTest.getYourMark()), "Game Result", JOptionPane.INFORMATION_MESSAGE);
        }
        System.out.println(gameTest.whereIs3().size());
        System.out.println(gameTest.getYourMark());
    }
}
