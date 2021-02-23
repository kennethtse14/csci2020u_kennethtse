package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

    private TableView<StudentRecord> people;

    @FXML
    public void initialize() {
        SIDColumn.setCellValueFactory(new PropertyValueFactory<>("SID"));
        assignmentsColumn.setCellValueFactory(new PropertyValueFactory<>("Assignments"));
        midtermColumn.setCellValueFactory(new PropertyValueFactory<>("Midterm"));
        finalExamColumn.setCellValueFactory(new PropertyValueFactory<>("FinalExam"));
        finalMarkColumn.setCellValueFactory(new PropertyValueFactory<>("FinalMark"));
        letterGradeColumn.setCellValueFactory(new PropertyValueFactory<>("LetterGrade"));
        tableView.setItems(DataSource.getAllMarks());

    }

}
