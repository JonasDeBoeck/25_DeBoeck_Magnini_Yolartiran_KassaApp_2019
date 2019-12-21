package view;

import controller.KassaController;
import controller.LogController;
import controller.OverviewController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class KassaView {
	private Stage stage = new Stage();
	private KassaController controller;
	private LogController log;
	private OverviewController overviewController;

	public KassaView(KassaController controller, LogController log, OverviewController overviewController){
		setController(controller);
		setLog(log);
		controller.setView(this);
		stage.setTitle("KASSA VIEW");
		stage.setResizable(false);		
		stage.setX(20);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 750, 500);
		BorderPane borderPane = new KassaMainPane(this.controller, log, overviewController);
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(borderPane);
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}

	private void setController(KassaController controller) {
		this.controller = controller;
	}

	public void setLog(LogController log) {
		this.log = log;
	}
}
