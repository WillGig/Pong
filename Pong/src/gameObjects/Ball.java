package gameObjects;
import game.Game;
import utils.Sound.SoundEffect;

public class Ball extends GameObject{

	//speed increase per half second
	public static float ACCELERATION = 0.1f;
	
	//randomness in each bounce
	public static float INSTABILITY = 0.0f;
	
	public static float BASESPEED = 6.0f;
	
	public static int COLOR = 0xffff0000;
	
	public static boolean CHANGECOLORONBOUNCE = false;
	
	//In radians with 0 being up and pi/2 right
	private double direction;
	
	private float speed;
	
	//Ensures that a ball does not collide with a player multiple times in one hit
	private boolean canBounce;
	
	public Ball() {
		super(Game.WIDTH/2, Game.HEIGHT/2, 16, 16);
		
		image = new int[width*height];
		
		for(int i = 0; i < width * height; i++)
			image[i] = COLOR;
		
		direction = (Math.random() * Math.PI/8 + Math.PI/8) + (Math.PI/2) * (int)(Math.random()*4+1);
		
		speed = BASESPEED;
		
		canBounce = true;
	}

	@Override
	public void update() {
		x += speed *  Math.sin(direction);
		y += speed * -Math.cos(direction);
		
		if(y + height/2 > Game.HEIGHT || y - height/2 < 0)
			direction = Math.PI - direction;
		
		if(direction > Math.PI*2)
			direction -= Math.PI*2;
		else if(direction < 0)
			direction += Math.PI*2;
		
		speed += ACCELERATION/30.0f;
	}
	
	public void bounce()
	{
		if(!canBounce)
			return;
		direction = 2*Math.PI - direction + Math.random()*INSTABILITY;
		canBounce = false;
		if(CHANGECOLORONBOUNCE)
		{
			int color = (int)(Math.random()*0xffffff) + 0xff000000;
			for(int i = 0; i < width * height; i++)
				image[i] = color;
		}
		SoundEffect.BEEP.play();
	}
	
	//direction is in radians with 0 being up and pi/2 right
	public double getDirection()
	{
		return direction;
	}
	
	public void setDirection(double direction)
	{
		this.direction = direction;
	}
	
	public void setCanBounce(boolean bounce)
	{
		canBounce = bounce;
	}
}
