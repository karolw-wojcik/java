package piratex;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InvestorsObj {

    @FXML
    private CheckBox checkBox;
    @FXML
    private Label profit;
    @FXML
    private TextField takenMoney;
    @FXML
    private Label offeredStuff;
    @FXML
    private Label offeredMoney;
    @FXML
    private Label login;

    private RootController rootController;
    private Offer offer;

    public void init(RootController rootController, Offer offer) {
        this.rootController = rootController;
        this.offer = offer;

        profit.setText(String.valueOf((offer.getAttack().getProfit() - offer.getAttack().getPirateProfit()) * offer.getMoney()/offer.getAttack().getCost()));
        offeredMoney.setText(String.valueOf(offer.getMoney()));
        offeredStuff.setText(offer.getStuff());
        login.setText(offer.getPirate().getName());
    }

    public TextField getTakenMoney() {
        return takenMoney;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public Offer getOffer() {
        return offer;
    }
}
