//package bujo;
//
package bujo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Wyatt Byroade
 */
public class Main extends Application {

  public Scene scene;
  static AnchorPane root;
  static List<AnchorPane> grid = new ArrayList<AnchorPane>();
  static List<String> fxmlFiles = new ArrayList<String>();
  public static int current = 0;

  @Override
  public void start(Stage stage) throws Exception {
    root = FXMLLoader.load(Main.class.getResource("anchor.fxml"));

    fxmlFiles.add("ToDoTable.fxml");
    fxmlFiles.add("EntryForm.fxml");
    grid.add(FXMLLoader.load(Main.class.getResource(fxmlFiles.get(0))));

    root.getChildren().add(grid.get(0));

    Scene scene = new Scene(root, 800,600);
    stage.setScene(scene);
    stage.setResizable(false);
    stage.show();

  }


  public static AnchorPane getPane(int current){
    return grid.get(current);
  }

  public static void setPane(int newCurrent){
    try {
      root.getChildren().remove((grid.get(0)));
      grid.remove(0);
      System.out.println("grid size: " + Integer.toString(grid.size()));
      grid.add(FXMLLoader.load(Main.class.getResource(fxmlFiles.get(newCurrent))));
      System.out.println("grid size: " + Integer.toString(grid.size()));
      root.getChildren().add(grid.get(0));
      current = newCurrent;
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }



  /**
   * @param args the command line argument
   */
  public static void main(String[] args) {
    launch(args);
  }

}
