package lv.bootcamp.codenames.codenamesgame.model;

public class Team {
    private Player spymaster;
    private Player operative;
    private int playerCount;

    public void addPlayer(Player player){
        if (spymaster==null){
            spymaster=player;
            playerCount++;
        }
        else if (operative==null){
            operative=player;
            playerCount++;
        }
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public boolean isTeamFull(){
        return spymaster!=null&&operative!=null;
    }

    public Player getSpymaster() {
        return spymaster;
    }

    public void setSpymaster(Player spymaster) {
        this.spymaster = spymaster;
    }

    public Player getOperative() {
        return operative;
    }

    public void setOperative(Player operative) {
        this.operative = operative;
    }
}
