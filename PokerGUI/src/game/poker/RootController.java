package game.poker;

import PokerPackage.eGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

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
	
	@FXML
	private void initialize(){
		fiveCardStud.setVisible(false);
		setGame(fiveCardStud);
		fiveCardJokerPoker.setOnAction(this::handleGame);
		fiveCardWildPoker.setOnAction(this::handleGame);
		fiveCardDraw.setOnAction(this::handleGame);
		sevenCardDraw.setOnAction(this::handleGame);
		texasHoldEm.setOnAction(this::handleGame);
		omaha.setOnAction(this::handleGame);
		
		
	}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
	}
	
	private void handleGame(ActionEvent e){
		MenuItem gameType = (MenuItem) e.getSource();
		setGame(gameType);
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
