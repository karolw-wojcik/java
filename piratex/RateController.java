package piratex;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class RateController {

    @FXML
    private TextField additionalOpinion;
    @FXML
    private ImageView image;
    @FXML
    private Label title;
    @FXML
    private TextField rate;
    @FXML
    private Label pirateLogin;

    private RootController rootController;
    private Attack attack;

    public void init(RootController controller, Attack attack) {
        rootController = controller;
        this.attack = attack;
        pirateLogin.setText(attack.getPirate().getName());
        title.setText(attack.getName());
        image.setImage(new Image(getClass().getResource(attack.getImage()).toExternalForm()));
    }

    public void cancel() throws IOException {
        rootController.loadInvestments();
    }

    public void submit() throws IOException {
        Pirate pirate = attack.getPirate();
        double rating;
        try {
            rating = Double.valueOf(rate.getText());
        }
        catch (Exception e){
            rating = pirate.getRating();
        }

        pirate.setRating((pirate.getRating() + rating)/2);
        pirate.setOpinion(pirate.getOpinion() + "\n" + additionalOpinion.getText());
        rootController.loadInvestments();
    }
}
