package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import java.util.Random;

public class Controller {
    private Pane rootPane;
    private Random rand = new Random();

    public Controller(Pane rootPane){
        this.rootPane = rootPane;
    }

    public void setRootPane(){
        ImageView imgView = (ImageView)rootPane.getChildren().get(0);
        Canvas canvas = (Canvas)rootPane.lookup("#setCanves");
        double width = imgView.getFitWidth();
        double height = imgView.getFitWidth();
        canvas.setWidth(width);
        canvas.setHeight(height);

        setCanvesContext(canvas,width,height);
        setCanvesClicked(canvas);

//        TextField textField = new TextField("aaaa");
//        rootPane.getChildren().add(textField);
        Button check = (Button)rootPane.lookup("#checkButton");
        setCheckButton(check);
    }

    private void setCanvesContext(Canvas canvas, double width, double height){
        double x = 0.42 * width - width / 36.0;
        double y = 0.285 * height;

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(height * 0.01);
        gc.strokeArc(x, y,
                width / 18.0, height / 40.0,
                180, 180, ArcType.OPEN);
        x += width / 18.0;
        gc.strokeArc(x, y,
                width / 18.0, height / 40.0,
                180, 180, ArcType.OPEN);
    }

    private void setCanvesClicked(Canvas canvas){
        canvas.setOnMouseClicked((e)->{
//            ((TextField)rootPane.getChildren().get(2)).setText(String.valueOf(rand.nextInt(256)));
            canvas.getGraphicsContext2D().setStroke(getARandomColor());
            System.out.println(canvas.getGraphicsContext2D().getStroke());
        });
    }

    private Color getARandomColor(){
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        Color col = Color.rgb(r, g, b);
        return col;
    }

    private void setCheckButton(Button checkButton){
        checkButton.setRotate(30);
    }
}
