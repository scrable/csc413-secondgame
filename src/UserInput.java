package src;

import src.WorldItems.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserInput implements KeyListener
{
    private final int up;
    private final int down;
    private final int right;
    private final int left;
    private final int shoot;
    private final int scarab;
    private Player player;

    UserInput(Player player, int up, int down, int left, int right, int shoot, int scarab)
    {
        this.player = player;
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
        this.shoot = shoot;
        this.scarab = scarab;
    }

    @Override
    public void keyTyped(KeyEvent ke)
    {

    }

    @Override
    public void keyPressed(KeyEvent ke)
    {
        int keyPressed = ke.getKeyCode();
        if (keyPressed == up)
        {
            this.player.toggleUpPressed();
        }
        if (keyPressed == down)
        {
            this.player.toggleDownPressed();
        }
        if (keyPressed == left)
        {
            this.player.toggleLeftPressed();
        }
        if (keyPressed == right)
        {
            this.player.toggleRightPressed();
        }
        if (keyPressed == shoot)
        {
            this.player.toggleShootPressed();
        }
        if (keyPressed == scarab)
        {
            this.player.toggleScarabPressed();
        }
    }

    @Override
    public void keyReleased(KeyEvent ke)
    {
        int keyReleased = ke.getKeyCode();
        if (keyReleased == up)
        {
            this.player.unToggleUpPressed();
        }
        if (keyReleased == down)
        {
            this.player.unToggleDownPressed();
        }
        if (keyReleased == left)
        {
            this.player.unToggleLeftPressed();
        }
        if (keyReleased == right)
        {
            this.player.unToggleRightPressed();
        }
        if (keyReleased == shoot)
        {
            this.player.unToggleShootPressed();
        }
        if (keyReleased == scarab)
        {
            this.player.unToggleScarabPressed();
        }
    }
}
