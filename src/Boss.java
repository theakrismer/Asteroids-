import edu.digipen.*;

import java.awt.event.KeyEvent;

/**
 * Created by david.krismer on 7/1/2015.
 */
public class Boss extends GameObject
{
	int entryTimer = 0;
	int spawnBossName = 0;
	int spawnStuffMax = 1700;
	int bossTextMax = 1800;
	int shakeActive = 0;
	GameObject bossFightText;
	GameObject bossHPBar;
	GameObject bossFollower;
	float bossSpeed = 0.3f;
	int playOnce = 0;
	int timerToWin = 0;

	int playSound1 = 0;
	int playSound2 = 0;
	int playSound3 = 0;
	int playSound4 = 0;
	int playSound5 = 0;
	float opacity = 0;


	public Boss()
	{
		super("Boss", 800, 400, "Boss.png", 3, 1, 3, 0.05f);
		SoundManager.addBackgroundSound("rumble", "rumble.wav", false);
		SoundManager.addBackgroundSound("bossExplode", "bossExplode.wav", false);
		SoundManager.addBackgroundSound("finalExplode", "finalExplode.wav", false);

	}

	@Override public void update (float dt)
	{

		Ship ship = (Ship)ObjectManager.getGameObjectByName("Ship");
		BossFollower bossFollower = (BossFollower)ObjectManager.getGameObjectByName("BossFollower");
		if (bossFollower != null)
		{
			if (bossFollower.bossIsDead == true)
			{
				GameObject fade = ObjectManager.getGameObjectByName("HyperdriveFade");
				fade.setZOrder(999999999);
				fade.setOpacity(opacity);
				timerToWin++;
				opacity += 0.001;
				while (playOnce == 0)
				{
					playOnce = 1;
					SoundManager.playBackgroundSound("rumble");
				}
				shakeActive++;
				//System.out.println("Boss follower died");
				while (shakeActive >= 15)
				{
					shakeActive = 0;
					ship.shakeActivated = true;
				}

				if (timerToWin >= 60)
				{
					while (playSound1 == 0)
					{
						playSound1++;
						SoundManager.playBackgroundSound("bossExplode");
					}
				}

				if (timerToWin >= 180)
				{
					while (playSound2 == 0)
					{
						playSound2++;
						SoundManager.playBackgroundSound("bossExplode");
					}
				}

				if (timerToWin >= 300)
				{
					while (playSound3 == 0)
					{
						playSound3++;
						SoundManager.playBackgroundSound("bossExplode");
					}
				}

				if (timerToWin >= 500)
				{
					while (playSound4 == 0)
					{
						playSound4++;
						SoundManager.playBackgroundSound("bossExplode");
					}
				}

				if (timerToWin >= 1000)
				{
					while (playSound5 == 0)
					{
						playSound5++;
						SoundManager.playBackgroundSound("finalExplode");
					}
				}
				if (timerToWin >= 1600)
				{
					GameLevelManager.goToLevel(new WinScreen());
				}

			}
		}





		//System.out.println(getPositionY());
		if (InputManager.isTriggered(KeyEvent.VK_9))
		{
			/*entryTimer = 50;
			spawnStuffMax = 60;
			bossTextMax = 70;
			setPosition(0f, 150.01291f);
			bossSpeed = 0;*/
		}




		entryTimer++;
		if(entryTimer <= 1500)
		{
			setPositionY(getPositionY() - bossSpeed);
		}
		if (entryTimer >= spawnStuffMax)
		{
			while (spawnBossName == 0)
			{
				spawnBossName++;
				bossFightText = new GameObject("BossText", 400, 100, "BossFightText.png");
				bossFightText.setPosition(0, 0);
				ObjectManager.addGameObject(bossFightText);
				SoundManager.playBackgroundSound("Explode");
				//create boss health Bar
				bossHPBar = new GameObject("BossHPBar", 100, 500, "BossHPBar.png");
				bossHPBar.setPosition(344, -20);

				GameObject bossHPMeasure = new BossHPBar();
				ObjectManager.addGameObject(bossHPMeasure);

				//create the bosses hitbox object
				bossFollower = new BossFollower();
				ObjectManager.addGameObject(bossFollower);
			}
		}
		if (entryTimer >= bossTextMax)
		{
			bossFightText.kill();
		}

	}

	@Override public void initialize()
	{
		SoundManager.addBackgroundSound("Explode", "AsteroidExplode.wav", false);
		animationData.play();
	}
}
