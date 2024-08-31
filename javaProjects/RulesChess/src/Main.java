import java.util.Scanner;

// Piece Class
abstract class Piece {
    private boolean isWhite;
    private Spot position;

    public Piece(boolean isWhite, Spot position) {
        this.isWhite = isWhite;
        this.position = position;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public Spot getPosition() {
        return position;
    }

    public void setPosition(Spot position) {
        this.position = position;
    }

    public abstract String getSymbol();
}

// Spot Class
class Spot {
    private int x, y;

    public Spot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

// Board Class
class Board {
    private Piece[][] board;

    public Board() {
        board = new Piece[8][8];
        initializeBoard();
    }

    private void initializeBoard() {
        // Initialize pieces here
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(true, new Spot(1, i));
            board[6][i] = new Pawn(false, new Spot(6, i));
        }
        board[0][0] = new Rook(true, new Spot(0, 0));
        board[0][1] = new Knight(true, new Spot(0, 1));
        board[0][2] = new Bishop(true, new Spot(0, 2));
        board[0][3] = new Queen(true, new Spot(0, 3));
        board[0][4] = new King(true, new Spot(0, 4));
        board[0][5] = new Bishop(true, new Spot(0, 5));
        board[0][6] = new Knight(true, new Spot(0, 6));
        board[0][7] = new Rook(true, new Spot(0, 7));

        board[7][0] = new Rook(false, new Spot(7, 0));
        board[7][1] = new Knight(false, new Spot(7, 1));
        board[7][2] = new Bishop(false, new Spot(7, 2));
        board[7][3] = new Queen(false, new Spot(7, 3));
        board[7][4] = new King(false, new Spot(7, 4));
        board[7][5] = new Bishop(false, new Spot(7, 5));
        board[7][6] = new Knight(false, new Spot(7, 6));
        board[7][7] = new Rook(false, new Spot(7, 7));
    }

    public Piece getPiece(int x, int y) {
        if (x >= 0 && x < 8 && y >= 0 && y < 8) {
            return board[x][y];
        }
        return null;
    }

    public void setPiece(int x, int y, Piece piece) {
        if (x >= 0 && x < 8 && y >= 0 && y < 8) {
            board[x][y] = piece;
        }
    }

    public void printBoard() {
        System.out.println("  0 1 2 3 4 5 6 7 ");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                if (piece != null) {
                    System.out.print(piece.getSymbol() + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}

// Player Class
abstract class Player {
    private String name;
    private boolean isWhite;

    public Player(String name, boolean isWhite) {
        this.name = name;
        this.isWhite = isWhite;
    }

    public String getName() {
        return name;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public abstract boolean makeMove(Board board, int startX, int startY, int endX, int endY);
}

// HumanPlayer Class
class HumanPlayer extends Player {
    public HumanPlayer(String name, boolean isWhite) {
        super(name, isWhite);
    }

    @Override
    public boolean makeMove(Board board, int startX, int startY, int endX, int endY) {
        Piece piece = board.getPiece(startX, startY);
        if (piece != null && piece.isWhite() == isWhite()) {
            board.setPiece(endX, endY, piece);
            board.setPiece(startX, startY, null);
            return true;
        } else {
            System.out.println("Invalid move. No piece at start position or it's not your piece.");
            return false;
        }
    }
}

// King Class
class King extends Piece {
    public King(boolean isWhite, Spot position) {
        super(isWhite, position);
    }

    @Override
    public String getSymbol() {
        return isWhite() ? "K" : "k";
    }
}

// Queen Class
class Queen extends Piece {
    public Queen(boolean isWhite, Spot position) {
        super(isWhite, position);
    }

    @Override
    public String getSymbol() {
        return isWhite() ? "Q" : "q";
    }
}

// Rook Class
class Rook extends Piece {
    public Rook(boolean isWhite, Spot position) {
        super(isWhite, position);
    }

    @Override
    public String getSymbol() {
        return isWhite() ? "R" : "r";
    }
}

// Knight Class
class Knight extends Piece {
    public Knight(boolean isWhite, Spot position) {
        super(isWhite, position);
    }

    @Override
    public String getSymbol() {
        return isWhite() ? "N" : "n";
    }
}

// Bishop Class
class Bishop extends Piece {
    public Bishop(boolean isWhite, Spot position) {
        super(isWhite, position);
    }

    @Override
    public String getSymbol() {
        return isWhite() ? "B" : "b";
    }
}

// Pawn Class
class Pawn extends Piece {
    public Pawn(boolean isWhite, Spot position) {
        super(isWhite, position);
    }

    @Override
    public String getSymbol() {
        return isWhite() ? "P" : "p";
    }
}

// Game Class
class Game {
    private Board board;
    private Player currentPlayer;
    private Player player1, player2;

    public Game() {
        board = new Board();
    }

    public void initialize(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        currentPlayer = player1;
    }

    public boolean isEnd() {
        // Game end logic (e.g., checkmate) would be here
        return false;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void printBoard() {
        board.printBoard();
    }

    public void move(Player player, int startX, int startY, int endX, int endY) {
        if (player.equals(currentPlayer)) {
            if (player.makeMove(board, startX, startY, endX, endY)) {
                currentPlayer = (currentPlayer == player1) ? player2 : player1;
            }
        } else {
            System.out.println("It's not your turn.");
        }
    }

    public String getStatus() {
        // Return game status (e.g., checkmate, stalemate)
        return "Game ongoing";
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();

        System.out.println("Welcome to Chess!");

        System.out.println("Player 1, enter your name:");
        String player1Name = scanner.nextLine();
        System.out.println("Player 1, choose your color (white/black):");
        String player1Color = scanner.nextLine();
        boolean isPlayer1White = player1Color.equalsIgnoreCase("white");

        System.out.println("Player 2, enter your name:");
        String player2Name = scanner.nextLine();
        // Automatically assign the opposite color to Player 2
        boolean isPlayer2White = !isPlayer1White;

        // Create Player objects
        Player player1 = new HumanPlayer(player1Name, isPlayer1White);
        Player player2 = new HumanPlayer(player2Name, isPlayer2White);

        // Initialize the game with the two players
        game.initialize(player1, player2);

        // Main game loop
        while (!game.isEnd()) {
            game.printBoard();
            System.out.println(game.getCurrentPlayer().getName() + "'s turn.");
            System.out.println("Enter your move (format: startX startY endX endY):");

            int startX = scanner.nextInt();
            int startY = scanner.nextInt();
            int endX = scanner.nextInt();
            int endY = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            game.move(game.getCurrentPlayer(), startX, startY, endX, endY);
        }

        System.out.println("Game Over. Result: " + game.getStatus());
    }
}
