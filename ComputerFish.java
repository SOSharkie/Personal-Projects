import java.awt.Color;
import java.awt.Graphics;


public class ComputerFish {
	int positionX;
	int positionY;
	int size;
	int diameter; 
	int fishSpeed;
	boolean moveRight;
	boolean moveLeft;
	boolean moveUp;
	boolean moveDown;
	boolean hittingLeftWall;
	boolean hittingTopWall;
	boolean hittingRightWall;
	boolean hittingBottomWall;
	int radius;
	int centerX;
	int centerY;
	int distanceFromPlayer;
	
	ComputerFish(int startingSize, int speed) {
		fishSpeed = speed;
		if (startingSize > 5 || startingSize < 0){
			throw new IllegalArgumentException("Size must be between 0 and 4");
		}
		size = startingSize;
		if (size == 0){
			diameter = 15;
		}
		else if (size == 1){
			diameter = 25;
		}
		else if (size == 2){
			diameter = 35;
		}
		else if (size == 3){
			diameter = 45;
		}
		else if (size == 4){
			diameter = 50;
		}
		else {
			diameter = 100;
		}
		while (positionX < 15 || positionX > fishPanel.widthOfPanel - diameter) {
			positionX = (int)(Math.random() * fishPanel.widthOfPanel);
		}
		while (positionY < 55 || positionY > fishPanel.heightOfPanel - diameter - fishPanel.heightOfMenuBar) {
			positionY = (int)(Math.random() * fishPanel.heightOfPanel);
		}
		radius = (int)(diameter * .5);
		centerX = positionX + radius;
		centerY = positionY + radius;
	}
	
	public void draw(Graphics g){
			g.setColor(Color.DARK_GRAY);
			g.fillOval(positionX, positionY, diameter, diameter);
	}
	
	/**
	 * Randomly chooses the direction of a computer fish, and if hitting a wall makes it bounce away.
	 */
	public void directionUpdate(PlayerFish playerFish) { 
		int	direction = 0;;
		int diagonal = (int)(Math.random() * 3) + 1;  // chooses if going diagonal or not: 1, 2, or 3
		int random123 = (int)(Math.random() * 3) + 1;  // chooses an int: 1, 2, or 3
		
		if (hittingRightWall && hittingTopWall){
			direction = 2;
		}
		else if (hittingRightWall && hittingBottomWall){
			direction = 1;
		}
		else if (hittingLeftWall && hittingBottomWall){
			direction = 0;
		}
		else if (hittingLeftWall && hittingTopWall){
			direction = 3;
		}
		else if (hittingRightWall){   //direction cannot be 0
			if (random123 == 1){ direction = 1; }
			if (random123 == 2){ direction = 2; }
			if (random123 == 3){ direction = 3; } 
		}
		else if (hittingTopWall){  //direction cannot be 1
			if (random123 == 1){ direction = 0; }
			if (random123 == 2){ direction = 2; }
			if (random123 == 3){ direction = 3; } 
		}
		else if (hittingLeftWall){  //direction cannot be 2
			if (random123 == 1){ direction = 0; }
			if (random123 == 2){ direction = 1; }
			if (random123 == 3){ direction = 3; }  
		}
		else if (hittingBottomWall){ //direction cannot be 3
			if (random123 == 1){ direction = 0; }
			if (random123 == 2){ direction = 1; }
			if (random123 == 3){ direction = 2; } 
		}
		else if (playerFish.playerSize > size){  // smaller fish go away from the player
			if (playerFish.topLeftY < positionY){  // the player is above the fish
				if (random123 == 1){ direction = 0; }
				if (random123 == 2){ direction = 2; }
				if (random123 == 3){ direction = 3; }
			}
			else if (playerFish.topLeftY > positionY){  //the player is below the fish
				if (random123 == 1){ direction = 0; }
				if (random123 == 2){ direction = 1; }
				if (random123 == 3){ direction = 2; }
			}
			else if (playerFish.topLeftX < positionX){  // the player is to the left
				if (random123 == 1){ direction = 0; }
				if (random123 == 2){ direction = 1; }
				if (random123 == 3){ direction = 3; }
			}
			else if (playerFish.topLeftX > positionX){  //the player is to the right
				if (random123 == 1){ direction = 1; }
				if (random123 == 2){ direction = 2; }
				if (random123 == 3){ direction = 3; }
			}
		}
		else if (playerFish.playerSize < size){  // bigger fish go towards the player
			if (playerFish.topLeftY > positionY){  // the player is above the fish
				if (random123 == 1){ direction = 0; }
				if (random123 == 2){ direction = 2; }
				if (random123 == 3){ direction = 3; }
			}
			else if (playerFish.topLeftY < positionY){  //the player is below the fish
				if (random123 == 1){ direction = 0; }
				if (random123 == 2){ direction = 1; }
				if (random123 == 3){ direction = 2; }
			}
			else if (playerFish.topLeftX > positionX){  // the player is to the left
				if (random123 == 1){ direction = 0; }
				if (random123 == 2){ direction = 1; }
				if (random123 == 3){ direction = 3; }
			}
			else if (playerFish.topLeftX < positionX){  //the player is to the right
				if (random123 == 1){ direction = 1; }
				if (random123 == 2){ direction = 2; }
				if (random123 == 3){ direction = 3; }
			}
		}
		else {
			direction = (int)(Math.random() * 4); //chooses up, down, left, or right
		}
		

		if (direction == 0){  //move right
			moveLeft = false;
			moveRight = true;
			if (diagonal < 3) { //not diagonal
				moveUp = false;
				moveDown = false;
			}
		}
		if (direction == 1){ //move up
			moveDown = false;
			moveUp = true;
			if (diagonal < 3) {
				moveRight = false;  //not diagonal
				moveLeft = false;
			}
		}
		if (direction == 2){ //move left
			moveRight = false;
			moveLeft = true; 
			if (diagonal < 3) {  //not diagonal
				moveUp = false;
				moveDown = false;
			}
		}
		if (direction == 3){  //move down
			moveUp = false;
			moveDown = true;
			if (diagonal < 3) {  //not diagonal
				moveLeft = false;
				moveRight = false;
			}
		}
	}
	
