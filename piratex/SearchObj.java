package piratex;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;


public class SearchObj {

    @FXML
    Label title;

    @FXML
    Label price;

    @FXML
    Label time;

    @FXML
    ImageView image;

    @FXML
    StackPane pane;
    ArrayList<String> paneStylesheets;
    String paneStyle;

    Attack attack;
    RootController rootController;

    public void init(RootController controller, Attack attack){
        rootController = controller;
        this.attack = attack;
        title.setText(attack.getName());
        price.setText(String.format("%s - %s BTC",
                new DecimalFormat("0.######", DecimalFormatSymbols.getInstance( Locale.ENGLISH ))
                        .format(attack.getCost()/100),
                new DecimalFormat("0.######", DecimalFormatSymbols.getInstance( Locale.ENGLISH ))
                        .format((attack.getProfit() - attack.getPirateProfit())/100)
        ));
        time.setText(attack.getTime());
        image.setImage(new Image(getClass().getResource(attack.getImage()).toExternalForm()));
    }

    public void initialize(){

    }

    public void onMouseClicked() throws IOException {
        rootController.openAttack(attack);
    }

    public void onMouseEntered(){
        paneStylesheets = new ArrayList<>(pane.getStylesheets());
        paneStyle = new String(pane.getStyle());
        pane.setStyle("-fx-background-color: red");
    }

    public void onMouseExited(){
        pane.getStylesheets().removeAll(pane.getStylesheets());
        pane.getStylesheets().setAll(paneStylesheets);
        pane.setStyle(paneStyle);
    }
}
