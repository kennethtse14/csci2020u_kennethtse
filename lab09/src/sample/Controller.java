package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class Controller {

    @FXML
    private Canvas canvas;
    @FXML
    private GraphicsContext gc;

    private ArrayList<String[]> stock1 = new ArrayList<String[]>();
    private ArrayList<String[]> stock2 = new ArrayList<String[]>();
    private ArrayList<Double> stockClosingPrice1 = new ArrayList<Double>();
    private ArrayList<Double> stockClosingPrice2 = new ArrayList<Double>();


    public void downloadStockPrices(String url1, String url2){
        // Google's stock closing price values
        try{
            InputStream file1 = new URL(url1).openStream();
            // read the file one line at a time
            BufferedReader br = new BufferedReader(new InputStreamReader(file1,"UTF-8"));
            String line;
            while((line = br.readLine()) != null){
                String[] columns = line.split(",");
                stock1.add(columns);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        for(int i = 0; i < stock1.size(); i++){
            try{
                // get 4th column (stock closing price column)
                stockClosingPrice1.add(Double.valueOf(stock1.get(i)[4]));
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }

        // Apple's stock closing price values
        try{
            InputStream file2 = new URL(url2).openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(file2,"UTF-8"));
            String line;
            while((line = br.readLine()) != null){
                String[] columns = line.split(",");
                stock2.add(columns);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        for(int j = 0; j < stock2.size(); j++){
            try{
                // get 4th column (stock closing price column)
                stockClosingPrice2.add(Double.valueOf(stock2.get(j)[4]));
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void initialize(){
        gc = canvas.getGraphicsContext2D();
        downloadStockPrices("https://query1.finance.yahoo.com/v7/finance/download/GOOG?period1=1262322000&period2=1451538000&interval=1mo&events=history&includeAdjustedClose=true"
        ,"https://query1.finance.yahoo.com/v7/finance/download/AAPL?period1=1262322000&period2=1451538000&interval=1mo&events=history&includeAdjustedClose=true");
        drawLinePlot();
        gc.setFill(Color.RED);
        plotLine1();
        gc.setFill(Color.BLUE);
        plotLine2();
    }

    // Line for Google Stock
    public void plotLine1() {
        gc.setFill(Color.RED);
        double startX = 0;
        double startY = 925;
        for(int i = 0; i < stockClosingPrice1.size()-1; i++){
            gc.setFill(Color.RED);
            gc.strokeLine(startX,startY - stockClosingPrice1.get(i) + 50,startX + 10, startY - stockClosingPrice1.get(i+1) + 50);
            startX += 10;
        }
    }

    // Line for Apple Stock
    public void plotLine2(){
        gc.setFill(Color.BLUE);
        double startX2 = 0;
        double startY2 = 925;
        for(int j = 0; j < stockClosingPrice2.size()-1; j++){
            gc.setFill(Color.BLUE);
            gc.strokeLine(startX2,startY2 - stockClosingPrice2.get(j) + 50,startX2 + 10, startY2 - stockClosingPrice2.get(j+1) + 50);
            startX2 += 10;
        }
    }

    public void drawLinePlot() {
        // x-axis
        gc.setFill(Color.BLACK);
        gc.strokeLine(0, 10, 0, 975);
        // y-axis
        gc.setFill(Color.BLACK);
        gc.strokeLine(0, 975, 975, 975);

        // Google Stock
        gc.setFill(Color.RED);
        plotLine1();

        // Apple Stock
        gc.setFill(Color.BLUE);
        plotLine2();
    }
}