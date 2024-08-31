package ChessGame;

public abstract class Player {
    protected boolean whiteSide;
    protected boolean humanPlayer;

    public boolean isWhiteSide() {
        return whiteSide;
    }

    public boolean isHumanPlayer() {
        return humanPlayer;
    }
}
