package application;
	
import controller.KassaController;
import controller.KlantController;
import controller.LogController;
import controller.OverviewController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Winkel;
import view.KassaView;
import view.KlantView;

/**
 * @Author Jonas De Boeck, Thibault Magnini
 * */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Winkel model = new Winkel();
		KassaController kassaController = new KassaController(model);
		LogController logController = new LogController(model);
		KlantController klantController = new KlantController(model);
        OverviewController overviewController = new OverviewController(model);
		kassaController.registerObserver(klantController);
		kassaController.registerObserver(overviewController);
		kassaController.registerObserver(logController);
		new KassaView(kassaController, logController, overviewController);
		new KlantView(klantController);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
