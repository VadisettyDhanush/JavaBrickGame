package ChessGame;

public class Board {
    private Spot[][] boxes;

    public Board() {
        this.boxes = new Spot[8][8];
        this.resetBoard();
    }

    public Spot getBox(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new IllegalArgumentException("Index out of bound");
        }
        return boxes[x][y];
    }

    public void resetBoard() {
        // Initialize white pieces
        boxes[0][0] = new Spot(0, 0, new Rook(true));
        boxes[0][1] = new Spot(0, 1, new Knight(true));
        boxes[0][2] = new Spot(0, 2, new Bishop(true));
        boxes[0][3] = new Spot(0, 3, new Queen(true));
        boxes[0][4] = new Spot(0, 4, new King(true));
        boxes[0][5] = new Spot(0, 5, new Bishop(true));
        boxes[0][6] = new Spot(0, 6, new Knight(true));
        boxes[0][7] = new Spot(0, 7, new Rook(true));
        for (int i = 0; i < 8; i++) {
            boxes[1][i] = new Spot(1, i, new Pawn(true));
        }

        // Initialize black pieces
        boxes[7][0] = new Spot(7, 0, new Rook(false));
        boxes[7][1] = new Spot(7, 1, new Knight(false));
        boxes[7][2] = new Spot(7, 2, new Bishop(false));
        boxes[7][3] = new Spot(7, 3, new Queen(false));
        boxes[7][4] = new Spot(7, 4, new King(false));
        boxes[7][5] = new Spot(7, 5, new Bishop(false));
        boxes[7][6] = new Spot(7, 6, new Knight(false));
        boxes[7][7] = new Spot(7, 7, new Rook(false));
        for (int i = 0; i < 8; i++) {
            boxes[6][i] = new Spot(6, i, new Pawn(false));
        }

        // Initialize remaining boxes without any piece
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                boxes[i][j] = new Spot(i, j, null);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("   0 1 2 3 4 5 6 7\n");
        for (int row = 0; row < 8; row++) {
            sb.append(row).append(" ");
            for (int col = 0; col < 8; col++) {
                Spot spot = boxes[row][col];
                Piece piece = spot.getPiece();
                if (piece == null) {
                    sb.append(". ");
                } else {
                    char symbol = getPieceSymbol(piece);
                    sb.append(symbol).append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private char getPieceSymbol(Piece piece) {
        if (piece instanceof Pawn) return 'P';
        if (piece instanceof Rook) return 'R';
        if (piece instanceof Knight) return 'N';
        if (piece instanceof Bishop) return 'B';
        if (piece instanceof Queen) return 'Q';
        if (piece instanceof King) return 'K';
        return '.';
    }
}
