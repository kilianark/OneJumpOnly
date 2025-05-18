package kilian.m8.uf3.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class SimpleBtn {
    TextureRegion region;
    Rectangle bounds;

    public SimpleBtn(TextureRegion region, float x, float y, float width, float height)  {
        this.region = region;
        this.bounds = new Rectangle(x, y, width, height);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(region, bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public boolean isTouched(float touchX, float touchY) {
        return bounds.contains(touchX, touchY);
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
