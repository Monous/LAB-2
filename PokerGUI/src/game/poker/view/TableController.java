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
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class TableController {

	private Play playGame;
	private ArrayList<Player> players = new ArrayList<Player>();
	
	@FXML
	private Text winnerText;
	@FXML
	private Button playAgain;
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
	private Text p1HandType;
	

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
	private Text p2HandType;

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
	private Text p3HandType;
	

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
	@FXML
	private Text p4HandType;
	

	private Map<Player, Map<String, Object>> playersAndNodes = new HashMap<Player, Map<String, Object>>();


	public TableController() {

	}

	@FXML
	private void initialize() {
		playAgain.setVisible(false);
		startGame.setOnAction(this::handleStartGame);
		winnerText.setVisible(false);
	
		ImageView deckImage = new ImageView(
				new Image(getClass().getResourceAsStream("/img/b1fh.png"), 75, 75, true, true));
		this.deckBox.getChildren().add(deckImage);

		Map<String, Object> p1Nodes = new HashMap<String, Object>() {
			{
				put("Sit", p1Sit);
				put("Leave", p1Leave);
				put("Name", p1Name);
				put("NameEntry", p1NameEntry);
				put("CardBox", p1CardBox);
				put("HandTypeText", p1HandType);
			}
		};

		Map<String, Object> p2Nodes = new HashMap<String, Object>() {
			{
				put("Sit", p2Sit);
				put("Leave", p2Leave);
				put("Name", p2Name);
				put("NameEntry", p2NameEntry);
				put("CardBox", p2CardBox);
				put("HandTypeText", p2HandType);
			}
		};

		Map<String, Object> p3Nodes = new HashMap<String, Object>() {
			{
				put("Sit", p3Sit);
				put("Leave", p3Leave);
				put("Name", p3Name);
				put("NameEntry", p3NameEntry);
				put("CardBox", p3CardBox);
				put("HandTypeText", p3HandType);
			}
		};

		Map<String, Object> p4Nodes = new HashMap<String, Object>() {
			{
				put("Sit", p4Sit);
				put("Leave", p4Leave);
				put("Name", p4Name);
				put("NameEntry", p4NameEntry);
				put("CardBox", p4CardBox);
				put("HandTypeText", p4HandType);
			}
		};

		playersAndNodes.put(new Player(), p1Nodes);
		playersAndNodes.put(new Player(), p2Nodes);
		playersAndNodes.put(new Player(), p3Nodes);
		playersAndNodes.put(new Player(), p4Nodes);

		for (Map<String, Object> playerMap : playersAndNodes.values()) {
			((Button) playerMap.get("Leave")).setVisible(false);
			((Text) playerMap.get("Name")).setVisible(false);
			((Button) playerMap.get("Sit")).setOnAction(this::handleSit);
			((Text) playerMap.get("HandTypeText")).setVisible(false);
		}

	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	private void handleSit(ActionEvent event) {
		Button btn = (Button) event.getSource();
		for (Player player : playersAndNodes.keySet()) {
			// Button playerButton = (Button)
			// playersAndNodes.get(player).get("Sit");
			// if (playerButton == null) System.out.println("True");
			if (((Button) playersAndNodes.get(player).get("Sit")).getId() == btn.getId()) {
				player.setName(((TextField) playersAndNodes.get(player).get("NameEntry")).getText());
				((Button) playersAndNodes.get(player).get("Sit")).setVisible(false);
				((Button) playersAndNodes.get(player).get("Leave")).setVisible(true);
				((Button) playersAndNodes.get(player).get("Leave")).setOnAction(this::handleLeave);
				((Text) playersAndNodes.get(player).get("Name")).setText(player.getName());
				((Text) playersAndNodes.get(player).get("Name")).setVisible(true);
				((TextField) playersAndNodes.get(player).get("NameEntry")).setVisible(false);
				// ((HBox)
				// playersAndNodes.get(player).get("CardBox")).setVisible(true);
			}
		}
		/*
		 * switch (id){ case "p1Sit": name = p1NameEntry.getText(); PlayerDomain
		 * player1 = new PlayerDomain(); player1.setName(name);
		 * players.add(player1); p1Leave.setVisible(true);
		 * p1Leave.setOnAction(this::handleLeave); break;
		 * 
		 * case "p2Sit": name = p2NameEntry.getText(); PlayerDomain player2 =
		 * new PlayerDomain(); player2.setName(name); players.add(player2);
		 * p2Leave.setVisible(true); p2Leave.setOnAction(this::handleLeave);
		 * break;
		 * 
		 * case "p3Sit": name = p3NameEntry.getText(); PlayerDomain player3 =
		 * new PlayerDomain(); player3.setName(name); players.add(player3);
		 * p3Leave.setVisible(true); p3Leave.setOnAction(this::handleLeave);
		 * break;
		 * 
		 * case "p4Sit": name = p4NameEntry.getText(); PlayerDomain player4 =
		 * new PlayerDomain(); player4.setName(name); players.add(player4);
		 * p4Leave.setVisible(true); p4Leave.setOnAction(this::handleLeave); }
		 */
	}

	@FXML
	private void handleLeave(ActionEvent event) {
		Button btn = (Button) event.getSource();
		for (Player player : playersAndNodes.keySet()) {
			if (((Button) playersAndNodes.get(player).get("Leave")).getId() == btn.getId()) {
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
	private void handleStartGame(ActionEvent e) {
		this.startGame.setVisible(false);
		eGame game = RootController.getGameType();

		this.playGame = new Play(game);
		// this.playGame.setPlayers((ArrayList<Player>)
		// playersAndNodes.keySet());
		for (Player p : playersAndNodes.keySet()) {
			if (((Button) playersAndNodes.get(p).get("Sit")).isVisible() == false) {
				this.playGame.addPlayer(p);
			}
		}

		this.playGame.play();
		for (Player p : playersAndNodes.keySet()) {
			System.out.println(p.getHand());
		}
		// ImageView deckImage = new
		// ImageView("PokerGUI/resources/img/b1fh.png");

		deal();
		
		showWinners();
		playAgain.setOnAction(this::handlePlayAgain);
		playAgain.setVisible(true);
		
	}
	
	private void handlePlayAgain(ActionEvent e){
		this.communityCards.getChildren().clear();
		for (Map<String, Object> playerMap : playersAndNodes.values()) {
			((HBox) playerMap.get("CardBox")).getChildren().clear();;
			((Text) playerMap.get("HandTypeText")).setText(null);;
		}
		
		this.winnerText.setText(null);
		this.playAgain.setVisible(false);
		this.startGame.setVisible(true);
	}
	
	public void showWinners(){
		for (Player p : this.playGame.getPlayer()){
			((Text)playersAndNodes.get(p).get("HandTypeText")).setText(p.getHand().getHandType().toString());
			((Text)playersAndNodes.get(p).get("HandTypeText")).setVisible(true);
		}
		
		String winners = new String("The winner(s) are: ");
		//if (this.playGame.getWinners().isEmpty()) System.out.println("True");
		for (Player p : this.playGame.getWinners()){
			// The if statement is because the if there is a tie, the winner's name would show twice....probably should look into this
			if (!winners.contains(p.getName())) winners = winners + p.getName() + "  ";
		}
		this.winnerText.setText(winners);
		this.winnerText.setVisible(true);
	}

	private void deal() {
		// This will work for all games.
		SequentialTransition seqTrans = new SequentialTransition();
		for (int i = 0; i < this.playGame.getEGame().getCardsDealt(); i++) {
			
			for (Player p : this.playGame.getPlayer()) {
				ImageView cardBackImg = new ImageView(
						new Image(getClass().getResourceAsStream("/img/b1fv.png"), 75, 75, true, true));
				
				ImageView curImg = getCardImage(p.getHand().getHand().get(i));
				

				((HBox) playersAndNodes.get(p).get("CardBox")).getChildren().add(curImg);
				curImg.setVisible(false);

				
				Bounds startBounds = this.deckBox.localToScene(this.deckBox.getBoundsInLocal());
				Point2D startPoint = new Point2D(startBounds.getMinX(), startBounds.getMinY());

				Bounds endBounds = ((HBox) playersAndNodes.get(p).get("CardBox")).localToScene(((HBox) playersAndNodes.get(p).get("CardBox")).getBoundsInLocal());
				Point2D endPoint = new Point2D(endBounds.getMinX(), endBounds.getMinY());

				ParallelTransition trans = createTransition(startPoint, endPoint, cardBackImg);

				// final ParallelTransition transFadeCardInOut =
				// createFadeTransition(curImg);
				trans.setCycleCount(1);
				trans.setAutoReverse(false);
				
				trans.onFinishedProperty().set(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent actionEvent) {

						// get rid of the created card, run the fade in/fade out
						// transition
						// This isn't going to fire until the transMoveRotCard
						// is
						// complete.
						pokerTable.getChildren().remove(cardBackImg);

						// transFadeCardInOut.play();

						curImg.setVisible(true);
					
						
						
					}
				});
				
				//trans.play();
				seqTrans.getChildren().add(trans);
			}
			
		}
		
		seqTrans.play();
		
		if (this.playGame.getCommunityCards().size() > 0){
			for (Card c : this.playGame.getCommunityCards()){
				System.out.println(c);
				ImageView curImg = getCardImage(c);
				this.communityCards.getChildren().add(curImg);
			}
		}
		
	}

	private ParallelTransition createTransition(Point2D pntStartPoint, Point2D pntEndPoint, ImageView cardBackImg) {

		if (pokerTable.getChildren().contains(cardBackImg))
			pokerTable.getChildren().remove(cardBackImg);
		
		cardBackImg.setX(pntStartPoint.getX());
		cardBackImg.setY(pntStartPoint.getY() - 30);
		
		this.pokerTable.getChildren().add(cardBackImg);
	

		TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), cardBackImg);
		translateTransition.setFromX(0);
		translateTransition.setToX(pntEndPoint.getX() - pntStartPoint.getX());
		translateTransition.setFromY(0);
		translateTransition.setToY(pntEndPoint.getY() - pntStartPoint.getY());

		translateTransition.setCycleCount(1);
		translateTransition.setAutoReverse(false);

		// int rnd = randInt(1,6);

		// System.out.println(rnd);

		RotateTransition rotateTransition = new RotateTransition(Duration.millis(150), cardBackImg);
		rotateTransition.setByAngle(90F);
		rotateTransition.setCycleCount(3);
		rotateTransition.setAutoReverse(false);

		ParallelTransition parallelTransition = new ParallelTransition();
		parallelTransition.getChildren().addAll(translateTransition, rotateTransition);

		// SequentialTransition seqTrans = new SequentialTransition();
		// seqTrans.getChildren().addAll(parallelTransition);

		return parallelTransition;
	}

	/*
	 * private ParallelTransition createFadeTransition(final ImageView img) {
	 * 
	 * FadeTransition fadeOutTransition = new
	 * FadeTransition(Duration.seconds(.25), cardBackImg);
	 * fadeOutTransition.setFromValue(1.0); fadeOutTransition.setToValue(0.0);
	 * 
	 * FadeTransition fadeInTransition = new
	 * FadeTransition(Duration.seconds(.25), img);
	 * fadeInTransition.setOnFinished(new EventHandler<ActionEvent>() {
	 * 
	 * @Override public void handle(ActionEvent e) { img.setVisible(true); } });
	 * fadeInTransition.setFromValue(0.0); fadeInTransition.setToValue(1.0);
	 * 
	 * ParallelTransition parallelTransition = new ParallelTransition();
	 * parallelTransition.getChildren().addAll(fadeOutTransition,
	 * fadeInTransition);
	 * 
	 * return parallelTransition; }
	 */

	private ImageView getCardImage(Card c) {
		String src = c.getSuit().getSuit() + c.getRank().getRank();
		String cardFile = "/img/" + src + ".png";
		return new ImageView(new Image(getClass().getResourceAsStream(cardFile), 75, 75, true, true));
	}
}
