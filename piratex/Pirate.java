package piratex;

import java.util.ArrayList;

public class Pirate {
    private String name;
    private String opinion;
    private String image;
    private double rating;
    private ArrayList<Attack> attacks;
    private ArrayList<Offer> offers;

    public Pirate(){
        this.name = "Test";
        this.opinion = "test";
        this.image = "test.png";
        this.rating = 5;
        attacks = new ArrayList<>();
        offers = new ArrayList<>();
    }

    public Pirate(String name, String opinion, String image, double rating){
        this.name = name;
        this.opinion = opinion;
        this.image = image;
        this.rating = rating;
        attacks = new ArrayList<>();
        offers = new ArrayList<>();
    }

    public String getImage() {
        return image;
    }

    public ArrayList<Attack> getAttacks() {
        return attacks;
    }

    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }

    public String getOpinion(){
        return opinion;
    }

    public ArrayList<Offer> getOffers() {
        return offers;
    }

    public void setRating(double v) {
        rating = v;
    }

    public void setOpinion(String s) {
        opinion = s;
    }

    public void setImage(String imageUrl) {
        image = imageUrl;
    }
}
