package application;

// Importing classes
import javafx.scene.image.Image;

public class Ryu extends Player {

	public Ryu(int frameWidth, int frameHeight) {
		
		// Inheriting Player class.
		super(frameWidth, frameHeight);
		
		// Setting player properties
		this.dirHor = LEFT;
		this.setImage();
		super.setX(frameWidth - (int) (super.width));
		
	}
	
	// setImage method checks for all conditions and refreshes the image object to best fit that scenario.
	public void setImage() {

		// Check if player is facing right.
		if (dirHor == RIGHT) {

			// Check if player is airborne.
			if (onAir == false) {
				
				// Check if player is shooting a projectile
				if (isShootingProjectile == true) {
					
					// Setting player image
					imgPlayer = new Image("file:game_images/RyuHadoukenRight.gif");
					
				// Check if player is blocking.		
				}else if (isBlocking == true) {
					
					// Setting player image
					imgPlayer = new Image("file:game_images/RyuRightBlock.png");
					
				// Check if player is kicking.	
				}else if (isKicking == true) {
					
					// Setting player image
					imgPlayer = new Image("file:game_images/RyuRightKick.gif");
					
				// Check if player is dead	
				}else if (isDead == true) {
				
					// Setting player image
					imgPlayer = new Image("file:game_images/RyuKO.png");
					
				}else {
					
					// Set image object to default right movement images
					if (horMove == true) {

						// Setting player image
						imgPlayer = new Image("file:game_images/RyuRight.gif");

					}else {

						// Setting player image
						imgPlayer = new Image("file:game_images/RyuRightWalk.gif");

					}
					
				}

			}else {

				// Check if player is jumping up straight while facing right.
				if (straightJump == true) {

					// Setting player image
					imgPlayer = new Image("file:game_images/RyuJumpRight.gif");

				}else {

					// Setting player image
					imgPlayer = new Image("file:game_images/RyuJumpRightVector.gif");

				}

			}

		// Check if player is facing left.	
		}else if (dirHor == LEFT) {

			// Check if player is facing airborne.
			if (onAir == false) {
				
				// Check if player is shooting a fireball
				if (isShootingProjectile == true) {
					
					// Setting player image
					imgPlayer = new Image("file:game_images/RyuHadoukenLeft.gif");
					
				// Check if player is blocking	
				}else if (isBlocking == true) {

					// Setting player image
					imgPlayer = new Image("file:game_images/RyuLeftBlock.png");
					
				// Check if player is kicking
				}else if (isKicking == true) {
					
					// Setting player image
					imgPlayer = new Image("file:game_images/RyuLeftKick.gif");
					
				// Check if player is dead
				}else if (isDead == true) {
				
					// Setting player image
					imgPlayer = new Image("file:game_images/RyuKO.png");
				
				}else {
					
					// Set image object to default left movement images
					if (horMove == true) {

						// Setting player image
						imgPlayer = new Image("file:game_images/RyuLeft.gif");

					}else {
						
						// Setting player image
						imgPlayer = new Image("file:game_images/RyuLeftWalk.gif");

					}
					
				}

			}else {

				// Check if player is jumping straight up while facing left.
				if (straightJump == true) {

					// Setting player image
					imgPlayer = new Image("file:game_images/RyuJumpLeft.gif");

				}else {

					// Setting player image
					imgPlayer = new Image("file:game_images/RyuJumpLeftVector.gif");

				}

			}

		}
		
		// Refresh ImageView and sets variables to match dimensions of the new ImageView
		ivPlayer.setImage(imgPlayer);
		width = imgPlayer.getWidth();
		height = imgPlayer.getHeight();
		
		// Checks player isDead boolean
		if (isDead == true) {
			
			// Sets y position of player to lay flat on the surface once dead
			this.setY((int) roomHeight - (int) height);
			
		}

	}

}
