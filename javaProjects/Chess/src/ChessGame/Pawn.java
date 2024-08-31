package ChessGame;

public class Pawn extends Piece {
    public Pawn(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        // Implement Pawn movement logic
        return true; // Simplified for placeholder
    }
}
