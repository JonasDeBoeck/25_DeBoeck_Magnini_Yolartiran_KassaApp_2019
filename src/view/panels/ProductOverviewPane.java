package view.panels;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.Artikel;
import model.Winkel;

import java.util.Map;


public class ProductOverviewPane extends GridPane {
	private TableView<Artikel> table;
	private Winkel winkel;
	
	
	public ProductOverviewPane() {
		winkel = new Winkel();
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
		this.add(new Label("Products:"), 0, 0, 1, 1);
		table = new TableView<>();
		TableColumn<Artikel, String> artikelId = new TableColumn<Artikel, String>("Artikel ID");
		TableColumn<Artikel, String> naam = new TableColumn<Artikel, String>("Naam");
		TableColumn<Artikel, Double> prijs = new TableColumn<Artikel, Double>("Prijs");
		TableColumn<Artikel, Integer> aantal = new TableColumn<Artikel, Integer>("Aantal");
		TableColumn<Artikel, String> categorie = new TableColumn<Artikel, String>("Artikel Categorie");
		table.getColumns().addAll(artikelId, naam, prijs, aantal, categorie);
		this.add(table, 0,1,1,2);
		for (Artikel artikel : winkel.getArtikels().values()) {

		}
	}
	
	

}
