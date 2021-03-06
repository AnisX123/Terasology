package org.terasology.mods.miniions.rendering.gui.components;

import org.lwjgl.opengl.Display;
import org.terasology.entitySystem.EventHandlerSystem;
import org.terasology.logic.manager.AssetManager;
import org.terasology.mods.miniions.minionenum.MinionMessagePriority;
import org.terasology.mods.miniions.utilities.MinionMessage;
import org.terasology.rendering.gui.framework.UIDisplayContainer;
import org.terasology.rendering.gui.framework.UIGraphicsElement;

import javax.vecmath.Vector2f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA.
 * User: Overdhose
 * Date: 24/05/12
 * Time: 3:34
 * Queue for message icons containing miniion messages
 */
public class UIMessageQueue extends UIDisplayContainer implements EventHandlerSystem {

    private static final float ICON_SIZE = 32.0f;

    private Queue<MinionMessage> messageQueue;
    private ArrayList<UIGraphicsElement> elements;
    static int messageCounter = 0;
    private UIGraphicsElement _messageTex;

    public UIMessageQueue() {
        elements = new ArrayList<UIGraphicsElement>();
        messageQueue = new PriorityQueue<MinionMessage>();
        float height = Display.getHeight() / 2;
        setSize(new Vector2f(ICON_SIZE, height));
        setPosition(new Vector2f(4, 4));
    }

    public void addIconToQueue(MinionMessage minionmessage) {
        messageQueue.add(minionmessage);
        removeAllDisplayElements();
        elements.clear();
        messageQueue.peek();
        int queheight = (int) getSize().y / (int) ICON_SIZE;
        int counter = 0;
        Iterator<MinionMessage> it = messageQueue.iterator();
        while (it.hasNext()) {
            MinionMessage minionMessage = it.next();
            minionMessage.setIndex(counter);
            elements.add(getMessageIcon(minionMessage.getMinionMessagePriority(), counter));
            addDisplayElement(elements.get(counter));
            //addDisplayElement(getMessageIcon(minionMessage.getMinionMessagePriority(),counter));
            if (counter++ > queheight) {
                break;
            }
        }
    }

    private UIGraphicsElement getMessageIcon(MinionMessagePriority minionMessagePriority, int counter) {
        UIGraphicsElement _messageTexture = new UIGraphicsElement(AssetManager.loadTexture("engine:items"));
        _messageTexture.getTextureSize().set(new Vector2f(16f / 256f, 16f / 256f));
        float originStart = 0;
        switch (minionMessagePriority) {
            case Urgent: {
                originStart += 16.0f;
                break;
            }
            case Normal: {
                originStart = 0;
                break;
            }
            case Debug: {
                originStart += 32.0f;
                break;
            }
            default: {
                originStart += 48.0f;
            }
        }
        _messageTexture.getTextureOrigin().set(new Vector2f(224.0f / 256f, originStart / 256f));
        _messageTexture.setSize(new Vector2f(ICON_SIZE, ICON_SIZE));
        _messageTexture.setPosition(new Vector2f(0.0f, counter * ICON_SIZE));
        _messageTexture.setVisible(true);
        return _messageTexture;
    }

    /*@Override
    public void update() {
    }*/

    @Override
    public void initialise() {
    }

    @Override
    public void shutdown() {
    }
}
