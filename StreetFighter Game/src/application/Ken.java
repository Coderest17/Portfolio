package application;

// Importing classes
import javafx.scene.image.Image;

public class Ken extends Player {

	public Ken(int frameWidth, int frameHeight) {
		
		// Inheriting Player class
		super(frameWidth, frameHeight);
		
		// Setting player properties
		this.dirHor = RIGHT;
		this.setImage();
		super.setX(0);
		
	}
	
	// setImage method checks for all conditions and refreshes the image object to best fit that scenario.
	public void setImage() {

		// Check if player is facing right.
		if (dirHor == RIGHT) {

			// Check if player is airborne
			if (onAir == false) {
				
				// Check if player is shooting a projectile
				if (isShootingProjectile == true) {
					
					// Setting player image
					imgPlayer = new Image("file:game_images/KenHadoukenRight.gif");
					
				// Check if player is blocking
				}else if (isBlocking == true) {
					
					// Setting player image
					imgPlayer = new Image("file:game_images/KenBlockRight.png");
					
				// Check if player is kicking	
				}else if (isKicking == true) {
					
					// Setting player image
					imgPlayer = new Image("file:game_images/KenRightKick.gif");
					
				// Check if player is dead
				}else if (isDead == true) {
					
					// Setting player image
					imgPlayer = new Image("file:game_images/KenKO.png");
					
				}else {
					
					// Set image object to default right movement images.
					if (horMove == true) {

						// Setting player image
						imgPlayer = new Image("file:game_images/KenRight.gif");

					}else {

						// Setting player image
						imgPlayer = new Image("file:game_images/KenRightWalk.gif");

					}
					
				}

			}else {

				// Check if player is jumping straight up while facing right.
				if (straightJump == true) {

					// Setting player image
					imgPlayer = new Image("file:game_images/KenRightJump.gif");

				}else {

					// Setting player image
					imgPlayer = new Image("file:game_images/KenRightJumpVector.gif");

				}

			}

		// Check if player is facing left.	
		}else if (dirHor == LEFT) {

			// Check if player is airborne
			if (onAir == false) {
				
				// Check if player is shooting a fireball.
				if (isShootingProjectile == true) {
					
					// Setting player image
					imgPlayer = new Image("file:game_images/KenHadoukenLeft.gif");
					
				// Check if player is blocking.	
				}else if (isBlocking == true) {
					
					// Setting player image
					imgPlayer = new Image("file:game_images/KenBlockLeft.png");
					
				// Check if player is kicking	
				}else if (isKicking == true) {
					
					// Setting player image
					imgPlayer = new Image("file:game_images/KenLeftKick.gif");
					
				// Check if player is dead.	
				}else if (isDead == true) {
				
					// Setting player image
					imgPlayer = new Image("file:game_images/KenKO.png");
				
				}else {
					
					// Set image object to default left movement images.
					if (horMove == true) {

						// Setting player image
						imgPlayer = new Image("file:game_images/KenLeft.gif");

					}else {

						// Setting player image
						imgPlayer = new Image("file:game_images/KenLeftWalk.gif");

					}
					
				}

			}else {

				// Check if player is jumping straight up while facing left.
				if (straightJump == true) {

					// Setting player image
					imgPlayer = new Image("file:game_images/KenLeftJump.gif");

				}else {

					// Setting player image
					imgPlayer = new Image("file:game_images/KenLeftJumpVector.gif");

				}

			}

		}
		
		// Refresh ImageView and sets variables to match dimensions of the new ImageView
		ivPlayer.setImage(imgPlayer);
		width = imgPlayer.getWidth();
		height = imgPlayer.getHeight();
		
		// Checking player isDead boolean
		if (isDead == true) {
		
			// Sets y position of player to lay flat on the surface once dead
			this.setY((int) roomHeight - (int) height);
			
		}

	}
	
}
