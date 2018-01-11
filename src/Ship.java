import edu.digipen.*;
import edu.digipen.math.PFRandom;
import edu.digipen.math.Vec2;

import java.awt.event.KeyEvent;
import java.util.Random;


public class Ship extends Movement
{
	int shootEffect = 1;
	public int score = 0;
	public int warpScore = 80;
	int timeTillWarp = 0;
	int timeTillGameOver = 0;
	int activateFade = 0;
	float opacity = 1;
	public boolean controlsLock = false;
	GameObject fade;
	boolean godmode = false;
	int godTextOnce = 0;
	Light light = (Light)ObjectManager.getGameObjectByName("Light");
	float getRandomValue(float min, float max)
	{
		//create a new number
		Random r = new Random(/*SEED!!!*/);
		//get a float between 0 and 1 then multiply it by the min/max difference.
		float num = r.nextFloat();
		num = num * (max-min) + min;
		return num;
	}







	// Max health is used for quick resetting (initialize) and also may use so
	//  that your player doesn't get to much health from power ups or health packs
	public int MaxHealth = 100;

	// This is how much health your ship has
	//   ( maybe add functionality for health inside of applyDamage(int damage) )
	//   You may also add other functions to mess with this like addHealth...
	public int ShipHealth = 0;

	public Ship()
	{
		super("Ship", 50, 50, "Ship.png", 3, 1, 3, 0.5f);
		setCircleCollider(20);
		SoundManager.addSoundEffect("Lazer", "Lazer.wav");
		SoundManager.addSoundEffect("Lazer2", "lazer2.wav");
		SoundManager.addSoundEffect("Lazer3", "Lazer.wav");
		SoundManager.addSoundEffect("Lazer4", "lazer2.wav");
		SoundManager.addBackgroundSound("LowHealth", "lowHealth.wav", true);
	}

	@Override
	public void initialize()
	{
		animationData.play();
		ShipHealth = MaxHealth;
		setPosition(0, -150);
		light = (Light)ObjectManager.getGameObjectByName("Light");
		SoundManager.addBackgroundSound("Explode", "asteroidExplode.wav", false);
		SoundManager.addBackgroundSound("Hyperdrive", "hyperdrive.wav", false);
	}

