package game.poker.view;


import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import PokerPackage.Card;
import PokerPackage.Deck;
import PokerPackage.Play;
import PokerPackage.Player;
import PokerPackage.Rank;
import PokerPackage.Suit;
import PokerPackage.eGame;
import game.poker.MainApp;
import game.poker.RootController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;



public class TableController{

	private Play playGame;
	private ArrayList<Player> players = new ArrayList<Player>();
	@FXML
	private HBox communityCards;
	@FXML
	private BorderPane pokerTable;
	private MainApp mainApp;
	
	@FXML
	private HBox deckBox;
	@FXML
	private Button startGame;
	
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
	private Map<Player, Map<String, Object>> playersAndNodes = new HashMap<Player, Map<String, Object>>();
	
	public TableController(){
		
	}
	
	@FXML
	private void initialize(){
		startGame.setOnAction(this::handleStartGame);
		
		ImageView deckImage = new ImageView(new Image(getClass().getResourceAsStream(
				"/img/b1fh.png"), 75, 75, true, true));
		this.deckBox.getChildren().add(deckImage);
		
		Map<String, Object> p1Nodes = new HashMap<String, Object>(){{
			put("Sit", p1Sit);
			put("Leave", p1Leave);
			put("Name", p1Name);
			put("NameEntry", p1NameEntry);
			put("CardBox", p1CardBox);
		}};
		
		Map<String, Object> p2Nodes = new HashMap<String, Object>(){{
			put("Sit", p2Sit);
			put("Leave", p2Leave);
			put("Name", p2Name);
			put("NameEntry", p2NameEntry);
			put("CardBox", p2CardBox);
		}};
		
		Map<String, Object> p3Nodes = new HashMap<String, Object>(){{
			put("Sit", p3Sit);
			put("Leave", p3Leave);
			put("Name", p3Name);
			put("NameEntry", p3NameEntry);
			put("CardBox", p3CardBox);
		}};
		
		Map<String, Object> p4Nodes = new HashMap<String, Object>(){{
			put("Sit", p4Sit);
			put("Leave", p4Leave);
			put("Name", p4Name);
			put("NameEntry", p4NameEntry);
			put("CardBox", p4CardBox);
		}};
		
		playersAndNodes.put(new Player(), p1Nodes); playersAndNodes.put(new Player(), p2Nodes); playersAndNodes.put(new Player(), p3Nodes);
		playersAndNodes.put(new Player(), p4Nodes);
		
		for (Map<String, Object> playerMap : playersAndNodes.values()){
			((Button) playerMap.get("Leave")).setVisible(false);
			((Text) playerMap.get("Name")).setVisible(false);
			((Button) playerMap.get("Sit")).setOnAction(this::handleSit);
		}
		
		
		//p1Nodes.put("Sit", p1Sit); p1Nodes.put("Leave", p1Leave); p1Nodes.put("Name", p1Name); p1Nodes.put("NameEntry", p1NameEntry); p1Nodes.put(key, value)
		/*p1Leave.setVisible(false); p2Leave.setVisible(false); p3Leave.setVisible(false); p4Leave.setVisible(false);
		p1Name.setVisible(false); p2Name.setVisible(false); p3Name.setVisible(false); p4Name.setVisible(false);
		p1Sit.setOnAction(this::handleSit); p2Sit.setOnAction(this::handleSit); p3Sit.setOnAction(this::handleSit);
		p4Sit.setOnAction(this::handleSit);*/
		
	}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
	}
	
	private void handleSit(ActionEvent event){
		Button btn = (Button) event.getSource();
		for (Player player : playersAndNodes.keySet()){
			//Button playerButton = (Button) playersAndNodes.get(player).get("Sit");
			//if (playerButton == null) System.out.println("True");
			if (((Button) playersAndNodes.get(player).get("Sit")).getId() == btn.getId()){
				player.setName(((TextField) playersAndNodes.get(player).get("NameEntry")).getText());
				((Button) playersAndNodes.get(player).get("Sit")).setVisible(false);
				((Button) playersAndNodes.get(player).get("Leave")).setVisible(true);
				((Button) playersAndNodes.get(player).get("Leave")).setOnAction(this::handleLeave);
				((Text) playersAndNodes.get(player).get("Name")).setText(player.getName());
				((Text) playersAndNodes.get(player).get("Name")).setVisible(true);
				((TextField) playersAndNodes.get(player).get("NameEntry")).setVisible(false);
				//((HBox) playersAndNodes.get(player).get("CardBox")).setVisible(true);
			}
		}
		/*
		switch (id){
		case "p1Sit":
			name = p1NameEntry.getText();
			PlayerDomain player1 = new PlayerDomain();
			player1.setName(name);
			players.add(player1);
			p1Leave.setVisible(true); p1Leave.setOnAction(this::handleLeave);
			break;
			
		case "p2Sit":
			name = p2NameEntry.getText();
			PlayerDomain player2 = new PlayerDomain();
			player2.setName(name);
			players.add(player2);
			p2Leave.setVisible(true); p2Leave.setOnAction(this::handleLeave);
			break;
			
		case "p3Sit":
			name = p3NameEntry.getText();
			PlayerDomain player3 = new PlayerDomain();
			player3.setName(name);
			players.add(player3);
			p3Leave.setVisible(true); p3Leave.setOnAction(this::handleLeave);
			break;
			
		case "p4Sit":
			name = p4NameEntry.getText();
			PlayerDomain player4 = new PlayerDomain();
			player4.setName(name);
			players.add(player4);
			p4Leave.setVisible(true); p4Leave.setOnAction(this::handleLeave);
		}
		*/
	}
	
	@FXML
	private void handleLeave(ActionEvent event){
		Button btn = (Button) event.getSource();
		for (Player player : playersAndNodes.keySet()){
			if (((Button) playersAndNodes.get(player).get("Leave")).getId() == btn.getId()){
				// IF YOU HAVE PROBLEMS IN THE DAL LAYER, START HERE
				player.generateNewId();
				((Button) playersAndNodes.get(player).get("Sit")).setVisible(true);
				((Button) playersAndNodes.get(player).get("Leave")).setVisible(false);
				((Button) playersAndNodes.get(player).get("Sit")).setOnAction(this::handleSit);
				((Text) playersAndNodes.get(player).get("Name")).setVisible(false);
				((TextField) playersAndNodes.get(player).get("NameEntry")).clear();
				((TextField) playersAndNodes.get(player).get("NameEntry")).setVisible(true);
			}
		}
	}
	
	@FXML
	private void handleStartGame(ActionEvent e){
		this.startGame.setVisible(false);
		eGame game = RootController.getGameType();
		
		
		this.playGame = new Play(game);
		//this.playGame.setPlayers((ArrayList<Player>) playersAndNodes.keySet());
		for (Player p : playersAndNodes.keySet()){
			if (((Button) playersAndNodes.get(p).get("Sit")).isVisible() == false){
				this.playGame.addPlayer(p);
			}
		}
		
		this.playGame.play();
		for (Player p : playersAndNodes.keySet()){
			System.out.println(p.getHand());
		}
		//ImageView deckImage = new ImageView("PokerGUI/resources/img/b1fh.png");
		
		
		for (Player p : playersAndNodes.keySet()){
			if (p.getHand() != null){
				for (Card c : p.getHand().getHand()){
					((HBox) playersAndNodes.get(p).get("CardBox")).getChildren().add((ImageView)getCardImage(c));
					//communityCards.getChildren().add(getCardImage(c));
				}
			}
		}
	}
	
	private void deal(){
		ImageView cardBackImg = new ImageView(new Image(getClass().getResourceAsStream(
				"/img/b1fv.png"), 75, 75, true, true));
		// this.playGame.getEGame().getCardsDealt() yields how many cards are to be dealt to each player
		for (int i = 0; i < this.playGame.getEGame().getCardsDealt(); i++){
			for (Player p : playersAndNodes.keySet()){
				if (p.getHand() != null){
					// Getting the card to be dealt
					p.getHand().getHand().get(i);
				}
			}
		}
	}
	
	private ImageView getCardImage(Card c){
		String src = c.getSuit().getSuit() + c.getRank().getRank();
		String cardFile = "/img/" + src + ".png";
		return new ImageView(new Image(getClass().getResourceAsStream(
				cardFile), 75, 75, true, true));
	}
}
