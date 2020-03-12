package piratex;

import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javax.swing.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.animation.Animation;
import java.lang.String;
import java.time.temporal.Temporal;
public class RootController {
    private int second,minute,hour,day,month,year;
    private String s,m;
    @FXML private Label time;
    @FXML
    private StackPane insidePane;


    private FXMLLoader insideLoader;

    private Pirate pirate;
    private ArrayList<Attack> attacks;

    @FXML
    private TextField phrase;
    @FXML
    private AnchorPane invMenu;

    public void initialize() throws IOException {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            second = LocalDateTime.now().getSecond();
            minute = LocalDateTime.now().getMinute();
            hour = LocalDateTime.now().getHour();
            day = LocalDateTime.now().getDayOfMonth();
            month = LocalDateTime.now().getMonthValue();
            year = LocalDateTime.now().getYear();
            if(minute<10) m="0";
            else m="";
            if(second<10) s="0";
            else s="";
            time.setText(" "+hour + ":" + m+(minute) + ":" +s+ second +"\n"+ day+"-0"+month+"-"+year);
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        pirate = new Pirate("Mario", "", "mario.jpg", 5);
        attacks = new ArrayList<>();
        attacks.add(new Attack("Elektronika bez ryzyka", 65, 500, 300, OffsetDateTime.of(LocalDateTime.of(2019, 01, 26, 05, 45),
                ZoneOffset.ofHoursMinutes(1, 0)), "W przyszłym miesiącu trasę z Szanghaju do Los Angeles (USA) będzie przebywał kontenerowiec MS Emma Mærsk, przewożąc na pokładzie dużą ilość zaawansowanych\n" +
                "urządzeń elektronicznych. Trasa statku pozwala na okno czasowe umożliwiające przejęcie znacznej ilości zaopatrzenia w pobliżu wyspy Midway Atoll, gdzie też towar zostanie ukryty do momentu, gdy bezpiecznie będzie można go przenieść. Załoga statku oraz ochrona zostały zinfiltrowane, punkt w którym przeprowadzimy akcję pozwoli nam na zablokowanie komunikacji radiowej statku, będąc jednocześnie w martwym punkcie patroli morskich z wyspy. \n" +
                "Do przeprowadzenia akcji będziemy potrzebowali kutra rybackiego, aby niepostrzeżenie dostać się w pobliże kontenerowca, małej barki do przetransportowania łupu na wyspę, dwóch motorówek, około 14 osobowej załogi, pełnego wyposażenia dla załogi. \n", true));

        attacks.add(new Attack("Diamenty na statku", new Pirate("test2", "test2", "test2.jpg", 4), 40, 500, 250, OffsetDateTime.of(LocalDateTime.of(2019, 01, 10, 01, 30),
                ZoneOffset.ofHoursMinutes(1, 0)), "W styczniu przyszłego roku prom pasażerski “Symphony of the Seas” oprócz pełnej listy pasażerów będzie przewoził kolekcję diamentów należących do królowej Anglii, klejnoty wystawione będą jako ekspozycja na jednym z pokładów rozrywkowych statku. Naszym celem będzie kradzież tejże ekspozycji, planowo bez wzbudzenia podejrzeń ze strony pasażerów \n" +
                "oraz załogi statku. Przejęliśmy plany pokładów statku przygotowując plany cichej, profesjonalnej akcji, gdzie w grupie 4 osób nocą wejdziemy na pokład, omijając zabezpieczenia podmienimy biżuterię na przygotowane wcześniej profesjonalne repliki, cała akcja odbyłaby się w czasie \n" +
                "trwania jednej z największych atrakcji na statku balu karnawałowego. Taka dywersja pozwoli nam ograniczyć ryzyko wykrycia i zwiększyć szansę powodzenia akcji, drużyna którą zabieramy jest profesjonalnie przeszkolona i kilkakrotnie wykonywałą podobne zadania. Potrzebujemy środków za zakup wymaganego sprzętu.\n", false));

        attacks.add(new Attack("Kane", pirate, 50, 800, 400, OffsetDateTime.of(LocalDateTime.of(2019, 02, 22, 14, 10),
                ZoneOffset.ofHoursMinutes(1, 0)), "Naszym celem jest tankowiec “Kane” przewożący transport ropy naftowej z Rosji do Japonii, celem napadu jest przyjęcie tankowca zaraz po wypłynięciu z obszaru Morza Beringa,\n" +
                "Porzucenie załogi oraz ukrycia statku w przygotowanym zamkniętym i ukrytym porcie na wyspie “Adak”, gdzie towar zostanie w ciągu 48 godzin wypompowany i przewieziony do zainteresowanego kupca. Akcja będzie wymagała 10 osób, sprzętu do przejęcia kontroli nad statkiem, na pokład przedostaniemy się motorówką wprost z wyspy kiedy tankowiec będzie ją\n" +
                "mijał. Problematyczną częścią akcji jest utrzymanie w ukryciu statku podczas przepompowywania ładunku, wiąże się to z wysokim ryzykiem, ale również wysokim oczekiwanym zyskiem.\n", false ));

        insideLoader = new FXMLLoader();
        insideLoader.setController(new MainController());
        insideLoader.setLocation(
                getClass().getResource(
                        "main.fxml"
                )
        );
        AnchorPane newPane = insideLoader.load();
        MainController controller = insideLoader.getController();
        controller.init(this, attacks);
        insidePane.getChildren().add(newPane);

    }

