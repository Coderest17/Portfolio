package application;

// Importing classes
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class Menu {

	// Global fields
	private Random rnd;
	private Pane startScreenPane, charSelectPane, wildCardPane;
	private GridPane charSelectUI, historyDisplay;
	private HBox startScreenButtons, historyBottomHalf;
	private Button startScreenStart, startScreenHistory, startScreenExit, confirmPlayerName, historySearchButton, backButton, backButtonCharSelect;
	private TextField player1Name, player2Name;
	private static TextArea historyResults;
	private TextField historySearch;
	private Label historyLabel;
	private Image startScreen, charSelect, wildCard;
	private ImageView startScreenView, charSelectView, wildCardView;
	private static FileReader fileRead;
	private FileWriter fileWrite;
	private static BufferedWriter bufWrite;
	private static BufferedReader bufRead;
	private static ArrayList<String> matchHistory, winners;
	private File history;
	private Font fighterSpirit;
	private int [][] wildCards;
	public int scroller = 0;
	
	// The constructor for the Menu class initalizes all of the panes so that their contents can be modified by the following methods.
	public Menu() throws Exception, FileNotFoundException {
		
		// Initializing font
		fighterSpirit = Font.loadFont(new FileInputStream(new File("Fighting Spirit 2 bold.otf")), 20);
		
		// Setting scroller value to 0, to represent the main screen
		scroller = 0;
		
		// Initializing start screen Pane and HBox
		startScreenPane = new Pane();
		startScreenButtons = new HBox(60);
		
		// Initializing start screen ImageView
		startScreen = new Image("file:MainMenu.png");
		startScreenView = new ImageView(startScreen);
		
		// Initializing character select Pane and GridPane
		charSelectPane = new Pane();
		charSelectUI = new GridPane();
		
		// Initializing character select ImageView
		charSelect = new Image("file:CharSelect.png");
		charSelectView = new ImageView(charSelect);
		
		// Initializing match history GridPane and HBox
		historyDisplay = new GridPane();
		historyBottomHalf = new HBox(80);
		
		// Initializing history textfile
		history = new File("history.txt");
		
		// Checking for existence of the historys file.
		if (history.exists() == false) {
			
			// Creating textfile if file does not exist
			history.createNewFile();
			
		}
		
		// Initializing file reader and writer objects
		fileRead = new FileReader(history);
		bufRead = new BufferedReader(fileRead);
		fileWrite = new FileWriter(history, true);
		bufWrite = new BufferedWriter(fileWrite);
		
		// Initializing match history and winner names ArrayLists
		matchHistory = new ArrayList<String>();
		winners = new ArrayList<String>();
		
		// Creating Back button for main menu
		backButton = new Button();
		backButton.setPrefSize(100, 30);
		backButton.setText("BACK");
		backButton.setFont(fighterSpirit);
		backButton.setOnAction(e -> navigation(e));
		
		// Creating Back button for character select screen
		backButtonCharSelect = new Button();
		backButtonCharSelect.setPrefSize(120, 30);
		backButtonCharSelect.setFont(fighterSpirit);
		backButtonCharSelect.setText("BACK");
		backButtonCharSelect.setOnAction(e -> navigation(e));
		
		// Initializing wild card Pane
		wildCardPane = new Pane();
		
		// Initializing background ImageView for the wild card Pane
		wildCard = new Image("file:wildCardBackground.png");
		wildCardView = new ImageView(wildCard);
		
		// Initializing instance of Random class
		rnd = new Random();
		
		// Initializing 2D array with wild card coordinates and random effect value
		wildCards = new int[][] {
			
			// FORMAT: {X1, X2, Y1, Y2, Random Value (0-3)},
			{95, 174, 92, 203, rnd.nextInt(4)},
			{210, 289, 92, 203, rnd.nextInt(4)},
			{325, 404, 92, 203, rnd.nextInt(4)},
			{440, 519, 92, 203, rnd.nextInt(4)}
			
		};
		
	}
	
	// Method used to initialized the Start screen for the program.
	public Pane startScreen() {
		
		// Initializing the start button for the start screen
		startScreenStart = new Button();
		startScreenStart.setPrefSize(150, 60);
		startScreenStart.setText("START");
		startScreenStart.setFont(fighterSpirit);
		startScreenStart.setOnAction(e -> navigation(e));
		
		// Initializing the history button for the start screen
		startScreenHistory = new Button();
		startScreenHistory.setPrefSize(150, 60);
		startScreenHistory.setText("HISTORY");
		startScreenHistory.setFont(fighterSpirit);
		startScreenHistory.setOnAction(e -> navigation(e));
		
		// Initializing the exit button for the start screen
		startScreenExit = new Button();
		startScreenExit.setPrefSize(150, 60);
		startScreenExit.setText("EXIT");
		startScreenExit.setFont(fighterSpirit);
		startScreenExit.setOnAction(e -> exit());
		
		// Adding Buttons to HBox Pane.
		startScreenButtons.getChildren().addAll(startScreenHistory, startScreenStart, startScreenExit);
		
		// Setting constant position.
		startScreenButtons.setLayoutX(100);
		startScreenButtons.setLayoutY(480);
		
		// Adding buttons to Pane.
		startScreenPane.getChildren().addAll(startScreenView, startScreenButtons);
		
		// Returning Pane
		return startScreenPane;
		
	}
	
	// Accessor methods for the Startscreen pane 
	public double startScreenWidth() {
		
		// Returning the width of the start screen Pane
		return startScreen.getWidth();
		
	}
	
	public double startScreenHeight() {
		
		// Returning the height of the start screen Pane
		return startScreen.getHeight();
		
	}

	// Method used to call the charSelectScreen pane and handles the names that will be passed into the game history text file
	public Pane charSelectScreen() {
		
		// Initializing player 1's name textfield
		player1Name = new TextField();
		player1Name.setPrefSize(140, 40);
		
		// Sets the textfield back to white once user input has been detected within the TextField
		player1Name.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {	
				
				// Changing background colour back to white when user starts typing again
				player1Name.setStyle("-fx-control-inner-background: white");
				
			}
		});
		
		// Initializing player 1's name textfield
		player2Name = new TextField();
		player2Name.setPrefSize(140, 40);
		
		// Sets the textfield back to white once user input has been detected within the TextField
		player2Name.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {	
				
				// Changing background colour back to white when user starts typing again
				player2Name.setStyle("-fx-control-inner-background: white");
				
			}
		});
		
		// Initializing button to check if both textfields satisfy start requirements.
		confirmPlayerName = new Button();
		confirmPlayerName.setPrefSize(150, 40);
		confirmPlayerName.setText("GO!");
		confirmPlayerName.setFont(fighterSpirit);
		confirmPlayerName.setOnAction(e -> checkName());
		
		// Adding all fx components to a gridpane, to easily arrange them.
		charSelectUI.add(player1Name, 0, 0);
		charSelectUI.add(confirmPlayerName, 1, 0);
		charSelectUI.add(player2Name, 2, 0);
		charSelectUI.add(backButtonCharSelect, 0, 1);
		
		// Setting GridPane properties
		charSelectUI.setLayoutX(10);
		charSelectUI.setLayoutY(300);
		charSelectUI.setVgap(10);
		charSelectUI.setHgap(50);
		
		// Adding GridPane to Pane with background ImageView.
		charSelectPane.getChildren().addAll(charSelectView, charSelectUI);
		
		// Returning GridPane
		return charSelectPane;
		
	}
	
	// Accessor methods for the Character Select Pane
	public double charSelectScreenWidth() {
		
		// Returning the width of the character select Pane
		return charSelect.getWidth();
		
	}
	
	public double charSelectScreenHeight() {
		
		// Returning the height of the character select Pane
		return charSelect.getHeight();
		
	}
	
	 // The exit method handles any form of termination input that is received from the program 
	public void exit() {
		
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
		
		// Exiting system if user clicks yes
		if (result.get() == ButtonType.YES) {
			
			// Displaying goodbye alert
			Alert newAlert = new Alert(AlertType.INFORMATION);
			newAlert.setTitle("StreetFighter");
			newAlert.setGraphic(new ImageView(new Image("file:exitCard.png")));
			newAlert.setHeaderText(null);
			newAlert.setContentText("Thanks for playing Street Fighter!");
			newAlert.showAndWait();
			
			// Exiting game
			System.exit(0);
			
		}
		
	}
	
	 // The historyScreen method handles the search values and commands needed to search the history file.
	public GridPane historyScreen() {
		
		// Re-initializing history GridPane and HBox,
		// to clear up old information when restart is called
		historyDisplay = new GridPane();
		historyBottomHalf = new HBox(80);
		
		// Initializing game history label
		historyLabel = new Label();
		historyLabel.setPrefSize(180, 70);
		historyLabel.setText("MATCH HISTORY");
		historyLabel.setFont(fighterSpirit);
		historyLabel.setAlignment(Pos.CENTER);
		
		// Initializing game history textarea
		historyResults = new TextArea();
		historyResults.setPrefSize(400, 300);
		historyResults.setEditable(false);
		historyResults.wrapTextProperty();
		
		// Initializing history search textfield
		historySearch = new TextField();
		historySearch.setPrefSize(140, 40);
		
		// Sets the textfield back to white once user input has been detected within the TextField
		historySearch.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {	
				
				// Changing background colour back to white when user starts typing again
				historySearch.setStyle("-fx-control-inner-background: white");
				
			}
		});
		
		// Initializing history search button
		historySearchButton = new Button();
		historySearchButton.setPrefSize(180, 40);
		historySearchButton.setText("SEARCH FOR WINNER:");
		historySearchButton.setOnAction(e -> searchhistory());
		
		// Adding search textField and button to HBox placed at the bottom of the pane.
		historyBottomHalf.getChildren().addAll(historySearch, historySearchButton);
		
		// Setting GridPane fields and adding all components to the GridPane.
		historyDisplay.setPadding(new Insets(15,15,15,15));
		historyDisplay.setVgap(20);
		historyDisplay.setHalignment(historyLabel, HPos.CENTER);
		historyDisplay.add(historyLabel, 0, 0);
		historyDisplay.add(historyResults, 0, 1);
		historyDisplay.add(historyBottomHalf, 0, 2);
		historyDisplay.add(backButton, 0, 0);
		
		// Calls the readHistory method to write in values from the textfile for the user's view.
		readHistory();
		
		// Returning history GridPane
		return historyDisplay;
		
	}
	
	
	 // The readHistory method pulls the textFile and writes the contents to a TextArea that is viewable by the user.
	public static void readHistory() {
		
		// Declaring String variable to store line of text from file
		String lineOfText;
		
		// Specific format is used when writing to the textfile which allows for easy filter of specific information.
		try {
			
			// Using loop to read through text file
			while ((lineOfText = bufRead.readLine()) != null) {
				
				matchHistory.add(lineOfText + "\n");
				
				// pulls winners from the textfile for use inside the searchHistory method,
				// using the .split method as the winner's name is the 4th word in the
				// match history wording - giving us index 3
				winners.add(lineOfText.split(" ")[3]);
				
			}
						
			// Using a loop to iterate through the history ArrayList- starting from the last index 
			// (to display the most recent game first in the text area)
			for (int i = matchHistory.size() - 1; i >= 0; i--) {
				
				// Adding game history from index i to text area
				historyResults.appendText(matchHistory.get(i));
				
			}
			
			// Close buffered and file Writer objects.
			bufRead.close();
			fileRead.close();
			
		}
		
		catch(IOException e) {}
		
	}

	
	// The searchHistory method uses selection sort to arrange the data pulled from 
	// the history file - and uses a binary search to find a match.
	public void searchhistory() {
		
		// Checking if input is empty
		if (historySearch.getText().isEmpty()) {
			
			// Setting background to red
			historySearch.setStyle("-fx-control-inner-background: red");
			
			// Displaying alert
			Alert result = new Alert(AlertType.ERROR);
			result.setHeaderText(null);
			result.setGraphic(new ImageView(new Image("file:exitCard.png")));
			result.setTitle("StreetFighter");
			result.setContentText("Enter a search value!");
			result.showAndWait();
			
			// Ending method call
			return;
			
		}
		
		// Declaring variables for sorting process
		int start = 0, size = winners.size() - 1, middle = 0;
		boolean found = false;
		
		// Getting search name from text field
		String searchName = historySearch.getText();
		
		// Using the Selection Sort algorithm to sort the winner names
		for (int i = 0; i < winners.size() - 1; i++) {
			
			// Using loop to iterate through winner names ArrayList
			for (int j = i + 1; j < winners.size(); j++) {
				
				// Using the .compareTo method to compare the values at index i and j
				if (winners.get(i).compareToIgnoreCase(winners.get(j)) > 0) {
					
					// Storing the name at index i
					String temp = winners.get(i);
					
					// Swapping the names at index i and j
					winners.set(i, winners.get(j));
					winners.set(j, temp);
					
				}
				
			}
			
		}
		
		System.out.print(winners);
		
		// Using binary search to find the entered name in ArrayList
		while (start <= size && !found) {
			
			// Initializing middle index to start search
			middle = (start + size) / 2;
			
			// Calling compareToIgnoreCase method on search name and name at current search index
			int output = searchName.compareToIgnoreCase(winners.get(middle));
			
			// Checking output of the compareToIgnoreCase method
			if (output == 0) {
				
				// Setting found boolean
				found = true;
				
			}
			
			// Checking comparison method result
			else if (output > 0) {
				
				// Ignoring left side of search index
				// to speed up search process
				start = middle + 1;
				
			}
			
			else {
				
				// Ignoring right side of search index
				// to speed up search process
				size = middle - 1;
				
			}
			
			
		}
		
		System.out.print("\n" + found);
		
		// Checking found boolean
		if (found == true) {
			
			// Declaring win count variable
			int count = 0;
			
			// Using loop to iterate through all winner's names
			for (int i = 0; i < winners.size(); i++) {
				
				// Checking if name at index i matches the search name
				if (searchName.equalsIgnoreCase(winners.get(i).toString())) {
					
					// Increasing the win count value
					count++;
					
				}
				
			}
			
			// Checking win count for grammar purposes
			if (count == 1) {
				
				// Displaying alert with win info on user- using "win" for proper grammar
				Alert result = new Alert(AlertType.INFORMATION);
				result.setHeaderText(null);
				result.setGraphic(new ImageView(new Image("file:exitCard.png")));
				result.setTitle("StreetFighter");
				result.setContentText("The user " + searchName + " has " + count + " win!");
				result.showAndWait();
				
			}else {
				
				// Displaying alert with win info on user- using "wins"
				Alert result = new Alert(AlertType.INFORMATION);
				result.setHeaderText(null);
				result.setGraphic(new ImageView(new Image("file:exitCard.png")));
				result.setTitle("StreetFighter");
				result.setContentText("The user " + searchName + " has " + count + " wins!");
				result.showAndWait();
				
			}
			
		}else {
			
			// Displaying alert with win info on user
			Alert result = new Alert(AlertType.INFORMATION);
			result.setHeaderText(null);
			result.setTitle("StreetFighter");
			result.setGraphic(new ImageView(new Image("file:exitCard.png")));
			result.setContentText("The user, " + searchName + ", does not have any wins.");
			result.showAndWait();
			
		}
		
	}
	
	// The checkName method checks if there is text within both playerName textfields,
	// and stops the program for running based on certain conditions
	public void checkName() {
		
		// Checking for empty input in both text fields
		if (player1Name.getText().isEmpty() && player2Name.getText().isEmpty()) {

			// Setting background to red
			player1Name.setStyle("-fx-control-inner-background: red");
			player2Name.setStyle("-fx-control-inner-background: red");

			// Displaying alert
			Alert result = new Alert(AlertType.ERROR);
			result.setHeaderText(null);
			result.setTitle("StreetFighter");
			result.setContentText("Please enter a name in both fields!");
			result.setGraphic(new ImageView(new Image("file:exitCard.png")));
			result.showAndWait();

			// Ending method call
			return;

		}

		// Checking for empty input in player 1 textfield
		else if (player1Name.getText().isEmpty()) {

			// Setting background to red
			player1Name.setStyle("-fx-control-inner-background: red");

			// Displaying alert
			Alert result = new Alert(AlertType.ERROR);
			result.setHeaderText(null);
			result.setGraphic(new ImageView(new Image("file:exitCard.png")));
			result.setTitle("StreetFighter");
			result.setContentText("Please enter a name in both fields!");
			result.showAndWait();

			// Ending method call
			return;

		}

		// Checking for empty input in player 2 textfield
		else if (player2Name.getText().isEmpty()) {

			// Setting background to red
			player2Name.setStyle("-fx-control-inner-background: red");

			// Displaying alert
			Alert result = new Alert(AlertType.ERROR);
			result.setHeaderText(null);
			result.setTitle("StreetFighter");
			result.setGraphic(new ImageView(new Image("file:exitCard.png")));
			result.setContentText("Please enter a name in both fields!");
			result.showAndWait();

			// Ending method call
			return;

		}

		// scroller is set to 3 once all checks are gone through, moving on to the next pane.	
		scroller = 3;
			
	}
	
	
	// The writeName method writes the player's names into the history file.
	public void writeName(boolean player2Dead) {
		
		// Declaring String variable to store the winner's name
		String winner;
		
		// Checking player 2's isDead boolean
		if (player2Dead == true) {
			
			// Storing winner's name
			winner = player1Name.getText();
			
		}else {
			
			// Storing winner's name
			winner = player2Name.getText();
			
		}
		
		// Using try-catch block to write to file
		try {
			
			// Writing winner's name to text file
			bufWrite.write(player1Name.getText() + " Vs. " + player2Name.getText() + ": " + winner + " wins!");
			bufWrite.newLine();
			
			// Closing file writer objects
			bufWrite.close();
			fileWrite.close();
			
		}catch(IOException e) {}
		
	}
	
	// The getWinnerName method returns the name of the winner based on the Dead boolean within the player class.
	public String getWinnerName(boolean player1Dead) {
		
		// Declaring String variable
		String winner;
		
		// check if player 1 is dead and return the string values that are stored within the player1/plauer2Name textfields.
		if (player1Dead == true) {
			
			// Storing winner's name
			winner = player1Name.getText();
			
		}else {
			
			// Storing winner's name
			winner = player2Name.getText();
			
		}
		
		// Returning winner's name
		return winner;
		
	}
	
	// The wildCardPane method simply adds the background to the pane itself
	public Pane wildCardPane() {
		
		// Adding wild card ImageView to the Pane
		wildCardPane.getChildren().add(wildCardView);
		
		// Returning modified Pane to Main
		return wildCardPane;
		
	}
	
	
	// The checkCard method sets up barriers around each card within the wildCardPane and checks for clicks on these barriers.
	// It changes the pane to the gamePane ONLY WHEN a card has been selected, which is what this if statement checks.
	public int checkCard(MouseEvent e) {
		
		// Initializing wild card effect value
		int wildCardPick = -1;
		
		// Checking if the mouse click's location falls on wild card 1
		if (e.getX() >= wildCards[0][0] && e.getX() <= wildCards[0][1] && e.getY() >= wildCards[0][2] && e.getY() <= wildCards[0][3]){
			
			// Updating wild card effect value with the wild card's random value
			// (i.e. 5th value in array)
			wildCardPick = wildCards[0][4];
			
			// Updating scroller value
			scroller = 4;
			
		}
		
		// Checking if the mouse click's location falls on wild card 2
		else if (e.getX() >= wildCards[1][0] && e.getX() <= wildCards[1][1] && e.getY() >= wildCards[1][2] && e.getY() <= wildCards[1][3]){
			
			// Updating wild card effect value with the wild card's random value
			// (i.e. 5th value in array)
			wildCardPick = wildCards[1][4];

			// Updating scroller value
			scroller = 4;
						
		}
		
		// Checking if the mouse click's location falls on wild card 3
		else if (e.getX() >= wildCards[2][0] && e.getX() <= wildCards[2][1] && e.getY() >= wildCards[2][2] && e.getY() <= wildCards[2][3]){
			
			// Updating wild card effect value with the wild card's random value
			// (i.e. 5th value in array)
			wildCardPick = wildCards[2][4];

			// Updating scroller value
			scroller = 4;
						
		}
		
		// Checking if the mouse click's location falls on wild card 4
		else if (e.getX() >= wildCards[3][0] && e.getX() <= wildCards[3][1] && e.getY() >= wildCards[3][2] && e.getY() <= wildCards[3][3]){
	
			// Updating wild card effect value with the wild card's random value
			// (i.e. 5th value in array)
			wildCardPick = wildCards[3][4];

			// Updating scroller value
			scroller = 4;

		}
		
		// Returning wild card's effect value to Main
		return wildCardPick;
		
	}
	
	
	// The navigation method sets the scroller variable to a specific value depending on what button calls the method.
	public void navigation(ActionEvent e) {
		
		// SCROLLER LEGEND
		// 0 - StartScreenPane
		// 1 - HistoryPane
		// 2 - CharSelectPane
		// 3 - WildCardPane
		// 4 - GamePane
		
		// Checking action event source
		if (e.getSource() == startScreenHistory) {
			
			// Setting scroller value to 1, representing the history Pane
			scroller = 1;
			
		}
		
		// Checking action event source
		else if (e.getSource() == startScreenStart) {
			
			// Setting scroller value to 1, representing the CharSelectPane
			scroller = 2;
			
		}
		
		// Checking action event source and previous scroller state
		else if (e.getSource() == backButton && scroller  == 1) {
			
			// Setting scroller value to 1, representing the StartScreenPane
			scroller = 0;
			
		}
		
		// Checking action event source and previous scroller state
		else if (e.getSource() == backButtonCharSelect && scroller == 2) {
			
			// Setting scroller value to 1, representing the StartScreenPane
			scroller = 0;
			
		}
		
	}
	
	// Accessor method for scroller, to be used by main.
	public int getScroller() {
		
		// Returning scroller value
		return scroller;
		
	}
	
	//The reset method sets all of the fx components that the user acts with to their original state. 
	public void reset() throws IOException {
		
		// Setting scroller value to 0, to bring game back to main menu
		scroller = 0;
		
		// Clear textfields
		player1Name.clear();
		player2Name.clear();
		historySearch.clear();
		
		// re-intialize previous arraylists
		matchHistory = new ArrayList<String>();
		winners = new ArrayList<String>();
		
		// re-initialize previous file reading and writing objects
		fileRead = new FileReader(history);
		bufRead = new BufferedReader(fileRead);
		fileWrite = new FileWriter(history, true);
		bufWrite = new BufferedWriter(fileWrite);
		
		// Using loop to iterate through wild cards
		for (int i = 0; i < wildCards.length; i++) {
			
			// Setting the effect value of the wild card to a new random 
			//value using the Random class' .nextInt method
			wildCards[i][4] = rnd.nextInt(4);
			
		}
		
	}
	
}
