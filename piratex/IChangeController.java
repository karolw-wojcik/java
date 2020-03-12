package piratex;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class IChangeController {
    @FXML
    private TextField newMoney;

    @FXML
    private TextField newStuff;

    @FXML
    private TextField newCost;

    private RootController rootController;
    private Offer offer;

    public void init(RootController controller, Offer offer){
        this.offer = offer;
        rootController = controller;

        newMoney.setText(new DecimalFormat("0.######", DecimalFormatSymbols.getInstance( Locale.ENGLISH )).format(offer.getMoney()));
        newCost.setText(new DecimalFormat("0.######", DecimalFormatSymbols.getInstance( Locale.ENGLISH )).format(offer.getWannaForStuff()));
        newStuff.setText(offer.getStuff());
    }


    public void cancel() throws IOException {
        rootController.loadInvestments();
    }

    public void delete() throws IOException {
        Attack attack = offer.getAttack();
        Pirate pirate = offer.getPirate();

        attack.getOffers().remove(offer);
        pirate.getOffers().remove(offer);
        rootController.loadInvestments();
    }


    public void change() throws IOException {
        double investedMoney;
        try {
            investedMoney = Double.valueOf(newMoney.getText());
        }
        catch (NumberFormatException e){
            return;
        }
        double stuffCosts;
        try {
            stuffCosts = Double.valueOf(newCost.getText());
        }
        catch (NumberFormatException e){
            return;
        }
        offer.setMoney(investedMoney);
        offer.setWannaForStuff(stuffCosts);
        offer.setStuff(newStuff.getText());
        rootController.loadInvestments();
    }
}
