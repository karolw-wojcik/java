package piratex;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class NewAttack {

    @FXML
    private TextField pirateProfit;
    @FXML
    private TextField profit;
    @FXML
    private CheckBox stuff;
    @FXML
    private TextField cost;
    @FXML
    private DatePicker time;
    @FXML
    private TextField title;
    @FXML
    private TextArea description;

    private RootController rootController;
    private Pirate pirate;

    public void init(RootController controller, Pirate pirate){
        rootController = controller;
        this.pirate = pirate;
    }

    public void cancel() throws IOException {
        rootController.loadAttacks();
    }

    public void save() throws IOException {
        double costs, profits, pirateProfits;
        OffsetDateTime times;
        try{
            costs = Double.valueOf(cost.getText());
            profits = Double.valueOf(profit.getText());
            pirateProfits = Double.valueOf(pirateProfit.getText());
            times = OffsetDateTime.of(LocalDateTime.of(time.getValue(), LocalTime.of(15, 00)), ZoneOffset.ofHoursMinutes(1, 0));
        }
        catch (Exception e){
            return;
        }
        Attack attack = new Attack(title.getText(), pirate, costs, profits, pirateProfits, times, description.getText(), stuff.isSelected());

        pirate.getAttacks().add(attack);
        rootController.getAttacks().add(attack);

        rootController.loadAttacks();
    }
}
