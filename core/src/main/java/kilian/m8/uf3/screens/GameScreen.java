package kilian.m8.uf3.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;

import kilian.m8.uf3.actors.Ground;
import kilian.m8.uf3.actors.Player;
import kilian.m8.uf3.helpers.AssetManager;
import kilian.m8.uf3.utils.Settings;
import kilian.m8.uf3.utils.SimpleBtn;

public class GameScreen implements Screen {

    private final Game game;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Stage stage;

    private SimpleBtn btnLeft, btnRight, btnJump;
    private Player player;
    private Ground ground;

    public GameScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);

        stage = new Stage();

        btnLeft = new SimpleBtn(AssetManager.btnLeft, -20f, 0f, Settings.BUTTON_WIDTH, Settings.BUTTON_HEIGHT);
        btnRight = new SimpleBtn(AssetManager.btnRight, 75f, 0f, Settings.BUTTON_WIDTH, Settings.BUTTON_HEIGHT);
        btnJump = new SimpleBtn(AssetManager.btnJump, Settings.SCREEN_WIDTH - Settings.BUTTON_WIDTH, 0f, Settings.BUTTON_WIDTH, Settings.BUTTON_HEIGHT);

        player = new Player();
        stage.addActor(player);

        ground = new Ground(AssetManager.groundTexture, 200f, 100f, 400f, 40f);
        stage.addActor(ground);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        stage.act(delta);

        boolean moveLeftPressed = false;
        boolean moveRightPressed = false;
        boolean jumpPressed = false;

        int maxPointers = 2;
        for (int i = 0; i < maxPointers; i++) {
            if (Gdx.input.isTouched(i)) {
                Vector3 touchPos = new Vector3(Gdx.input.getX(i), Gdx.input.getY(i), 0);
                camera.unproject(touchPos);

                if (btnLeft.isTouched(touchPos.x, touchPos.y)) {
                    moveLeftPressed = true;
                }
                if (btnRight.isTouched(touchPos.x, touchPos.y)) {
                    moveRightPressed = true;
                }
                if (btnJump.isTouched(touchPos.x, touchPos.y)) {
                    jumpPressed = true;
                }
            }
        }

        if (moveLeftPressed && !moveRightPressed) {
            player.moveLeft(delta);
        } else if (moveRightPressed && !moveLeftPressed) {
            player.moveRight(delta);
        } else {
            player.stopHoritzontal();
        }

        if (jumpPressed) {
            player.jump();
        }

        batch.begin();
        batch.draw(AssetManager.blueDungeonBackground, 0, 0, Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);
        btnLeft.draw(batch);
        btnRight.draw(batch);
        btnJump.draw(batch);
        batch.end();

        stage.draw();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
    }
}
