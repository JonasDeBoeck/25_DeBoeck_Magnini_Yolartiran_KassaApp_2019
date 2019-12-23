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

/**
 * @Author Thibault Magnini
 */

public class KassaTabPane extends GridPane {

    private TableView<Artikel> tabel;
    private KassaController controller;
    private Label totaal, totaalMetKorting, korting;
    private TextField invoer;
    private Button afsluit, onHold, offHold, betaal, annuleer;

    public KassaTabPane(KassaController controller) {
        tabel = new TableView<>();
        setController(controller);
        invoer = new TextField("Vul hier een Artikel code in!");
        betaal = new Button("BETAAL");
        totaal = new Label();
        afsluit = new Button("Afsluiten");
        onHold = new Button("On hold");
        offHold = new Button("Haal on hold winkelwagentje terug");
        totaalMetKorting = new Label();
        korting = new Label();
        annuleer = new Button("ANNULEER");

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
                                    if (controller.getState().equals("State: Inactief")){
                                        controller.notifyObserversAfsluit();
                                    } else controller.notifyObservers();
                                    korting.setText("Korting: " + controller.getTotaleKorting());
                                    totaalMetKorting.setText("Totaal met korting: "+ controller.getTotaalPrijsMetKorting());
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
        this.add(afsluit, 2,4);
        this.add(onHold,3,1);
        this.add(offHold, 3,1);
        this.add(betaal, 2,4);
        this.add(korting, 1,2);
        this.add(totaalMetKorting, 2,2);
        this.add(annuleer, 3,4);
        onHold.setVisible(false);
        betaal.setVisible(false);
        annuleer.setVisible(false);
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


        afsluit.setOnAction(event -> {
            if (controller.leegMandje()) {
                Alert fout = new Alert(Alert.AlertType.ERROR);
                fout.setTitle("FOUT");
                fout.setHeaderText("Lege Winkelkar");
                fout.setContentText("Je kan niet afsluiten met een lege winkel kar");
                fout.showAndWait();
            } else {
                try {
                    controller.setKorting();
                    korting.setText("Korting: " + controller.getTotaleKorting());
                    totaalMetKorting.setText("Totaal met korting: " + controller.getTotaalPrijsMetKorting());
                    controller.notifyObserversAfsluit();
                    controller.setStateOnInactief();
                    totaal.setVisible(true);
                    invoer.setVisible(false);
                    annuleer.setVisible(true);
                    offHold.setVisible(false);
                    onHold.setVisible(false);
                    betaal.setVisible(true);
                    afsluit.setVisible(false);
                    korting.setVisible(true);
                    totaalMetKorting.setVisible(true);
                } catch (IndexOutOfBoundsException e) {
                    Alert fout = new Alert(Alert.AlertType.ERROR);
                    fout.setTitle("FOUT");
                    fout.setHeaderText("Lege Winkelkar");
                    fout.setContentText("Je kan niet afsluiten met een lege winkel kar");
                    fout.showAndWait();
                }
            }
        });

        betaal.setOnAction(event -> {
            controller.setStateOnBetaald();
            controller.printKassaBon();
            controller.save("artikel." + PropertiesLoadSave.load("DATABASE"), controller.getWinkelWagentje());
            controller.notifyObserversBetaal();
            controller.clearCart();
            controller.notifyObservers();
            tabel.setItems(controller.getWinkelWagentje());
            totaal.setVisible(true);
            invoer.setVisible(true);
            tabel.setVisible(true);
            offHold.setVisible(true);
            onHold.setVisible(false);
            betaal.setVisible(false);
            afsluit.setVisible(true);
            korting.setVisible(false);
            totaalMetKorting.setVisible(false);
            annuleer.setVisible(false);
            updateTotaal();
        });

        onHold.setOnAction(event ->{
            controller.zetOnHold();
            controller.notifyObservers();
            onHold.setVisible(controller.legeOnHold());
            tabel.setItems(controller.getWinkelWagentje());
            totaal.setVisible(false);
        });

        annuleer.setOnAction(event -> {
            controller.setStateOnGeannuleerd();
            controller.clearCart();
            controller.notifyObservers();
            tabel.setItems(controller.getWinkelWagentje());
            totaal.setVisible(true);
            invoer.setVisible(true);
            tabel.setVisible(true);
            offHold.setVisible(true);
            onHold.setVisible(false);
            betaal.setVisible(false);
            afsluit.setVisible(true);
            korting.setVisible(false);
            totaalMetKorting.setVisible(false);
            controller.setStateOnActief();
            updateTotaal();
        });

        offHold.setOnAction(event ->{
            if (controller.legeOnHold()) {
                Alert fout = new Alert(Alert.AlertType.ERROR);
                fout.setTitle("FOUT");
                fout.setHeaderText("Lege on hold winkelwagen");
                fout.setContentText("Er is momenteel geen on hold winkelwagentje");
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
