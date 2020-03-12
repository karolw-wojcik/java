package piratex;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.ArrayList;

public class MainController {

    @FXML
    private Tab popularTab;

    @FXML
    private Tab benefitTab;

    @FXML
    private Tab upcomingTab;

    private ArrayList<Attack> attacks;
    private RootController rootController;

    public void initialize() {

    }

    public void init(RootController controller, ArrayList<Attack> attacks) throws IOException {
        this.attacks = attacks;
        rootController = controller;
        onSelectionChanged();
    }

    public void onSelectionChanged() throws IOException {
        GridPane activeGrid;
        Sorting sorter;
        if(popularTab.isSelected()){
            activeGrid = (GridPane) ((ScrollPane)popularTab.getContent()).getContent();
            sorter = Sorting.Popular;
        }
        else if(benefitTab.isSelected()){
            activeGrid = (GridPane) ((ScrollPane)benefitTab.getContent()).getContent();
            sorter = Sorting.Benefit;
        }
        else{
            activeGrid = (GridPane) ((ScrollPane)upcomingTab.getContent()).getContent();
            sorter = Sorting.Upcomming;
        }
        ArrayList<Attack> attacksToShow;
        try{
            attacksToShow = new ArrayList<>(attacks);
        }
        catch (NullPointerException e){
            attacksToShow = null;
        }

        if(attacksToShow == null || attacksToShow.isEmpty())
            return;

        sorter.sort(attacksToShow);
        activeGrid.getChildren().removeAll(activeGrid.getChildren());

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
            activeGrid.addRow(i++, newPane);
        }
    }
}
