package application;
	
import controller.KassaController;
import controller.KlantController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Winkel;
import view.KassaView;
import view.KlantView;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Winkel model = new Winkel();
		KassaController kassaController = new KassaController(model);
		KlantController klantController = new KlantController(model);
		kassaController.registerObserver(klantController);
		new KassaView(kassaController);
		new KlantView(klantController);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
