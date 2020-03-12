package piratex;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class InvestObj {

    @FXML
    private Label pirate;

    @FXML
    private Label date;

    @FXML
    private Label status;

    @FXML
    private Label title;

    @FXML
    private Label attackDate;

    @FXML
    private Label offeredMoney;

    @FXML
    private Label offeredStuff;

    @FXML
    private Label benefit;

    RootController rootController;
    Offer offer;

    public void init(RootController controller, Offer offer){
        rootController = controller;
        this.offer = offer;
        pirate.setText(offer.getAttack().getPirate().getName());
        date.setText(String.valueOf(offer.getDate()));
        status.setText(offer.getStatus());
        title.setText(offer.getAttack().getName());
        attackDate.setText(offer.getAttack().getTime());
        offeredMoney.setText(new DecimalFormat("0.######", DecimalFormatSymbols.getInstance( Locale.ENGLISH )).format(offer.getMoney()));
        offeredStuff.setText(offer.getStuff());
        double percent = offer.getMoney() / offer.getAttack().getCost();
        double rate = offer.getAttack().getProfit() - offer.getAttack().getPirateProfit();
        benefit.setText(new DecimalFormat("0.######", DecimalFormatSymbols.getInstance( Locale.ENGLISH )).format(percent * rate + offer.getWannaForStuff()));
    }


    public void change() throws IOException {
        rootController.loadInvestmentChange(offer);
    }

    public void pay() {
        if(offer.getStatus() == "Zaakceptowany")
            offer.setStatus("Opłacony");
    }

    public void rate() throws IOException {
        rootController.loadRate(offer.getAttack());
    }
}
