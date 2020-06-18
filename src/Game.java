

import java.awt.*;
import java.util.ArrayList;

import java.util.Scanner;

public class Game {
    private int[][] matrix;
    private int scale;
    private int yourMark = 0;
    private ArrayList<Text>texts=new ArrayList<>();


    //yourMark is the mark you get.

public Game(int Scale){

    this.scale=Scale;
}
public void paint(){
    for (Text text:texts){
        text.paint();
    }
    Text info=new Text(new Font("Times New Roman",0,30),"UNDO",Color.BLACK, 40, 30);
    info.paint();
    StdDraw.show(70);
}
public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
    }

    public void initiatingMatrix(int scale) {
        int[][] matrix = new int[scale * 2 - 1][scale * 2 - 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        matrix[i][j] = 2;
                    } else matrix[i][j] = 0;
                } else if (j % 2 == 0) {
                    matrix[i][j] = 0;
                } else matrix[i][j] = 3;

            }
        }
        setMatrix(matrix);
    }

    public int checkGame(Coordinate coordinate, int whoDidIt) {
        //computer=5,human=-5,make sure the coordinate is of 0's.
        //return value -1 means that the game is over
        //return value 5 means next chance should be given to computer.
        //return value  -5 means next chance should be given to pla1.
        int returnValue = -whoDidIt;
        ArrayList<Coordinate>coordinatesOf3=whereIs3();
        matrix[coordinate.getX()][coordinate.getY()] = whoDidIt;

        for (int i = 0; i < coordinatesOf3.size(); i++) {
            if (isConquered(coordinatesOf3.get(i))) {

                matrix[coordinatesOf3.get(i).getX()][coordinatesOf3.get(i).getY()] = 2*whoDidIt;
                returnValue = whoDidIt;
                if (whoDidIt == -5) {
                    yourMark++;
                    texts.add(new Text(new Font("Times New Roman",0,150),"A",
                            Color.RED,75+coordinatesOf3.get(i).getY()*75,
                            150*scale-95-coordinatesOf3.get(i).getX()*75));

                }
                else texts.add(new Text(new Font("Times New Roman",0,150),"B",
                        Color.BLUE,75+coordinatesOf3.get(i).getY()*75,
                        150*scale-95-coordinatesOf3.get(i).getX()*75));
                paint();
            }
//            else matrix[coordinatesOf3.get(i).getX()][coordinatesOf3.get(i).getY()] = 3;




        }
        ArrayList<Coordinate>coordinatesOf10=whereIs10();
        for (int i = 0; i < coordinatesOf10.size(); i++) {
            if (!isConquered(coordinatesOf10.get(i))){
                matrix[coordinatesOf10.get(i).getX()][coordinatesOf10.get(i).getY()]=3;
                for (int j = 0; j < texts.size(); j++) {
                    if (texts.get(j).getX()==75+coordinatesOf10.get(i).getY()*75&texts.get(j).getY()==150*scale-95-coordinatesOf10.get(i).getX()*75){
                        texts.remove(j);
                        paint();
                    }
                }


            }
        }
        if (whereIs3().size() == 0) {
            return -1;
        }
        return returnValue;
    }

    public ArrayList<Coordinate> whereIs3() {
        //to find where is 3,if the size is 0,then it's certain that the game is over;
        ArrayList<Coordinate> coordinateArrayList = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 3) coordinateArrayList.add(new Coordinate(i, j));
            }
        }
        return coordinateArrayList;
    }
    public ArrayList<Coordinate> whereIs10() {
        //to find where is 3,if the size is 0,then it's certain that the game is over;
        ArrayList<Coordinate> coordinateArrayList = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j]==-10||matrix[i][j]==10) coordinateArrayList.add(new Coordinate(i, j));
            }
        }
        return coordinateArrayList;
    }

    public boolean isConquered(Coordinate coordinate) {
        //decide whether the line already exists or not.
        if (matrix[coordinate.getX() - 1][coordinate.getY()] != 0 & matrix[coordinate.getX() + 1][coordinate.getY()] != 0
                & matrix[coordinate.getX()][coordinate.getY() - 1] != 0 & matrix[coordinate.getX()][coordinate.getY() + 1] != 0) {
            return true;
        }
        return false;
    }


    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public int getScale() {
        return scale;
    }
    public int getYourMark() {
        return yourMark;
    }
    public void setYourMark(int yourMark) {
        this.yourMark = yourMark;
    }
}
