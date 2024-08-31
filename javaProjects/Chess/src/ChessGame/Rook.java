package ChessGame;

public class Rook extends Piece {
    public Rook(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        // Implement Rook movement logic
        return true; // Simplified for placeholder
    }
}
