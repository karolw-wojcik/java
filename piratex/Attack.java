package piratex;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

public class Attack {

    private Pirate pirate;
    private String name;
    private double cost;
    private double profit;
    private double pirateProfit;
    private OffsetDateTime time;
    private OffsetDateTime addTime;
    private String description;
    private boolean stuff;
    private ArrayList<Offer> offers;
    private String status;


    public Attack(){
        pirate = new Pirate("Test", "test", "./test.png",5);
        pirate.getAttacks().add(this);
        name = "Test";
        cost = 10;
        profit = 44;
        pirateProfit = 14;
        time = OffsetDateTime.of(LocalDateTime.of(2018, 12, 25, 05, 45),
                ZoneOffset.ofHoursMinutes(1, 0));
        description = "Potrzebujemy boczku.";
        stuff = true;
        offers = new ArrayList<>();
        status = "Niepokryty";
        addTime = OffsetDateTime.now();
    }

    public Attack(String name, double cost, double profit, double pirateProfit, OffsetDateTime time, String description, boolean stuff){
        this.pirate = new Pirate();
        this.name = name;
        this.cost = cost;
        this.profit = profit;
        this.pirateProfit = pirateProfit;
        this.time = time;
        this.description = description;
        this.stuff = stuff;
        offers = new ArrayList<>();
        status = "Niepokryty";
        addTime = OffsetDateTime.now();
        pirate.getAttacks().add(this);
    }

    public Attack(String name, Pirate pirate, double cost, double profit, double pirateProfit, OffsetDateTime time, String description, boolean stuff){
        this.pirate = pirate;
        this.name = name;
        this.cost = cost;
        this.profit = profit;
        this.pirateProfit = pirateProfit;
        this.time = time;
        this.description = description;
        this.stuff = stuff;
        offers = new ArrayList<>();
        status = "Niepokryty";
        addTime = OffsetDateTime.now();
        pirate.getAttacks().add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getProfit(){
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getPirateProfit(){
        return pirateProfit;
    }

    public void setPirateProfit(double pirateProfit) {
        this.pirateProfit = pirateProfit;
    }

    public String getTime() {
        return String.valueOf(time);
    }

    public void setTime(OffsetDateTime time) {
        this.time = time;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return pirate.getImage();
    }

    public Pirate getPirate() {
        return pirate;
    }

    public String getDescription() {
        return description;
    }

    public boolean getStuff() {
        return stuff;
    }

    public void setStuff(boolean stuff) {
        this.stuff = stuff;
    }

    public ArrayList<Offer> getOffers() {
        return offers;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OffsetDateTime getAddTime() {
        return addTime;
    }
}
