package bujo;

import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import javax.swing.text.Document;

public class EntryFormController {

  @FXML
  private Label label;

  @FXML
  private TextField nameEntry;

  @FXML
  private TextField detailEntry;

  @FXML
  private Button submitButton;

  @FXML
  private DatePicker deadlineEntry;

  @FXML
  private AnchorPane basepane;

  @FXML
  public void handleButtonAction(ActionEvent event) {
    System.out.println("Testing Button 2");
    Main.setPane(0);
  }


  public void createTask(ActionEvent actionEvent) {
    String taskName = nameEntry.getText();
    String taskDetail = detailEntry.getText();
    Date taskDeadline = Date.valueOf(deadlineEntry.getValue());
    Task newTask = new Task(taskName,taskDetail,1,1,taskDeadline);
    boolean insertSuccessful = newTask.insertIntoDB();
    if (insertSuccessful) {
      System.out.println("Insert successful");
    }
    Main.setPane(0);
  }



}

