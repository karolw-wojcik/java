package piratex;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.ArrayList;

import static piratex.Sorting.Popular;
import static piratex.Sorting.values;

public class SearchController {

    @FXML
    private ChoiceBox<Sorting> sort;

    @FXML
    private MenuButton filter;

    @FXML
    private TextField minProfit;

    @FXML
    private TextField maxProfit;

    @FXML
    private TextField pirateRate;

    @FXML
    private CheckBox stuffPayment;

    @FXML
    private GridPane grid;

    private RootController rootController;
    private ArrayList<Attack> attacks;
    private ArrayList<Attack> attacksToShow;
    private String phrase;

    public void init(RootController controller, ArrayList<Attack> attacks, String phrase) throws IOException {
        rootController = controller;
        this.attacks = attacks;
        this.phrase = phrase;
        attacksToShow = new ArrayList<>();
        for(Attack attack : attacks)
            if(attack.getName().contains(phrase))
                attacksToShow.add(attack);

        for(Sorting sorter : values())
            sort.getItems().add(sorter);
        sort.setValue(Popular);
        sort.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            attacksToShow = new ArrayList<>();
            for(Attack attack : attacks)
                if(attack.getName().contains(phrase))
                    attacksToShow.add(attack);

            Sorting value = sort.getItems().get((Integer) newValue);
            value.sort(attacksToShow);
            try {
                showAttacks();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        filter.showingProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue == false){
                attacksToShow = new ArrayList<>();
                for(Attack attack : attacks){
                    double profit = (attack.getProfit() - attack.getPirateProfit())/100;
                    double min, max, rate;

                    try{
                        min = Double.valueOf(minProfit.getText());
                    }
                    catch (Exception e){
                        min = 0;
                    }
                    try{
                        max = Double.valueOf(maxProfit.getText());
                    }
                    catch (Exception e){
                        max = Double.MAX_VALUE;
                    }
                    try{
                        rate = Double.valueOf(pirateRate.getText());
                    }
                    catch (Exception e){
                        rate = 0;
                    }

                    if(
                            profit >= min && profit <= max && attack.getPirate().getRating() >= rate
                                    && (!stuffPayment.isSelected() || attack.getStuff())
                            && attack.getName().contains(phrase)
                    )
                        attacksToShow.add(attack);
                }
                try {
                    showAttacks();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        showAttacks();
    }

    private void showAttacks() throws IOException {
        grid.getChildren().removeAll(grid.getChildren());
        int i = 0;
        for(Attack attack : attacksToShow) {
            FXMLLoader insideLoader = new FXMLLoader();
            insideLoader.setController(new SearchObj());
            insideLoader.setLocation(
                    getClass().getResource(
                            "searchObj.fxml"
                    )
            );
            StackPane newPane = insideLoader.load();
            SearchObj controller = insideLoader.getController();
            controller.init(rootController, attack);
            grid.addRow(i++, newPane);
        }
    }


}
