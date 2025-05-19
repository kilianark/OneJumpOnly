package kilian.m8.uf3.helpers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;

public class AssetManager {

    public static TextureRegion blueDungeonBackground;

    public static Texture sheetPlayerMoveRt;
    public static Texture sheetPlayerMoveLt;
    public static Texture sheetPlayerJumpRt;
    public static Texture sheetPlayerJumpLt;

    public static TextureRegion[] playerMoveRt;
    public static TextureRegion[] playerMoveLt;
    public static TextureRegion[] playerJumpRt;
    public static TextureRegion[] playerJumpLt;

    public static Animation<TextureRegion> playerAnimationMoveRight;
    public static Animation<TextureRegion> playerAnimationMoveLeft;
    public static Animation<TextureRegion> playerAnimationJumpRight;
    public static Animation<TextureRegion> playerAnimationJumpLeft;

    public static Texture sheetButtons;
    public static TextureRegion btnLeft;
    public static TextureRegion btnRight;
    public static TextureRegion btnJump;

    public static Texture groundTexture;

    public static Texture spikes;


    public static void load() {
        Texture backgroundTexture = new Texture("images/background/blueDungeon.png");
        blueDungeonBackground = new TextureRegion(backgroundTexture);

        groundTexture = new Texture("images/structures/noHarmful/ground.png");
        groundTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        spikes = new Texture("images/structures/harmful/spikes.png");
        spikes.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        sheetPlayerMoveRt = new Texture("images/player/moveRt.png");
        sheetPlayerMoveRt.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        sheetPlayerMoveLt = new Texture("images/player/moveLt.png");
        sheetPlayerMoveLt.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        sheetPlayerJumpRt = new Texture("images/player/jumpRt.png");
        sheetPlayerJumpRt.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        sheetPlayerJumpLt = new Texture("images/player/jumpLt.png");
        sheetPlayerJumpLt.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        sheetButtons = new Texture("images/ui/movement_btns.png");
        sheetButtons.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        playerMoveRt = new TextureRegion[4];
        playerMoveLt = new TextureRegion[4];
        playerJumpRt = new TextureRegion[4];
        playerJumpLt = new TextureRegion[4];

        for (int i = 0; i < 4; i++) {
            playerMoveRt[i] = new TextureRegion(sheetPlayerMoveRt, i * 100, 0, 100, 100);
            playerMoveLt[i] = new TextureRegion(sheetPlayerMoveLt, i * 100, 0, 100, 100);
            playerJumpRt[i] = new TextureRegion(sheetPlayerJumpRt, i * 100, 0, 100, 100);
            playerJumpLt[i] = new TextureRegion(sheetPlayerJumpLt, i * 100, 0, 100, 100);
        }

        btnRight = new TextureRegion(sheetButtons, 0, 0, 100, 100);
        btnLeft = new TextureRegion(sheetButtons, 100, 0, 100, 100);
        btnJump = new TextureRegion(sheetButtons, 200, 0, 100, 100);

        playerAnimationMoveRight = new Animation<>(0.1f, playerMoveRt);
        playerAnimationMoveRight.setPlayMode(Animation.PlayMode.LOOP);

        playerAnimationMoveLeft = new Animation<>(0.1f, playerMoveLt);
        playerAnimationMoveLeft.setPlayMode(Animation.PlayMode.LOOP_REVERSED);

        playerAnimationJumpRight = new Animation<>(0.1f, playerJumpRt);
        playerAnimationJumpRight.setPlayMode(Animation.PlayMode.NORMAL);

        playerAnimationJumpLeft = new Animation<>(0.1f, playerJumpLt);
        playerAnimationJumpLeft.setPlayMode(Animation.PlayMode.NORMAL);
    }

    public static void dispose() {
        blueDungeonBackground.getTexture().dispose();
        sheetPlayerMoveRt.dispose();
        sheetPlayerMoveLt.dispose();
        sheetPlayerJumpRt.dispose();
        sheetPlayerJumpLt.dispose();
        sheetButtons.dispose();
        btnLeft.getTexture().dispose();
        btnRight.getTexture().dispose();
        btnJump.getTexture().dispose();
        groundTexture.dispose();
    }
}
