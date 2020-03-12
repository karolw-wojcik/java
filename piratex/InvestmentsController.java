package piratex;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import static piratex.Sorting.*;
import static piratex.Sorting.Popular;

public class InvestmentsController {

    @FXML
    ChoiceBox<String> status;

    @FXML
    ChoiceBox<Sorting> sortAttr;

    @FXML
    GridPane grid;

    private RootController rootController;
    private Pirate pirate;
    private ArrayList<Offer> attacksToShow;

    public void initialize(){
        status.getItems().setAll("Dowolny", "Zaproponowany", "Zaakceptowany", "Odrzucony", "Opłacony", "Zakończony");
        status.setValue("Dowolny");
        status.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            String value = status.getItems().get((Integer) newValue);
            attacksToShow = new ArrayList<>();
            if(value == "Dowolny")
                attacksToShow = pirate.getOffers();
            else
                for(Offer offer : pirate.getOffers())
                    if(offer.getStatus() == value)
                        attacksToShow.add(offer);
            try {
                showAttacks();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        for(Sorting sorter : values())
            sortAttr.getItems().add(sorter);
        sortAttr.setValue(Popular);
        sortAttr.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            Sorting value = sortAttr.getItems().get((Integer) newValue);
            ArrayList<Offer> tmp = new ArrayList<>(attacksToShow);
            switch(value){
                case Benefit:
                    tmp.sort((o1, o2) ->{
                        double percentO1 = o1.getMoney() / o1.getAttack().getCost();
                        double rateO1 = o1.getAttack().getProfit() - o1.getAttack().getPirateProfit();
                        double moneyO1 = percentO1 * rateO1 + o1.getWannaForStuff();
                        double percentO2 = o2.getMoney() / o2.getAttack().getCost();
                        double rateO2 = o2.getAttack().getProfit() - o2.getAttack().getPirateProfit();
                        double moneyO2 = percentO2 * rateO2 + o2.getWannaForStuff();

                        return Double.compare(moneyO1, moneyO2);
                    });
                    break;

                case Upcomming:
                    tmp.sort((o1, o2) -> {
                        Attack o1a = o1.getAttack();
                        Attack o2a = o2.getAttack();
                        return Comparator.comparing(Attack::getTime).compare(o1a, o2a);
                    });
                    break;

                default:
                    break;

            }
            attacksToShow = tmp;
            try {
                showAttacks();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void init(RootController controller, Pirate pirate) throws IOException {
        rootController = controller;
        this.pirate = pirate;
        attacksToShow = new ArrayList<>(pirate.getOffers());
        showAttacks();
    }

    private void showAttacks() throws IOException {
        grid.getChildren().removeAll(grid.getChildren());
        int i = 0;
        for(Offer attack : attacksToShow) {
            FXMLLoader insideLoader = new FXMLLoader();
            insideLoader.setController(new InvestObj());
            insideLoader.setLocation(
                    getClass().getResource(
                            "investObj.fxml"
                    )
            );
            StackPane newPane = insideLoader.load();
            InvestObj iController = insideLoader.getController();
            iController.init(rootController, attack);
            grid.addRow(i++, newPane);
        }
    }
}