    public void openAttack(Attack attack) throws IOException {
        insideLoader = new FXMLLoader();
        insideLoader.setController(new AttackController());
        insideLoader.setLocation(
                getClass().getResource(
                        "attack.fxml"
                )
        );
        AnchorPane newPane = insideLoader.load();
        AttackController controller = insideLoader.getController();
        controller.init(this, attack);
        insidePane.getChildren().removeAll(insidePane.getChildren());
        insidePane.getChildren().add(newPane);
    }

    public void loadMain() throws IOException {
        insideLoader = new FXMLLoader();
        insideLoader.setController(new MainController());
        insideLoader.setLocation(
                getClass().getResource(
                        "main.fxml"
                )
        );
        AnchorPane newPane = insideLoader.load();
        MainController controller = insideLoader.getController();
        controller.init(this, attacks);
        insidePane.getChildren().removeAll(insidePane.getChildren());
        insidePane.getChildren().add(newPane);
    }

    public Pirate getPirate() {
        return pirate;
    }

    public void loadInvestments() throws IOException {
        insideLoader = new FXMLLoader();
        insideLoader.setController(new InvestmentsController());
        insideLoader.setLocation(
                getClass().getResource(
                        "investments.fxml"
                )
        );
        StackPane newPane = insideLoader.load();
        InvestmentsController controller = insideLoader.getController();
        controller.init(this, pirate);
        insidePane.getChildren().removeAll(insidePane.getChildren());
        insidePane.getChildren().add(newPane);
        invMenu.setVisible(false);
    }

    public void loadInvestmentChange(Offer offer) throws IOException {
        insideLoader = new FXMLLoader();
        insideLoader.setController(new IChangeController());
        insideLoader.setLocation(
                getClass().getResource(
                        "investmentChange.fxml"
                )
        );
        StackPane newPane = insideLoader.load();
        IChangeController controller = insideLoader.getController();
        controller.init(this, offer);
        insidePane.getChildren().removeAll(insidePane.getChildren());
        insidePane.getChildren().add(newPane);
    }

    public void loadSearch() throws IOException {
        insideLoader = new FXMLLoader();
        insideLoader.setController(new SearchController());
        insideLoader.setLocation(
                getClass().getResource(
                        "search.fxml"
                )
        );
        AnchorPane newPane = insideLoader.load();
        SearchController controller = insideLoader.getController();
        controller.init(this, attacks, phrase.getText());
        insidePane.getChildren().removeAll(insidePane.getChildren());
        insidePane.getChildren().add(newPane);
    }

    public void loadRate(Attack attack) throws IOException {
        insideLoader = new FXMLLoader();
        insideLoader.setController(new RateController());
        insideLoader.setLocation(
                getClass().getResource(
                        "rate.fxml"
                )
        );
        StackPane newPane = insideLoader.load();
        RateController controller = insideLoader.getController();
        controller.init(this, attack);
        insidePane.getChildren().removeAll(insidePane.getChildren());
        insidePane.getChildren().add(newPane);
    }

    public void loadAttacks() throws IOException {
        insideLoader = new FXMLLoader();
        insideLoader.setController(new AttacksController());
        insideLoader.setLocation(
                getClass().getResource(
                        "attacks.fxml"
                )
        );
        StackPane newPane = insideLoader.load();
        AttacksController controller = insideLoader.getController();
        controller.init(this, pirate);
        insidePane.getChildren().removeAll(insidePane.getChildren());
        insidePane.getChildren().add(newPane);
        invMenu.setVisible(false);
    }

    public void loadInvestors(Attack attack) throws IOException {
        insideLoader = new FXMLLoader();
        insideLoader.setController(new InvestorsController());
        insideLoader.setLocation(
                getClass().getResource(
                        "investors.fxml"
                )
        );
        StackPane newPane = insideLoader.load();
        InvestorsController controller = insideLoader.getController();
        controller.init(this, attack);
        insidePane.getChildren().removeAll(insidePane.getChildren());
        insidePane.getChildren().add(newPane);
    }

    public void loadAccount() throws IOException {
        insideLoader = new FXMLLoader();
        insideLoader.setController(new AccountController());
        insideLoader.setLocation(
                getClass().getResource(
                        "account.fxml"
                )
        );
        StackPane newPane = insideLoader.load();
        AccountController controller = insideLoader.getController();
        controller.init(this, pirate);
        insidePane.getChildren().removeAll(insidePane.getChildren());
        insidePane.getChildren().add(newPane);
        invMenu.setVisible(false);
    }

    public void logout() {
        System.exit(0);
    }

    public ArrayList<Attack> getAttacks() {
        return attacks;
    }

    public void newAttack() throws IOException {
        insideLoader = new FXMLLoader();
        insideLoader.setController(new NewAttack());
        insideLoader.setLocation(
                getClass().getResource(
                        "newAttack.fxml"
                )
        );
        StackPane newPane = insideLoader.load();
        NewAttack controller = insideLoader.getController();
        controller.init(this, pirate);
        insidePane.getChildren().removeAll(insidePane.getChildren());
        insidePane.getChildren().add(newPane);
    }

    public void editAttack(Attack attack) throws IOException {
        insideLoader = new FXMLLoader();
        insideLoader.setController(new EditAttack());
        insideLoader.setLocation(
                getClass().getResource(
                        "editAttack.fxml"
                )
        );
        StackPane newPane = insideLoader.load();
        EditAttack controller = insideLoader.getController();
        controller.init(this, attack);
        insidePane.getChildren().removeAll(insidePane.getChildren());
        insidePane.getChildren().add(newPane);
    }

    public void setmenuvisible(MouseEvent event){
        if (!invMenu.isVisible()) invMenu.setVisible(true);
        else invMenu.setVisible(false);
    }
}
