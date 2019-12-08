package view;

import controller.KlantController;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Artikel;

public class KlantView extends GridPane{
	private Stage stage = new Stage();
	private KlantController controller;
		
	public KlantView(KlantController controller){
		setController(controller);

		stage.setTitle("KLANT VIEW");
		stage.setResizable(false);
		stage.setX(775);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 500, 500);
		scene.getStylesheets().add("view/panels/stylesheets/style.css");
		GridPane gridPane = new KlantMainPane(this.controller);
		gridPane.prefHeightProperty().bind(scene.heightProperty());
		gridPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(gridPane);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();		
	}

	private void setController(KlantController controller) {
		this.controller = controller;
	}
}