	@Override
	public void update(float dt)
	{

		//System.out.println(getPositionY());

		light = (Light)ObjectManager.getGameObjectByName("Light");
		//Score cheat
		if (InputManager.isTriggered(KeyEvent.VK_0))
		{
			//score += 100;
		}


		//player 2 hack
		if (InputManager.isPressed(KeyEvent.VK_P) && InputManager.isPressed(KeyEvent.VK_2))
		{
			GameObject p2 = new Player2();
			p2.setPosition(getPosition());
			ObjectManager.addGameObject(p2);
		}

		//godmode
		if (InputManager.isPressed(KeyEvent.VK_G) && InputManager.isPressed(KeyEvent.VK_O) && InputManager.isPressed(KeyEvent.VK_D))
		{
			godmode = true;
			System.out.println(godmode);
			while (godTextOnce == 0)
			{
				GameObject godText = new GameObject("godmode", 200, 50, "godmode.png");
				godText.setZOrder(-9999);
				godText.setPosition(-200, -200);
				godTextOnce = 1;
			}
		}
		if (godmode == true)
		{
			MaxHealth = 2000;
			ShipHealth = MaxHealth;
		}


		if (controlsLock != true)
		{
			//shooting func. below
			if (InputManager.isTriggered(KeyEvent.VK_SPACE))
			{
				if (shootEffect == 1)
				{
					SoundManager.stopBackgroundSound("Lazer4");
					SoundManager.playBackgroundSound("Lazer");
					shootEffect++;
				}
				if (shootEffect == 2)
				{
					SoundManager.stopBackgroundSound("Lazer");
					SoundManager.playBackgroundSound("Lazer2");
					shootEffect++;
				}
				if (shootEffect == 3)
				{
					SoundManager.stopBackgroundSound("Lazer2");
					SoundManager.playBackgroundSound("Lazer3");
					shootEffect++;
					;
				}
				if (shootEffect == 4)
				{
					SoundManager.stopBackgroundSound("Lazer3");
					SoundManager.playBackgroundSound("Lazer4");
					shootEffect = 1;
				}

				shoot();
			}
		}



		//hyperdrive mechanics
		if (score >= warpScore)
		{
			//System.out.println("detected that percent is full");

			//create an object to cue the player to press shift
			while(objectCreated == 0)
			{
				//System.out.println("while loop in effect");
				GameObject hyperdriveReady = new GameObject("hyperdriveReady", 400, 100, "hyperdriveReady.png");
				hyperdriveReady.setPosition(0, 200);
				ObjectManager.addGameObject(hyperdriveReady);
				objectCreated = 1;
			}
		}

		if (InputManager.isTriggered(KeyEvent.VK_SHIFT))
		{


			//wild multishot stuff. OP as fudge
			//SpecialShoot.SecondShoot();

			//Hyperdrive trigger
			if (score >= warpScore)
			{

				fade = new GameObject("HyperdriveFade", 800, 600, "HyperdriveFade.png");
				ObjectManager.addGameObject(fade);


				SoundManager.playBackgroundSound("Hyperdrive");
				//hyperdrive activate
				hyperdriveActive = true;
				GameObject hyperdriveReady = ObjectManager.getGameObjectByName("hyperdriveReady");
				if (ObjectManager.getGameObjectByName("hyperdriveReady") != null)
				{
					hyperdriveReady.kill();
				}
			}
			else
			{
				System.out.println("You have not charged your hyperdrive enough to jump!");
			}
		}
		if (hyperdriveActive == true)
		{


			opacity -= 0.01f;
			fade.setOpacity(opacity);

			GameObject warping = new GameObject("Warping", 400, 100, "LevelJump.png");
			warping.setPosition(0, 200);
			warping.setZOrder(20);
			ObjectManager.addGameObject(warping);

			timeTillWarp++;
			if (timeTillWarp >= 240)
			{



				if (warpToObama == 55)
				{
					GameLevelManager.goToLevel(new ObamaCare());
				}
				else
				{
					//warp to new level
					GameLevelManager.goToLevel(new BossLevel());
					timeTillWarp = 0;
				}

			}


			light.speedMultiplier = 20;


			if (ObjectManager.getGameObjectByName("Asteroid") != null)
			{
				ObjectManager.removeAllObjectsByName("Asteroid");
			}
			if (ObjectManager.getGameObjectByName("Enemy") != null)
			{
				ObjectManager.removeAllObjectsByName("Enemy");
			}
			if (ObjectManager.getGameObjectByName("HealthDrop") != null)
			{
				ObjectManager.removeAllObjectsByName("HealthDrop");
			}
			if (ObjectManager.getGameObjectByName("EnemyHealthDrop") != null)
			{
				ObjectManager.removeAllObjectsByName("EnemyHealthDrop");
			}
			if (ObjectManager.getGameObjectByName("HealthPickup") != null)
			{
				ObjectManager.removeAllObjectsByName("HealthPickup");
			}
		}



		//damage shake
		if (shakeActivated == true)
		{
			Vec2 camPos = Graphics.getCameraPosition();
			float camX = camPos.getX();
			float camY = camPos.getY();
			//int ranDir = PFRandom.randomRange(0, 2);

			timer++;

			startingValue++;


			//CREATE RANDOM ON COLLISION!!!
			if (random == 0)
			{
				//System.out.println("Zero");
				if (1 <= timer && timer <= 5)
				{
					Graphics.setCameraPosition(camX + 1, camY);
					//System.out.println("Phase1");
				}
				if (6 <= timer && timer <= 15)
				{
					Graphics.setCameraPosition(camX - 1, camY);

					//System.out.println("Phase2");
				}
				if (16 <= timer && timer <= 20)
				{
					//System.out.println("phase3");
					Graphics.setCameraPosition(camX + 1, camY);

				}

				if (startingValue > 20)
				{
					timer = 0;
					startingValue = 0;
					shakeActivated = false;
					//System.out.println("Loop ended");
			}
			}
			else if (random == 1)
			{
				//System.out.println("One");
				if (1 <= timer && timer <= 5)
				{
					Graphics.setCameraPosition(camX - 1, camY);
					//System.out.println("Phase1");
				}
				if (6 <= timer && timer <= 15)
				{
					Graphics.setCameraPosition(camX + 1, camY);

					//System.out.println("Phase2");
				}
				if (16 <= timer && timer <= 20)
				{
					//System.out.println("phase3");
					Graphics.setCameraPosition(camX - 1, camY);

				}

				if (startingValue > 20)
				{
					timer = 0;
					startingValue = 0;
					shakeActivated = false;
					//System.out.println("Loop ended");
				}
			}
		}







		// This will apply movement (implementation in Movement class)
		checkInput(dt);

		//world barriers
		worldBarriers();

		if (ShipHealth <= 0)
		{
			//HealthBar bar = (HealthBar)ObjectManager.getGameObjectByName("HPBar");
			//bar.newWidth = 0;
			GameObject p1 = new ShipPiece1();
			p1.setPosition(getPosition());
			ObjectManager.addGameObject(p1);

			GameObject p2 = new ShipPiece2();
			p2.setPosition(getPosition());
			ObjectManager.addGameObject(p2);

			GameObject p3 = new ShipPiece3();
			p3.setPosition(getPosition());
			ObjectManager.addGameObject(p3);

			kill();
			GameObject explosion = new Explosion();
			explosion.setPosition(getPosition());
			ObjectManager.addGameObject(explosion);
			explosion.animationData.play();
			//GameLevelManager.goToLevel(new MainMenu());
		}

		ScreenWrap.Wrapper(this);
	}

