package bujo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class anchorController {

  public MenuItem closeButton;

  public void handleCloseMenuItemAction() {
    System.out.println("Menu Close Button Clicked");
    // do what you have to do
    Platform.exit();
  }

  public void handleButtonAction() {
    System.out.println("Menu Button Clicked");
    if (Main.current == 0) {
      Main.setPane(1);
    }
    else {
      Main.setPane(0);
    }
  }
}
