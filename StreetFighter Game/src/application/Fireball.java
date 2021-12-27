package application;

// Importing classes
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Fireball {

	// Fields
	private Image imgFireball;
	private ImageView ivFireball;
	private int xPos, yPos, roomWidth, dir;
	private final int RIGHT = 0, LEFT = 180;
	private double width;

	// Constructors
	public Fireball(int frameWidth) {

		// Setting Fireball Image and ImageView
		imgFireball = new Image("file:game_images/Fireball2.gif");
		ivFireball = new ImageView(imgFireball);
		
		// Getting image width and height
		width = imgFireball.getWidth();
		
		// Initializing fields
		roomWidth = (int) frameWidth;
		xPos = frameWidth + 20;
		yPos = 0;
		
		// Setting location values
		ivFireball.setX(xPos);
		ivFireball.setY(yPos);

	}

	// Accessor Methods
	public int getDir() {
		return dir;
	}
	
	public int getX() {
		return xPos;
	}
	
	public ImageView getNode() {
		return ivFireball;
	}
	
	// Mutator Methods
	public void setX(int xVal) {

		// Updating x-values
		xPos = xVal;
		ivFireball.setX(xPos);

	}

	public void setY(int yVal) {

		// Updating y-values
		yPos = yVal;
		ivFireball.setY(yPos);
		
	}

	// the setPosition method sets the starting position for the fireball based on player location.
	public void setPosition(int playerX, int playerY, int newDir) {
		
		// Setting direction variable to match player's direction
		dir = newDir;
		
		// Checking player direction
		if (dir == RIGHT) {
			
			// Updating X and y value to match image of Player
			this.setX(playerX + 60);
			this.setY(playerY + 16);
			
			// Setting Fireball Image and ImageView
			imgFireball = new Image("file:game_images/HadoukenRight.gif");
			ivFireball.setImage(imgFireball);
			
			// Getting image width
			width = imgFireball.getWidth();
			
			
		}else if (dir == LEFT) {
			
			// Updating X and y value to match image of Player
			this.setX(playerX);
			this.setY(playerY + 16);
			
			// Setting Fireball Image and ImageView
			imgFireball = new Image("file:game_images/HadoukenLeft.gif");
			ivFireball.setImage(imgFireball);

			// Getting image width
			width = imgFireball.getWidth();
			
		}

	}
	
	public boolean isOffScreen() {

		// Checking x coordinate
		if (xPos > roomWidth || (xPos + (int) width) < 0) {

			// Returning true as object left screen
			return true;

		}else {

			// Returning false as fireball is in screen
			return false;

		}

	}

	public void move() {

		// Checking fireball direction
		if (dir == LEFT) {
			
			// Decreasing x position
			this.setX(xPos - 10);
			
		}else if (dir == RIGHT) {
			
			// Increasing x position
			this.setX(xPos + 10);
			
		}
		
	}

}