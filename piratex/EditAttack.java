package piratex;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.*;

public class EditAttack {

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
    private Attack attack;

    public void init(RootController controller, Attack attack){
        rootController = controller;
        this.attack = attack;

        pirateProfit.setText(String.valueOf(attack.getPirateProfit()));
        profit.setText(String.valueOf(attack.getProfit()));
        stuff.setSelected(attack.getStuff());
        cost.setText(String.valueOf(attack.getCost()));
        title.setText(attack.getName());
        description.setText(attack.getDescription());
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

        attack.setCost(costs);
        attack.setStuff(stuff.isSelected());
        attack.setDescription(description.getText());
        attack.setPirateProfit(pirateProfits);
        attack.setProfit(profits);
        attack.setTime(times);
        attack.setName(title.getText());

        rootController.loadAttacks();
    }
}
