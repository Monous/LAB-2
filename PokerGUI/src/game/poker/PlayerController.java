package game.poker;

import java.util.HashMap;
import java.util.Map;

import PokerPackage.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class PlayerController {
	private Player p;
	private MainApp mainApp;
	/*
	 * to get the buttons for each players, reference the fxml buttons in tablecontroller then add to the buttons arraylist
	 */
	Map<String, Object> playerNodes = new HashMap<String, Object>();
	
	private PlayerController(){
		
	}
	
	public void initialize(){
		
	}
	
	public void addPerson(){
		
	}
	
	// The Object can be casted to Button, HBOX, etc
	public void addNode(String nameOfNode, Object node){
		playerNodes.put(nameOfNode, node);
	}
}
