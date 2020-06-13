import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.Arrays;

public class GameGUI extends GameLogic{
    private VBox root;
    VBox vbControlls;
    private GraphicsContext gcCanvas;
    private GraphicsContext gcGuess;
    protected Button[] btnCtrl;
    protected Button btnCheck;

    private int numTry = 0;

    private static Colors[] color = Colors.arrColors;

    private Colors[] row;
    private int x = 10;
    private int y = 10;
    private int i =0;

    private void addRow(int r, int w){
        int i = 10;
        for (Colors clr : row) {
            gcCanvas.setFill(Color.valueOf(clr.toString()));
            gcCanvas.fillRect(i, y, 20, 20);
            i += 30;
        }
        i = 150;
        gcCanvas.setFill(Color.RED);
        for (int j = 0; j < r; j++) {
            gcCanvas.fillOval(i, y, 20, 20);
            i+=30;
        }

        gcCanvas.setFill(Color.YELLOW);
        for (int j = 0; j < w; j++) {
            gcCanvas.fillOval(i, y, 20, 20);
            i+=30;
        }

        y += 30;
    }

    void click(int index){
        if(row == null)
            row = new Colors[4];

        gcGuess.setFill(Color.valueOf(color[index].toString()));
        gcGuess.fillRect(this.x, 10, 20, 20);
        row[i] = color[index];

        this.x += 30;
        i++;

        if (i == 4){
            x =10;
            i = 0;}

    }

    private void makeControls(){
        btnCtrl = new Button[6];
        for (int i = 0; i<btnCtrl.length; i++){
            btnCtrl[i] = new Button();
            btnCtrl[i].setPrefSize(50, 50);
            btnCtrl[i].setStyle("-fx-background-color: " + color[i]);
            final int index = i;
            btnCtrl[i].setOnAction(e->{
                click(index);
                    }
            );
        }

        VBox vbControl = new VBox(10);
        HBox hb1 = new HBox(10);
        hb1.getChildren().addAll(btnCtrl[0], btnCtrl[1]);
        HBox hb2 = new HBox(10);
        hb2.getChildren().addAll(btnCtrl[2], btnCtrl[3]);
        HBox hb3 = new HBox(10);
        hb3.getChildren().addAll(btnCtrl[4], btnCtrl[5]);
        vbControl.getChildren().addAll(hb1, hb2, hb3);

        hb1.setAlignment(Pos.CENTER);
        hb2.setAlignment(Pos.CENTER);
        hb3.setAlignment(Pos.CENTER);


        Canvas cnvGuess = new Canvas(130, 40);
        vbControl.getChildren().addAll(cnvGuess);
        GraphicsContext gc = cnvGuess.getGraphicsContext2D();
        this.gcGuess= gc;
        gcGuess.setFill(Color.LIGHTSALMON);
        gc.fillRect(0,0, cnvGuess.getWidth(), cnvGuess.getHeight());
        int x = 10;
        int y = 10;
        for (int i = 0; i < 4; i++) {
            gc.clearRect(x,y,20,20);
            x += 30;
        }

        btnCheck = new Button("CHECK");
        btnCheck.setStyle("-fx-background-color: LIGHTSALMON");
        btnCheck.setPrefWidth(130);
        btnCheck.setAlignment(Pos.CENTER);
        btnCheck.setOnAction(e->{
            if(row == null || row[3] == null)
                return;

            int a = 10;
            for (int i = 0; i < 4; i++) {
                gcGuess.clearRect(a,10,20,20);
                a += 30;
            }

            int[] ret = {0, 0}; //right space, wrong space
            numTry++;
            if(super.CheckCombintion(row, ret) || numTry == 10){
                endGame();
            }

            addRow(ret[0], ret[1]);

            for (int i=0; i<4; i++)
                row[i] = null;
        });
        vbControl.getChildren().addAll(btnCheck);
        this.vbControlls = vbControl;
    }

    private void makeRows(Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        this.gcCanvas = gc;

        gc.setFill(Color.LIGHTSALMON);
        int y = 10;
        for (int j = 0; j < 10; j++) {
            int x = 10;
            for (int i = 0; i < 4; i++) {
                gc.fillRect(x, y, 20, 20);
                x += 30;
            }

            x = 150;
            for (int i = 0; i < 4; i++) {
                gc.fillRect(x, y, 20, 20);
                x += 30;
            }

            y += 30;
        }
    }

    private void makeGui(){
        makeControls();
        Canvas canvas = new Canvas(300, 400);
        makeRows(canvas);

        HBox hb = new HBox(10);
        hb.getChildren().addAll(canvas, vbControlls);

        this.root.getChildren().addAll(hb);
    }

    public GameGUI(){
        super();
        this.root = new VBox(10);
        makeGui();

        Button btnEnd = new Button("End");
        this.root.getChildren().addAll(btnEnd);
        btnEnd.setStyle("-fx-background-color: RED");
    }

    public VBox getRoot() {
        return root;
    }

    private void endGame(){
        btnCheck.setDisable(true);
    }
}
