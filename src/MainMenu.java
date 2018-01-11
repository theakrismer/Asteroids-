import edu.digipen.*;
import edu.digipen.math.PFRandom;
import edu.digipen.math.Vec2;
import edu.digipen.particlesystem.CircleEmitter;
import edu.digipen.particlesystem.ParticleSystem;

import java.awt.event.KeyEvent;

/**
 * Created by Chad on 6/23/2015.
 */
public class MainMenu extends GameLevel
{
	GameObject menuShip;
	GameObject startTextToShake;
	GameObject quitTextToShake;
	int shakeUp = 0;
	int shakeDown = 0;
	int correctSoundTimer = 0;
	int correctSoundTimer2 = 0;


	@Override public void create()
	{
		SoundManager.addBackgroundSound("MenuBG", "AsteroidsMenuBackground_EDITED.wav", true);



		GameObject startText = new StartText();
		GameObject quitText = new QuitText();

		startText.setPosition(0, 100);
		quitText.setPosition(0, -100);

		ObjectManager.addGameObject(startText);
		ObjectManager.addGameObject(quitText);


		GameObject menuShip1 = new MenuShips();
		menuShip1.setPosition(-4000, PFRandom.randomRange(10.0f, 30.0f));
		ObjectManager.addGameObject(menuShip1);
		GameObject menuShip2 = new MenuShips();
		menuShip2.setPosition(PFRandom.randomRange(-4050, -4010), PFRandom.randomRange(-40, -10));
		ObjectManager.addGameObject(menuShip2);

		menuShip = ObjectManager.getGameObjectByName("MenuShip");
		startTextToShake = ObjectManager.getGameObjectByName("StartText");
		quitTextToShake = ObjectManager.getGameObjectByName("QuitText");

	}

	@Override public void initialize()
	{
		SoundManager.playBackgroundSound("MenuBG");
		SoundManager.addBackgroundSound("flyby", "flyby2.wav", false);
		Graphics.setBackgroundColor(0f, 0f, 0f);

		CircleEmitter emitter = new CircleEmitter(new Vec2(0.0f, 0.0f), 10000, 0.1f, 20.0f, 600.0f, 0, 360);
		// remember to create json file
		ParticleSystem ps = ParticleSystem.create("particleSystemProperties.json", "particleProperties.json", emitter);

		ObjectManager.addGameObject(ps);
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


		if (menuShip.isInViewport() == false)
		{
			//reminder code: fix the audio



			correctSoundTimer2++;
		}

		if (correctSoundTimer2 >= 300)
		{
			correctSoundTimer = 0;
			correctSoundTimer2 = 0;
		}

		//System.out.println(correctSoundTimer);
		if(menuShip.isInViewport())
		{

			if (correctSoundTimer == 0)
			{
				correctSoundTimer = 1;
				SoundManager.playBackgroundSound("flyby");
			}


			Vec2 cameraPos = Graphics.getCameraPosition();
			shakeUp++;
			shakeDown++;
			if (shakeUp >= 0)
			{
				Graphics.setCameraPosition(cameraPos.getX(),
						cameraPos.getY() + 1);
				quitTextToShake.setPositionY(quitTextToShake.getPositionY() - 1);
				startTextToShake.setPositionY(startTextToShake.getPositionY() - 1);
			}
			if (shakeDown >= 3)
			{
				shakeUp = -3;
				Graphics.setCameraPosition(cameraPos.getX(), cameraPos.getY() - 1);
				quitTextToShake.setPositionY(quitTextToShake.getPositionY() + 1);
				startTextToShake.setPositionY(startTextToShake.getPositionY() + 1);
			}
			if (shakeDown >= 6)
			{
				shakeDown = -3;
			}

		}
	}

	@Override public void uninitialize()
	{

	}
}
