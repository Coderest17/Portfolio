/* ICS4U1 CPT: StreetFighter
 * Raunak Madan
 * Mr. Conway
 * Program Description:
 * This program creates the game StreetFighter within Java. In the main menu, use the 
 * "HISTORY" button to view the history of all games previously played. The text area 
 * displays the most recent game at the top. Also, you are able to enter in a user's name
 * in a text-field and view how many wins they have. In the main menu, you can also click 
 * on the "START" button to move to the character selection menu. Essentially, you and your 
 * friend can choose who wants to play as Ken and who wants to play as Ryu. From there, you
 * can click on "GO" to move on to the wild card portion of the game. Here, by clicking on a 
 * card, a random effect is added to the game. For instance, you could get 'Half Damage', 
 * 'Double Damage', 'Double Speed', or no effect. After receiving your wild card effect, 
 * you will be informed of the controls for the game. Not to mention, the controls are spaced
 * out in a way such that your hands do not collide with your friend's, which allows for a smooth
 * game play experience. In particular, player 1, AKA Ken, uses the W/A/D keys to move and C/V/B for 
 * special abilities such as shooting a fireball, blocking, and kicking. Player 2, AKA Ryu, 
 * uses the arrow keys to move and I/O/P for special abilities. Keep in mind that you can 
 * also specify a jump direction by pressing either the left key (or A) or the right key 
 * (or D) with the up key (or W). The goal of the game is to KO (or Knock-Out) the other 
 * player by reducing their health. Depending on your wild card's effect, you will do 15 
 * (times the damage factor) damage to the other player when you kick or throw a fireball. 
 * If the other player blocks, in the CORRECT DIRECTION, then they will take 1/3 of the 
 * usual damage. Note that if your fireball and your friend's fireball collide, they will 
 * destroy each other. In like manner, if you and friend are kicking each other, no one 
 * will take damage and you will both be set back. Moreover, if you are losing, don't worry! 
 * When you get to critical health, AKA health less than or equal to 30, a power up will 
 * appear above your location. By colliding with it, you are granted a one-time UNBLOCKABLE 
 * attack (kick or fireball) that does 40 damage to the other player. To clarify, it doesn't 
 * matter if you miss the kick or fireball, on your next successful attack, you WILL do 40 
 * damage to the other player. However, this power is only granted for 1 successful attack. 
 * Finally, when a player gets to 0 health, the game will end. At this point, you can select 
 * the option to return to the main menu and start once more, or exit the game if you've had 
 * your full share of enjoyment. Lastly, if you do decide to go back to the main menu and 
 * click on history, you'll notice that the game you just played is at the top of the text area. 
 * Have fun!
 * 
 * Program Details:
 * Main Class:
 * The Main class is as essential part of the game. It connects the various classes
 * together and allows the game to function. To begin, there are several booleans that are
 * used to check for the players' movement. There are also numerous Timelines created to 
 * essentially allow the game to function. For instance, there are Timelines that creates
 * fireballs after a certain amount of time- in order for the player gifs to complete their cycle.
 * Not to mention, there are Timelines used to cause delay between fireball creation so that the 
 * players do not spam that ability. Likewise, to allow the player to kick only when they have
 * released the key and pressed again, there are booleans such as player1KickAllowed that help
 * to accomplish that feature. The animation AnimationTimer is significant because it calls the
 * various methods that allow for movement, such as the moveHor method in the Player class. That
 * AnimationTimer also checks whether the player is in the air and whether they are kicking or 
 * blocking, and restricts movement accordingly. The Main class also allows for jumping to occur because an 
 * array of quadratic y-values - which are scaled for the height of the screen by multiplying them
 * by the height/18 - allow the user to somewhat experience a jump. This is accomplished through 
 * jump index integer values for each player, which slowly increment in a KeyFrame and adjust the 
 * position accordingly. Moreover, to easily create and remove Fireball objects for each player,
 * an ArrayList of type Fireball is utilized. The Main class also has various methods that are constantly 
 * called from a KeyFrame that check various collisions in the game. For example, to check for collision between 
 * fireballs, the collisionTimer's associated KeyFrame calls upon the checkFireballCollision() method. 
 * That method uses the .getBoundsInParent() method to check for intersection between the fireballs using the
 * .intersect method. The Main class also takes care of health bars and adjusts their width when the player takes 
 * damage. This is accomplished through the Rectangle object's setWidth method. The kick KeyFrames also check 
 * whether both players are kicking at the same time - by referencing their in-built isKicking booleans - and uses 
 * the setBack() method in the Player class to push them both back Finally, the various Methods in main also 
 * check for the players' health and create a power up sprite depending on whether one was previously created 
 * - using the powerUpCreated boolean. Lastly, the restart() method in Main takes care of restarting the 
 * game as the players are re-initialized, the powerUpCreated boolean (along with other booleans) are 
 * set back to their original state and the text area is refreshed (or called upon again using a method in 
 * Menu) - in order to display the most recent game played. That also includes setting the speed and damage 
 * factor variables back to 1 so that the wild card's effect has ended.
 * 
 * Menu Class:
 * The Menu class utilizes JavaFX layouts in order to create the various scenes of the game. In particular,
 * GridPanes are used to add elements such as TextFields, Labels, Buttons and TextAreas to the correct location. This
 * specific example was incorporated into the creation of the 'History' page. On another note, the Menu class
 * is responsible for sorting the names of the winners using the Selection Sort algorithm, searching for a user-
 * entered name using Binary Search, and displaying the number of wins that a certain user has. The Menu class
 * also uses a 2D array to store the location of the Wild card pixel values, along with the effect that each
 * wild card has - which is obtained by getting a random integer from the Random class. The Menu class is also
 * important because it manages the transitions between the various scenes of the game. A variable - scroller - 
 * is updated whenever the user moves to a different portion of the game. The reason being, all buttons are set
 * to call the navigation() method when they are called - and that method updates the scroller value depending
 * on which button submit the action. This is incorporated into a timer in Main, which constantly checks the Menu
 * class' scroller value. Finally, the Menu class has a reset() method that sets the scroller value back to its
 * original state (AKA 0), clears the various TextFields and re-initializes the ArrayLists so that the TextArea has
 * the most recent information if the user decides to see the updated history.
 * 
 * Player Class:
 * The Player class is what allows the Ryu and Ken classes to function. The Player class is the main structure
 * of both the Ryu and Ken classes - as they use inheritance to extend upon the Player class. Specifically, the Player
 * class has several booleans, which constantly update Main about several aspects of each player - such as whether
 * the player is kicking, blocking, shooting a projectile, has a power up, and more. It also has a method to adjust the
 * player's health when they take damage, and it updates the isDead boolean if the health property falls below 1. Moreover,
 * to make the players move, the setX/Y methods are used to set the location of the ImageView. In particular, the moveHor
 * method takes in an amount, checks the direction and space on the screen, and calls the setX method. Furthermore, the setBack
 * method is used to push the player back by a distance of 30 - depending on which direction they are facing. This is very
 * similar to how the moveHor method works - with the exception that you move 30 in the opposite direction. This method is
 * called in situations where the player gets kicked or hit by a fireball. It is also used when both players kick each other at
 * the same time- giving the impression that both players were jolted back by the force. Lastly, there are methods like setXYZ
 * which set booleans' values and set the player's direction. The getXYZ methods return values like the x-Position and y-Position.
 * 
 * Ryu / Ken Class:
 * These classes extend upon the Player class. In their constructors, they set the appropriate direction and have
 * their own setImage() methods, which reference the appropriate images that those players need (i.e. Ken or Ryu images).
 * Based on a variety of situations that the player can be in, the setImage() method checks the state of various booleans
 * and sets the object's image to correspond to that environment.
 * 
 * Fireball Class:
 * The Fireball class is used to create fireballs for both player 1 and 2. It uses the setPosition method to set itself to
 * the appropriate direction and location depending on where the user is - by getting the user's location and direction as 
 * inputs. The isOffScreen() method checks if the fireball has left the screen, which informs the fireball animation 
 * KeyFrame to remove that specific fireball from the Pane. This check is accomplished by using the x-Position, the 
 * fireball's width, and the width of the room. Finally, the move() method adjusts the x-Position of the fireball by 10
 * depending on the direction of the fireball. In like manner with the Player class, the Fireball class also has the setX
 * method, which updates the ImageView's location - which is called from the move() method.
 * 
 */

