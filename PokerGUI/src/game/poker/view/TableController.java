package game.poker.view;


import java.awt.event.ActionListener;

import PokerPackage.Table;
import game.poker.MainApp;
import game.poker.PlayerController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class TableController{
	private Table table;
	private MainApp mainApp;
	
	@FXML
	private Button p1Sit;
	@FXML
	private Button p1Leave;
	@FXML
	private Text p1Name;
	@FXML
	private TextField p1NameEntry;
	@FXML
	private HBox p1CardBox;
	
	
	@FXML
	private Button p2Sit;
	@FXML
	private Button p2Leave;
	@FXML
	private Text p2Name;
	@FXML
	private TextField p2NameEntry;
	@FXML
	private HBox p2CardBox;
	
	
	@FXML
	private Button p3Sit;
	@FXML 
	private Button p3Leave;
	@FXML
	private Text p3Name;
	@FXML
	private TextField p3NameEntry;
	@FXML
	private HBox p3CardBox;
	
	
	@FXML
	private Button p4Sit;
	@FXML
	private Button p4Leave;
	@FXML
	private Text p4Name;
	@FXML
	private TextField p4NameEntry;
	@FXML
	private HBox p4CardBox;
	
	public TableController(){
		
	}
	
	@FXML
	private void initialize(){
		p1Leave.setVisible(false); /*p2Leave.setVisible(false);*/ p3Leave.setVisible(false); p4Leave.setVisible(false);
		p1Sit.setOnAction(this::handleSit); p2Sit.setOnAction(this::handleSit); p3Sit.setOnAction(this::handleSit);
		p4Sit.setOnAction(this::handleSit);
		/*p1Sit.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				
			}
		});*/
	}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
	}
	
	private void handleSit(ActionEvent event){
		//PlayerController playerController;
		Button btn = (Button) event.getSource();
		String id = btn.getId();
		switch (id){
		case "p1Sit":
			System.out.println("workd");
			break;
		case "p2Sit":
			System.out.println("p2");
			break;
		case "p3Sit":
			System.out.println("p3");
			break;
		case "p4Sit":
			System.out.println("p4");
			break;
		}
	}
	
	
	@FXML
	public void handleLeave(){
		
	}
	
	@FXML
	public void handleStartGame(){
		
	}
}
