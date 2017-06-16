package app;

import data.CharacterReport;
import data.DrawingObject;
import data.Player;

import java.awt.*;
import java.util.List;

import static app.GameConstants.DEFAULT_CHARACTER_SIZE;
import static app.GameConstants.SCREEN_WIDTH;

/**
 * Created by 조은지 on 2017-06-16.
 */
public class PlayerFactory {
    private static PlayerFactory instance = null;
    private List<CharacterReport> characterReports;

    private PlayerFactory(){

    }

    static public PlayerFactory getInstance(){
        if(instance == null){
            instance = new PlayerFactory();
        }
        return instance;
    }

    ///

    public void setCharacterReports(List<CharacterReport> list){
        this.characterReports = list;
    }

    public Player newPlayer(int index){
        CharacterReport report = characterReports.get(index);

        Player player = new Player();
        player.setBrakingForce(report.getBrakingForce());
        player.setMaxDx(report.getMaxDx());
        player.setAx(report.getAx());
        player.getObject().setImage(report.getPath(), DEFAULT_CHARACTER_SIZE);

        final DrawingObject object = player.getObject();
        final int height = object.getHeight();
        object.setPoint(new Point(SCREEN_WIDTH / 2, GameConstants.SCREEN_HEIGHT - height));
        return player;
    }

    public List<CharacterReport> getCharacterReports() {
        return characterReports;
    }

    public int getReportSize(){
        return characterReports.size();
    }
}
