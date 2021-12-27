package application;

// Importing classes
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player {
	
	// Fields
	protected Image imgPlayer;
	protected ImageView ivPlayer;
	protected boolean isDead, onAir, straightJump, horMove, isShootingProjectile, isBlocking, powerUpActive, isKicking;
	protected int dirHor, health;
	protected double width, height;
	protected final int RIGHT = 0, LEFT = 180, NO_MOVE = 578;
	protected int xPos, yPos;
	protected int roomWidth, roomHeight;
	
	// Constructors
	public Player(int frameWidth, int frameHeight) {
		
		// Setting Player Image and ImageView
		imgPlayer = new Image("file:game_images/RyuRight.gif");
		ivPlayer = new ImageView(imgPlayer);

		// Getting image width and height
		width = imgPlayer.getWidth();
		height = imgPlayer.getHeight();
		
		// Initializing fields
		dirHor = NO_MOVE;
		health = 100;
		isKicking = false;
		powerUpActive = false;
		isBlocking = false;
		isShootingProjectile = false;
		onAir = false;
		isDead = false;
		roomWidth = frameWidth;
		roomHeight = frameHeight;
		xPos = -400;
		yPos = (int) roomHeight - (int) height;
		
		// Setting location values
		ivPlayer.setX(xPos);
		ivPlayer.setY(yPos);

	}
	
	// Adjusts the health of the player
	public void adjustHealth(int change) {
		
		// Adjusting health
		health += change;
		
		// Checking for negatives
		if (health <= 0) {
			
			// Setting health to 0
			health = 0;
			
			// Updating isDead boolean
			isDead = true;
			
		}
		
	}
	
	// Accessor methods
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public ImageView getNode() {
		return ivPlayer;
	}
	
	public int getDirHor() {
		return dirHor;
	}
	
	public double getX() {
		return xPos;
	}
	
	public double getY() {
		return yPos;
	}
	
	// Mutator methods that returns booleans that both the Ryu and Ken classes use to set the sprite shown
	public void setKicking(boolean bol) {
		
		// Updating isKicking boolean value
		isKicking = bol;
		
	}
	
	public void setPowerUp(boolean bol) {
		
		// Updating powerUpActive boolean value
		powerUpActive = bol;
		
	}
	
	public void setBlocking(boolean bol) {
		
		// Updating isBlocking boolean value
		isBlocking = bol;
		
	}
	
	public void setShootingProjectile(boolean bol) {
		
		// Updating isShootingProjectile boolean value
		isShootingProjectile = bol;
		
	}
	
	public void setStraightJump(boolean bol) {
		
		// Updating straightJump boolean value
		straightJump = bol;
		
	}
	
	public void setOnAir(boolean bol) {
		
		// Updating onAir boolean value
		onAir = bol;
		
	}
	
	public void setHorMove(boolean bol) {
		
		// Updating horizontal movement boolean value
		horMove = bol;
		
	}
	
	// Sets the dirHor variable to decide if the Ryu and Ken classes want to use the LEFT or RIGHT version of their images.
	public void setDirHor(int newDir) {
		
		// Setting direction variable
		dirHor = newDir;
		
		// Updating horizontal movement boolean
		horMove = true;
		
		// Updating player image using built-in setImage method
		this.setImage();
		
	}
	
	public void setImage() {
		
		// Does nothing as the separate Ken/Ryu classes
		// overwrite this method
		
	}
	
	public void setX(int xVal) {

		// Updating x-values
		xPos = xVal;
		ivPlayer.setX(xPos);

	}

	public void setY(int yVal) {

		// Updating y-values
		yPos = yVal;
		ivPlayer.setY(yPos);

	}
	
	public void setBack() {
		
		// Checking Player direction
		if (dirHor == LEFT) {
			
			// Checking for right edge
			if (xPos + width + 30 < roomWidth) {

				// Increasing x-pos
				this.setX(xPos + 30);

			}
			
		}else {
			
			// Checking for left edge
			if (xPos > 30) {

				// Decreasing x-pos
				this.setX(xPos - 30);

			}
			
		}
		
	}
	
	public void moveHor(int moveAmount) {
		
		// Checking Player direction
		if (dirHor == LEFT) {

			// Checking for left edge
			if (xPos > moveAmount) {

				// Decreasing x-pos
				this.setX(xPos - moveAmount);

			}

		}else if (dirHor == RIGHT) {

			// Checking for right edge
			if (xPos + width + moveAmount < roomWidth) {

				// Increasing x-pos
				this.setX(xPos + moveAmount);

			}

		}
		
	}

}