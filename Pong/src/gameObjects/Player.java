package gameObjects;

import game.Game;
import utils.InputHandler;

public class Player extends GameObject{

	public static float speed = 6.0f;
	
	private int upKey, downKey;
	
	public Player(double x, double y) 
	{
		super(x, y, 20, 80);
		
		image = new int[width*height];
		
		for(int i = 0; i < width * height; i++)
			image[i] = 0xffffffff;
	}
	
	public Player(double x, double y, int upKey, int downKey)
	{
		super(x, y, 20, 80);
		
		this.upKey = upKey;
		this.downKey = downKey;
		
		image = new int[width*height];
		
		for(int i = 0; i < width * height; i++)
			image[i] = 0xffffffff;
	}

	@Override()
	public void update() 
	{
		if(InputHandler.KeyPressed(upKey))
		{
			if(y > height/2)
				y -= speed;
		}
		else if(InputHandler.KeyPressed(downKey))
		{
			if(y + height/2 < Game.HEIGHT)
				y += speed;
		}
	}
	
	public void update(Ball b) 
	{
		update();
	}
	
	public void setControlKeys(int up, int down)
	{
		upKey = up;
		downKey = down;
	}

}
