package org.terasology.model;

import org.terasology.logic.manager.AssetManager;
import org.terasology.rendering.gui.framework.UIGraphicsElement;

import javax.vecmath.Vector2f;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/*
 * @author bi0hax
 * Icons for showing status effects in the buff bar.
 */
public class BuffIcon {
    private static Map<String, BuffIcon> bufficons;
    private UIGraphicsElement _element;
    private int _x;
    private int _y;

    public BuffIcon() {
        _element = new UIGraphicsElement(AssetManager.loadTexture("engine:buffs"));
        _element.setSize(new Vector2f(32, 32));
        _element.getTextureSize().set(new Vector2f(0.0624f, 0.0624f));
        _element.setVisible(true);
        _element.setPosition(new Vector2f(-10f, -16f));

        setAtlasPosition(0, 0);
    }

    public static BuffIcon get(String name) {
        if (bufficons == null) {
            loadIcons();
        }

        return bufficons.get(name.toLowerCase(Locale.ENGLISH));
    }

    private static void loadIcons() {
        bufficons = new HashMap<String, BuffIcon>();
        //* BUFFS & DE·BUFFS *//
        BuffIcon poisoned = new BuffIcon();
        BuffIcon cured = new BuffIcon();
        BuffIcon speed = new BuffIcon();
        BuffIcon maxspeed = new BuffIcon();

        //* ATLAS POSITIONS *//
        //DeBuffs
        poisoned.setAtlasPosition(0, 0);
        //Buffs
        cured.setAtlasPosition(1, 0);
        speed.setAtlasPosition(0, 1);
        maxspeed.setAtlasPosition(1, 1);

        bufficons.put("poisoned", poisoned);
        bufficons.put("cured", cured);
        bufficons.put("speed", speed);
        bufficons.put("maxspeed", maxspeed);

    }

    //@return x-offset in icon sheet
    public int getX() {
        return _x;
    }

    //@return y-offset in icon sheet
    public int getY() {
        return _y;
    }

    private void setAtlasPosition(int x, int y) {
        _x = x;
        _y = y;

        if (_element == null) {
            return;
        }

        _element.getTextureOrigin().set(new Vector2f(x * 0.0625f, y * 0.0625f));
    }
}
