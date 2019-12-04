package view.panels;

import controller.KassaController;
import database.PropertiesLoadSave;
import javafx.beans.property.ReadOnlyObjectWrapper;
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
    private Button verkoop;

    public KassaTabPane(KassaController controller) {
        tabel = new TableView<>();
        setController(controller);
        invoer = new TextField();

        totaal = new Label();
        verkoop = new Button("BETAAL");

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
                    fout.setHeaderText("Artikel niet gevonden");
                    fout.setContentText("Het artikel dat u heeft opgegeven kan niet worden gevonden");
                    fout.showAndWait();
                } else {
                    controller.addToCart(art);
                    tabel.setItems(controller.getWinkelWagentje());
                    controller.notifyObservers();
                    updateTotaal();
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
        });
    }


    private void updateTotaal(){
        totaal.setVisible(true);
        totaal.setText("totaal: " + controller.updateTotaalPrijs());
    }

    private void setController(KassaController controller) {
        this.controller = controller;
    }
}
