package application;
	
import controller.KassaController;
import database.DatabaseContext;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Winkel;
import view.KassaView;
import view.KlantView;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Winkel model = new Winkel();
		KassaController controller = new KassaController(model);
		KassaView kassaView = new KassaView(controller);
		KlantView klantView = new KlantView();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
