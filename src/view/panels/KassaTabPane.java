package view.panels;

import controller.KassaController;
import database.PropertiesLoadSave;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import model.Artikel;


public class KassaTabPane extends GridPane {

    private TableView<Artikel> tabel;
    private KassaController controller;
    private Label totaal;
    private TextField invoer;
    private ObservableList<Artikel> cart;
    private Button verkoop;

    public KassaTabPane(KassaController controller) {
        tabel = new TableView<>();
        setController(controller);
        invoer = new TextField();
        cart = FXCollections.observableArrayList();
        totaal = new Label();
        verkoop = new Button("BETAAL");

        tabel.setItems(cart);

        TableColumn<Artikel, Integer> naam = new TableColumn<>("Artikel");
        naam.setCellValueFactory(new PropertyValueFactory<Artikel, Integer>("Naam"));

        TableColumn<Artikel, Double> prijs = new TableColumn<>("Prijs");
        prijs.setCellValueFactory(new PropertyValueFactory<Artikel, Double>("Prijs"));

        tabel.getColumns().addAll(naam, prijs);

        this.add(invoer, 1,1);
        this.add(tabel, 1,3);
        this.add(totaal, 1,4);
        this.add(verkoop, 1,5);

        invoer.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER){
                Artikel art = controller.vindArtikel(invoer.getText());
                if(art == null){
                    Alert fout = new Alert(Alert.AlertType.ERROR);
                    fout.setTitle("FOUT");
                    fout.setHeaderText("U heeft geen zoekopdracht opgegeven");
                    fout.setContentText("Gelieve een zoekopdracht in te geven");
                    fout.showAndWait();
                } else {
                    cart.add(art);
                    tabel.setItems(cart);
                    updateTotaal();
                }
                invoer.clear();
            }
        });

        verkoop.setOnAction(event -> {
            controller.save("artikel." + PropertiesLoadSave.load("DATABASE"), cart);
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
