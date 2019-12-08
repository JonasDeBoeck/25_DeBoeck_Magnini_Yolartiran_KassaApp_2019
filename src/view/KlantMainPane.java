package view;

import controller.KlantController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import model.Artikel;

import java.util.Map;

public class KlantMainPane extends GridPane {
    private KlantController controller;
    private TableView<Map.Entry<Artikel, Integer>> tabel;
    private Label totaal, totaalMetKorting, korting;

    public KlantMainPane (KlantController controller) {
        setController(controller);
        controller.setView(this);
        tabel = new TableView<>();
        totaal = new Label("Totaal prijs: ");
        totaalMetKorting = new Label();
        korting = new Label();

        TableColumn<Map.Entry<Artikel, Integer>, String> naam = new TableColumn<>("Artikel");
        naam.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Artikel, Integer>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<Artikel, Integer>, String> param) {
                return new SimpleStringProperty(String.valueOf(param.getValue().getKey().getNaam()));
            }
        });

        TableColumn<Map.Entry<Artikel, Integer>, String> prijs = new TableColumn<>("Prijs");
        prijs.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Artikel, Integer>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<Artikel, Integer>, String> param) {
                return new SimpleStringProperty(String.valueOf(param.getValue().getKey().getPrijs()));
            }
        });

        TableColumn<Map.Entry<Artikel, Integer>, String> aantal = new TableColumn<>("Aantal");
        aantal.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Artikel, Integer>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<Artikel, Integer>, String> param) {
                return new SimpleStringProperty(String.valueOf(param.getValue().getValue()));
            }
        });

        tabel.getColumns().add(naam);
        tabel.getColumns().add(prijs);
        tabel.getColumns().add(aantal);

        tabel.setMinSize(220,300);
        tabel.setMaxWidth(242);

        this.setPadding(new Insets(10,10,10,10));
        this.setVgap(8);
        this.setHgap(10);

        this.add(tabel, 0, 0);
        this.add(totaal, 0, 1);
        this.add(totaalMetKorting, 0,2);
        this.add(korting, 0,3);

        totaalMetKorting.setVisible(false);
        korting.setVisible(false);

    }

    private void setController(KlantController controller) {
        this.controller = controller;
    }

    public void update (ObservableMap<Artikel, Integer> artikels, String prijs) {
        tabel.setVisible(true);
        totaalMetKorting.setVisible(false);
        korting.setVisible(false);
        tabel.setItems(FXCollections.observableArrayList(artikels.entrySet()));
        totaal.setText("Totaal prijs: " + prijs);
    }

    public void updateAfsluit(String totalePrijs, String korting, String totalePrijsMetKorting){
        tabel.setVisible(false);
        totaal.setText("Totaal prijs: " + totalePrijs);
        totaalMetKorting.setText("Totale prijs met korting: " + totalePrijsMetKorting);
        totaalMetKorting.setVisible(true);
        this.korting.setText("Verdiende korting: " + korting);
        this.korting.setVisible(true);
    }
}
