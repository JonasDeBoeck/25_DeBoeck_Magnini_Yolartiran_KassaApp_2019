package view.panels;

import controller.KassaController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import model.Artikel;

import java.util.Map;


public class ProductOverviewPane extends GridPane {
	//TODO Code vereenvoudigen
	private TableView<Artikel> table;
	private KassaController controller;
	
	public ProductOverviewPane(KassaController controller) {
		setController(controller);

		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
		this.add(new Label("Products:"), 0, 0, 1, 1);

		TableColumn<Map.Entry<String, Artikel>, String> artikelID = new TableColumn<>("Artikel ID");
		artikelID.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Artikel>, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, Artikel>, String> entryStringCellDataFeatures) {
				return new SimpleStringProperty(entryStringCellDataFeatures.getValue().getKey());
			}
		});

		TableColumn<Map.Entry<String, Artikel>, String> naam = new TableColumn<>("Naam");
		naam.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Artikel>, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, Artikel>, String> entryStringCellDataFeatures) {
				return new SimpleStringProperty(entryStringCellDataFeatures.getValue().getValue().getNaam());
			}
		});

		TableColumn<Map.Entry<String, Artikel>, String> prijs = new TableColumn<>("Prijs");
		prijs.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Artikel>, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, Artikel>, String> entryStringCellDataFeatures) {
				return new SimpleStringProperty(String.valueOf(entryStringCellDataFeatures.getValue().getValue().getPrijs()));
			}
		});

		TableColumn<Map.Entry<String, Artikel>, String> aantalInStock = new TableColumn<>("Aantal in Stock");
		aantalInStock.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Artikel>, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, Artikel>, String> entryStringCellDataFeatures) {
				return new SimpleStringProperty(String.valueOf(entryStringCellDataFeatures.getValue().getValue().getAantalInStock()));
			}
		});

		TableColumn<Map.Entry<String, Artikel>, String> categorie = new TableColumn<>("Categorie");
		categorie.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Artikel>, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, Artikel>, String> entryStringCellDataFeatures) {
				return new SimpleStringProperty(String.valueOf(entryStringCellDataFeatures.getValue().getValue().getArtikelCategorie()));
			}
		});

		ObservableList<Map.Entry<String, Artikel>> items = FXCollections.observableArrayList(controller.getProducten().entrySet());
		final TableView<Map.Entry<String, Artikel>> table = new TableView<>(items);

		table.getColumns().setAll(artikelID, naam, prijs, aantalInStock, categorie);
		table.getSortOrder().add(naam);
		table.sort();
		this.add(table, 0,1,1,2);
	}

	public void setController(KassaController controller) {
		this.controller = controller;
	}
}
