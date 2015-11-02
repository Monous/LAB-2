package game.poker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import PokerPackage.Table;
import game.poker.view.TableController;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private BorderPane pokerTable;
	private Table table = new Table();
	
	public MainApp(){
		
	}
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Poker");
		
		initRootLayout();
		
		showPokerTable();
	}
	
	public void initRootLayout(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			
			this.rootLayout = (BorderPane) loader.load();
			
			Scene scene = new Scene(rootLayout);
			
			this.primaryStage.setScene(scene);
			this.primaryStage.show();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void showPokerTable(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PokerTable.fxml"));
			this.pokerTable = (BorderPane) loader.load();
			this.rootLayout.setCenter(pokerTable);
			
			TableController controller = loader.getController();
			controller.setMainApp(this);
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
