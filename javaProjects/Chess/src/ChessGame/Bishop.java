package ChessGame;

public class Bishop extends Piece {
    public Bishop(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        // Implement Bishop movement logic
        return true; // Simplified for placeholder
    }
}
