package ChessGame;

public class King extends Piece {
    private boolean castlingDone = false;

    public King(boolean white) {
        super(white);
    }

    public boolean isCastlingDone() {
        return castlingDone;
    }

    public void setCastlingDone(boolean castlingDone) {
        this.castlingDone = castlingDone;
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        if (x + y == 1) {
            return true;
        }

        return isValidCastling(board, start, end);
    }

    private boolean isValidCastling(Board board, Spot start, Spot end) {
        if (this.isCastlingDone()) {
            return false;
        }
        // Implement castling logic here
        return false;
    }

    public boolean isCastlingMove(Spot start, Spot end) {
        int xDiff = Math.abs(start.getX() - end.getX());
        int yDiff = Math.abs(start.getY() - end.getY());
        return (xDiff == 2 && yDiff == 0) || (xDiff == 0 && yDiff == 2);
    }
}
