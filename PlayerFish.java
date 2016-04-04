import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.text.Format;

import javax.imageio.ImageIO;
import javax.swing.Icon;

public class PlayerFish {
	int movementAmount = 3;
	protected boolean leftPressed;
	protected boolean rightPressed;
	protected boolean downPressed;
	protected boolean upPressed;
	protected int diameterOfPlayer;
	protected int playerSize;
	protected int topLeftX;
	protected int topLeftY;
	protected int radiusOfPlayer;
	protected int centerX;
	protected int centerY;
	
	PlayerFish() {
		while (topLeftX < 15 || topLeftX > fishPanel.widthOfPanel - diameterOfPlayer) {
			topLeftX = (int)(Math.random() * fishPanel.widthOfPanel);
		}
		while (topLeftY < 115 || topLeftY > fishPanel.heightOfPanel - diameterOfPlayer - fishPanel.heightOfMenuBar) {
			topLeftY = (int)((Math.random() * fishPanel.heightOfPanel));
		}
		playerSize = 1;	
		diameterOfPlayer = 19;
		radiusOfPlayer = (int)(diameterOfPlayer * .5);
		centerX = topLeftX + radiusOfPlayer;
		centerY = topLeftY + radiusOfPlayer;
	}

	protected void draw(Graphics g) {
		if (playerSize == 1){
			movementAmount = 3;
			diameterOfPlayer = 19;
		}
		else if (playerSize == 2){
			movementAmount = 3;
			diameterOfPlayer = 21;
		}
		else if (playerSize == 3){
			movementAmount = 3;
			diameterOfPlayer = 29;
		}
		else if (playerSize == 4){
			movementAmount = 3;
			diameterOfPlayer = 31;
		}
		else if (playerSize == 5){
			movementAmount = 3;
			diameterOfPlayer = 39;
		}
		else if (playerSize == 6){
			movementAmount = 3;
			diameterOfPlayer = 41;
		}
		else if (playerSize == 7){
			movementAmount = 3;
			diameterOfPlayer = 49;
		}
		else if (playerSize == 8){
			movementAmount = 3;
			diameterOfPlayer = 50;
		}
		else if (playerSize == 9){
			movementAmount = 3;
			diameterOfPlayer = 51;
		}
		else if (playerSize == 10){
			movementAmount = 3;
			diameterOfPlayer = 59;
		}
		else {
			movementAmount = 3;
			diameterOfPlayer = 61;
		}
		radiusOfPlayer = (int)(diameterOfPlayer * .5);
		g.setColor(Color.RED);
		g.fillOval(topLeftX, topLeftY, diameterOfPlayer, diameterOfPlayer);
	}
	
}

