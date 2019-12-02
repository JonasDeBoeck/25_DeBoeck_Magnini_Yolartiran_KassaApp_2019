package view.panels;

import com.sun.org.apache.xpath.internal.operations.String;
import controller.KassaController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import model.Artikel;
import model.Winkel;


public class KassaTabPane extends GridPane {

    private TableView<Artikel> tabel;
    private KassaController controller;
    private Label error;
    private Label totaal;
    private TextField invoer;
    private Winkel a;
    private ObservableList<Artikel> cart;
    private Button verkoop;

    public KassaTabPane(KassaController controller) {
        //TODO bekke deftige layout
        tabel = new TableView<>();
        setController(controller);
        invoer = new TextField();
        error = new Label();
        a = new Winkel();
        cart = FXCollections.observableArrayList();
        totaal = new Label();
        verkoop = new Button("BETAAL");

        tabel.setItems(cart);

        TableColumn<Artikel, Integer> naam = new TableColumn<>("Artikel");
        naam.setCellValueFactory(new PropertyValueFactory<Artikel, Integer>("Artikel"));

        TableColumn<Artikel, Double> prijs = new TableColumn<>("Prijs");
        prijs.setCellValueFactory(new PropertyValueFactory<Artikel, Double>("Prijs"));

        tabel.getColumns().addAll(naam, prijs);

        this.add(invoer, 1,1);
        this.add(error, 1,2);
        this.add(tabel, 1,3);
        this.add(totaal, 1,4);
        this.add(verkoop, 1,5);

        error.setVisible(false);

        invoer.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER){
                Artikel art = a.vindArtikel(invoer.getText());
                if(art == null){
                    //TODO ff alert fixen
                    error.setText("artikel " + invoer.getText() + " bestaat niet!");
                    error.setVisible(true);
                } else {
                    cart.add(art);
                    //TODO enkel prijs word in tabel gestoken, de naam niet idk why
                    tabel.setItems(cart);
                    updateTotaal();
                }
                invoer.clear();
            }
        });

        verkoop.setOnAction(event -> {
            cart.clear();
            totaal.setVisible(false);
            //TODO Stock update
        });
    }


    private void updateTotaal(){
        double totaal1 = 0;
        for(Artikel a : cart){
            totaal1 += a.getPrijs();
        }
        totaal.setVisible(true);
        totaal.setText("totaal: " + totaal1);
    }

    private void setController(KassaController controller) {
        this.controller = controller;
    }

}
