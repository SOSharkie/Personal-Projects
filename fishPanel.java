import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class fishPanel extends JPanel implements FocusListener, ActionListener {

	public static void main(String[] args) {
		JFrame window = new JFrame("Fish Game");
		window.setContentPane(new fishPanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocation(75,75);
		window.setSize(widthOfPanel,heightOfPanel);
		window.setResizable(false);
		window.setVisible(true);
	}
	
	ArrayList<ComputerFish> compFishSize0 = new ArrayList<ComputerFish>();
	ArrayList<ComputerFish> compFishSize1 = new ArrayList<ComputerFish>();
	ArrayList<ComputerFish> compFishSize2 = new ArrayList<ComputerFish>();
	ArrayList<ComputerFish> compFishSize3 = new ArrayList<ComputerFish>();
	ArrayList<ComputerFish> compFishSize4 = new ArrayList<ComputerFish>();

	double currentRecord = 999.9;
	static int edgeOfScreen = 10; //Put on 10 for PC's
	static int widthOfPanel = 900;
	static int heightOfPanel = 600; 
	static int heightOfMenuBar = 25;
	boolean gameOver;
	PlayerFish playerFish = new PlayerFish();
	int setSpeed2; //for computer fish
	int setSpeed3;
	int timerSeconds = 00;
	int timerTenthSeconds = 0;
	Timer timeCounter;
	Timer movementTimer;
	Timer fishTimer;
	JButton newGame;
	JButton startStop;
	JLabel timerLabel = new JLabel("Time= " + timerSeconds + ":" + timerTenthSeconds + "    Record= " + currentRecord);
	JLabel difficultyLabel = new JLabel("Easy-->");
	JSlider difficultyLevel = new JSlider(0, 3, 0);
	
//-------------------------------------Start of fishPanel-------------------------------------------------	

	public fishPanel() {
		this.setLayout(new BorderLayout());
		this.setBackground(Color.CYAN);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3)); // a border of 3 pixels
		
		timeCounter = new Timer(100, this);  //fires every 1/10 second
		movementTimer = new Timer(30, this); // fires every 1/30 of a second
		fishTimer = new Timer(1000, this); //timer fires every second
		JPanel buttonPanel = new JPanel();
		startStop = new JButton("Stop");
		newGame = new JButton("New Game");
		
		buttonPanel.add(newGame);
		buttonPanel.add(startStop);
		startStop.setEnabled(false);
		buttonPanel.add(difficultyLabel);
		buttonPanel.add(difficultyLevel);
		buttonPanel.add(timerLabel);
		this.add(buttonPanel, BorderLayout.NORTH);
		 
        addFocusListener(this);
        ButtonListener buttonListener = new ButtonListener();
        startStop.addActionListener(buttonListener);
        newGame.addActionListener(buttonListener);
        
        addKeyListener( new KeyAdapter() {
        public void keyPressed(KeyEvent evt) {
           int code = evt.getKeyCode();  
           if (code == KeyEvent.VK_LEFT) {
        	   playerFish.leftPressed = true;
           }
      	   if (code == KeyEvent.VK_RIGHT) {
      		 playerFish.rightPressed = true;
      	  }
      	   if (code == KeyEvent.VK_UP) {
      		 playerFish.upPressed = true;
      	  }
      	   if (code == KeyEvent.VK_DOWN) {
      		 playerFish.downPressed = true;
      	  }
      	  repaint();
        }
        });
           
        addKeyListener( new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
            	int key = evt.getKeyCode();
                if (key == KeyEvent.VK_LEFT) { 
                	playerFish.leftPressed = false;
                 }
                 else if (key == KeyEvent.VK_RIGHT) {  
                	 playerFish.rightPressed = false;
                 }
                 else if (key == KeyEvent.VK_UP) {  
                	 playerFish.upPressed = false;
                 }
                 else if (key == KeyEvent.VK_DOWN) {  
                	 playerFish.downPressed = false;
                 }
            }
        });
	} 
