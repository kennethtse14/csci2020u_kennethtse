package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Controller {
    @FXML
    private Canvas mainCanvas;
    @FXML
    private GraphicsContext gc;

    // Bar Chart
    private static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };

    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };

    private static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };

    private static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };

    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };

    @FXML
    private void initialize(){
        gc = mainCanvas.getGraphicsContext2D();
        drawBarGraph(300,300,avgHousingPricesByYear,Color.RED,avgCommercialPricesByYear,Color.BLUE);
        drawPieChart(800,300,ageGroups,purchasesByAgeGroup,pieColours);
    }

    public void drawBarGraph(int w, int h, double[] data, Color color, double[] data2, Color color2) {

        double xInc = (w/2) / data.length;
        double maxVal = Double.NEGATIVE_INFINITY;
        double minVal = 0;

        for (double val: data2) {
            if (val > maxVal)
                maxVal = val;

            if(val < minVal)
                minVal = val;
        }

        // Plotting the bars of data
        double x = 0;
        for (double val : data){
            gc.setFill(color);
            double height = ((val - minVal) / (maxVal - minVal)) * h;
            gc.fillRect(x,(h - height), xInc, height+10);
            x += xInc + xInc + 5;
        }

        double x2 = xInc;
        for (double val2 : data2){
            gc.setFill(color2);
            double height2 = ((val2 - minVal) / (maxVal - minVal)) * h;
            gc.fillRect(x2,(h - height2), xInc, height2+10);
            x2 += xInc + xInc + 5;
        }

    }

    public void drawPieChart(int w, int h, String[] data, int[] data1, Color[] color){

        int i = 0;
        double startAngle = 0;
        double endAngle = 0;
        int value = 0;


        for (int val1 : data1){
            value += val1;
        }

        // Drawing Pie Chart
        for (int val1 : data1){
            gc.setFill(color[i]);
            endAngle = (val1/(double)value) * 360;
            gc.fillArc(500, 0, 400,400, startAngle, endAngle, ArcType.ROUND);

            startAngle += endAngle ;
            i++;
        }
    }

}
