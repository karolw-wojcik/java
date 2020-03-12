package piratex;


import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class Offer {
    private Pirate pirate;
    private Attack attack;
    private double money;
    private double wannaForStuff;
    private String stuff;
    private String status;
    private OffsetDateTime date;

    public Offer(Pirate pirate, Attack attack, double money, double wannaForStuff, String stuff){
        this.pirate = pirate;
        this.attack = attack;
        this.money = money;
        this.wannaForStuff = wannaForStuff;
        this.stuff = stuff;
        status = "Zaproponowany";
        date = OffsetDateTime.now();
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    public Pirate getPirate() {
        return pirate;
    }

    public Attack getAttack() {
        return attack;
    }

    public double getMoney() {
        return money;
    }

    public String getStuff() {
        return stuff;
    }

    public double getWannaForStuff() {
        return wannaForStuff;
    }

    public void setWannaForStuff(double wannaForStuff) {
        this.wannaForStuff = wannaForStuff;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setMoney(double investedMoney) {
        money = investedMoney;
    }

    public void setStuff(String text) {
        stuff = text;
    }
}