//------------------------------------End of fishPanel------------------------------------------
//-----------------------------Start of Drawing and Listeners-----------------------------------
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == movementTimer){
			for (int i = 0; i < compFishSize0.size(); i++){
				compFishSize0.get(i).fishMovement();
			}
			for (int i = 0; i < compFishSize1.size(); i++){
				compFishSize1.get(i).fishMovement();
			}
			for (int i = 0; i < compFishSize2.size(); i++){
				compFishSize2.get(i).fishMovement();
			}
			for (int i = 0; i < compFishSize3.size(); i++){
				compFishSize3.get(i).fishMovement();		
			}
			for (int i = 0; i < compFishSize4.size(); i++){
				compFishSize4.get(i).fishMovement();		
			}
			if (playerFish.leftPressed) {
				if (playerFish.topLeftX > 3){
					playerFish.topLeftX -= playerFish.movementAmount;
				}
			}
			if (playerFish.rightPressed) {
				if (playerFish.topLeftX < widthOfPanel - playerFish.diameterOfPlayer - edgeOfScreen + 2){
					playerFish.topLeftX += playerFish.movementAmount;
				}	
			}
			if (playerFish.upPressed) {
				if (playerFish.topLeftY > 45){
					playerFish.topLeftY -= playerFish.movementAmount;
				}
			}
			if (playerFish.downPressed) {
				if (playerFish.topLeftY < heightOfPanel - playerFish.diameterOfPlayer - heightOfMenuBar - edgeOfScreen + 2){
					playerFish.topLeftY += playerFish.movementAmount;
				}
			}
			playerFish.centerX = playerFish.topLeftX + playerFish.radiusOfPlayer;
			playerFish.centerY = playerFish.topLeftY + playerFish.radiusOfPlayer;
		}
		if (source == fishTimer){
			for (int i = 0; i < compFishSize0.size(); i++){
				compFishSize0.get(i).directionUpdate(playerFish);
			}
			for (int i = 0; i < compFishSize1.size(); i++){
				compFishSize1.get(i).directionUpdate(playerFish);
			}
			for (int i = 0; i < compFishSize2.size(); i++){
				compFishSize2.get(i).directionUpdate(playerFish);
			}
			for (int i = 0; i < compFishSize3.size(); i++){
				compFishSize3.get(i).directionUpdate(playerFish);
			}
			for (int i = 0; i < compFishSize4.size(); i++){
				compFishSize4.get(i).directionUpdate(playerFish);
			}
		}
		if (source == timeCounter){
			timerTenthSeconds++;
			if (timerTenthSeconds == 9){
				timerSeconds++;
				timerTenthSeconds = 0;
			}
		timerLabel.setText("Time= " + timerSeconds + ":" + timerTenthSeconds + "   Record= " + currentRecord);
		}
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawAllComputerFish(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 42, 1000, 3);
		if (!hasFocus()){
			g.setFont(new Font("Helvetica", Font.PLAIN, 20));
			g.drawString("Completely engulf a smaller fish to get bigger.", 10, 65);
			g.drawString("Eat all the fish to win!", 10, 85);
			g.drawString("But watch out for fish bigger than you.", 10, 105);
		}
		g.setFont(new Font("TimesRoman", Font.PLAIN, 180));
		if (win()){
			g.setColor(Color.ORANGE);
			movementTimer.stop();
			fishTimer.stop();
			timeCounter.stop();
			g.drawString("You win!", 40, 315);
			if (timerSeconds < currentRecord){
				currentRecord = timerSeconds + (.1 * timerTenthSeconds);
			}
			difficultyLevel.setEnabled(true);
			startStop.setEnabled(false);
		}
		if (gameOver && timerSeconds > 1){
			g.setColor(Color.RED);
			movementTimer.stop();
			fishTimer.stop();
			timeCounter.stop();
			g.drawString("Game Over", 40, 315);
			difficultyLevel.setEnabled(true);
			startStop.setEnabled(false);
		}
		else {
			playerFish.draw(g);
		}
	}
	
	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			char command = event.getActionCommand().charAt(2);
			setSpeed2 = 2;
			setSpeed3 = 3;
			if (command == 'w'){
				difficultyLevel.setEnabled(true);
				timerSeconds = 0;
				timerTenthSeconds = 0;
				gameOver = false;
				compFishSize0.clear();
				compFishSize1.clear();
				compFishSize2.clear();
				compFishSize3.clear();
				compFishSize4.clear();
				compFishSize0.add(new ComputerFish(0, setSpeed2));
				compFishSize0.add(new ComputerFish(0, setSpeed3));
				compFishSize1.add(new ComputerFish(1, setSpeed2));
				compFishSize1.add(new ComputerFish(1, setSpeed3));
				compFishSize1.add(new ComputerFish(1, setSpeed2));
				compFishSize2.add(new ComputerFish(2, setSpeed2));
				compFishSize2.add(new ComputerFish(2, setSpeed3));
				compFishSize2.add(new ComputerFish(2, setSpeed3));
				compFishSize3.add(new ComputerFish(3, setSpeed2));
				compFishSize3.add(new ComputerFish(3, setSpeed2));
				compFishSize4.add(new ComputerFish(4, setSpeed2));
				compFishSize4.add(new ComputerFish(4, setSpeed3));
				compFishSize4.add(new ComputerFish(4, setSpeed2));
				playerFish.playerSize = 1;
				startStop.setText("Start");
				startStop.setEnabled(true);
				repaint();
			}
			if (command == 'a') {
				if (difficultyLevel.getValue() == 3){
					difficultyLabel.setText("Insane-->");
					for (int i = 0; i < compFishSize0.size(); i++){
						compFishSize0.get(i).changeSpeed(4); }
					for (int i = 0; i < compFishSize1.size(); i++){
						compFishSize1.get(i).changeSpeed(4); }
					for (int i = 0; i < compFishSize2.size(); i++){
						compFishSize2.get(i).changeSpeed(4); }
					for (int i = 0; i < compFishSize3.size(); i++){
						compFishSize3.get(i).changeSpeed(4); }
					for (int i = 0; i < compFishSize4.size(); i++){
						compFishSize4.get(i).changeSpeed(4); }
				}
				else if (difficultyLevel.getValue() == 2){
					difficultyLabel.setText("Hard-->");
					for (int i = 0; i < compFishSize0.size(); i++){
						compFishSize0.get(i).changeSpeed(3); }
					for (int i = 0; i < compFishSize1.size(); i++){
						compFishSize1.get(i).changeSpeed(3); }
					for (int i = 0; i < compFishSize2.size(); i++){
						compFishSize2.get(i).changeSpeed(3); }
					for (int i = 0; i < compFishSize3.size(); i++){
						compFishSize3.get(i).changeSpeed(3); }
					for (int i = 0; i < compFishSize4.size(); i++){
						compFishSize4.get(i).changeSpeed(3); }
				}
				else if (difficultyLevel.getValue() == 1){
					difficultyLabel.setText("Medium-->");
					for (int i = 0; i < compFishSize0.size(); i++){
						compFishSize0.get(i).changeSpeed(2); }
					for (int i = 0; i < compFishSize1.size(); i++){
						compFishSize1.get(i).changeSpeed(3); }
					for (int i = 0; i < compFishSize2.size(); i++){
						compFishSize2.get(i).changeSpeed(3); }
					for (int i = 0; i < compFishSize3.size(); i++){
						compFishSize3.get(i).changeSpeed(2); }
					for (int i = 0; i < compFishSize4.size(); i++){
						compFishSize4.get(i).changeSpeed(2); }
				}
				else {
					difficultyLabel.setText("Easy-->");
					for (int i = 0; i < compFishSize0.size(); i++){
						compFishSize0.get(i).changeSpeed(2); }
					for (int i = 0; i < compFishSize1.size(); i++){
						compFishSize1.get(i).changeSpeed(2); }
					for (int i = 0; i < compFishSize2.size(); i++){
						compFishSize2.get(i).changeSpeed(2); }
					for (int i = 0; i < compFishSize3.size(); i++){
						compFishSize3.get(i).changeSpeed(2); }
					for (int i = 0; i < compFishSize4.size(); i++){
						compFishSize4.get(i).changeSpeed(2); }
				}
				requestFocus();	
				difficultyLevel.setEnabled(false);
		    	movementTimer.start();
		    	fishTimer.start();
		    	timeCounter.start();
				startStop.setText("Stop");
			}
			if (command == 'o') {	
				difficultyLevel.setEnabled(true);
				startStop.setText("Start");
		    	movementTimer.stop();
		    	fishTimer.stop();
		    	timeCounter.stop();
			}
		}
	}
	

    public void focusGained(FocusEvent evt) {
    	repaint();  
    }
    
    public void focusLost(FocusEvent evt) {
    	playerFish.upPressed = false;
    	playerFish.downPressed = false;
    	playerFish.rightPressed = false;
    	playerFish.leftPressed = false;
    	movementTimer.stop();
    	fishTimer.stop();
    	timeCounter.stop();
    	repaint(); 
    }

    public boolean win(){
    	if (compFishSize0.isEmpty() && compFishSize1.isEmpty() && compFishSize2.isEmpty() 
    	&& compFishSize3.isEmpty() && timerSeconds > 5 && compFishSize4.isEmpty()){
    		return true;
    	}
    	else { return false; }
    }
    public void drawAllComputerFish(Graphics g){
		for (int i = 0; i < compFishSize0.size(); i++){
			if (compFishSize0.get(i).eatenByPlayer(playerFish)){
				compFishSize0.remove(i);
			}
			else { compFishSize0.get(i).draw(g); }
		}
		for (int i = 0; i < compFishSize1.size(); i++){
			if (compFishSize1.get(i).eatenByPlayer(playerFish)){
				compFishSize1.remove(i);
			}
			else if(compFishSize1.get(i).eatPlayer(playerFish)){
				gameOver = true;
			}
			else { compFishSize1.get(i).draw(g); }
		}
		for (int i = 0; i < compFishSize2.size(); i++){
			if (compFishSize2.get(i).eatenByPlayer(playerFish)){
				compFishSize2.remove(i);
			}
			else if(compFishSize2.get(i).eatPlayer(playerFish)){
				gameOver = true;
			}	
			else { compFishSize2.get(i).draw(g); }
		}
		for (int i = 0; i < compFishSize3.size(); i++){
			if (compFishSize3.get(i).eatenByPlayer(playerFish)){
				compFishSize3.remove(i);
			}
			else if(compFishSize3.get(i).eatPlayer(playerFish)){
				gameOver = true;
			}	
			else { compFishSize3.get(i).draw(g); }
		}
		for (int i = 0; i < compFishSize4.size(); i++){
			if (compFishSize4.get(i).eatenByPlayer(playerFish)){
				compFishSize4.remove(i);
			}
			else if(compFishSize4.get(i).eatPlayer(playerFish)){
				gameOver = true;
			}	
			else { compFishSize4.get(i).draw(g); }
		}
    }
    
}