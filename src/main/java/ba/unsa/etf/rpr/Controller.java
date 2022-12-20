package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    public Button okdugme;
    public TextField usernameid;
    public Button cancleDugme;

    public void nekaAkcija(ActionEvent actionEvent) {
        usernameid.setText("Željezničar");
    }

    public void exitAkcija(ActionEvent actionEvent) {
        Stage stage = (Stage) cancleDugme.getScene().getWindow();
        stage.close();
    }
}
