package scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import game.Game;
import gameObjects.AIPlayer;
import gameObjects.Ball;
import gameObjects.Button;
import gameObjects.Player;

public class MainMenu extends Scene
{
	
	private Player player1, player2;
	
	private Ball ball;

	private Button play1, play2, settings, exit;
	
	public MainMenu()
	{
		play1 = new Button(Game.WIDTH/2, Game.HEIGHT/2 - 0, 150, 40, "1 Player");
		play2 = new Button(Game.WIDTH/2, Game.HEIGHT/2 + 50, 150, 40, "2 Player");
		settings = new Button(Game.WIDTH/2, Game.HEIGHT/2 + 100, 150, 40, "SETTINGS");
		exit = new Button(Game.WIDTH/2, Game.HEIGHT/2 + 150, 150, 40, "EXIT");
		reset();
	}
	
	@Override
	public void update(Game game) 
	{
		play1.update();
		if(play1.IsClicked())
			game.SetScene(1);
		
		play2.update();
		if(play2.IsClicked())
			game.SetScene(2);
		
		settings.update();
		if(settings.IsClicked())
			game.SetScene(3);
		
		exit.update();
		if(exit.IsClicked())
			game.stop();
		
		//Background Game
		player1.update(ball);
		player2.update(ball);
		ball.update();
		
		if(ball.collides(player1) || ball.collides(player2))
			ball.bounce();
		else
			ball.setCanBounce(true);
		
		if((ball.getX() > Game.WIDTH || ball.getX() < 0) && player1.getY() == player2.getY())
			reset();
	}

	@Override
	public void render(int[] pixels) 
	{
		player1.render(pixels);
		player2.render(pixels);
		ball.render(pixels);
		
		play1.render(pixels);
		play2.render(pixels);
		settings.render(pixels);
		exit.render(pixels);
	}

	@Override
	public void renderText(Graphics g) 
	{
		play1.renderText(g);
		play2.renderText(g);
		settings.renderText(g);
		exit.renderText(g);
		
		Font font = new Font("Bell", 1, 75);
		g.setFont(font);
		g.setColor(Color.WHITE);
		int textWidth = (int)(g.getFontMetrics(font).stringWidth("PONG")/Game.SCALE);
		g.drawString("PONG", (int) ((Game.WIDTH/2 - textWidth/2)*Game.SCALE), (int) ((Game.HEIGHT/2 - 75)*Game.SCALE));
	}
	
	@Override
	public void start()
	{
		play1 = new Button(Game.WIDTH/2, Game.HEIGHT/2 - 0, 150, 40, "1 Player");
		play2 = new Button(Game.WIDTH/2, Game.HEIGHT/2 + 50, 150, 40, "2 Player");
		settings = new Button(Game.WIDTH/2, Game.HEIGHT/2 + 100, 150, 40, "SETTINGS");
		exit = new Button(Game.WIDTH/2, Game.HEIGHT/2 + 150, 150, 40, "EXIT");
		reset();
	}
	
	public void reset()
	{
		player1 = new AIPlayer(Game.WIDTH * 0.1, Game.HEIGHT/2);
		player2 = new AIPlayer(Game.WIDTH * 0.9, Game.HEIGHT/2);
		ball = new Ball();
	}

}
