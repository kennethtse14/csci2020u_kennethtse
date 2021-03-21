package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Controller {

    @FXML
    private TableView tableView;
    @FXML
    private TableColumn SIDColumn;
    @FXML
    private TableColumn assignmentsColumn;
    @FXML
    private TableColumn midtermColumn;
    @FXML
    private TableColumn finalExamColumn;
    @FXML
    private TableColumn finalMarkColumn;
    @FXML
    private TableColumn letterGradeColumn;

    @FXML
    private TextField SID;
    @FXML
    private TextField Midterm;
    @FXML
    private TextField Assignments;
    @FXML
    private TextField finalExam;

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem menuItem;


    private ObservableList<StudentRecord> data = DataSource.getAllMarks();

    private TableView<StudentRecord> people;

    private File currentFilename = new File("open.csv");

    @FXML
    public void initialize() {
        SIDColumn.setCellValueFactory(new PropertyValueFactory<>("SID"));
        assignmentsColumn.setCellValueFactory(new PropertyValueFactory<>("Assignments"));
        midtermColumn.setCellValueFactory(new PropertyValueFactory<>("Midterm"));
        finalExamColumn.setCellValueFactory(new PropertyValueFactory<>("FinalExam"));
        finalMarkColumn.setCellValueFactory(new PropertyValueFactory<>("FinalMark"));
        letterGradeColumn.setCellValueFactory(new PropertyValueFactory<>("LetterGrade"));
        tableView.setItems(data);

    }

    @FXML
    public void clear(){
        data.clear();
    }

    @FXML
    public void exit(){
        System.exit(0);
    }

    @FXML
    public void save() throws IOException {

        StudentRecord data;
        List<List<String>> list = new ArrayList<>();
        for (int i =0; i <tableView.getItems().size(); ++i){
            data = (StudentRecord) tableView.getItems().get(i);
            list.add(new ArrayList<>());
            list.get(i).add(data.getSID());
            list.get(i).add(String.valueOf(data.getAssignments()));
            list.get(i).add(String.valueOf(data.getMidterm()));
            list.get(i).add(String.valueOf(data.getFinalExam()));
        }

        StringBuffer stringBuffer = new StringBuffer();
        for (int i =0; i <list.size(); i ++){
            for (int j = 0; j < list.get(i).size(); j++){
                stringBuffer.append(list.get(i).get(j));
                stringBuffer.append(",");
            }
            stringBuffer.append("\n");
        }
        try(FileWriter fileWriter = new FileWriter(currentFilename)){
            fileWriter.append(stringBuffer.toString());
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void load(){
        currentFilename = new File("open.csv");
        BufferedReader bufferedReader;
        String line;
        try{
            bufferedReader = new BufferedReader(new FileReader(currentFilename));
            while((line = bufferedReader.readLine()) != null){
                String[] columns = line.split(",");
                StudentRecord loadData = new StudentRecord(columns[0],Float.parseFloat(columns[1]),Float.parseFloat(columns[2]),
                        Float.parseFloat(columns[3]));
                data.add(loadData);
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void saveAs() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        currentFilename = fileChooser.showSaveDialog(null);
        save();

    }


    @FXML
    public void add(){
        data.add(new StudentRecord(SID.getText(),Float.parseFloat(Assignments.getText()),Float.parseFloat(Midterm.getText())
                ,Float.parseFloat(finalExam.getText())));
    }


}