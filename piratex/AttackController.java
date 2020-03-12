package piratex;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;


public class AttackController {

    @FXML
    private CheckBox moneyCheck;

    @FXML
    private TextField offeredMoney;

    @FXML
    private CheckBox stuffCheck;

    @FXML
    private TextField stuffOffered;

    @FXML
    private TextField stuffCost;

    @FXML
    private Label sumBenefit;

    @FXML
    private Label benefit;

    @FXML
    private ImageView image;

    @FXML
    private Label pName;

    @FXML
    private Label pRate;

    @FXML
    private Label title;

    @FXML
    private Label date;

    @FXML
    private Label description;

    @FXML
    private Label comments;

    private RootController rootController;
    private Attack attack;

    public void initialize(){
        offeredMoney.setDisable(true);
        stuffOffered.setDisable(true);
        stuffCost.setDisable(true);
    }

    public void init(RootController controller, Attack attack){
        rootController = controller;
        this.attack = attack;
        title.setText(attack.getName());
        date.setText(attack.getTime());
        image.setImage(new Image(getClass().getResource(attack.getImage()).toExternalForm()));
        pName.setText(attack.getPirate().getName());
        pRate.setText(String.valueOf(attack.getPirate().getRating()));
        description.setText(attack.getDescription());
        comments.setText(attack.getPirate().getOpinion());
        stuffCheck.setDisable(!attack.getStuff());
    }

    public void onMoneyCheck() {
        offeredMoney.setDisable(!moneyCheck.isSelected());

    }

    public void onStuffCheck() {
        stuffOffered.setDisable(!stuffCheck.isSelected());
        stuffCost.setDisable(!stuffCheck.isSelected());
    }

    public void onMoneyChanged() {
        double investPercent = 0;
        try {
            investPercent = Double.valueOf(offeredMoney.getText()) / attack.getCost();
        }
        catch (NumberFormatException e){
            investPercent = 0;
        }
        double rate = (attack.getProfit() - attack.getPirateProfit());
        double moneyProfit = investPercent * rate;
        double profit = moneyProfit;
        try {
            profit += Double.valueOf(stuffCost.getText());
        }
        catch (NumberFormatException e){
            profit = moneyProfit;
        }
        sumBenefit.setText(new DecimalFormat("0.######", DecimalFormatSymbols.getInstance( Locale.ENGLISH )).format(profit));
        benefit.setText(new DecimalFormat("0.######", DecimalFormatSymbols.getInstance( Locale.ENGLISH )).format(moneyProfit));
    }

    public void offer() throws IOException {
        if(Double.valueOf(sumBenefit.getText()) == 0.0)
            return;
        double investedMoney;
        try {
            investedMoney = Double.valueOf(offeredMoney.getText());
        }
        catch (NumberFormatException e){
            investedMoney = 0;
        }
        double stuffCosts;
        try {
            stuffCosts = Double.valueOf(stuffCost.getText());
        }
        catch (NumberFormatException e){
            stuffCosts = 0;
        }
        Offer offer = new Offer(rootController.getPirate(), attack, investedMoney, stuffCosts, stuffOffered.getText());
        attack.getOffers().add(offer);
        rootController.getPirate().getOffers().add(offer);
        rootController.loadInvestments();
    }
}
