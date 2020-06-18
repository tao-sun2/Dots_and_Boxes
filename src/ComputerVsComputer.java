import javax.swing.*;
import java.io.IOException;

public class ComputerVsComputer {
    public static void computerVsCom(int scale, String difficulty, String fileName, int[] whoDidIt, int[] whichEdge) throws IOException {
//        int scale = 5;
        DotsBoxes game = new DotsBoxes(600, 600, scale);
        Game gameTest = new Game(scale);
        gameTest.initiatingMatrix(scale);
        game.setFileName(fileName);
        int gameRun = 5;
        if (whoDidIt != null) {
            for (int i = 0; i < whoDidIt.length; i++) {
                gameRun = game.ComputerRecover(gameTest, whoDidIt[i], whichEdge[i]);
                game.paint();
            }
        }
        while (true) {

            try {
                if (difficulty.equalsIgnoreCase("hard")) {
                    gameRun = game.ComputerUpdate(gameTest, game.getWhoDidIt(),
                            Machine.smarterMove(game.getEdges(), gameTest.getMatrix(), scale));
                } else {
                    gameRun = game.ComputerUpdate(gameTest, game.getWhoDidIt(),
                            Machine.randomMove(game.getEdges()));
                }
                game.paint();
                gameTest.paint();
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            if (gameRun == -1) {
                break;
            }

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
