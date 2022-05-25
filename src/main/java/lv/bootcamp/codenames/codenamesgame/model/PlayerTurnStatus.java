package lv.bootcamp.codenames.codenamesgame.model;

public class PlayerTurnStatus {
    private boolean isPlayersTurn;
    private boolean isSpymaster;

    public boolean isPlayersTurn() {
        return isPlayersTurn;
    }

    public void setPlayersTurn(boolean playersTurn) {
        isPlayersTurn = playersTurn;
    }

    public boolean isSpymaster() {
        return isSpymaster;
    }

    public void setSpymaster(boolean spymaster) {
        isSpymaster = spymaster;
    }
}
