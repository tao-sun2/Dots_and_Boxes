

import javax.swing.*;
import java.io.IOException;

public class PeopleVsPeople {
    public static void PvP(int scale,String fileName,int[]whoDidIt,int[]whichEdge) throws IOException {
        DotsBoxes game = new DotsBoxes(600, 600, scale);
        Game gameTest = new Game(game.getScale());
        gameTest.initiatingMatrix(scale);
        game.setFileName(fileName);
        int gameRun =1;
        if(whoDidIt!=null){
            for (int i = 0; i < whoDidIt.length; i++) {
                gameRun=game.ComputerRecover(gameTest,whoDidIt[i],whichEdge[i]);
                game.paint();
            }
        }
        while (true) {
            gameRun = game.update(gameTest, game.getWhoDidIt());
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
