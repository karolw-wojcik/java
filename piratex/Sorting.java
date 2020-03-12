package piratex;

import java.util.ArrayList;
import java.util.Comparator;

public enum Sorting {
    Popular{
        @Override
        public void sort(ArrayList<Attack> list) {

        }

        @Override
        public String toString() {
            return "Popularność";
        }
    },
    Upcomming{
        @Override
        public void sort(ArrayList<Attack> list) {
            list.sort(Comparator.comparing(Attack::getTime));
        }

        @Override
        public String toString() {
            return "Najbliższe";
        }
    },
    Benefit{
        @Override
        public void sort(ArrayList<Attack> list) {
            list.sort((o1, o2) -> {
                double rateO1 = (o1.getProfit() - o1.getPirateProfit())/o1.getCost();
                double rateO2 = (o2.getProfit() - o2.getPirateProfit())/o2.getCost();
                return (Double.compare(rateO1, rateO2));
            });
        }

        @Override
        public String toString() {
            return "Najkorzystniejsze";
        }
    };


    public abstract void sort(ArrayList<Attack> list);
    public abstract String toString();
}
