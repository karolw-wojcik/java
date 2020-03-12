package piratex;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.ArrayList;

public class InvestorsController {

    @FXML
    private Label cost;
    @FXML
    private Label title;
    @FXML
    private GridPane grid;

    private RootController rootController;
    private Attack attack;
    private ArrayList<InvestorsObj> controllers;

    public void init(RootController controller, Attack attack) throws IOException {
        rootController = controller;
        this.attack = attack;

        cost.setText(String.valueOf(attack.getCost()));
        title.setText(attack.getName());

        grid.getChildren().removeAll(grid.getChildren());
        int i = 0;
        for(Offer offer : attack.getOffers()) {
            FXMLLoader insideLoader = new FXMLLoader();
            insideLoader.setController(new InvestorsObj());
            insideLoader.setLocation(
                    getClass().getResource(
                            "investorsObj.fxml"
                    )
            );
            StackPane newPane = insideLoader.load();
            InvestorsObj iController = insideLoader.getController();
            iController.init(rootController, offer);
            controllers.add(iController);
            grid.addRow(i++, newPane);
        }
    }

    public void choose() throws IOException {
        for(InvestorsObj controller : controllers){
            double money;
            try{
                money = Double.parseDouble(controller.getTakenMoney().getText());
            }
            catch (Exception e){
                money = 0;
            }

            Offer offer = controller.getOffer();

            offer.setMoney(money);
            offer.setStatus((controller.getCheckBox().isSelected() ? "Zaakceptowany" : "Odrzucony"));
        }
        rootController.loadAttacks();
    }

    public void cancel() throws IOException {
        rootController.loadAttacks();
    }
}
