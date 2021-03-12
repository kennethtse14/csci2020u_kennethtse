package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.util.HashMap;
import java.util.Map;

public class Controller {

    @FXML
    private Canvas mainCanvas;
    @FXML
    private GraphicsContext gc;

    private static Color[] colors = {Color.ORANGE, Color.BEIGE, Color.AQUA, Color.YELLOW};

    HashMap<String, Integer> map = new HashMap<String, Integer>();

    @FXML
    private void initialize(){
        gc = mainCanvas.getGraphicsContext2D();

        FileLoader loader = new FileLoader("weatherwarnings-2015.csv");
        loader.loadFile();
        map = loader.getMap();

        drawPieChart(800,300,map, colors);
        drawLegend(map,colors);

    }

    // Draw pie chart
    public void drawPieChart(int w, int h, HashMap<String, Integer> map, Color[] colors){

        int i = 0;
        double startAngle = 0;
        double endAngle = 0;
        int value = 0;


        for (Map.Entry<String, Integer> tempmap : map.entrySet()){
            value += tempmap.getValue();
        }

        // Drawing Pie Chart
        for (Map.Entry<String, Integer> tempmap : map.entrySet()){
            endAngle = (tempmap.getValue() /(double)value) * 360;
            gc.setFill(colors[i]);
            gc.fillArc(450, 0, 400,400, startAngle, endAngle, ArcType.ROUND);
            gc.strokeArc(450, 0, 400,400, startAngle, endAngle, ArcType.ROUND);
            startAngle += endAngle ;
            i++;
        }
    }

    // Draw Legend
    public void drawLegend(HashMap<String, Integer> map, Color[] colors){

        int i = 0;
        int startRectY = 50;
        int startTextY = 70;

        // Drawing Legend
        for (Map.Entry<String, Integer> tempmap : map.entrySet()){
            gc.setFill(colors[i]);
            gc.fillRect(50,startRectY,60,30);
            gc.strokeRect(50,startRectY,60,30);;
            gc.strokeText(tempmap.getKey(), 120, startTextY);
            i++;
            startRectY += 50;
            startTextY += 50;
        }

    }
}
