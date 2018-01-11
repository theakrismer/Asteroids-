import edu.digipen.*;
import edu.digipen.math.Vec2;
import edu.digipen.particlesystem.CircleEmitter;
import edu.digipen.particlesystem.ParticleSystem;

import java.awt.event.KeyEvent;

/**
 * Created by david.krismer on 7/3/2015.
 */
public class WinScreen extends GameLevel
{
	float opacity = 1;
	int fadeTimer = 0;
	@Override public void create()
	{
		GameObject startText = new StartText();
		GameObject quitText = new QuitText();
		GameObject winText = new GameObject("YouWin", 400, 100, "YouWin.png");

		GameObject fade = new GameObject("HyperdriveFade", 800, 600, "HyperdriveFade.png");
		fade.setOpacity(1);
		fade.setZOrder(999999999);
		ObjectManager.addGameObject(fade);


		startText.setPosition(0, 100);
		quitText.setPosition(0, -100);
		winText.setPosition(0,0);

		ObjectManager.addGameObject(startText);
		ObjectManager.addGameObject(quitText);
		ObjectManager.addGameObject(winText);


	}

	@Override public void initialize()
	{
		Graphics.setBackgroundColor(0f, 0f, 0f);

		CircleEmitter emitter = new CircleEmitter(new Vec2(0.0f, 0.0f), 10000, 0.1f, 20.0f, 600.0f, 0, 360);
		// remember to create json file
		ParticleSystem ps = ParticleSystem.create("particleSystemProperties.json", "particleProperties.json", emitter);

		ObjectManager.addGameObject(ps);
	}

	@Override public void update(float v)
	{
		GameObject fade = ObjectManager.getGameObjectByName("HyperdriveFade");

		fade.setOpacity(opacity);
		opacity -= 0.001;
		System.out.println(opacity);

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

	}
}
