package gameObjects;

import game.Game;


public class AIPlayer extends Player{

	public AIPlayer(double x, double y) {
		super(x, y);
	}
	
	public void update(Ball b) 
	{
		int goalY = Game.HEIGHT/2;
		
		if(Math.abs(x - b.getX()) < 200*Game.SCALE)
			goalY = (int) b.getY();
		
		if(goalY - y < speed)
		{
			if(y > height/2)
				y -= speed;
		}
		else if(goalY - y > speed)
		{
			if(y + height/2 < Game.HEIGHT)
				y += speed;
		}
	}

}
