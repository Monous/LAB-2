package game.poker;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import model.eGame;

public class RootController {
	private MainApp mainApp;
	private static eGame game;
	
	@FXML
	private MenuItem fiveCardStud;
	@FXML
	private MenuItem fiveCardJokerPoker;
	@FXML
	private MenuItem fiveCardWildPoker;
	@FXML
	private MenuItem fiveCardDraw;
	@FXML
	private MenuItem sevenCardDraw;
	@FXML
	private MenuItem texasHoldEm;
	@FXML
	private MenuItem omaha;
	
	ArrayList<MenuItem> gameItems;
	
	@FXML
	private void initialize(){
		/*fiveCardStud.setVisible(false);
		setGame(fiveCardStud);
		fiveCardJokerPoker.setOnAction(this::handleGame);
		fiveCardWildPoker.setOnAction(this::handleGame);
		fiveCardDraw.setOnAction(this::handleGame);
		sevenCardDraw.setOnAction(this::handleGame);
		texasHoldEm.setOnAction(this::handleGame);
		omaha.setOnAction(this::handleGame);
		*/
		gameItems = new ArrayList<MenuItem>(Arrays.asList(fiveCardStud, fiveCardJokerPoker, fiveCardWildPoker, fiveCardDraw,
				sevenCardDraw, texasHoldEm, omaha));
		
		for (MenuItem mi : gameItems){
			mi.setOnAction(this::handleGame);
			if (mi == fiveCardStud){
				mi.setVisible(false);
				setGame(fiveCardStud);
			}
		}
		
	}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
	}
	
	private void handleGame(ActionEvent e){
		MenuItem gameType = (MenuItem) e.getSource();
		
		for (MenuItem mi : gameItems){
			if (!mi.isVisible()) mi.setVisible(true);
			if (mi == gameType){
				mi.setVisible(false);
				setGame(gameType);
			}
		}
		//setGame(gameType);
	}
	
	private void setGame(MenuItem game){
		switch (game.getId()){
		case "fiveCardStud":
			this.game = eGame.FIVECARDSTUD;
			break;
		case "fiveCardJokerPoker":
			this.game = eGame.FIVECARDJOKERPOKER;
			break;
		case "fiveCardWildPoker":
			this.game = eGame.FIVECARDWILDPOKER;
			break;
		case "fiveCardDraw":
			this.game = eGame.FIVECARDDRAW;
			break;
		case "sevenCardDraw":
			this.game = eGame.SEVENCARDDRAW;
			break;
		case "texasHoldEm":
			this.game = eGame.HOLDEM;
			break;
		case "omaha":
			this.game = eGame.OMAHA;
			break;
		}
	}
	
	public static eGame getGameType(){
		return game;
	}

}
