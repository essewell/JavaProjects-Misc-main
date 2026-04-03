
/**
 * TheBoard is where all of the computing is done for the Snake Game. All the movement, painting, sound, and calculating methods are within 
 * this class.
 *
 * @Emmett Sewell
 * @1.0
 */


import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.sound.sampled.*;

public class TheBoard extends JPanel implements ActionListener {
    final int boardWidth = 300;
    final int boardHeight = 300;
    final int dotSize = 10;
    final int allDots = 900;
    final int randomPos = 29;
    final int x[] = new int[allDots];
    final int y[] = new int[allDots];
    
    int length;
    int appleX;
    int appleY;
    int score = 0;
    int delay = 100;
    
    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = true;
    boolean inGame = false;
    boolean gameOver = false;
    boolean initialStart = true;
    boolean viewHighScores = false;
    
    
    Timer timer;
    Image snake;
    Image apple;
    Image snakeHead;
    
    /**
     * Constructor for objects of class TheBoard
     */
    public TheBoard() {
        super();
        createBoard();
    }
    /**
     * paintComponent allows the text on screen to be written and give the user instructions
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (initialStart){
            firstBootScreen(g);
        }
        if (!inGame){
            showIntroScreen(g);
            
        }
        if (inGame){
            scoreUpdate(g);
        }
    }
    /**
     * A method that constantly redraws a score onto the frame
     */
    public void scoreUpdate(Graphics g){
        String Score = Integer.toString(score);
        String message = "Score: ";
        Font small = new Font("Helvetica", Font.BOLD, 13);
        FontMetrics metr = getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(Score, (boardWidth-metr.stringWidth(Score))-10, boardHeight-285);
        g.drawString(message, (boardWidth-metr.stringWidth(message))-30, boardHeight-285);
    }
    /**
     * Initial boot screen for when the game is first launched, has instructions that will not reappear
     */
    public void firstBootScreen(Graphics g) {
        String Welcome  = "SNAKE";
        String Instructions = "Use Arrow Keys to Move";
        String instructions = "Collect the Apples to win";
        Font small = new Font("Helvetica", Font.BOLD, 20);
        FontMetrics metr = getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(Welcome, (boardWidth-metr.stringWidth(Welcome))/2, boardHeight-250);
        g.drawString(Instructions, (boardWidth-metr.stringWidth(Instructions))/2, boardHeight-175);
        g.drawString(instructions, (boardWidth-metr.stringWidth(instructions))/2, boardHeight-125);
    }
    /**
     * Only draws the "press S to start", so that anytime a gameover occurs this will be shown
     */
    public void showIntroScreen(Graphics g) {
        String message = "Press s to start";
        Font small = new Font("Helvetica", Font.BOLD, 26);
        FontMetrics metr = getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (boardWidth-metr.stringWidth(message))/2, boardHeight-50);
    }
    /**
     * createBoard sets basic parameters for the board
     */
    public void createBoard(){
        setBackground(Color.blue);
        setFocusable(true);
        addKeyListener(new actions());
        setPreferredSize(new Dimension(boardWidth, boardHeight));    
    }
    /**
     * graphics creates imageIcons for each opject in the game
     */
    public void graphics(){
        ImageIcon snakeGraphic = new ImageIcon("dot.png");
        snake = snakeGraphic.getImage();
        ImageIcon appleGraphic = new ImageIcon("apple.png");
        apple = appleGraphic.getImage();
        ImageIcon snakeHeadGraphic = new ImageIcon("head.png");
        snakeHead = snakeHeadGraphic.getImage();
        ImageIcon iconGraphic = new ImageIcon("icon");
    }
    /**
     * runGame sets basic parameters, so that the game can be restarted when a gameOver occurs
     * It also runs the placeApple method so that the game may progress
     * the Timer is run from this method, so that the delay and speed are always consistent with the gamestate
     */
    public void runGame(){
        length = 3;
        delay = 140;
        for (int i = 0; i<length; i++){
            x[i] = 50 - i * 10;
            y[i] = 50;
        }
        placeApple();
        timer = new Timer(delay,this);
        timer.start();
    }
    /**
     * paint runs the image creation
     */
    public void paint(Graphics g){
        super.paint(g);
        createImages(g);
    }
    /**
     * createImages exists to place each apple icon where the game has designated its random location to be.
     * It also keeps the snake drawn at the right state and will keep the snake's images in line as it moves around the board 
     */
    public void createImages(Graphics g){
        if (inGame){
            g.drawImage(apple, appleX, appleY, this);
            for (int i = 0; i<length; i++){
                if (i == 0) {
                    g.drawImage(snakeHead, x[i], y[i], this);
                } else {
                    g.drawImage(snake, x[i], y[i], this);
                }
            }
        }
        if (gameOver) {
            gameOver(g);
        }
    }
    /**
     * gameOver draws the end game screen
     */
    public void gameOver(Graphics g) {
        String message = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 28);
        FontMetrics metr = getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (boardWidth-metr.stringWidth(message))/2, boardHeight/2);
    }
    /**
     * appleCheck checks constantly if the apple has been collected, and will play a coresponding sound and add points to the score
     * it also reruns the placeApple method so the game can progress
     */
    public void appleCheck() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            length++;
            playSound("collect.wav");
            score =  score + 10;
            placeApple();
        }
    }
    /**
     * placeApple exists to place the apple at a random x and y coordinate on the board.
     */
    public void placeApple() {
        int temp = (int) (Math.random() * randomPos);
        appleX = ((temp * dotSize));
        
        temp = (int) (Math.random() * randomPos);
        appleY = ((temp * dotSize));
    }
    /**
     * collision detection checks for self-collions and board limit exceedences.
     * It plays the death sound when a collision is detected
     */
    public void collisionDetection() {
        for (int i = length; i > 0; i--) {
            if ((i > 4) && (x[0] == x[i]) && (y[0] == y[i])) {
                inGame = false;
                gameOver = true;
                playSound("death.wav");
            }
        }
        if (y[0] >= boardHeight) {
            inGame = false;
            gameOver = true;
            playSound("death.wav");
        }
        if (y[0] < 0) {
            inGame = false;
            gameOver = true;
            playSound("death.wav");
        }
        if (x[0] >= boardWidth) {
            inGame = false;
            gameOver = true;
            playSound("death.wav");
        }
        if (x[0] < 0) {
            inGame = false;
            gameOver = true;
            playSound("death.wav");
        }
        if (!inGame) {
            score = 0;
            timer.stop();
        }
    }
    /**
     * movement uses keyadapters to move the snake around the board. 
     * movement of the snake is stored in arrays that will tell the drawing method where each image needs to be placed.
     */
    public void movement() {
        for (int z = length; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }
        if (left) {
            x[0] -= dotSize;
        }
        if (right) {
            x[0] += dotSize;
        }
        if (up) {
            y[0] -= dotSize;
        }
        if (down) {
            y[0] += dotSize;
        }
    }
    /**
     * actionPerformed
     */
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            appleCheck();
            collisionDetection();
            movement();
        }
        repaint();
    }
    /**
     * playSound uses the sound sampled method to play different ".wav" files stored in the game folder.
     * These sounds correspond to different events occuring in the game.
     */
    public static synchronized void playSound(final String url) {
        new Thread(new Runnable() {
 
        public void run() {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                TheBoard.class.getResourceAsStream("audio/" + url));
                clip.open(inputStream);
                clip.start(); 
            }     
            catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        }).start();
    }
    /**
     * actions is the class that checks for key movement. Each key has been set up for movement, and S is set up for starting the game.
     */
    public class actions extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if ((key == KeyEvent.VK_LEFT) && (!right)) {
                left = true;
                up = false;
                down = false;
            }
            if ((key == KeyEvent.VK_RIGHT) && (!left)) {
                right = true;
                up = false;
                down = false;
            }
            if ((key == KeyEvent.VK_UP) && (!down)) {
                up = true;
                right = false;
                left = false;
            }
            if ((key == KeyEvent.VK_DOWN) && (!up)) {
                down = true;
                right = false;
                left = false;
            }
             else {
                while (inGame == false){
                    if (key == 's' || key == 'S') {
                        up = false;
                        down = false;
                        left = false;
                        right = true;
                        inGame = true;
                        gameOver = false;
                        initialStart = false;
                        playSound("click.wav");
                        graphics();
                        runGame();
                    }
                }
            }
        }
    }
}      