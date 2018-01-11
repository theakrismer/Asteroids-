import edu.digipen.*;
import edu.digipen.math.PFRandom;
import edu.digipen.math.Vec2;
import edu.digipen.particlesystem.CircleEmitter;
import edu.digipen.particlesystem.ParticleSystem;

import java.awt.event.KeyEvent;

/**
 * Created by david.krismer on 6/30/2015.
 */
public class GameOver extends GameLevel
{
	@Override public void create()
	{

		GameObject p1 = new ShipPiece1();
		p1.setPosition(PFRandom.randomRange(-250,250), PFRandom.randomRange(-250, 250));
		ObjectManager.addGameObject(p1);

		GameObject p2 = new ShipPiece2();
		p2.setPosition(PFRandom.randomRange(-250,250), PFRandom.randomRange(-250, 250));
		ObjectManager.addGameObject(p2);

		GameObject p3 = new ShipPiece3();
		p3.setPosition(PFRandom.randomRange(-250,250), PFRandom.randomRange(-250, 250));
		ObjectManager.addGameObject(p3);





		GameObject startText = new GameOverStartText();
		GameObject quitText = new GameOverQuitText();
		GameObject gameOver = new GameOverText();

		startText.setRotation(PFRandom.randomRange(-30,30));
		quitText.setRotation(PFRandom.randomRange(-30, 30));
		gameOver.setRotation(PFRandom.randomRange(-30, 30));

		startText.setPosition(PFRandom.randomRange(-100, 100), PFRandom.randomRange(-250, -110));
		quitText.setPosition(PFRandom.randomRange(-100, 100), PFRandom.randomRange(-100, -80));
		gameOver.setPosition(PFRandom.randomRange(-50, 50), PFRandom.randomRange(100, 250));

		ObjectManager.addGameObject(startText);
		ObjectManager.addGameObject(quitText);
		ObjectManager.addGameObject(gameOver);
	}

	@Override public void initialize()
	{
		Graphics.setBackgroundColor(0,0,0);
		CircleEmitter emitter = new CircleEmitter(new Vec2(0.0f, 0.0f), 10000, 0.1f, 20.0f, 600.0f, 0, 360);
		ParticleSystem ps = ParticleSystem.create("particleSystemProperties.json", "particleProperties.json", emitter);
		ObjectManager.addGameObject(ps);

		SoundManager.addBackgroundSound("GameOverMusic", "AsteroidsGameOver.wav",
				true);
		SoundManager.playBackgroundSound("GameOverMusic");
	}

	@Override public void update(float v)
	{

		if (InputManager.isTriggered(KeyEvent.VK_SPACE))
		{
			GameLevelManager.goToLevel(new AstroidLevel());
		}

		if (InputManager.isTriggered(KeyEvent.VK_ESCAPE))
		{
			Game.quit();
		}

	}

	@Override public void uninitialize()
	{
		ObjectManager.clearData();
		SoundManager.stopAllPlayingSounds();
	}
}