	// Check Collision for colliding with Asteroids. You can add more functionality
	//   to this.  Like maybe create a health pack object that you may collide with
	//   to gain health.
	@Override
	public void collisionReaction(GameObject collidedWith)
	{
		if ("Enemy".equals(collidedWith.getName()))
		{
			random = PFRandom.randomRange(0,2);
			shakeActivated = true;
			collidedWith.kill();
			ShipHealth -= PFRandom.randomRange(30,40);
			System.out.println("Health: " + ShipHealth);
			SoundManager.playBackgroundSound("Explode");
		}

		if ("StarBurst".equals(collidedWith.getName()))
		{
			random = PFRandom.randomRange(0,2);
			shakeActivated = true;
			collidedWith.kill();
			ShipHealth -= PFRandom.randomRange(20,40);
			System.out.println("Health: " + ShipHealth);
			SoundManager.playBackgroundSound("Explode");
		}
		if ("GeneralEnemyBullet".equals(collidedWith.getName()))
		{
			random = PFRandom.randomRange(0,2);
			shakeActivated = true;
			collidedWith.kill();
			ShipHealth -= PFRandom.randomRange(10,20);
			System.out.println("Health: " + ShipHealth);
			SoundManager.playBackgroundSound("Explode");
		}
		if ("AimedShot".equals(collidedWith.getName()))
		{
			random = PFRandom.randomRange(0,2);
			shakeActivated = true;
			collidedWith.kill();
			ShipHealth -= PFRandom.randomRange(1,5);
			System.out.println("Health: " + ShipHealth);
			SoundManager.playBackgroundSound("Explode");
		}


		if ("Asteroid".equals(collidedWith.getName()))
		{
			random = PFRandom.randomRange(0,2);
			shakeActivated = true;
			collidedWith.kill();
			ShipHealth -= PFRandom.randomRange(20, 45);
			System.out.println("Health: " + ShipHealth);

			//DESTROYED ASTEROID CODE! NEEDS TO BE REUSED WITH SHOOTING!
			Vec2 deathSpot = collidedWith.getPosition();


			int healthdropNum = 0;
			while(healthdropNum <= 4)
			{
				healthdropNum++;
				GameObject healthDrop = new HealthDrops();
				healthDrop.setPosition(deathSpot.getX() + getRandomValue(-5, 5), deathSpot.getY() + getRandomValue(-5, 5));
				ObjectManager.addGameObject(healthDrop);

			}
			SoundManager.playBackgroundSound("Explode");

			if (ShipHealth <= 30)
			{
				SoundManager.playBackgroundSound("LowHealth");
			}


		}

		if ("HealthDrop".equals(collidedWith.getName()))
		{
			score = score + 1;
			System.out.println("Score: "+ score);
			if (ShipHealth < 100)
			{

				ShipHealth += 1;
				System.out.println("Health: " + ShipHealth);

				if (ShipHealth > 30)
				{
					SoundManager.stopBackgroundSound("LowHealth");
				}
			}
			collidedWith.kill();
		}
		if ("EnemyHealthDrop".equals(collidedWith.getName()))
		{
			score = score + 2;
			System.out.println("Score: "+ score);
			if (ShipHealth < 100)
			{

				ShipHealth += 2;
				System.out.println("Health: " + ShipHealth);
				if (ShipHealth > 30)
				{
					SoundManager.stopBackgroundSound("LowHealth");
				}
			}
			collidedWith.kill();
		}
		if ("HealthPickup".equals(collidedWith.getName()))
		{

			//System.out.println("Score: " + score);
			collidedWith.kill();
			ShipHealth = 100;
			System.out.println("HEALTH RESTORED");
			SoundManager.stopBackgroundSound("LowHealth");

		}
		if ("FollowBullet".equals(collidedWith.getName()))
		{
			random = PFRandom.randomRange(0,2);
			shakeActivated = true;
			collidedWith.kill();
			ShipHealth -= PFRandom.randomRange(10, 30);
			System.out.println("Health: " + ShipHealth);
			SoundManager.playBackgroundSound("Explode");
			if (ShipHealth <= 30)
			{
				SoundManager.playBackgroundSound("LowHealth");
			}
		}
		if ("BossFollower".equals(collidedWith.getName()))
		{
			setPositionY(165.7f);
		}
	}

