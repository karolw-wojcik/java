package piratex;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class AttackObj {

    @FXML
    private Label benefit;
    @FXML
    private Label offeredMoney;
    @FXML
    private Label attackDate;
    @FXML
    private Label title;
    @FXML
    private Label status;
    @FXML
    private Label date;

    private RootController rootController;
    private Attack attack;


    public void edit() throws IOException {
        rootController.editAttack(attack);
    }

    public void choose() throws IOException {
        rootController.loadInvestors(attack);
    }

    public void pay() {
        if(attack.getStatus() == "Gotowy") {
            attack.setStatus("Zakończony");
            for (Offer offer : attack.getOffers()) {
                offer.setStatus("Zakończony");
            }
        }
    }

    public void init(RootController rootController, Attack attack) {
        this.rootController = rootController;
        this.attack = attack;
        date.setText(String.valueOf(attack.getAddTime()));
        status.setText(attack.getStatus());
        title.setText(attack.getName());
        attackDate.setText(attack.getTime());
        offeredMoney.setText(new DecimalFormat("0.######", DecimalFormatSymbols.getInstance( Locale.ENGLISH )).format(attack.getCost()));
        benefit.setText(new DecimalFormat("0.######", DecimalFormatSymbols.getInstance( Locale.ENGLISH )).format(attack.getPirateProfit()));
    }
}