	/**
	 * moves the fish either up, down, left, or right
	 */
	public void fishMovement() {  // moves the fish either up, down, left, or right
		if (moveLeft){
			hittingRightWall = false;
			positionX -= fishSpeed;
			if (positionX < 5){  //hitting the left wall
				positionX = 5;
				hittingLeftWall = true;
			}
		}
		if (moveRight){
			hittingLeftWall = false;
			positionX += fishSpeed;
			if (positionX > fishPanel.widthOfPanel - diameter - fishPanel.edgeOfScreen){  //hitting the right wall
				positionX = fishPanel.widthOfPanel - diameter - fishPanel.edgeOfScreen;
				hittingRightWall = true;
			}
		}
		if (moveUp){
			hittingBottomWall = false;
			positionY -= fishSpeed;
			if (positionY < 46){  //hitting the top wall
				positionY = 46;
				hittingTopWall = true;
			}
		}
		if (moveDown){
			hittingTopWall = false;
			positionY += fishSpeed;
			if (positionY > (fishPanel.heightOfPanel - diameter - fishPanel.heightOfMenuBar - fishPanel.edgeOfScreen)){  //hitting the bottom wall
				positionY = (fishPanel.heightOfPanel - diameter - fishPanel.heightOfMenuBar - fishPanel.edgeOfScreen);
				hittingBottomWall = true;
			}
		}
		centerX = positionX + radius;
		centerY = positionY + radius;
	}
	public boolean eatenByPlayer(PlayerFish playerFish) {
		if (playerFish.diameterOfPlayer < diameter){
			return false;
		}
		else if (playerFish.topLeftX <= positionX && (playerFish.topLeftX + playerFish.diameterOfPlayer) >= (positionX + diameter) 
			&& playerFish.topLeftY <= positionY && (playerFish.topLeftY + playerFish.diameterOfPlayer) >= (positionY + diameter)){
			playerFish.playerSize++;
			return true;
		}
		else { 
			return false; 
		}
	}
	public boolean eatPlayer(PlayerFish playerFish) {
		distanceFromPlayer = (int)Math.sqrt((playerFish.centerX - centerX)*(playerFish.centerX - centerX) + 
		(playerFish.centerY - centerY)*(playerFish.centerY - centerY));
		
		if (playerFish.diameterOfPlayer > diameter){
			return false;
		}
		else if (distanceFromPlayer < radius + playerFish.radiusOfPlayer){
			return true;
		}
		else {
			return false;
		}
	}
	
	public void changeSpeed(int newSpeed) {
		fishSpeed = newSpeed;
	}
}
