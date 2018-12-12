package bujo;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ToDoTableController implements Initializable {

  @FXML
  public Button clickButton;
  @FXML
  public TableView<Task> todoItemTable;

  @FXML
  private AnchorPane basepane;


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    populateTaskTable();
  }

  public void populateTaskTable() {
    ObservableList<Task> taskList = Task.getTaskList();

    TableColumn taskNameCol = new TableColumn("Task");
    taskNameCol.setMinWidth(60.0);
    taskNameCol.setCellValueFactory(
            new PropertyValueFactory<Task, String>("taskName"));

    TableColumn taskDetailCol = new TableColumn("Task Detail");
    taskDetailCol.setMinWidth(500.0);
    taskDetailCol.setCellValueFactory(
            new PropertyValueFactory<Task, String>("taskDetail"));

//    TableColumn requestEnteredTimestampCol = new TableColumn("Request Submitted On");
//    requestEnteredTimestampCol.setMinWidth(70.0);
//    requestEnteredTimestampCol.setCellValueFactory(
//            new PropertyValueFactory<Request, String>("requestEnteredTimestampReadable"));
//
//    TableColumn roomNumCol = new TableColumn("Room Number");
//    roomNumCol.setMinWidth(60.0);
//    roomNumCol.setCellValueFactory(
//            new PropertyValueFactory<Request, Integer>("requestRoomNum"));
//
//    TableColumn detailsCol = new TableColumn("Details");
//    detailsCol.setMinWidth(360.0);
//    detailsCol.setCellValueFactory(
//            new PropertyValueFactory<Request, String>("requestDetail"));

    todoItemTable.setItems(taskList);
    todoItemTable.getColumns().clear();
    todoItemTable.getColumns().addAll(taskNameCol, taskDetailCol);
  }

  public void handleButtonAction(ActionEvent actionEvent) {
    System.out.println("Testing button 1");
    Main.setPane(1);
//    clickButton.getScene().setRoot();

//            root.getChildren().setAll(FXMLLoader.load("JFXTreeTableViewExample.fxml"));
//    content.getChildren().setAll(FXMLLoader.load("vista2.fxml"));
  }


  public void handleCloseMenuItemAction(ActionEvent actionEvent) {
    System.out.println("Closing App");
//    stage.close();
//    close();
    Platform.exit();
  }

  public void markAsComplete(ActionEvent actionEvent) {
    Task selectedTask;
    selectedTask = todoItemTable.getSelectionModel().getSelectedItem();
    if (selectedTask != null) {
      System.out.println("Delete Task " + selectedTask.getTaskName());
      selectedTask.deleteTask();
      populateTaskTable();
    }
    else {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Error");
      alert.setHeaderText("User not deleted.");
      alert.setContentText("Select a user and then click Delete User button.");
      alert.showAndWait();
    }
  }
}