package application;

// Importing classes
import java.util.ArrayList;
import java.util.Optional;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Main extends Application {
	
	// Declaring various menu scenes for game
	private Scene menuScreen, charSelectScreen, highScoreScreen, gameScreen, wildCardScreen;
	
	// Declaring AnimationTimer for checking current menu state
	private AnimationTimer timerPanel;
	
	// Declaring game arena background Image/ImageView
	private Image backGround;
	private ImageView bgView;
	
	// Declaring Menu object to retrieve various menu layouts
	private Menu menu;
	
	// Declaring game Pane
	private Pane gamePane;
	
	// Declaring Player objects
	private Ryu player1;
	private Ken player2;
	
	// Declaring AnimationTimer to animate players
	private AnimationTimer animation;
	
	// Declaring player functionality booleans
	private boolean player1GoUp, player1GoRight, player1GoLeft, player1ProjectileAllowed = true, player1KickAllowed = true;
	private boolean player2GoUp, player2GoRight, player2GoLeft, player2ProjectileAllowed = true, player2KickAllowed = true;
	
	// Declaring ArrayList to store player 1 and 2's fireballs (AKA Hadoukens)
	private ArrayList<Fireball> player1Fireballs = new ArrayList<Fireball>();
	private ArrayList<Fireball> player2Fireballs = new ArrayList<Fireball>();
	
	// Declaring integer variables to store player 1 and 2's jump indexes
	private int player1JumpIndex = 0, player2JumpIndex = 0;
	
	// Declaring integer variables to store player 1 and 2's y-position before jumping
	private int player1PreJump, player2PreJump;
	
	// Declaring Timelines to cause fireball delay for player 1 and 2
	private Timeline player1FireballDelayTimer, player2FireballDelayTimer;
	
	// Declaring Timelines to animate player and create fireball (AKA Hadoukens)
	private Timeline player1FireballShootTimer, player2FireballShootTimer;
	
	// Declaring Timelines to periodically adjust player y-positions from the 'yJumpValues' method's array
	private Timeline player1JumpTimer, player2JumpTimer;
	
	// Declaring Timelines to animate player and check for kick collision
	private Timeline player1KickTimer, player2KickTimer;
	
	// Declaring Tiemlines to move fireballs and call upon various collision methods
	private Timeline moveFireballsTimer, collisionTimer;
	
	// Declaring integer array to store y-jump values for player jumping
	private int[] jumpValues = yJumpValues(535);
	
	// Declaring booleans to prevent player nodes from being duplicated, facilitate the power up feature,
	// and ignore keys being pressed/released when user is not in game arena
	private boolean spawnImage = false, powerUpCreated = false, powerUpRemoved = false, gameOn = false;
	
	// Declaring Rectangle variables to create player 1 and 2's health bars
	private Rectangle player1Health = new Rectangle(730, 85, 218, 22), player2Health = new Rectangle(291, 85, 218, 22);
	
	// Declaring double variables to facilitate wild card feature as all damage/speed values are
	// multiplied by the below factors
	private double damageFactor = 1, speedFactor = 1;
	
	// Declaring power up ImageView
	private ImageView powerUp;
	
	public void start(Stage primaryStage) {
		
		try {
			
			// Initializing Menu object
			menu = new Menu();
			
			// Initializing background Image/ImageView
			backGround = new Image("file:background.png");
			bgView = new ImageView(backGround);
			
			// Initializing game Pane
			gamePane = new Pane();
			gamePane.setMaxSize(1225, 535);
			
			// Adding background ImageView to game Pane
			gamePane.getChildren().add(bgView);
			
			// Initializing player 1 using the Ryu class
			player1 = new Ryu(1225, 535);
			
			// Initializing player 2 using the Ken class
			player2 = new Ken(1225, 535);
			
			// Retrieving various menu scene layouts from the Menu class using the 'menu' object
			menuScreen = new Scene(menu.startScreen(), menu.startScreenWidth(), menu.startScreenHeight());
			charSelectScreen = new Scene(menu.charSelectScreen(), menu.charSelectScreenWidth(), menu.charSelectScreenHeight());
			highScoreScreen = new Scene(menu.historyScreen());
			wildCardScreen = new Scene(menu.wildCardPane(), 608, 328);
			
			// Initializing game Scene with game Pane
			gameScreen = new Scene(gamePane);
			
			// Setting style-sheet property for game scenes
			menuScreen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			charSelectScreen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			highScoreScreen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			// Setting main stage to menu screen
			primaryStage.setScene(menuScreen);
			primaryStage.setResizable(false);
			primaryStage.show();
			
			// Prompting user for confirmation when closing window
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

				public void handle(WindowEvent e) {

					// Creating confirmation alert
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setContentText("Are you sure you want to exit?");
					alert.setGraphic(new ImageView(new Image("file:exitCard.png")));
					alert.setTitle("StreetFighter");
					alert.setHeaderText(null);

					// Clearing and adding Yes/No buttons
					alert.getButtonTypes().clear();
					alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

					// Storing user choice
					Optional<ButtonType> result = alert.showAndWait();

					// Canceling event if user clicks no
					if (result.get() == ButtonType.NO) {

						// Using consume method to go back to GUI
						e.consume();

					}else {
						
						// Displaying goodbye alert
						Alert newAlert = new Alert(AlertType.INFORMATION);
						newAlert.setHeaderText(null);
						newAlert.setTitle("StreetFighter");
						newAlert.setGraphic(new ImageView(new Image("file:exitCard.png")));
						newAlert.setContentText("Thanks for playing StreetFighter!");
						newAlert.showAndWait();
						
					}

				}

			});
			
			// Initializing timerPanel AnimationTimer to check the current menu state
			// and set the primary stage to the correct scene
			timerPanel = new AnimationTimer() {
				
				public void handle(long val) {
					
					// Checking menu state
					if (menu.getScroller() == 1) {
						
						// Setting main stage to high score screen
						primaryStage.setScene(highScoreScreen);
						primaryStage.centerOnScreen();
						
					}
					
					// Checking menu state
					else if (menu.getScroller() == 2) {
						
						// Setting main stage to character selection screen
						primaryStage.setScene(charSelectScreen);
						primaryStage.centerOnScreen();
						
					}
					
					// Checking menu state
					else if (menu.getScroller() == 0) {
						
						// Setting main stage to menu screen
						primaryStage.setScene(menuScreen);
						primaryStage.centerOnScreen();
						
					}
					
					// Checking menu state
					else if (menu.getScroller() == 3) {
						
						// Setting main stage to wild card screen
						primaryStage.setScene(wildCardScreen);
						primaryStage.centerOnScreen();
						
					}
					
					// Checking menu state
					else if (menu.getScroller() == 4) {
						
						// Checking spawn boolean
						if (spawnImage == true) {
							
							// Displaying game screen
							primaryStage.setScene(gameScreen);
							primaryStage.centerOnScreen();
							timerPanel.stop();
							
							// Starting player animation timer
							animation.start();
							
							// Starting collision timer
							collisionTimer.play();
							
							// Initializing properties of health bars
							player1Health.setFill(Color.GREEN);
							player2Health.setFill(Color.GREEN);
					
							try {
								
								// Adding player sprites to pane
								gamePane.getChildren().add(player1.getNode());
								gamePane.getChildren().add(player2.getNode());

								// Adding player health bars to pane
								gamePane.getChildren().add(player1Health);
								gamePane.getChildren().add(player2Health);

							}catch (Exception e) {}
								
							// Setting spawn boolean
							spawnImage = false;
							
							// Setting game on boolean
							gameOn = true;
							
						}
						
					}
					
				}
				
			};
			
			// Starting menu transition timer
			timerPanel.start();
			
			// Check if a key was pressed
			gameScreen.setOnKeyPressed(new EventHandler<KeyEvent>() {

				public void handle (KeyEvent e) {
					
					// Ending method call if game is not happening,
					// AKA not in game arena
					if (gameOn == false) {return;}

					// PLAYER 1
					// Checking up arrow key
					if (e.getCode() == KeyCode.UP) {

						// Setting up direction boolean
						player1GoUp = true;

					// Checking right arrow key
					}else if (e.getCode() == KeyCode.RIGHT) {

						// Setting right direction boolean
						player1GoRight = true;

					// Checking left arrow key
					}else if (e.getCode() == KeyCode.LEFT) {

						// Setting left direction boolean
						player1GoLeft = true;

					// Checking P key
					}else if (e.getCode() == KeyCode.P) {
						
						// Checking booleans
						if (player1ProjectileAllowed == true && player1.onAir == false && player1.isBlocking == false && player1.isKicking == false) {
							
							// Setting projectile allowed boolean
							player1ProjectileAllowed = false;
							
							// Setting player projectile boolean
							player1.setShootingProjectile(true);
							
							// Refreshing player image
							player1.setImage();

							// Playing shooting timer
							player1FireballShootTimer.play();
							
						}

					// Checking O key
					}else if (e.getCode() == KeyCode.O) {
						
						// Checking for booleans
						if (player1.onAir == false && player1.isKicking == false && player1.isShootingProjectile == false) {
							
							// Setting player 1's blocking boolean
							player1.setBlocking(true);
							
							// Updating image
							player1.setImage();
							
						}
						
					// Checking I key
					}else if (e.getCode() == KeyCode.I) {
						
						// Checking for booleans
						if (player1KickAllowed == true && player1.onAir == false && player1.isKicking == false && player1.isShootingProjectile == false && player1.isBlocking == false) {
							
							// Setting player kick boolean
							player1.setKicking(true);
							
							// Updating image
							player1.setImage();
							
							// Moving player
							player1.moveHor(25);
							
							// Setting boolean
							player1KickAllowed = false;
							
							// Starting player 1 kick timer
							player1KickTimer.play();
							
						}
					
					// PLAYER 2
					// Checking W key
					}else if (e.getCode() == KeyCode.W) {

						// Setting up direction boolean
						player2GoUp = true;

					// Checking D key
					}else if (e.getCode() == KeyCode.D) {

						// Setting right direction boolean
						player2GoRight = true;

					// Checking A key
					}else if (e.getCode() == KeyCode.A) {

						// Setting left direction boolean
						player2GoLeft = true;

					// Checking C key
					}else if (e.getCode() == KeyCode.C) {
						
						// Checking booleans
						if (player2ProjectileAllowed == true && player2.onAir == false && player2.isBlocking == false && player2.isKicking == false) {
							
							// Setting projectile allowed boolean
							player2ProjectileAllowed = false;
							
							// Setting player projectile boolean
							player2.setShootingProjectile(true);
							
							// Refreshing player image
							player2.setImage();
							
							// Playing shooting timer
							player2FireballShootTimer.play();
							
						}
					
					// Checking for V key
					}else if (e.getCode() == KeyCode.V) {
						
						// Checking for booleans
						if (player2.onAir == false && player2.isKicking == false && player2.isShootingProjectile == false) {
							
							// Setting player 2's blocking boolean
							player2.setBlocking(true);
							
							// Updating image
							player2.setImage();
							
						}
						
					// Checking for B key
					}else if (e.getCode() == KeyCode.B) {
						
						// Checking for booleans
						if (player2KickAllowed == true && player2.onAir == false && player2.isKicking == false && player2.isShootingProjectile == false && player2.isBlocking == false) {
							
							// Setting player kick boolean
							player2.setKicking(true);
							
							// Updating image
							player2.setImage();
							
							// Moving player
							player2.moveHor(18);
							
							// Setting boolean
							player2KickAllowed = false;
							
							// Starting player 2 kick timer
							player2KickTimer.play();
							
						}
						
					}

				}

			});
			
			wildCardScreen.setOnMouseClicked(new EventHandler<MouseEvent>() {
				
				public void handle (MouseEvent e) {
					
					// Checking if current menu state is 3,
					// which represents the wild card menu
					if (menu.getScroller() == 3) {
						
						// Getting which wild card ability was selected
						// from the method in menu class
						int wildCardChange = menu.checkCard(e);
						
						// Checking wild card ability selected
						if (wildCardChange == 0) {
							
							// Displaying Alert regarding wild card
							Alert result = new Alert(AlertType.INFORMATION);
							result.setTitle("StreetFighter");
							result.setHeaderText(null);
							result.setGraphic(new ImageView(new Image("file:exitCard.png")));
							result.setContentText("Sorry, you did not get an ability.");
							result.showAndWait();
							
							// Checking wild card ability selected
						}else if (wildCardChange == 1) {
							
							// Setting speed factor to 2 to double the speed
							speedFactor = 2;
							
							// Displaying Alert regarding wild card
							Alert result = new Alert(AlertType.INFORMATION);
							result.setTitle("StreetFighter");
							result.setHeaderText(null);
							result.setGraphic(new ImageView(new Image("file:exitCard.png")));
							result.setContentText("Hooray, you got double speed!");
							result.showAndWait();
						
						// Checking wild card ability selected
						}else if (wildCardChange == 2) {
							
							// Setting damage factor to 2 to double the damage
							damageFactor = 2;
							
							// Displaying Alert regarding wild card
							Alert result = new Alert(AlertType.INFORMATION);
							result.setTitle("StreetFighter");
							result.setHeaderText(null);
							result.setGraphic(new ImageView(new Image("file:exitCard.png")));
							result.setContentText("Hooray, you got double damage!");
							result.showAndWait();
							
						// Checking wild card ability selected
						}else if (wildCardChange == 3) {
							
							// Setting damage factor to 0.5 to half the damage
							damageFactor = 0.5;
							
							// Displaying Alert regarding wild card
							Alert result = new Alert(AlertType.INFORMATION);
							result.setTitle("StreetFighter");
							result.setHeaderText(null);
							result.setGraphic(new ImageView(new Image("file:exitCard.png")));
							result.setContentText("Hooray, you got half damage!");
							result.showAndWait();
						
						// Checking if no ability/card was clicked
						}else {
							
							// Ending method call
							return;
							
						}
						
						// Displaying Alert regarding player controls
						Alert result = new Alert(AlertType.INFORMATION);
						result.setTitle("StreetFighter");
						result.setHeaderText(null);
						result.setGraphic(new ImageView(new Image("file:exitCard.png")));
						result.setContentText("PLAYER CONTROLS:\n\nPLAYER 1 (KEN):\nW/A/D: Movement\nPress C: Hadouken\nHold V: Block\nPress B: Kick"
								+ "\n\nPLAYER 2 (RYU):\nArrow Keys: Movement\nPress P: Hadouken\nHold O: Block\nPress I: Kick");
						result.showAndWait();
						
						// Allowing next part of game
						spawnImage = true;
						
					}
					
				}
				
			});
			
			// Check if a key was released
			gameScreen.setOnKeyReleased(new EventHandler<KeyEvent>() {

				public void handle (KeyEvent e) {
					
					// Ending method call if game is not happening,
					// AKA not in game arena
					if (gameOn == false) {return;}

					// Checking up arrow key
					if (e.getCode() == KeyCode.UP) {

						// Setting up direction boolean
						player1GoUp = false;

					// Checking right arrow key
					}else if (e.getCode() == KeyCode.RIGHT) {

						// Setting right direction boolean
						player1GoRight = false;

						// Setting boolean
						player1.setHorMove(false);

						// Updating image
						player1.setImage();

					// Checking left arroy key
					}else if (e.getCode() == KeyCode.LEFT) {

						// Setting left direction boolean
						player1GoLeft = false;

						// Setting boolean
						player1.setHorMove(false);

						// Updating image
						player1.setImage();
						
					// Checking O key
					}else if (e.getCode() == KeyCode.O) {
						
						// Checking player 1's blocking boolean
						if (player1.isBlocking == true) {
							
							// Setting player 1's blocking boolean
							player1.setBlocking(false);
							
							// Updating image
							player1.setImage();
							
						}
						
					// Checking I key
					}else if (e.getCode() == KeyCode.I) {
						
						// Setting boolean
						player1KickAllowed = true;
						
					// Checking W key
					}else if (e.getCode() == KeyCode.W) {

						// Setting up direction boolean
						player2GoUp = false;

					// Checking D key
					}else if (e.getCode() == KeyCode.D) {

						// Setting right direction boolean
						player2GoRight = false;

						// Setting boolean
						player2.setHorMove(false);

						// Updating image
						player2.setImage();

					// Checking A key
					}else if (e.getCode() == KeyCode.A) {

						// Setting left direction boolean
						player2GoLeft = false;

						// Setting boolean
						player2.setHorMove(false);

						// Updating image
						player2.setImage();
						
					// Checking V key
					}else if (e.getCode() == KeyCode.V) {
						
						// Checking player 2's blocking boolean
						if (player2.isBlocking == true) {
							
							// Setting player 2's blocking boolean
							player2.setBlocking(false);
							
							// Updating image
							player2.setImage();
							
						}
						
					// Checking B key
					}else if (e.getCode() == KeyCode.B) {
						
						// Setting boolean
						player2KickAllowed = true;
						
					}
 
				}

			});
			
			KeyFrame kfPlayer1JumpAnimation = new KeyFrame(Duration.millis(50), new EventHandler<ActionEvent>() {

				public void handle(ActionEvent e) {

					// Setting y-position
					player1.setY(player1PreJump - jumpValues[player1JumpIndex]);
					
					// Checking if player made a straight/vertical jump
					if (player1.straightJump == true) {
						
						// Setting player image
						player1.setImage();
						
					}else {
						
						// Moving x
						player1.moveHor(20);
						
					}
					
					// Increasing jump index
					player1JumpIndex += 1;
					
					// Checking jump index for player 1
					if (player1JumpIndex == 12) {

						// Pausing timer
						player1JumpTimer.pause();

						// Setting jump index back to 0
						player1JumpIndex = 0;

						// Enabling projectiles
						player1.setOnAir(false);

						// Setting boolean
						player1.setHorMove(false);

						// Setting image
						player1.setImage();

					}

				}

			});
			
			KeyFrame kfPlayer2JumpAnimation = new KeyFrame(Duration.millis(50), new EventHandler<ActionEvent>() {

				public void handle(ActionEvent e) {

					// Setting y-position
					player2.setY(player2PreJump - jumpValues[player2JumpIndex]);
					
					// Checking if player made a straight/vertical jump
					if (player2.straightJump == true) {
						
						// Setting player image
						player2.setImage();
						
					}else {
						
						// Moving x
						player2.moveHor(20);
						
					}
					
					// Increasing jump index
					player2JumpIndex += 1;
					
					// Checking jump index for player 2
					if (player2JumpIndex == 12) {

						// Pausing timer
						player2JumpTimer.pause();

						// Setting jump index back to 0
						player2JumpIndex = 0;

						// Enabling projectiles
						player2.setOnAir(false);

						// Setting boolean
						player2.setHorMove(false);

						// Setting image
						player2.setImage();

					}

				}

			});
			
			// Creating KeyFrame to move fireballs
			KeyFrame kfMoveFireballs = new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {

				public void handle(ActionEvent e) {

					// PLAYER 1 FIREBALLS
					// Checking number of player 1 fireballs created
					if (player1Fireballs.size() != 0) {

						// Calling move method for player 1's fireball at index 0
						player1Fireballs.get(0).move();

						// Checking if fireball is off the screen
						if (player1Fireballs.get(0).isOffScreen()) {

							// Removing fireball from the pane
							gamePane.getChildren().remove(player1Fireballs.get(0).getNode());
							player1Fireballs.remove(0);

						}

					}

					// PLAYER 2 FIREBALLS
					// Checking number of player 2 fireballs created
					if (player2Fireballs.size() != 0) {

						// Calling move method for player 2's fireball at index 0
						player2Fireballs.get(0).move();

						// Checking if fireball is off the screen
						if (player2Fireballs.get(0).isOffScreen()) {

							// Removing fireball from the pane
							gamePane.getChildren().remove(player2Fireballs.get(0).getNode());
							player2Fireballs.remove(0);

						}

					}

				}

			});
			
			KeyFrame kfPlayer1FireballDelay = new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {

				public void handle(ActionEvent e) {
					
					// Changing boolean
					player1ProjectileAllowed = true;
					
					// Stopping timer
					player1FireballDelayTimer.pause();

				}

			});
			
			KeyFrame kfPlayer2FireballDelay = new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {

				public void handle(ActionEvent e) {
					
					// Changing boolean
					player2ProjectileAllowed = true;
					
					// Stopping timer
					player2FireballDelayTimer.pause();

				}

			});
			
			KeyFrame kfPlayer1FireballShoot = new KeyFrame(Duration.millis(400), new EventHandler<ActionEvent>() {

				public void handle(ActionEvent e) {

					// Creating Fireball
					player1Fireballs.add(new Fireball(1225));
					player1Fireballs.get(player1Fireballs.size()-1).setPosition((int) player1.getX(), (int) player1.getY(), player1.getDirHor());

					// Adding fireball to pane
					gamePane.getChildren().add(player1Fireballs.get(player1Fireballs.size()-1).getNode());

					// Stopping timer
					player1FireballShootTimer.pause();
					
					// Setting player boolean
					player1.setShootingProjectile(false);
					
					// Refreshing player image
					player1.setImage();
					
					// Playing timer
					player1FireballDelayTimer.play();

				}

			});
			
			KeyFrame kfPlayer2FireballShoot = new KeyFrame(Duration.millis(400), new EventHandler<ActionEvent>() {

				public void handle(ActionEvent e) {

					// Creating Fireball
					player2Fireballs.add(new Fireball(1225));
					player2Fireballs.get(player2Fireballs.size()-1).setPosition((int) player2.getX(), (int) player2.getY(), player2.getDirHor());

					// Adding fireball to pane
					gamePane.getChildren().add(player2Fireballs.get(player2Fireballs.size()-1).getNode());

					// Stopping timer
					player2FireballShootTimer.pause();
					
					// Setting player boolean
					player2.setShootingProjectile(false);
					
					// Refreshing player image
					player2.setImage();
					
					// Playing timer
					player2FireballDelayTimer.play();
					
				}

			});
			
			KeyFrame kfCollisionDetection = new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {

				public void handle(ActionEvent e) {

					// Calling collision check methods
					checkFireballCollision();
					checkPlayer1FireballCollision();
					checkPlayer2FireballCollision();
					powerUpCollision();
					
				}

			});
			
			KeyFrame kfPlayer1Kick = new KeyFrame(Duration.millis(250), new EventHandler<ActionEvent>() {

				public void handle(ActionEvent e) {
					
					// Setting player 1 kick boolean
					player1.setKicking(false);
					
					// Updating player image
					player1.setImage();
					
					// Stopping timer
					player1KickTimer.pause();
					
					// Ending method call if player is not in front
					if (player1.dirHor == 0 && player1.getX() > player2.getX()) {return;}
					
					// Ending method call if player is not in front
					if (player1.dirHor == 180 && player1.getX() < player2.getX()) {return;}

					// Checking for fireball collision with player 2
					if (player1.getNode().getBoundsInParent().intersects(player2.getNode().getBoundsInParent())) {

						// Checking fireball direction
						if (player2.dirHor != player1.dirHor) {

							// Checking for block boolean
							if (player2.isBlocking == true) {

								// Checking for powerup
								if (player1.powerUpActive == true) {

									// Decreasing player 2's health by super damage
									player2.adjustHealth(-40);

									// Setting player 1's power up boolean
									player1.setPowerUp(false);

								}else {

									// Decreasing player 2's health by 1/3 of normal damage
									player2.adjustHealth((int) (-5 * damageFactor));

								}

								// Setting back player
								player2.setBack();

								// Turning off block
								player2.setBlocking(false);

								// Updating image
								player2.setImage();

							// Checking if player 2 is also kicking
							}else if (player2.isKicking == true) {
								
								// Setting back player
								player1.setBack();
								
								// Setting back player
								player2.setBack();
								
							}else {

								// Checking for powerup boolean
								if (player1.powerUpActive == true) {

									// Decreasing player 2's health by super damage
									player2.adjustHealth(-40);

									// Setting player 1's power up boolean
									player1.setPowerUp(false);

								}else {

									// Decreasing player 2's health
									player2.adjustHealth((int) (-15 * damageFactor));

								}

								// Setting back player
								player2.setBack();

							}

						}else {

							// Checking for block boolean
							if (player2.isBlocking == true) {

								// Checking for powerup boolean
								if (player1.powerUpActive == true) {

									// Decreasing player 2's health by super damage
									player2.adjustHealth(-40);

									// Setting player 1's power up boolean
									player1.setPowerUp(false);

								}else {

									// Decreasing player 2's health
									player2.adjustHealth((int) (-15 * damageFactor));

								}

								// Setting back player
								player2.moveHor(30);

								// Turning off block boolean
								player2.setBlocking(false);

								// Updating image
								player2.setImage();

							}else {

								// Checking for powerup boolean
								if (player1.powerUpActive == true) {

									// Decreasing player 2's health by super damage
									player2.adjustHealth(-40);

									// Setting player 1's power up boolean
									player1.setPowerUp(false);

								}else {

									// Decreasing player 2's health
									player2.adjustHealth((int) (-15 * damageFactor));

								}

								// Setting back player
								player2.moveHor(30);

							}

						}

						// Checking if player 2's health is not 100 -
						// meaning the health bar must be updated
						if (player2.health != 100) {

							// Changing player 2's health bar width
							player2Health.setWidth(player2.health * 2.18);

						}

						// Checking if player 2's health is below 0
						if (player2.health <= 0) {

							// Updating image
							player2.setImage();
							
							// Stopping other timers
							collisionTimer.stop();

							// Stopping animation
							animation.stop();

							// Ending game method
							endGame();

						// Checking if player 2's health is critical,
						// AKA health below 30
						}else if (player2.health <= 30) {

							// Changing player 2's health bar colour
							player2Health.setFill(Color.RED);

							// Checking for powerup
							if (powerUpCreated == false) {

								// Creating power up
								powerUp = new ImageView(new Image("file:game_images/ball.png"));
								powerUp.setX((int) player2.getX());
								powerUp.setY(535 - 4 * new Image("file:game_images/ball.png").getHeight());

								// Adding power up to pane
								gamePane.getChildren().add(powerUp);

								// Setting power up boolean
								powerUpCreated = true;

							}

						}

					}
					
				}

			});
			
			KeyFrame kfPlayer2Kick = new KeyFrame(Duration.millis(250), new EventHandler<ActionEvent>() {

				public void handle(ActionEvent e) {
					
					// Setting player 2 kick boolean
					player2.setKicking(false);
					
					// Updating player image
					player2.setImage();
					
					// Stopping timer
					player2KickTimer.pause();
					
					// Ending method call if player is not in front
					if (player2.dirHor == 0 && player2.getX() > player1.getX()) {return;}
					
					// Ending method call if player is not in front
					if (player2.dirHor == 180 && player2.getX() < player1.getX()) {return;}

					// Checking for collision with player 1
					if (player2.getNode().getBoundsInParent().intersects(player1.getNode().getBoundsInParent())) {
						
						// Checking player directions
						if (player1.dirHor != player2.dirHor) {
							
							// Checking for block boolean
							if (player1.isBlocking == true) {
								
								// Checking for powerup boolean
								if (player2.powerUpActive == true) {
									
									// Decreasing player 1's health by super damage
									player1.adjustHealth(-40);
									
									// Setting player 2's power up boolean
									player2.setPowerUp(false);
									
								}else {
									
									// Decreasing player 1's health by 1/3 of normal damage
									player1.adjustHealth((int) (-5 * damageFactor));
									
								}
								
								// Setting back player
								player1.setBack();
								
								// Turning off block
								player1.setBlocking(false);
								
								// Updating image
								player1.setImage();
							
							// Checking if player 1 is also kicking
							}else if (player1.isKicking == true) {
								
								// Setting back player
								player1.setBack();
								
								// Setting back player
								player2.setBack();
								
							}else {
								
								// Checking for powerup boolean
								if (player2.powerUpActive == true) {
									
									// Decreasing player 1's health by super damage
									player1.adjustHealth(-40);
									
									// Setting player 2's power up boolean
									player2.setPowerUp(false);
									
								}else {
									
									// Decreasing player 1's health
									player1.adjustHealth((int) (-15 * damageFactor));
									
								}
								
								// Setting back player
								player1.setBack();
								
							}
							
						}else {
							
							// Checking for block boolean
							if (player1.isBlocking == true) {
								
								// Checking for powerup
								if (player2.powerUpActive == true) {
									
									// Decreasing player 1's health by super damage
									player1.adjustHealth(-40);
									
									// Setting player 2's power up boolean
									player2.setPowerUp(false);
									
								}else {
									
									// Decreasing player 1's health
									player1.adjustHealth((int) (-15 * damageFactor));
									
								}
								
								// Setting back player
								player1.moveHor(30);
								
								// Turning off block
								player1.setBlocking(false);
								
								// Updating image
								player1.setImage();
								
							}else {
								
								// Checking for powerup boolean
								if (player2.powerUpActive == true) {
									
									// Decreasing player 1's health by super damage
									player1.adjustHealth(-40);
									
									// Setting player 2's power up boolean
									player2.setPowerUp(false);
									
								}else {
									
									// Decreasing player 1's health
									player1.adjustHealth((int) (-15 * damageFactor));
									
								}
								
								// Setting back player
								player1.moveHor(30);
								
							}
							
						}
						
						// Checking if player 1's health is not 100 -
						// meaning the health bar must be updated
						if (player1.health != 100) {
							
							// Changing player 1's health bar width
							player1Health.setWidth(player1.health * 2.18);
							
						}
						
						// Checking if player 1's health is below 0
						if (player1.health <= 0) {
							
							// Updating image
							player1.setImage();
							
							// Stopping other timers
							collisionTimer.stop();

							// Stopping animation
							animation.stop();

							// Ending game method
							endGame();

						// Checking whether player 1's health is critical,
						// AKA health below 30
						}else if (player1.health <= 30) {

							// Changing player 1's health bar colour
							player1Health.setFill(Color.RED);
							
							// Checking for powerup boolean
							if (powerUpCreated == false) {
								
								// Creating power up
								powerUp = new ImageView(new Image("file:game_images/ball.png"));
								powerUp.setX((int) player1.getX());
								powerUp.setY(535 - 4 * new Image("file:game_images/ball.png").getHeight());
								
								// Adding power up to pane
								gamePane.getChildren().add(powerUp);
								
								// Setting power up boolean
								powerUpCreated = true;
								
							}

						}

					}
					
				}

			});
			
			// Intiailizing timer for collision detection
			collisionTimer = new Timeline(kfCollisionDetection);
			collisionTimer.setCycleCount(Timeline.INDEFINITE);
			
			// Initializing Timeline for player 1 jumps
			player1JumpTimer = new Timeline(kfPlayer1JumpAnimation);
			player1JumpTimer.setCycleCount(Timeline.INDEFINITE);
			
			// Initializing Timeline for player 2 jumps
			player2JumpTimer = new Timeline(kfPlayer2JumpAnimation);
			player2JumpTimer.setCycleCount(Timeline.INDEFINITE);
			
			// Initializing timer for player 1 kicks
			player1KickTimer = new Timeline(kfPlayer1Kick);
			player1KickTimer.setCycleCount(Timeline.INDEFINITE);
			
			// Initializing timer for player 2 kicks
			player2KickTimer = new Timeline(kfPlayer2Kick);
			player2KickTimer.setCycleCount(Timeline.INDEFINITE);
			
			// Initializing timer for moving fireballs
			moveFireballsTimer = new Timeline(kfMoveFireballs);
			moveFireballsTimer.setCycleCount(Timeline.INDEFINITE);
			moveFireballsTimer.play();
			
			// Initializing timer for delaying player 1's fireballs
			player1FireballDelayTimer = new Timeline(kfPlayer1FireballDelay);
			player1FireballDelayTimer.setCycleCount(Timeline.INDEFINITE);
			
			// Initializing timer for delaying player 2's fireballs
			player2FireballDelayTimer = new Timeline(kfPlayer2FireballDelay);
			player2FireballDelayTimer.setCycleCount(Timeline.INDEFINITE);
			
			// Initializing timer for shooting player 1 fireballs
			player1FireballShootTimer = new Timeline(kfPlayer1FireballShoot);
			player1FireballShootTimer.setCycleCount(Timeline.INDEFINITE);

			// Initializing timer for shooting player 2 fireballs
			player2FireballShootTimer = new Timeline(kfPlayer2FireballShoot);
			player2FireballShootTimer.setCycleCount(Timeline.INDEFINITE);
			
			// Setting up game animation timer
			animation = new AnimationTimer() {

				public void handle(long val) {

					// Checking player 1 booleans
					if (player1.isDead == false && player2.isDead == false && player1.isShootingProjectile == false && player1.isBlocking == false && player1.isKicking == false) {

						// Checking [up & right] movement booleans for player 1
						if (player1GoUp == true && player1GoRight == true) {

							// Checking jump index
							if (player1JumpIndex == 0) {

								// Saving previous y-coordinate
								player1PreJump = (int) player1.getY();

							}

							// Setting player horizontal direction
							player1.setDirHor(player1.RIGHT);

							// Disabling projectiles
							player1.setOnAir(true);

							// Setting straight jump boolean
							player1.setStraightJump(false);

							// Starting timer
							player1JumpTimer.play();

						// Checking [up & left] movement booleans for player 1
						}else if (player1GoUp == true && player1GoLeft == true) {

							// Checking jump index
							if (player1JumpIndex == 0) {

								// Saving previous y-coordinate
								player1PreJump = (int) player1.getY();

							}

							// Setting player horizontal direction
							player1.setDirHor(player1.LEFT);

							// Disabling projectiles
							player1.setOnAir(true);

							// Setting straight jump boolean
							player1.setStraightJump(false);

							// Starting timer
							player1JumpTimer.play();

						// Checking up movement boolean for player 1
						}else if (player1GoUp == true) {

							// Checking jump index
							if (player1JumpIndex == 0) {

								// Saving previous y-coordinate
								player1PreJump = (int) player1.getY();

							}

							// Disabling projectiles
							player1.setOnAir(true);

							// Setting straight jump boolean
							player1.setStraightJump(true);

							// Starting timer
							player1JumpTimer.play();

						// Checking right movement boolean for player 1
						}else if (player1GoRight == true && player1.onAir == false) {

							// Setting player horizontal direction
							player1.setDirHor(player1.RIGHT);

							// Moving x
							player1.moveHor((int) (5 * speedFactor));

							// Updating boolean
							player1.setHorMove(true);

						// Checking left movement boolean for player 1
						}else if (player1GoLeft == true && player1.onAir == false) {

							// Setting player horizontal direction
							player1.setDirHor(player1.LEFT);

							// Moving x
							player1.moveHor((int) (5 * speedFactor));

							// Updating boolean
							player1.setHorMove(true);

						}

					}
					
					// Checking player 2 booleans
					if (player2.isDead == false && player1.isDead == false && player2.isShootingProjectile == false && player2.isBlocking == false && player2.isKicking == false) {

						// Checking [up & right] movement booleans for player 2
						if (player2GoUp == true && player2GoRight == true) {

							// Checking jump index
							if (player2JumpIndex == 0) {

								// Saving previous y-coordinate
								player2PreJump = (int) player2.getY();

							}

							// Setting player horizontal direction
							player2.setDirHor(player2.RIGHT);

							// Disabling projectiles
							player2.setOnAir(true);

							// Setting straight jump boolean
							player2.setStraightJump(false);

							// Starting timer
							player2JumpTimer.play();

						// Checking [up & left] movement booleans for player 2
						}else if (player2GoUp == true && player2GoLeft == true) {

							// Checking jump index
							if (player2JumpIndex == 0) {

								// Saving previous y-coordinate
								player2PreJump = (int) player2.getY();

							}

							// Setting player horizontal direction
							player2.setDirHor(player2.LEFT);

							// Disabling projectiles
							player2.setOnAir(true);

							// Setting straight jump boolean
							player2.setStraightJump(false);

							// Starting timer
							player2JumpTimer.play();

						// Checking up movement boolean for player 2
						}else if (player2GoUp == true) {

							// Checking jump index
							if (player2JumpIndex == 0) {

								// Saving previous y-coordinate
								player2PreJump = (int) player2.getY();

							}

							// Disabling projectiles
							player2.setOnAir(true);

							// Setting straight jump boolean
							player2.setStraightJump(true);

							// Starting timer
							player2JumpTimer.play();

						// Checking right movement boolean for player 2
						}else if (player2GoRight == true && player2.onAir == false) {

							// Setting player horizontal direction
							player2.setDirHor(player1.RIGHT);

							// Moving x
							player2.moveHor((int) (5 * speedFactor));

							// Updating boolean
							player2.setHorMove(true);

						// Checking left movement boolean for player 2
						}else if (player2GoLeft == true && player2.onAir == false) {

							// Setting player horizontal direction
							player2.setDirHor(player2.LEFT);

							// Moving x
							player2.moveHor((int) (5 * speedFactor));

							// Updating boolean
							player2.setHorMove(true);

						}

					}

				}

			};
			
		} catch(Exception e) {
			
			// Outputting error, if error occurs
			e.printStackTrace();
			
		}
		
	}
	
	public void checkFireballCollision() {
		
		try {
			
			// Checking for collision between fireballs at index 0,
			// as the fireball delay only allows for 1 fireball in the room
			if (player1Fireballs.get(0).getNode().getBoundsInParent().intersects(player2Fireballs.get(0).getNode().getBoundsInParent())) {

				// Removing player 1's fireball
				gamePane.getChildren().remove(player1Fireballs.get(0).getNode());
				player1Fireballs.remove(0);

				// Removing player 2's fireball
				gamePane.getChildren().remove(player2Fireballs.get(0).getNode());
				player2Fireballs.remove(0);

			}
			
		}catch (Exception e) {}
		
	}
	
	public void checkPlayer1FireballCollision() {
		
		try {
			
			// Checking for fireball collision with player 1
			if (player2Fireballs.get(0).getNode().getBoundsInParent().intersects(player1.getNode().getBoundsInParent())) {
				
				// Checking fireball direction
				if (player1.dirHor != player2Fireballs.get(0).getDir()) {
					
					// Checking for block boolean
					if (player1.isBlocking == true) {
						
						// Checking for powerup boolean
						if (player2.powerUpActive == true) {
							
							// Decreasing player 1's health by super damage
							player1.adjustHealth(-40);
							
							// Setting player 2's power up boolean
							player2.setPowerUp(false);
							
						}else {
							
							// Decreasing player 1's health by 1/3 of normal damage
							player1.adjustHealth((int) (-5 * damageFactor));
							
						}
						
						// Setting back player
						player1.setBack();
						
						// Turning off block
						player1.setBlocking(false);
						
						// Updating image
						player1.setImage();
						
					}else {
						
						// Checking for powerup boolean
						if (player2.powerUpActive == true) {
							
							// Decreasing player 1's health by super damage
							player1.adjustHealth(-40);
							
							// Setting player 2's power up boolean
							player2.setPowerUp(false);
							
						}else {
							
							// Decreasing player 1's health
							player1.adjustHealth((int) (-15 * damageFactor));
							
						}
						
						// Setting back player
						player1.setBack();
						
					}
					
				}else {
					
					// Checking for block boolean
					if (player1.isBlocking == true) {
						
						// Checking for powerup boolean
						if (player2.powerUpActive == true) {
							
							// Decreasing player 1's health by super damage
							player1.adjustHealth(-40);
							
							// Setting player 2's power up boolean
							player2.setPowerUp(false);
							
						}else {
							
							// Decreasing player 1's health
							player1.adjustHealth((int) (-15 * damageFactor));
							
						}
						
						// Setting back player
						player1.moveHor(30);
						
						// Turning off block
						player1.setBlocking(false);
						
						// Updating image
						player1.setImage();
						
					}else {
						
						// Checking for powerup boolean
						if (player2.powerUpActive == true) {
							
							// Decreasing player 1's health by super damage
							player1.adjustHealth(-40);
							
							// Setting player 2's power up boolean
							player2.setPowerUp(false);
							
						}else {
							
							// Decreasing player 1's health
							player1.adjustHealth((int) (-15 * damageFactor));
							
						}
						
						// Setting back player
						player1.moveHor(30);
						
					}
					
				}

				// Removing player 2's fireball
				gamePane.getChildren().remove(player2Fireballs.get(0).getNode());
				player2Fireballs.remove(0);

				// Changing player 1's health bar width
				player1Health.setWidth(player1.health * 2.18);

				// Checking if player 1's health is below 0
				if (player1.health <= 0) {
					
					// Updating image
					player1.setImage();
					
					// Stopping other timers
					collisionTimer.stop();

					// Stopping animation
					animation.stop();

					// Ending game method
					endGame();

				// Checking if player 1 has reached critical health,
				// AKA health less than 30
				}else if (player1.health <= 30) {

					// Changing player 1's health bar colour
					player1Health.setFill(Color.RED);
					
					// Checking for powerup boolean
					if (powerUpCreated == false) {
						
						// Creating power up
						powerUp = new ImageView(new Image("file:game_images/ball.png"));
						powerUp.setX((int) player1.getX());
						powerUp.setY(535 - 4 * new Image("file:game_images/ball.png").getHeight());
						
						// Adding power up to pane
						gamePane.getChildren().add(powerUp);
						
						// Setting power up boolean
						powerUpCreated = true;
						
					}

				}

			}
			
		}catch (Exception e) {}
		
	}

	public void checkPlayer2FireballCollision() {

		try {
			
			// Checking for fireball collision with player 2
			if (player1Fireballs.get(0).getNode().getBoundsInParent().intersects(player2.getNode().getBoundsInParent())) {

				// Checking fireball direction
				if (player2.dirHor != player1Fireballs.get(0).getDir()) {

					// Checking for block boolean
					if (player2.isBlocking == true) {

						// Checking for powerup boolean
						if (player1.powerUpActive == true) {

							// Decreasing player 2's health by super damage
							player2.adjustHealth(-40);

							// Setting player 1's power up boolean
							player1.setPowerUp(false);

						}else {

							// Decreasing player 2's health by 1/3 of normal damage
							player2.adjustHealth((int) (-5 * damageFactor));

						}

						// Setting back player
						player2.setBack();

						// Turning off block
						player2.setBlocking(false);

						// Updating image
						player2.setImage();

					}else {

						// Checking for powerup boolean
						if (player1.powerUpActive == true) {

							// Decreasing player 2's health by super damage
							player2.adjustHealth(-40);

							// Setting player 1's power up boolean
							player1.setPowerUp(false);

						}else {

							// Decreasing player 2's health
							player2.adjustHealth((int) (-15 * damageFactor));

						}

						// Setting back player
						player2.setBack();

					}

				}else {

					// Checking for block boolean
					if (player2.isBlocking == true) {

						// Checking for powerup boolean
						if (player1.powerUpActive == true) {

							// Decreasing player 2's health by super damage
							player2.adjustHealth(-40);

							// Setting player 1's power up boolean
							player1.setPowerUp(false);

						}else {

							// Decreasing player 2's health
							player2.adjustHealth((int) (-15 * damageFactor));

						}

						// Setting back player
						player2.moveHor(30);

						// Turning off block
						player2.setBlocking(false);

						// Updating image
						player2.setImage();

					}else {

						// Checking for powerup boolean
						if (player1.powerUpActive == true) {

							// Decreasing player 2's health by super damage
							player2.adjustHealth(-40);

							// Setting player 1's power up boolean
							player1.setPowerUp(false);

						}else {

							// Decreasing player 2's health
							player2.adjustHealth((int) (-15 * damageFactor));

						}

						// Setting back player
						player2.moveHor(30);

					}

				}

				// Removing player 1's fireball
				gamePane.getChildren().remove(player1Fireballs.get(0).getNode());
				player1Fireballs.remove(0);

				// Changing player 2's health bar width
				player2Health.setWidth(player2.health * 2.18);

				// Checking if player 2's health is below 0
				if (player2.health <= 0) {

					// Updating image
					player2.setImage();
					
					// Stopping other timers
					collisionTimer.stop();

					// Stopping animation
					animation.stop();

					// Ending game method
					endGame();

				// Checking if player 2 has reached critical health,
				// AKA health less than 30
				}else if (player2.health <= 30) {

					// Changing player 2's health bar colour
					player2Health.setFill(Color.RED);

					// Checking for powerup
					if (powerUpCreated == false) {

						// Creating power up
						powerUp = new ImageView(new Image("file:game_images/ball.png"));
						powerUp.setX((int) player2.getX());
						powerUp.setY(535 - 4 * new Image("file:game_images/ball.png").getHeight());

						// Adding power up to pane
						gamePane.getChildren().add(powerUp);

						// Setting power up boolean
						powerUpCreated = true;

					}

				}

			}
			
		}catch (Exception e) {}

	}
	
	public void powerUpCollision() {
		
		// Checking power up created boolean to check whether
		// the power up was already given
		if (powerUpCreated == true) {
		
			// Checking for collision of player 1 with the power up sprite
			if (player1.getNode().getBoundsInParent().intersects(powerUp.getBoundsInParent())) {
				
				// Removing power up images
				gamePane.getChildren().remove(powerUp);
				
				// Setting power up boolean
				player1.setPowerUp(true);
				
				// Setting power up removed boolean
				powerUpRemoved = true;
				
			// Checking for collision of player 2 with the power up sprite
			}else if (player2.getNode().getBoundsInParent().intersects(powerUp.getBoundsInParent())) {
				
				// Removing power up images
				gamePane.getChildren().remove(powerUp);

				// Setting power up boolean
				player2.setPowerUp(true);
				
				// Setting power up removed boolean
				powerUpRemoved = true;
				
			}
			
		}
		
	}
	
	public void restart() {
		
		try {
			
			// Resetting menu elements
			menu.reset();
			
		}catch (Exception e) {}
		
		// Removing old Player images and health bars
		gamePane.getChildren().removeAll(player1.getNode(), player2.getNode(), player1Health, player2Health);
		
		// Creating health bars
		player1Health = new Rectangle(730, 85, 218, 22);
		player2Health = new Rectangle(291, 85, 218, 22);
		
		// Reinitializing players
		player1 = new Ryu(1225, 535);
		player2 = new Ken(1225, 535);
		
		// Checking power up creation
		if (powerUpRemoved == false) {
			
			// Removing power up sprite
			gamePane.getChildren().remove(powerUp);
			
		}
		
		// Resetting factors
		damageFactor = 1;
		speedFactor = 1;
		
		// Resetting power up booleans
		powerUpCreated = false;
		powerUpRemoved = false;
		
		// Resetting player 1's movement booleans
		player1GoUp = false;
		player1GoRight = false;
		player1GoLeft = false;
		
		// Resetting player 1's ability booleans
		player1ProjectileAllowed = true;
		player1KickAllowed = true;
		
		// Resetting player 2's movement booleans
		player2GoUp = false;
		player2GoRight = false;
		player2GoLeft = false;

		// Resetting player 2's ability booleans
		player2ProjectileAllowed = true;
		player2KickAllowed = true;
		
		// Clearing player 1 and 2's Fireball ArrayLists
		player1Fireballs.clear();
		player2Fireballs.clear();
		
		// Starting panel timer
		timerPanel.start();
		
	}
	
	// Declaring method to display final game alert
	public void endGame() {

		// Creating method to create Alerts when game ends
		Platform.runLater(new Runnable() {

			public void run() {
				
				// Calling menu method
				menu.writeName(player1.isDead);
				
				// Setting game on boolean
				gameOn = false;
				
				// Creating confirmation alert
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setContentText(menu.getWinnerName(player1.isDead) + " Wins!\n\nDo you want to play again?");
				
				// Checking player 1 isDead boolean
				if (player1.isDead == true) {
					
					// Setting graphic to Ken's image
					alert.setGraphic(new ImageView(new Image("file:KenMugshot.png")));
					
				}else {
					
					// Setting graphic to Ryu's image
					alert.setGraphic(new ImageView(new Image("file:RyuMugshot.png")));
					
				}
				
				alert.setTitle("StreetFighter");
				alert.setHeaderText(null);
				
				// Clearing and adding Yes/No buttons
				alert.getButtonTypes().clear();
				alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
				
				// Storing user choice
				Optional<ButtonType> result = alert.showAndWait();
				
				// Canceling event if user clicks no
				if (result.get() == ButtonType.YES) {
					
					// Calling restart method
					restart();
					
					// Re-initializing high score screen
					highScoreScreen = new Scene(menu.historyScreen());
					
				}else {
					
					// Displaying goodbye alert
					Alert newAlert = new Alert(AlertType.INFORMATION);
					newAlert.setTitle("StreetFighter");
					newAlert.setHeaderText(null);
					newAlert.setGraphic(new ImageView(new Image("file:exitCard.png")));
					newAlert.setContentText("Thanks for playing StreetFighter!");
					newAlert.showAndWait();
					
					// Exiting GUI
					System.exit(0);
					
				}

			}

		});

	}
	
	public static int quadratic(int x, int height) {

		// Getting y-value using the following quadratic equation:
		// f(x) = -0.25x^2 + 9
		double yValue = (-0.25 * x * x) + 9;
		
		// Multiplying y-value by a factor of the height
		yValue = yValue * height/18;
		
		// Rounding the y-value to create an integer
		int answer = (int) Math.round(yValue);
		
		// Returning modified y-value
		return answer;

	}

	public static int[] yJumpValues(int height) {

		// Creating array to store y-values for the jump
		int[] yValues = new int[12];
		
		// Creating index variable to store current location in array
		int index = 0;
		
		// Using loop to iterate through x-values from 1 position after the
		// left root, -6 --> -5, until the right root, at x = 6, to end at 0.
		for (int x = -5; x < 7; x++) {
			
			// Storing returned value from 'quadratic' method in array
			yValues[index] = quadratic(x, height);
			
			// Increasing index counter
			index += 1;
			
		}

		// Returning array of y-values for player jumps
		return yValues;

	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}

/*
 * IMAGE CITATIONS
 * 
 * �SNES - Super Street Fighter II: The New Challengers - Character Select - The Spriters Resource.� 
 * 	The Spriter�s Resource, www.spriters-resource.com/snes/supersf2/sheet/5562. Accessed 3 Nov. 2021.
 * 
 * �SNES - Super Street Fighter II: The New Challengers - Ken - The Spriters Resource.� 
 * The Spriter�s Resource, www.spriters-resource.com/snes/supersf2/sheet/5556. Accessed 3 Nov. 2021.
 * 
 * �SNES - Super Street Fighter II: The New Challengers - Ryu - The Spriters Resource.� 
 * The Spriter�s Resource, www.spriters-resource.com/snes/supersf2/sheet/5557. Accessed 3 Nov. 2021.
 * 
 * Virtual Backgrounds. �Street Fighter II Ryu�s Stage.�
 * Virtual Backgrounds, 24 May 2021, virtualbackgrounds.site/background/street-fighter-ii-ryus-stage., Accessed 3 Nov. 2021.
 */
