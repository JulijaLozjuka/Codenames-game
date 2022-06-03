package lv.bootcamp.codenames.codenamesgame.model.gameelements;

public class Card {
    private String text;
    private Color color;
    private boolean isRevealed;
    private int type;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    @Override
    public String toString() {
        return "Card{" +
                "text='" + text + '\'' +
                ", color=" + color +
                ", isRevealed=" + isRevealed +
                '}';
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
