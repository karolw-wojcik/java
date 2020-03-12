package piratex;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import static piratex.Sorting.Popular;
import static piratex.Sorting.values;

public class AttacksController {

    @FXML
    private ChoiceBox<Sorting> sortAttr;
    @FXML
    private ChoiceBox<String> status;
    @FXML
    private GridPane grid;


    private RootController rootController;
    private Pirate pirate;
    private ArrayList<Attack> attacksToShow;

    public void initialize() {
        status.getItems().setAll("Dowolny", "Niepokryty", "Przygotowywany", "Gotowy", "Zakończony");
        status.setValue("Dowolny");
        status.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            String value = status.getItems().get((Integer) newValue);
            attacksToShow = new ArrayList<>();
            if (value == "Dowolny")
                attacksToShow = pirate.getAttacks();
            else
                for (Attack attack : pirate.getAttacks())
                    if (attack.getStatus() == value)
                        attacksToShow.add(attack);
            try {
                showAttacks();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        for (Sorting sorter : values())
            sortAttr.getItems().add(sorter);
        sortAttr.setValue(Popular);
        sortAttr.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            Sorting value = sortAttr.getItems().get((Integer) newValue);
            value.sort(attacksToShow);
            try {
                showAttacks();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void showAttacks() throws IOException{
        grid.getChildren().removeAll(grid.getChildren());
        int i = 0;
        for(Attack attack : attacksToShow) {
            FXMLLoader insideLoader = new FXMLLoader();
            insideLoader.setController(new AttackObj());
            insideLoader.setLocation(
                    getClass().getResource(
                            "attackObj.fxml"
                    )
            );
            StackPane newPane = insideLoader.load();
            AttackObj controller = insideLoader.getController();
            controller.init(rootController, attack);
            grid.addRow(i++, newPane);
        }
    }

    public void init(RootController controller, Pirate pirate) throws IOException {
        rootController = controller;
        this.pirate = pirate;
        attacksToShow = new ArrayList<>(pirate.getAttacks());
        showAttacks();
    }

    public void newAttack() throws IOException {
        rootController.newAttack();
    }
}
