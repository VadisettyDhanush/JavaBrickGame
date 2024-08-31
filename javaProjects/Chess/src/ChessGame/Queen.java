package ChessGame;

public class Queen extends Piece {
    public Queen(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        // Implement Queen movement logic
        return true; // Simplified for placeholder
    }
}
