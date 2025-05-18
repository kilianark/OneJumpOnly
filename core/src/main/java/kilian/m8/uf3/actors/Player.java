package kilian.m8.uf3.actors;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import kilian.m8.uf3.helpers.AssetManager;
import kilian.m8.uf3.utils.Settings;

public class Player extends Actor {

    private static final float OFFSET_X = 60f;
    private static final float JUMP_VELOCITY = 600f;
    private static final float GRAVITY = -900f;

    private Animation<TextureRegion> animMoveRight;
    private Animation<TextureRegion> animMoveLeft;
    private Animation<TextureRegion> animJumpRight;
    private Animation<TextureRegion> animJumpLeft;

    private float stateTime = 0f;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean lastDirectionRight = true;

    private float velocityY = 0f;
    private boolean isOnGround = true;



    public Player() {
        animMoveRight = AssetManager.playerAnimationMoveRight;
        animMoveLeft = AssetManager.playerAnimationMoveLeft;
        animJumpRight = AssetManager.playerAnimationJumpRight;
        animJumpLeft = AssetManager.playerAnimationJumpLeft;

        setSize(Settings.PLAYER_WIDTH, Settings.PLAYER_HEIGHT);
        setPosition(Settings.PLAYER_START_X, Settings.PLAYER_START_Y);
    }

    public void moveLeft(float delta) {
        movingLeft = true;
        movingRight = false;
        float newX = getX() - Settings.PLAYER_SPEED * delta;
        setX(newX);
    }

    public void moveRight(float delta) {
        movingRight = true;
        movingLeft = false;
        float newX = getX() + Settings.PLAYER_SPEED * delta;
        setX(newX);
    }

    public void jump() {
        if (isOnGround) {
            velocityY = JUMP_VELOCITY;
            isOnGround = false;
        }
    }

    public void stopHoritzontal() {
        movingLeft = false;
        movingRight = false;
        stateTime = 0;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (!isOnGround || movingLeft || movingRight)  stateTime += delta;
        else stateTime = 0;

        if (movingLeft) {
            if (lastDirectionRight) {
                setX(getX() - OFFSET_X);
                lastDirectionRight = false;
            }

            setX(getX() - Settings.PLAYER_SPEED * delta);

        } else if (movingRight) {
            if (!lastDirectionRight) {
                setX(getX() + OFFSET_X);
                lastDirectionRight = true;
            }

            setX(getX() + Settings.PLAYER_SPEED * delta);
        }

        if (!isOnGround) {
            velocityY += GRAVITY * delta;
            setY(getY() + velocityY * delta);

            if (getY() <= Settings.PLAYER_START_Y) {
                setY(Settings.PLAYER_START_Y);
                velocityY = 0;
                isOnGround = true;
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        TextureRegion currentFrame;

        if (!isOnGround) {
            if (lastDirectionRight) currentFrame = animJumpRight.getKeyFrame(stateTime, false);
            else currentFrame = animJumpLeft.getKeyFrame(stateTime, false);
        } else if (movingLeft) {
            currentFrame = animMoveLeft.getKeyFrame(stateTime, true);
        } else if (movingRight) {
            currentFrame = animMoveRight.getKeyFrame(stateTime, true);
        } else {
            if (lastDirectionRight) currentFrame = animMoveRight.getKeyFrame(0);
            else currentFrame = animMoveLeft.getKeyFrame(0);
        }

        batch.draw(currentFrame, getX(), getY(), getWidth(), getHeight());
    }
}
