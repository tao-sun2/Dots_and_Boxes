
import java.awt.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class DotsBoxes {
    private ArrayList<Edge> edges = new ArrayList<>();
    private Edge Button;
    private ArrayList<Dot> dots = new ArrayList<>();
    private ArrayList<Text>texts=new ArrayList<>();
    private Color currentColor = Color.RED;
    private int scale;
    private int whoDidIt = -5;
    private int gameJudge = 0;
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getWhoDidIt() {
        return whoDidIt;
    }

    public void setWhoDidIt(int whoDidIt) {
        this.whoDidIt = whoDidIt;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public DotsBoxes(int canvasWidth, int canvasHeight, int scale) {
        this.scale=scale;

        StdDraw.setCanvasSize(canvasWidth, canvasHeight);
        StdDraw.enableDoubleBuffering();

        StdDraw.setXscale(0, (this.getScale()) * 150);
        StdDraw.setYscale(0, (this.getScale()) * 150);
        initialize();
    }

    public void initialize() {

        for (int i = 75; i <= 75 + (this.scale - 1) * 145; i += 145) {
            for (int j = 70; j <= 70 + (this.scale - 1) * 150; j += 150) {
                if (i < 75 + (this.scale - 1) * 145)
                    edges.add(new Edge(i, j, 150, 10));
                if (i < 75 + (this.scale - 1) * 145)
                    edges.add(new Edge(j, i, 10, 150));
            }
        }
        Button=new Edge(0,0,60,60);
        Button.setColor(Color.YELLOW);
        Button.setVisible(true);

        for (int i = 0; i < this.scale; i++) {
            for (int j = 0; j < this.scale; j++) {
                dots.add(new Dot(75 + 150 * i, 75 + 150 * j, 15));
            }
        }
//        for (int i = 1; i <this.scale ; i++) {
//            Blanks.add(new Edge())
//
//        }
    }

    public int update(Game gameTest, int whoDidIt) throws IOException {
        gameJudge=0;

        Point mousePoint = new Point((int) StdDraw.mouseX(), (int) StdDraw.mouseY());
        boolean isMousePressed = StdDraw.isMousePressed();
        boolean foundEdge = false;
        if (Button.getBounds().contains(mousePoint)){
            if (isMousePressed)undo(gameTest);
        }
        for (int i=0;i<edges.size();i++) {
            if (edges.get(i).isFree()) {
                if (!foundEdge && edges.get(i).getBounds().contains(mousePoint)) {
                    edges.get(i).setColor(currentColor);
                    edges.get(i).setVisible(true);


                    if (isMousePressed) {
                        edges.get(i).setFree(false);
                        Storage.write(String.format("%s.txt",fileName),whoDidIt,i);
                        Rectangle rectangle = edges.get(i).getBounds();

                        gameJudge = gameTest.checkGame(whichEdge(edges.get(i).getBounds()), whoDidIt);
                        if (gameJudge != -1) {
                            setWhoDidIt(gameJudge);
                            if (gameJudge == -5) {
                                currentColor = Color.RED;
                            }
                            if (gameJudge == 5) {
                                currentColor = Color.BLUE;
                            }
                        }


                    }
                    foundEdge = true; // Avoid multiple selections.
                } else {
                    edges.get(i).setVisible(false);
                }
            }
        }
        return gameJudge;
    }

    public int ComputerUpdate(Game gameTest, int whoDidIt,int move) throws IOException {
        edges.get(move).setColor(currentColor);
        edges.get(move).setVisible(true);
        edges.get(move).setFree(false);
        Rectangle rectangle = edges.get(move).getBounds();

                        gameJudge = gameTest.checkGame(whichEdge(rectangle), whoDidIt);
                        if (gameJudge != -1) {
                            setWhoDidIt(gameJudge);
                            if (gameJudge == -5) {
                                currentColor = Color.RED;
                            }
                            if (gameJudge == 5) {
                                currentColor = Color.BLUE;
                            }
                        }


                        Storage.write(String.format("%s.txt",fileName),whoDidIt,move);


        return gameJudge;
    }
    public int ComputerRecover(Game gameTest, int whoDidIt,int move) throws IOException {
        edges.get(move).setColor(currentColor);
        edges.get(move).setVisible(true);
        edges.get(move).setFree(false);
        Rectangle rectangle = edges.get(move).getBounds();

        gameJudge = gameTest.checkGame(whichEdge(rectangle), whoDidIt);
        if (gameJudge != -1) {
            setWhoDidIt(gameJudge);
            if (gameJudge == -5) {
                currentColor = Color.RED;
            }
            if (gameJudge == 5) {
                currentColor = Color.BLUE;
            }
        }
        return gameJudge; }
    public void undo(Game gameTest) throws IOException {
        ArrayList<String>stringArrayList=Storage.read(String.format("%s.txt",fileName));
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
        int lastLine=whoDidit.length-1;
        int lastNumber=whoDidit[lastLine];
        int upperBoundOfLastNumber=3;
        for (int i = lastLine; i >=0; i--) {
            if (whoDidit[i]!=lastNumber){
                upperBoundOfLastNumber=i;
                break;
            }
        }
        int starter=1;
        for (int i=upperBoundOfLastNumber;i>=0;i--){
            if (whoDidit[i]==lastNumber){
                starter=i+1;break;
            }
            else starter=0;
        }

for (int i=starter;i<whoDidit.length;i++){
    deleteEdge(whichEdge[i],gameTest);
//    System.out.printf("you delete %s\n",stringArrayList.get(i+4));
}

    }
    public void deleteEdge(int i,Game gameTest){
edges.get(i).setFree(true);
edges.get(i).setColor(Color.WHITE);
edges.get(i).setVisible(false);
int x=whichEdge(edges.get(i).getBounds()).getX();
int y=whichEdge(edges.get(i).getBounds()).getY();
gameTest.getMatrix()[x][y]=0;
paint();
    }


    public void paint() {
        StdDraw.clear();
        // Paint edges first, so dots will be on the top layer.
        for (Edge edge : edges) {
            edge.paint();
        }
        for (Dot dot : dots) {
            dot.paint();
        }
        for (Text text:texts){
            text.paint();
        }
        Button.paint();
                StdDraw.show(1);
    }


    public  Coordinate whichEdge(Rectangle rectangle) {
        Coordinate coordinate = new Coordinate(-1, -1);

        if (rectangle.getWidth() == 150) {
            coordinate = new Coordinate(2 * scale - 1 - (int) rectangle.getY() / 70, (int) rectangle.getX() / 70);
        }
        if (rectangle.getWidth() == 10) {
            coordinate = new Coordinate(2 * scale - 2 - (int) rectangle.getY() / 70, (int) rectangle.getX() / 70 - 1);
        }


        return coordinate;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }

    public ArrayList<Dot> getDots() {
        return dots;
    }

    public void setDots(ArrayList<Dot> dots) {
        this.dots = dots;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    public int getGameJudge() {
        return gameJudge;
    }
}

