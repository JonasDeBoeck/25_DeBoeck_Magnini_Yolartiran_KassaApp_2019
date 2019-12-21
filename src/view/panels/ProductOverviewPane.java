package view.panels;

import controller.KassaController;
import controller.OverviewController;
import database.PropertiesLoadSave;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import model.Artikel;

import java.util.Map;


public class ProductOverviewPane extends GridPane {
	private TableView<Artikel> table;
	private OverviewController controller;
	
	public ProductOverviewPane(OverviewController controller) {
		setController(controller);
		controller.setView(this);
		table = new TableView<>();
		this.setPadding(new Insets(10,10,10,10));
		this.setVgap(8);
		this.setHgap(10);
		this.add(new Label("Products:"), 0, 0, 1, 1);

		TableColumn<Artikel,String> articleID = new TableColumn<>("artikel code");
		articleID.setCellValueFactory(new PropertyValueFactory<>("artikelId"));

		TableColumn<Artikel,String> naam = new TableColumn<>("naam");
		naam.setCellValueFactory(new PropertyValueFactory<>("naam"));

		TableColumn<Artikel,String> categorie = new TableColumn<>("artikel categorie");
		categorie.setCellValueFactory(new PropertyValueFactory<>("artikelCategorie"));

		TableColumn<Artikel,String> prijs = new TableColumn<>("Prijs");
		prijs.setCellValueFactory(new PropertyValueFactory<>("prijs"));

		TableColumn<Artikel,String> stock = new TableColumn<>("Stock");
		stock.setCellValueFactory(new PropertyValueFactory<>("aantalInStock"));

		table.getColumns().add(articleID);
		table.getColumns().add(naam);
		table.getColumns().add(categorie);
		table.getColumns().add(prijs);
		table.getColumns().add(stock);

		table.getItems().addAll(controller.getProducten().values());
		table.sort();
		this.add(table, 0,1,1,2);

	}

	public void setController(OverviewController controller) {
		this.controller = controller;
	}

	public void updateStock(){
        controller.load("artikel." + PropertiesLoadSave.load("DATABASE"));
        table.getItems().setAll(controller.getProducten().values());
    }


}
