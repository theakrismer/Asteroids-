import edu.digipen.*;
import edu.digipen.math.Vec2;
import edu.digipen.particlesystem.CircleEmitter;
import edu.digipen.particlesystem.ParticleSystem;

import java.awt.event.KeyEvent;

/**
 * Created by david.krismer on 6/30/2015.
 */
public class BossLevel extends GameLevel
{
	int timeToGameOver = 0;
	boolean mute = false;
	boolean playerDead = false;
	int stopMusic = 0;
	@Override public void create()
	{
		//Create the player
		Ship ship = new Ship();
		ship.setPositionY(ship.getPositionY() - 150);
		ship.updateDir(90);
		ObjectManager.addGameObject(ship);

		//create health bar
		GameObject hpMeter = new GameObject("HPMeter", 200, 50, "HealthMeter.png");
		hpMeter.setPosition(0, -250);
		ObjectManager.addGameObject(hpMeter);
		hpMeter.setZOrder(99999);

		GameObject hpBar = new HealthBar("HPBar", 190,  24, "HealthBar.png");
		hpBar.setPosition(0, -258);
		ObjectManager.addGameObject(hpBar);

		//test create the boss
		GameObject boss = new Boss();
		boss.setPosition(0, 600);
		boss.setScale(1f, 1f);
		ObjectManager.addGameObject(boss);

		GameObject fade = new GameObject("HyperdriveFade", 800, 600, "HyperdriveFade.png");
		fade.setOpacity(0);
		ObjectManager.addGameObject(fade);


	}

	@Override public void initialize()
	{
		//set the background colour and make a particle system
		CircleEmitter emitter = new CircleEmitter(new Vec2(0.0f, 0.0f), 10000, 0.1f, 20.0f, 600.0f, 0, 360);
		ParticleSystem ps = ParticleSystem.create("particleSystemProperties.json", "particleProperties.json", emitter);
		ps.setZOrder(-2000);
		ObjectManager.addGameObject(ps);
		Graphics.setBackgroundColor(0.0f, 0.0f, 0f);

		//Add and start the boss music
		SoundManager.addBackgroundSound("BossBG", "AsteroidsBossMusic.wav", true);
		SoundManager.playBackgroundSound("BossBG");
	}

	@Override public void update(float v)
	{
		BossFollower bossFollower = (BossFollower)ObjectManager.getGameObjectByName("BossFollower");

		if (bossFollower != null)
		{
			if (bossFollower.bossIsDead)
			{
				while (stopMusic == 0)
				{
					stopMusic++;
					SoundManager.stopAllPlayingSounds();
				}
			}
		}



		//remove scoring, so you can never boost up to hyperspace
		Ship player = (Ship)ObjectManager.getGameObjectByName("Ship");
		if (player != null)
		{
			player.warpScore = 999999999;
			player.score = 0;
			if(player.ShipHealth <= 0)
			{
				playerDead = true;
			}
		}
		if (playerDead == true)
		{
			timeToGameOver++;
			if (timeToGameOver >= 180)
			{
				GameLevelManager.goToLevel(new GameOver());
			}
		}

		//Basic functionality
		if (InputManager.isTriggered(KeyEvent.VK_E))
		{
			Graphics.setDrawCollisionData(true);
		}

		if (InputManager.isTriggered(KeyEvent.VK_Q))
		{
			Graphics.setDrawCollisionData(false);
		}

		if (InputManager.isTriggered(KeyEvent.VK_ESCAPE))
		{
			ObjectManager.clearData();
			SoundManager.stopAllPlayingSounds();
			GameLevelManager.goToLevel(new MainMenu());
		}

		//Pausing/muting music
		if(InputManager.isTriggered(KeyEvent.VK_M))
		{
			if (mute == false)
			{
				SoundManager.pauseBackgroundSound("BossBG");
				mute = true;
			}
			else if(mute == true)
			{
				SoundManager.unpauseBackgroundSound("BossBG");
				mute = false;
			}
		}

	}

	@Override public void uninitialize()
	{

	}
}
