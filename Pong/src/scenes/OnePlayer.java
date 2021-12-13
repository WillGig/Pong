package scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import game.Game;
import gameObjects.AIPlayer;
import gameObjects.Ball;
import gameObjects.Button;
import gameObjects.Player;
import utils.InputHandler;

public class OnePlayer extends Scene
{
	private Player player1;

	protected Player player2;
	
	private Ball ball;
	
	private boolean paused;
	
	private Button start, menu;
	
	private int p1Score, p2Score;
	
	@Override
	public void update(Game game) 
	{
		
		if(InputHandler.KeyPressedAndSetFalse(KeyEvent.VK_SPACE))
			paused = !paused;
		
		if(paused)
		{
			start.update();
			if(start.IsClicked())
				paused = false;
			
			menu.update();
			if(menu.IsClicked())
				game.SetScene(0);
			
			return;
		}
			
		player1.update();
		player2.update(ball);
		ball.update();
		
		if(ball.collides(player1) || ball.collides(player2))
			ball.bounce();
		else
			ball.setCanBounce(true);
		
		if(ball.getX() > Game.WIDTH)
		{
			reset();
			p1Score++;
		}
		else if(ball.getX() < 0)
		{
			reset();
			p2Score++;
		}
	}

	@Override
	public void render(int[] pixels) 
	{
		player1.render(pixels);
		player2.render(pixels);
		ball.render(pixels);
		
		if(paused)
		{
			start.render(pixels);
			menu.render(pixels);
		}
	}

	@Override
	public void renderText(Graphics g) 
	{
		if(paused)
		{
			start.renderText(g);
			menu.renderText(g);
		}
		
		//Show Scores
		g.setColor(Color.WHITE);
		Font font = new Font("Times", 0, (int)(40*Game.SCALE));
		g.setFont(font);
		int textWidth = (int)(g.getFontMetrics(font).stringWidth(p1Score + "  |  " + p2Score)/Game.SCALE);
		g.drawString(p1Score + "  |  " + p2Score, (int)((Game.WIDTH/2 - textWidth/2)*Game.SCALE), (int)(50*Game.SCALE));
	}
	
	@Override
	public void start()
	{
		reset();
		p1Score = 0;
		p2Score = 0;
	}
	
	public void reset()
	{
		player1 = new Player(Game.WIDTH * 0.1, Game.HEIGHT/2, KeyEvent.VK_W, KeyEvent.VK_S);
		player2 = new AIPlayer(Game.WIDTH * 0.9, Game.HEIGHT/2);
		ball = new Ball();
		
		paused = true;
		
		start = new Button(Game.WIDTH/2, Game.HEIGHT/2 - 50, 150, 50, "START");
		menu = new Button(Game.WIDTH/2, Game.HEIGHT/2 + 50, 150, 50, "MENU");
	}

}
