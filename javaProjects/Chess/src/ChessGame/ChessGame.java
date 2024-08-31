package ChessGame;

import java.util.Scanner;

public class ChessGame {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // Player 1 input
        System.out.print("Enter player 1 type (human/computer): ");
        String player1Type = scanner.nextLine().trim().toLowerCase();
        System.out.print("Enter player 1 color (true for white, false for black): ");
        boolean player1Color = scanner.nextBoolean();
        scanner.nextLine(); // Consume newline

        // Player 2 input
        System.out.print("Enter player 2 type (human/computer): ");
        String player2Type = scanner.nextLine().trim().toLowerCase();
        System.out.print("Enter player 2 color (true for white, false for black): ");
        boolean player2Color = scanner.nextBoolean();
        scanner.nextLine(); // Consume newline

        // Create players
        Player player1 = createPlayer(player1Type, player1Color);
        Player player2 = createPlayer(player2Type, player2Color);

        // Initialize the game
        Game game = new Game();
        game.initialize(player1, player2);

        // Game loop
        while (!game.isEnd()) {
            System.out.println(game.getBoard()); // Display board state

            Player currentPlayer = game.getCurrentTurn();
            System.out.println("Current turn: " + (currentPlayer.isWhiteSide() ? "White" : "Black"));

            System.out.print("Enter move (startX startY endX endY): ");
            int startX = scanner.nextInt();
            int startY = scanner.nextInt();
            int endX = scanner.nextInt();
            int endY = scanner.nextInt();

            boolean moveResult = game.playerMove(currentPlayer, startX, startY, endX, endY);
            if (!moveResult) {
                System.out.println("Invalid move. Try again.");
            }
        }

        System.out.println("Game Over. Result: " + game.getStatus());
        scanner.close();
    }

    private static Player createPlayer(String type, boolean color) {
        if ("human".equals(type)) {
            return new HumanPlayer(color);
        } else if ("computer".equals(type)) {
            return new ComputerPlayer(color);
        } else {
            throw new IllegalArgumentException("Invalid player type");
        }
    }
}
