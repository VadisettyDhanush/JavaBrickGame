import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MapGenerator {

    public int[][] map;
    public int brickWidth;
    public int brickHeight;

    public MapGenerator(int row, int col) {
        map = new int[row][col];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = 1;
            }
        }

        brickWidth = 540 / col;
        brickHeight = 150 / row;
    }

    public void draw(Graphics2D g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    g.setColor(new Color(0x573131)); // Brick color
                    g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);

                    g.setStroke(new BasicStroke(4));
                    g.setColor(Color.BLACK);
                    g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }

    public void setBrickValue(int value, int row, int col) {
        map[row][col] = value;
    }
}

class GamePlay extends JPanel implements KeyListener, ActionListener {
    private boolean play = true;
    private int score = 0;
    private int totalBricks = 21;
    private Timer timer;
    private int delay;
    private int playerX = 310;
    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private MapGenerator map;

    public GamePlay(String difficulty) {
        map = new MapGenerator(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setBackground(Color.WHITE); // Set background color to white
        setDifficulty(difficulty);
        timer = new Timer(delay, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        map.draw((Graphics2D) g);

        g.setColor(Color.BLUE);
        g.fillRect(playerX, 550, 100, 12);

        g.setColor(Color.RED); // Ball color
        g.fillOval(ballposX, ballposY, 20, 20);

        g.setColor(Color.BLACK);
        g.setFont(new Font("MV Boli", Font.BOLD, 25));
        g.drawString("Score: " + score, 520, 30);

        // Calculate and display ball speed
        double speed = Math.sqrt(ballXdir * ballXdir + ballYdir * ballYdir);
        int displaySpeed = (int) Math.ceil(speed);
        g.setFont(new Font("MV Boli", Font.BOLD, 20));
        g.drawString("Ball Speed: " + displaySpeed, 20, 30);

        if (totalBricks <= 0) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(new Color(0XFF6464));
            g.setFont(new Font("MV Boli", Font.BOLD, 30));
            g.drawString("You Won, Score: " + score, 190, 300);
            g.setFont(new Font("MV Boli", Font.BOLD, 20));
            g.drawString("Press Enter to Restart.", 230, 350);
        }

        if (ballposY > 570) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.BLACK);
            g.setFont(new Font("MV Boli", Font.BOLD, 30));
            g.drawString("Game Over, Score: " + score, 190, 300);
            g.setFont(new Font("MV Boli", Font.BOLD, 20));
            g.drawString("Press Enter to Restart", 230, 350);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (play) {
            if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 12))) {
                ballYdir = -ballYdir;
            }
            for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if (map.map[i][j] > 0) {
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
                        if (ballRect.intersects(rect)) {
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            score += 5;

                            if (ballposX + 19 <= rect.x || ballposX + 1 >= rect.x + rect.width) {
                                ballXdir = -ballXdir;
                            } else {
                                ballYdir = -ballYdir;
                            }
                        }
                    }
                }
            }

            ballposX += ballXdir;
            ballposY += ballYdir;
            if (ballposX < 0) {
                ballXdir = -ballXdir;
            }
            if (ballposY < 0) {
                ballYdir = -ballYdir;
            }
            if (ballposX > 670) {
                ballXdir = -ballXdir;
            }
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 600) {
                playerX = 600;
            } else {
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 10) {
                playerX = 10;
            } else {
                moveLeft();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                play = true;
                ballposX = 120;
                ballposY = 350;
                ballXdir = -1;
                ballYdir = -2;
                score = 0;
                totalBricks = 21;
                map = new MapGenerator(3, 7);
                timer.setDelay(delay); // Reset the timer delay
                repaint();
            }
        }
    }

    private void moveRight() {
        play = true;
        playerX += 50;
    }

    private void moveLeft() {
        play = true;
        playerX -= 50;
    }

    private void setDifficulty(String difficulty) {
        switch (difficulty) {
            case "Easy":
                delay = 15;
                ballXdir = -1;
                ballYdir = -2;
                break;
            case "Medium":
                delay = 10;
                ballXdir = -2;
                ballYdir = -3;
                break;
            case "Hard":
                delay = 5;
                ballXdir = -3;
                ballYdir = -4;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

class DifficultySelectionScreen extends JFrame implements ActionListener {
    private JButton easyButton, mediumButton, hardButton;

    public DifficultySelectionScreen() {
        setTitle("Select Difficulty");
        setSize(300, 200);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        easyButton = new JButton("Easy");
        mediumButton = new JButton("Medium");
        hardButton = new JButton("Hard");

        easyButton.addActionListener(this);
        mediumButton.addActionListener(this);
        hardButton.addActionListener(this);

        add(easyButton, gbc);
        gbc.gridy = 1;
        add(mediumButton, gbc);
        gbc.gridy = 2;
        add(hardButton, gbc);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String difficulty = "";
        if (e.getSource() == easyButton) {
            difficulty = "Easy";
        } else if (e.getSource() == mediumButton) {
            difficulty = "Medium";
        } else if (e.getSource() == hardButton) {
            difficulty = "Hard";
        }

        // Create the game window with the selected difficulty
        JFrame gameFrame = new JFrame("Brick Breaker");
        gameFrame.setSize(700, 600);
        gameFrame.setResizable(false);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.add(new GamePlay(difficulty));
        gameFrame.setVisible(true);

        // Close the difficulty selection screen
        this.dispose();
    }
}

public class Main {
    public static void main(String[] args) {
        new DifficultySelectionScreen();
    }
}
