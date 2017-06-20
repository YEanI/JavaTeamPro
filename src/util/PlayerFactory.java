package util;

import data.CharacterReport;
import data.DrawingObject;
import data.Player;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.GameConstants.DEFAULT_CHARACTER_SIZE;
import static util.GameConstants.SCREEN_HEIGHT;
import static util.GameConstants.SCREEN_WIDTH;

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

    public void setCharacterReports(List<CharacterReport> list){
        this.characterReports = list;
    }

    public Player newPlayer(int index){
        CharacterReport report = characterReports.get(index);

        Player player = new Player();
        player.setCharacterName(report.getName());
        player.setBrakingForce(report.getBrakingForce());
        player.setMaxDx(report.getMaxDx());
        player.setAx(report.getAx());
        player.getObject().setImage(ImageUtil.loadImage(report.getPath(), DEFAULT_CHARACTER_SIZE));
        HashMap<String, Integer> aa = report.getPercent();
        int[] aaa = new int[5];

        for (Map.Entry<String, Integer> entry : aa.entrySet()) {
            if (entry.getKey().equals("F")) {
                aaa[4] = entry.getValue();
            }else {
                aaa[entry.getKey().charAt(0) - 'A'] = entry.getValue();
            }
        }

        int num = aaa[0];
        for(int i = 1; i<5; i++){
            aaa[i] += num;
            num = aaa[i];
        }
        HashMap<String, Double> bb = report.getGradeSpeed();
        double [] bbb = new double[5];
        for (Map.Entry<String, Double> entry : bb.entrySet()){
            if (entry.getKey().equals("F")){
                bbb[4] = entry.getValue();
            }else {
                bbb[entry.getKey().charAt(0) - 'A'] = entry.getValue();
            }
        }
        BombFactory.getInstance().setData(aaa, bbb);
        final DrawingObject object = player.getObject();
        final int height = object.getHeight();
        final Point point = object.getPoint();
        point.setLocation(SCREEN_WIDTH / 2, SCREEN_HEIGHT - height);
        return player;
    }

    public List<CharacterReport> getCharacterReports() {
        return characterReports;
    }

    public int getReportSize(){
        return characterReports.size();
    }
}
