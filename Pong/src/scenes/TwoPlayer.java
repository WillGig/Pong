package scenes;

import java.awt.event.KeyEvent;

import game.Game;
import gameObjects.Player;

public class TwoPlayer extends OnePlayer
{

	@Override
	public void reset()
	{
		super.reset();
		player2 = new Player(Game.WIDTH * 0.9, Game.HEIGHT/2, KeyEvent.VK_UP, KeyEvent.VK_DOWN);
	}

}