	// This function we can subtract damage from our health.  We can call this from
	//   other methods (functions) w/ in this class or from other
	//   classes even (i.e another classes collisionReaction method)
	public void applyDamage(int damage)
	{

	}

	// This will get our Health for us to use for checks and what not
	public int getShipHealth()
	{
		return ShipHealth;
	}

	// This will get our MaxHealth for us to use for checks and what not
	public  int getMaxHealth()
	{
		return MaxHealth;
	}

	// This will allow us to change our max health for upgrades or even down grades
	public void setMaxHealth(int newMaxHealth)
	{
		MaxHealth = newMaxHealth;
	}

	public void worldBarriers()
	{
		//X axis commented, due to screen wrapping (see script and "ScreenWrap.Wrapper(this);")
		/*if (getPositionX() >= 380)
		{
			setPositionX(380);
		}
		if (getPositionX() <= -380)
		{
			setPositionX(-380);
		}*/
		if (getPositionY() >= 265)
		{
			setPositionY(265);
		}
		if (getPositionY() <= -265)
		{
			setPositionY(-265);
		}

	}

	public void shoot()
	{
		Vec2 dir = getAcceleration();
		dir.normalize();
		ObjectManager.addGameObject(new Bullet(dir, getPosition()));
	}

	HyperdriveBar hyperdriveBar = (HyperdriveBar)ObjectManager.getGameObjectByName("HyperdriveBar");
	//float percentFilled = hyperdriveBar.newWidth;
	int objectCreated = 0;
	boolean shakeActivated = false;
	int timer = 0;
	int startingValue = 0;
	int random = PFRandom.randomRange(0,2);
	public boolean hyperdriveActive = false;
	public boolean playerDead = false;
	int warpToObama = PFRandom.randomRange(1,100);
	}

