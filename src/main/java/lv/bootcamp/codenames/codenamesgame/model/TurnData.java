package lv.bootcamp.codenames.codenamesgame.model;

import lv.bootcamp.codenames.codenamesgame.model.gameelements.Hint;

public class TurnData {
    private SelectedCard selectedCard;
    private Hint hint;

    public TurnData() {
        this.selectedCard = new SelectedCard();
        this.hint = new Hint("");
    }

    public SelectedCard getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(SelectedCard selectedCard) {
        this.selectedCard = selectedCard;
    }

    public Hint getHint() {
        return hint;
    }

    public void setHint(Hint hint) {
        this.hint = hint;
    }
}
