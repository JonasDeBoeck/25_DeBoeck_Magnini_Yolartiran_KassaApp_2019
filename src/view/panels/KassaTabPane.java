package view.panels;

import controller.KassaController;
import database.PropertiesLoadSave;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.geometry.Insets;
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
    private Button verkoop, onHold, offHold;

    public KassaTabPane(KassaController controller) {
        tabel = new TableView<>();
        setController(controller);
        invoer = new TextField("Vul hier een Artikel code in!");

        totaal = new Label();
        verkoop = new Button("Afsluiten");
        onHold = new Button("On hold");
        offHold = new Button("actief");

        TableColumn<Artikel, String> naam = new TableColumn<>("Artikel");
        naam.setCellValueFactory(new PropertyValueFactory<Artikel, String>("Naam"));

        TableColumn<Artikel, Double> prijs = new TableColumn<>("Prijs");
        prijs.setCellValueFactory(new PropertyValueFactory<Artikel, Double>("Prijs"));

        TableColumn<Artikel, Artikel> verwijder = new TableColumn<>("Delete");
        verwijder.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        verwijder.setCellFactory(
                param -> new TableCell<Artikel, Artikel>() {
                    private final Button deleteButton = new Button("Verwijder");

                    @Override
                    protected void updateItem(Artikel item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null) {
                            setGraphic(null);
                            return;
                        }

                        setGraphic(deleteButton);
                        deleteButton.setOnAction(
                                event -> {
                                    controller.deleteFromCart(item);
                                    tabel.setItems(controller.getWinkelWagentje());
                                    controller.notifyObservers();
                                    updateTotaal();
                                }
                        );
                    }
                }
        );

        tabel.getColumns().addAll(naam, prijs, verwijder);
        tabel.setMinSize(220,300);
        tabel.setMaxWidth(242);

        this.setPadding(new Insets(10,10,10,10));
        this.setVgap(8);
        this.setHgap(10);

        this.add(invoer, 1,1);
        this.add(tabel, 2,1);
        this.add(totaal, 2,3);
        this.add(verkoop, 2,4);
        this.add(onHold,3,1);
        this.add(offHold, 3,1);
        onHold.setVisible(false);

        invoer.setOnMouseClicked(event -> {
            invoer.clear();
        });

        invoer.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER){
                Artikel art = controller.vindArtikel(invoer.getText());
                if(art == null){
                    Alert fout = new Alert(Alert.AlertType.ERROR);
                    fout.setTitle("FOUT");
                    fout.setHeaderText("Artikel niet gevonden");
                    fout.setContentText("Het artikel dat u heeft opgegeven kan niet worden gevonden");
                    fout.showAndWait();
                } else {
                    controller.addToCart(art);
                    tabel.setItems(controller.getWinkelWagentje());
                    controller.notifyObservers();
                    updateTotaal();
                    onHold.setVisible(controller.legeOnHold());
                    offHold.setVisible(false);
                }
                invoer.clear();
            }
        });


        verkoop.setOnAction(event -> {
            controller.save("artikel." + PropertiesLoadSave.load("DATABASE"), controller.getWinkelWagentje());
            controller.clearCart();
            controller.notifyObservers();
            tabel.setItems(controller.getWinkelWagentje());
            totaal.setVisible(false);
            onHold.setVisible(false);
            offHold.setVisible(true);
        });

        onHold.setOnAction(event ->{
            controller.zetOnHold();
            controller.notifyObservers();
            onHold.setVisible(controller.legeOnHold());
            tabel.setItems(controller.getWinkelWagentje());
            totaal.setVisible(false);
        });

        offHold.setOnAction(event ->{
            if (controller.legeOnHold()) {
                Alert fout = new Alert(Alert.AlertType.ERROR);
                fout.setTitle("FOUT");
                fout.setHeaderText("Lege on hold winkelwagen");
                fout.setContentText("Er is momenteel geen lege on hold");
                fout.showAndWait();
            } else {
                controller.zetOffHold();
                controller.notifyObservers();
                offHold.setVisible(false);
                onHold.setVisible(controller.legeOnHold());
                tabel.setItems(controller.getWinkelWagentje());
                updateTotaal();
            }
        });

    }


    private void updateTotaal(){
        totaal.setVisible(true);
        totaal.setText("Totaal prijs: " + controller.updateTotaalPrijs());
    }

    private void setController(KassaController controller) {
        this.controller = controller;
    }
}
