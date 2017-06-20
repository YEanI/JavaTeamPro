package view;

import data.Bomb;
import data.Player;
import org.junit.Test;

import java.lang.reflect.Method;

import static util.GameConstants.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by minchul on 2017-06-13.
 */
public class GameViewTest {
    private final Bomb bomb;
    private final Player player;
    private final GameView gameView;
    private Method checkCrushMethod;
    private final int x = 100;
    private final int y = 100;

    public GameViewTest() {
        gameView = new GameView();
        player = null;
//        player = new PlayerBuilder()
//                .setMax_dx(10)
//                .setBraking_force(3)
//                .setAx(3)
//                .setImage("/images/character_mario.png", DEFAULT_CHARACTER_SIZE)
//                .build();
        bomb = null;
        try {
            checkCrushMethod = GameView.class.getDeclaredMethod("checkCrush", Player.class, Bomb.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        checkCrushMethod.setAccessible(true);
    }

    //////////////////////////// TOP
    @SuppressWarnings("Duplicates")
    @Test
    public void notCrushOnTopLeft() throws Exception {
        final int px = x + DEFAULT_CRUSH_OFFSET_X - DEFAULT_BOMB_SIZE;
        final int py = y - DEFAULT_BOMB_SIZE;
//        player.getObject().setPoint(new Point(x, y));
//        bomb.getObject().setPoint(new Point(px, py));
        assertFalse((Boolean) checkCrushMethod.invoke(gameView, player, bomb));
    }
    @SuppressWarnings("Duplicates")
    @Test
    public void notCrushOnTopLeftInsideX() throws Exception {
        final int px = x + DEFAULT_CRUSH_OFFSET_X - (DEFAULT_BOMB_SIZE / 2);
        final int py = y - DEFAULT_BOMB_SIZE;
//        player.getObject().setPoint(new Point(x, y));
//        bomb.getObject().setPoint(new Point(px, py));
        assertFalse((Boolean) checkCrushMethod.invoke(gameView, player, bomb));
    }
    @SuppressWarnings("Duplicates")
    @Test
    public void notCrushOnTopInsideX() throws Exception {
        final int px = x + (DEFAULT_CHARACTER_SIZE / 2);
        final int py = y - DEFAULT_BOMB_SIZE;
//        player.getObject().setPoint(new Point(x, y));
//        bomb.getObject().setPoint(new Point(px, py));
        assertFalse((Boolean) checkCrushMethod.invoke(gameView, player, bomb));
    }
    @SuppressWarnings("Duplicates")
    @Test
    public void notCrushOnTopRightInsideX() throws Exception {
        final int px = x + DEFAULT_CHARACTER_SIZE - DEFAULT_CRUSH_OFFSET_X - (DEFAULT_BOMB_SIZE / 2);
        final int py = y - DEFAULT_BOMB_SIZE;
//        player.getObject().setPoint(new Point(x, y));
//        bomb.getObject().setPoint(new Point(px, py));
        assertFalse((Boolean) checkCrushMethod.invoke(gameView, player, bomb));
    }
    @SuppressWarnings("Duplicates")
    @Test
    public void notCrushOnTopRight() throws Exception {
        final int px = x + DEFAULT_CHARACTER_SIZE - DEFAULT_CRUSH_OFFSET_X;
        final int py = y - DEFAULT_BOMB_SIZE;
//        player.getObject().setPoint(new Point(x, y));
//        bomb.getObject().setPoint(new Point(px, py));
        assertFalse((Boolean) checkCrushMethod.invoke(gameView, player, bomb));
    }
    //////////////////////////// TOP INSIDE Y
    @SuppressWarnings("Duplicates")
    @Test
    public void notCrushOnTopLeftInsideY() throws Exception {
        final int px = x + DEFAULT_CRUSH_OFFSET_X - DEFAULT_BOMB_SIZE;
        final int py = y - DEFAULT_BOMB_SIZE + (DEFAULT_BOMB_SIZE / 2);
//        player.getObject().setPoint(new Point(x, y));
//        bomb.getObject().setPoint(new Point(px, py));
        assertFalse((Boolean) checkCrushMethod.invoke(gameView, player, bomb));
    }
    @SuppressWarnings("Duplicates")
    @Test
    public void crushOnTopLeftInsideXInsideY() throws Exception {
        final int px = x + DEFAULT_CRUSH_OFFSET_X - (DEFAULT_BOMB_SIZE / 2);
        final int py = y - DEFAULT_BOMB_SIZE + (DEFAULT_BOMB_SIZE / 2);
//        player.getObject().setPoint(new Point(x, y));
//        bomb.getObject().setPoint(new Point(px, py));
        assertTrue((Boolean) checkCrushMethod.invoke(gameView, player, bomb));
    }
    @SuppressWarnings("Duplicates")
    @Test
    public void crushOnTopInsideXInsideY() throws Exception {
        final int px = x + (DEFAULT_CHARACTER_SIZE / 2);
        final int py = y - DEFAULT_BOMB_SIZE + (DEFAULT_BOMB_SIZE / 2);
//        player.getObject().setPoint(new Point(x, y));
//        bomb.getObject().setPoint(new Point(px, py));
        assertTrue((Boolean) checkCrushMethod.invoke(gameView, player, bomb));
    }
    @SuppressWarnings("Duplicates")
    @Test
    public void crushOnTopRightInsideXInsideY() throws Exception {
        final int px = x + DEFAULT_CHARACTER_SIZE - DEFAULT_CRUSH_OFFSET_X - (DEFAULT_BOMB_SIZE / 2);
        final int py = y - DEFAULT_BOMB_SIZE + (DEFAULT_BOMB_SIZE / 2);
//        player.getObject().setPoint(new Point(x, y));
//        bomb.getObject().setPoint(new Point(px, py));
        assertTrue((Boolean) checkCrushMethod.invoke(gameView, player, bomb));
    }
    @SuppressWarnings("Duplicates")
    @Test
    public void notCrushOnTopRightInsideY() throws Exception {
        final int px = x + DEFAULT_CHARACTER_SIZE - DEFAULT_CRUSH_OFFSET_X;
        final int py = y - DEFAULT_BOMB_SIZE + (DEFAULT_BOMB_SIZE / 2);
//        player.getObject().setPoint(new Point(x, y));
//        bomb.getObject().setPoint(new Point(px, py));
        assertFalse((Boolean) checkCrushMethod.invoke(gameView, player, bomb));
    }
    /////////////////
    //////////////////////////// MIDDLE
    @SuppressWarnings("Duplicates")
    @Test
    public void notCrushOnMiddleLeft() throws Exception {
        final int px = x + DEFAULT_CRUSH_OFFSET_X - DEFAULT_BOMB_SIZE;
        final int py = y + (DEFAULT_CHARACTER_SIZE / 2);
//        player.getObject().setPoint(new Point(x, y));
//        bomb.getObject().setPoint(new Point(px, py));
        assertFalse((Boolean) checkCrushMethod.invoke(gameView, player, bomb));
    }
    @SuppressWarnings("Duplicates")
    @Test
    public void crushOnMiddleLeftInsideX() throws Exception {
        final int px = x + DEFAULT_CRUSH_OFFSET_X - (DEFAULT_BOMB_SIZE / 2);
        final int py = y + (DEFAULT_CHARACTER_SIZE / 2);
//        player.getObject().setPoint(new Point(x, y));
//        bomb.getObject().setPoint(new Point(px, py));
        assertTrue((Boolean) checkCrushMethod.invoke(gameView, player, bomb));
    }
    @SuppressWarnings("Duplicates")
    @Test
    public void crushOnMiddle() throws Exception {
        final int px = x + (DEFAULT_CHARACTER_SIZE / 2);
        final int py = y + (DEFAULT_CHARACTER_SIZE / 2);
//        player.getObject().setPoint(new Point(x, y));
//        bomb.getObject().setPoint(new Point(px, py));
        assertTrue((Boolean) checkCrushMethod.invoke(gameView, player, bomb));
    }
    @SuppressWarnings("Duplicates")
    @Test
    public void crushOnMiddleRightInsideX() throws Exception {
        final int px = x + DEFAULT_CHARACTER_SIZE - DEFAULT_CRUSH_OFFSET_X - (DEFAULT_BOMB_SIZE / 2);
        final int py = y + (DEFAULT_CHARACTER_SIZE / 2);
//        player.getObject().setPoint(new Point(x, y));
//        bomb.getObject().setPoint(new Point(px, py));
        assertTrue((Boolean) checkCrushMethod.invoke(gameView, player, bomb));
    }
    @SuppressWarnings("Duplicates")
    @Test
    public void notCrushOnMiddleRight() throws Exception {
        final int px = x + DEFAULT_CHARACTER_SIZE - DEFAULT_CRUSH_OFFSET_X;
        final int py = y + (DEFAULT_CHARACTER_SIZE / 2);
//        player.getObject().setPoint(new Point(x, y));
//        bomb.getObject().setPoint(new Point(px, py));
        assertFalse((Boolean) checkCrushMethod.invoke(gameView, player, bomb));
    }
    /////////////////
}