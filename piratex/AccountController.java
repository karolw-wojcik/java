package piratex;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;


public class AccountController {

    @FXML
    private TextField actualPassword;
    @FXML
    private TextField adressBTC;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField Email;
    @FXML
    private ImageView image;
    @FXML
    private TextField newPassword;
    @FXML
    private TextField repeatPassword;
    @FXML
    private TextField login;

    private RootController rootController;
    private Pirate pirate;
    private String imageUrl;

    public void changeAvatar() {
        imageUrl = "./test.png";
        image.setImage(new Image(getClass().getResource(imageUrl).toExternalForm()));
    }

    public void init(RootController controller, Pirate pirate) {
        rootController = controller;
        this.pirate = pirate;
        imageUrl = pirate.getImage();
        image.setImage(new Image(getClass().getResource(imageUrl).toExternalForm()));
    }

    public void cancel() throws IOException {
        rootController.loadMain();
    }

    public void save() throws IOException {
        if(!actualPassword.getText().isEmpty()) {
            pirate.setImage(imageUrl);
            rootController.loadMain();
        }
    }
}
