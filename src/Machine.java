import java.awt.*;
import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.Random;

public class Machine {
    public static int randomMove(ArrayList<Edge>edges){
        Random random=new Random();
        int random1=random.nextInt(edges.size());
        while (!edges.get(random1).isFree()){
            random1=random.nextInt(edges.size());
        }
        return random1;
    }
    public static int smarterMove(ArrayList<Edge>edges,int[][] mat,int scale){
        int [][]matrix=new int[mat.length][mat.length];
        Game Buffergame =new Game(scale) ;
        for (int i=0;i<edges.size();i++){
            for (int k = 0; k < matrix.length; k++) {
                for (int j = 0; j < matrix.length; j++) {
                    matrix[k][j]=mat[k][j];
                }
            }

            Edge edge=edges.get(i);
            if(edge.isFree()) {
                Coordinate coordinate = whichEdge(edge.getBounds(), scale);
                matrix[coordinate.getX()][coordinate.getY()] = 7;
                Buffergame.setMatrix(matrix);
                if (Buffergame.whereIs3().size() == 0) {
                    break;
                }
                if (isWise(Buffergame.whereIs3(),matrix)==0|isWise(Buffergame.whereIs3(),matrix)==2){
                    return i;
                }
            }
        }
        return randomMove(edges);
    }

    public static Coordinate whichEdge(Rectangle rectangle,int scale) {
        Coordinate coordinate = new Coordinate(-1, -1);

        if (rectangle.getWidth() == 150) {
            coordinate = new Coordinate(2 * scale - 1 - (int) rectangle.getY() / 70, (int) rectangle.getX() / 70);
        }
        if (rectangle.getWidth() == 10) {
            coordinate = new Coordinate(2 * scale - 2 - (int) rectangle.getY() / 70, (int) rectangle.getX() / 70 - 1);
        }
        System.out.printf("x=%d y=%d\n", coordinate.getX(), coordinate.getY());

        return coordinate;
    }
    public static int isWise(ArrayList<Coordinate>coordinates,int[][] matrix){
        int []count=new int[coordinates.size()];
        for (int i = 0; i < coordinates.size(); i++) {

            Coordinate coordinate=coordinates.get(i);

            if (matrix[coordinate.getX()-1][coordinate.getY()]==0){
                count[i]++;
            }
            if (matrix[coordinate.getX()+1][coordinate.getY()]==0){
                count[i]++;
            }if (matrix[coordinate.getX()][coordinate.getY()-1]==0){
                count[i]++;
            }if (matrix[coordinate.getX()][coordinate.getY()+1]==0){
                count[i]++;
            }
            if (count[i]==0)return 0;
        }
        int numberOf2=0,numberOf1=0;
        for (int i = 0; i < count.length; i++) {
            if (count[i]==2){
                numberOf2++;
            }
            if (count[i]==1){
                numberOf1++;
            }
        }
        if (numberOf1==0&numberOf2>0){
            return 2;
        }
        if (numberOf1>0){
            return 1;
        }
        return 3;
    }

}
