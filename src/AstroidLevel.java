import edu.digipen.*;
import edu.digipen.math.PFRandom;
import edu.digipen.math.Vec2;
import edu.digipen.particlesystem.CircleEmitter;
import edu.digipen.particlesystem.ParticleSystem;

import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * Created by Chad on 6/23/2015.
 */
public class AstroidLevel extends GameLevel
{
	float getRandomValue(float min, float max)
	{
		//create a new number
		Random r = new Random(/*SEED!!!*/);
		//get a float between 0 and 1 then multiply it by the min/max difference.
		float num = r.nextFloat();
		num = num * (max-min) + min;
		return num;
	}
	int asteriodTimer = 0;
	int asteriodTimerTrigger = 300;
	int asteriodTimerForTrigger = 0;
	int healthSpawnTimer = 0;

	int enemySpawnTimer = 0;
	int enemySpawnTimerCap = 3600;
	int enemySpawnTimerFORCap = 0;
	boolean mute = false;
	Ship player = (Ship)ObjectManager.getGameObjectByName("Ship");
	boolean hyperdriveActive = false;
	int timeTillWarp = 0;
	int timeTillGameOver = 0;






	@Override public void create()
	{

		GameObject hpMeter = new GameObject("HPMeter", 200, 50, "HealthMeter.png");
		hpMeter.setPosition(-250, -250);
		ObjectManager.addGameObject(hpMeter);
		hpMeter.setZOrder(99999);

		GameObject hpBar = new HealthBar("HPBar", 190,  24, "HealthBar.png");
		hpBar.setPosition(-250, -258);
		ObjectManager.addGameObject(hpBar);

		GameObject hyperdriveMeter = new GameObject("HyperdriveMeter", 200, 50, "HyperdriveMeter.png");
		hyperdriveMeter.setPosition(250, -250);
		ObjectManager.addGameObject(hyperdriveMeter);
		hyperdriveMeter.setZOrder(99999);

		GameObject hyperdriveBar = new HyperdriveBar("HyperdriveBar", 190, 24, "HyperdriveBar.png");
		hyperdriveBar.setPosition(250, -258);
		ObjectManager.addGameObject(hyperdriveBar);

		//GameObject hyperdriveFade = new GameObject("HyperdriveFade", 5000, 5000, "HyperdriveFade.png");
		//hyperdriveFade.setZOrder(9999999);
		//hyperdriveFade.setModulationColor(1f, 0f, 0f ,0f);
		//ObjectManager.addGameObject(hyperdriveFade);




		Ship ship = new Ship();
		ship.updateDir(90);
		ObjectManager.addGameObject(ship);
		//sound

		SoundManager.addBackgroundSound("BG", "AsteroidsGameBackground2_EDITED.wav", true);
		//SoundManager.addBackgroundSound("LevelMusic","AsteroidsGameBackground.wav", true);
	}

	@Override public void initialize()
	{
		CircleEmitter emitter = new CircleEmitter(new Vec2(0.0f, 0.0f), 10000, 0.1f, 20.0f, 600.0f, 0, 360);
		ParticleSystem ps = ParticleSystem.create("particleSystemProperties.json", "particleProperties.json", emitter);
		ObjectManager.addGameObject(ps);

		//SoundManager.playBackgroundSound("LevelMusic");
		Graphics.setBackgroundColor(0.0f, 0.0f, 0f);

		int numSpawned = 0;		SoundManager.playBackgroundSound("BG");
		while (numSpawned <= 15)
		{
			numSpawned++;

			GameObject light = new Light();
			light.setPosition(PFRandom.randomRange(-300, 300), 300);
			//System.out.println(light.getPositionX());
			ObjectManager.addGameObject(light);

		}



		// Create Asteroids here
		//   Give each asteroid a random starting position.
		//
		//   If you want to be really fancy, you could use random to load in different
		//   size asteroids (I left it so you must specify the parameters of the
		//   constructor of the Asteroid class for this reason).
		//
		//   Another thing to try is to maybe giving your asteroids a random direction
		//     to travel when created.  You could do this inside of the Asteroids initialize function.
		//
		//   Another thing to try is to also maybe give the asteroids different speeds.
		//     Once again you could implement this functionality inside of the Asteroid class.



	}

	float random = getRandomValue(0,2);
	@Override public void update(float v)
	{
		Ship player = (Ship)ObjectManager.getGameObjectByName("Ship");
		if (player == null)
		{
			timeTillGameOver++;
		}
		if (timeTillGameOver >= 180)
			GameLevelManager.goToLevel(new GameOver());



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

		//sound
		if(InputManager.isTriggered(KeyEvent.VK_M))
		{
			if (mute == false)
			{
				SoundManager.pauseBackgroundSound("BG");
				mute = true;
			}
			else if(mute == true)
			{
				SoundManager.unpauseBackgroundSound("BG");
				mute = false;
			}
		}

		if(player != null)
		{
			hyperdriveActive = player.hyperdriveActive;
		}


		if (hyperdriveActive == false)
		{

			enemySpawnTimer++;
			enemySpawnTimerFORCap++;
			if (enemySpawnTimer >= enemySpawnTimerCap)
			{
				GameObject enemy = new Enemy();
				enemy.setPosition(-100000, 200);
				ObjectManager.addGameObject(enemy);
				enemySpawnTimer = 0;
			}
			if (enemySpawnTimerFORCap >= enemySpawnTimerCap)
			{
				enemySpawnTimerCap = enemySpawnTimerCap - 120;
				enemySpawnTimerFORCap = 0;
			}
			if (enemySpawnTimerCap <= 0)
			{
				enemySpawnTimerCap = 59;
			}

			asteriodTimer++;
			asteriodTimerForTrigger++;
			healthSpawnTimer++;

			if (asteriodTimer >= asteriodTimerTrigger)
			{
				int size = PFRandom.randomRange(32, 124);
				GameObject asteroid = new Asteroid(size);
				asteroid.setPosition(getRandomValue(-300, 300), getRandomValue(400, 300));
				ObjectManager.addGameObject(asteroid);
				asteriodTimer = 0;

			}
			if (asteriodTimerForTrigger >= 1800)
			{
				asteriodTimerTrigger -= 60;
				asteriodTimerForTrigger = 0;
			}
			if (asteriodTimerTrigger <= 0)
			{
				asteriodTimerTrigger = 30;
			}
			//if (healthSpawnTimer >= 3600)
			if (healthSpawnTimer >= 3600)
			{
				GameObject fullHealth = new FullHP();
				fullHealth.setPosition(getRandomValue(-200, 200), getRandomValue(-200, 200));
				ObjectManager.addGameObject(fullHealth);
				healthSpawnTimer = 0;
			}
		}
	}

	@Override public void uninitialize()
	{
		// You will want to remove all Asteroids from ObjectManager HERE!!!
		ObjectManager.removeAllObjects();
		//stop all sound
		SoundManager.stopAllPlayingSounds();
	}




}
