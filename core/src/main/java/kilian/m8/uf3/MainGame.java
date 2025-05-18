package kilian.m8.uf3;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import kilian.m8.uf3.helpers.AssetManager;
import kilian.m8.uf3.screens.GameScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainGame extends Game {

    @Override
    public void create() {
        AssetManager.load();
        setScreen(new GameScreen(this));
    }

    @Override
    public void resize (int width, int height){
        super.resize(width, height);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void pause(){
        super.pause();
    }

    @Override
    public void resume(){
        super.resume();
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetManager.dispose();
    }
}
