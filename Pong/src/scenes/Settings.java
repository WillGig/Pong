package scenes;

import java.awt.Graphics;

import game.Game;
import gameObjects.Ball;
import gameObjects.Button;
import gameObjects.CheckBox;
import gameObjects.ColorSlider;
import gameObjects.Player;
import gameObjects.Slider;
import utils.Sound;

public class Settings extends Scene{

	private Slider playerSpeed, ballSpeed, acceleration, instability, sound;
	private ColorSlider ballColor;
	private CheckBox changeColor, showFPS;
	
	private Button returnToMenu;
	
	@Override
	public void update(Game game) {
		
		playerSpeed.update();
		ballSpeed.update();
		acceleration.update();
		instability.update();
		ballColor.update();
		changeColor.update();
		showFPS.update();
		Game.SHOWFPS = showFPS.isChecked();
		sound.update();
		
		returnToMenu.update();
		if(returnToMenu.IsClicked())
		{
			saveSettings();
			game.SetScene(0);
		}
	}

	@Override
	public void render(int[] pixels) {
		playerSpeed.render(pixels);
		ballSpeed.render(pixels);
		acceleration.render(pixels);
		instability.render(pixels);
		returnToMenu.render(pixels);
		ballColor.render(pixels);
		changeColor.render(pixels);
		showFPS.render(pixels);
		sound.render(pixels);
	}

	@Override
	public void renderText(Graphics g) {
		playerSpeed.renderText(g);
		ballSpeed.renderText(g);
		acceleration.renderText(g);
		instability.renderText(g);
		returnToMenu.renderText(g);
		ballColor.renderText(g);
		changeColor.renderText(g);
		showFPS.renderText(g);
		sound.renderText(g);
	}

	@Override
	public void start() {
		playerSpeed = new Slider(Game.WIDTH/2, Game.HEIGHT/2 - 100, 200, "Player Speed", 1.0f, 10.0f);
		playerSpeed.setValue(Player.speed);
		ballSpeed = new Slider(Game.WIDTH/2, Game.HEIGHT/2 - 70, 200, "Ball Speed", 1.0f, 10.0f);
		ballSpeed.setValue(Ball.BASESPEED);
		acceleration = new Slider(Game.WIDTH/2, Game.HEIGHT/2 - 40, 200, "Ball Acceleration", 0.0f, 1.0f);
		acceleration.setValue(Ball.ACCELERATION);
		instability = new Slider(Game.WIDTH/2, Game.HEIGHT/2 - 10, 200, "Bounce Instability", 0.0f, 1.0f);
		instability.setValue(Ball.INSTABILITY);
		ballColor = new ColorSlider(Game.WIDTH/2, Game.HEIGHT/2 + 20, 200, "Ball Color");
		ballColor.setColorValue(Ball.COLOR);
		changeColor = new CheckBox(Game.WIDTH/2 + 90, Game.HEIGHT/2 + 50, "Change color on Bounce");
		changeColor.setChecked(Ball.CHANGECOLORONBOUNCE);
		showFPS = new CheckBox(Game.WIDTH/2 + 90, Game.HEIGHT/2 + 80, "Show FPS");
		showFPS.setChecked(Game.SHOWFPS);
		sound = new Slider(Game.WIDTH/2, Game.HEIGHT/2 + 110, 200, "Sound", 0.0f, 1.0f);
		sound.setValue(Sound.VOLUME);
		returnToMenu = new Button(Game.WIDTH/2, Game.HEIGHT * .8, 150, 50, "RETURN");
	}
	
	private void saveSettings()
	{
		Player.speed = playerSpeed.getValue();
		Ball.BASESPEED = ballSpeed.getValue();
		Ball.ACCELERATION = acceleration.getValue();
		Ball.INSTABILITY = instability.getValue();
		Ball.COLOR = ballColor.getColorValue();
		Ball.CHANGECOLORONBOUNCE = changeColor.isChecked();
		Sound.VOLUME = sound.getValue();
	}

}
