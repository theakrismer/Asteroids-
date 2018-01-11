import edu.digipen.GameObject;
import edu.digipen.ObjectManager;
import edu.digipen.SoundManager;
import edu.digipen.math.PFRandom;

/**
 * Created by david.krismer on 7/2/2015.
 */
public class starBurst extends GameObject
{
	int timeTillSplit = 0;
	int numberOfBulletSpawned = 0;
	int forwardsBackwards = 0;
	int speedApplied = 4;
	int splitRandom = 180;
	int changeSplit = 0;


	public starBurst()
	{
		super("StarBurst", 30, 30, "StarBurst.png", 4, 1, 4, 0.2f);
		setRectangleCollider(15, 15);
		SoundManager.addBackgroundSound("split", "split.wav", false);
		SoundManager.addBackgroundSound("alarm", "spliter.wav", true);
		SoundManager.playBackgroundSound("alarm");
		SoundManager.addBackgroundSound("hit", "hit.wav", false);
		animationData.play();
	}

	public void initialize()
	{


	}

	@Override
	public void update(float dt)
	{
		setPosition(getPositionX() + PFRandom.randomRange(-5,5f), getPositionY() - 1);


		forwardsBackwards = PFRandom.randomRange(0, 3);

		while(changeSplit == 0)
		{
			changeSplit++;
			splitRandom = PFRandom.randomRange(45, 180);
		}

		timeTillSplit++;
		if (timeTillSplit >= splitRandom)
		{
			SoundManager.stopBackgroundSound("alarm");
			SoundManager.playBackgroundSound("split");

			while (numberOfBulletSpawned <= 20)
			{
				changeSplit = 0;
				numberOfBulletSpawned++;
				GeneralEnemyBullet bullet = new GeneralEnemyBullet();
				bullet.setPosition(getPosition());
				ObjectManager.addGameObject(bullet);

				bullet.speedY = speedApplied  * PFRandom.randomRange(0.79f, 1f) * -1;
				bullet.speedX = PFRandom.randomRange(-10, 10);
			}
			kill();
		}

	}


	public void collisionReaction (GameObject collidedWith)
	{
		if ("Ship".equals(collidedWith.getName()))
		{
			SoundManager.stopBackgroundSound("alarm");
		}
		if("Bullet".equals(collidedWith.getName()))
		{
			collidedWith.kill();
			SoundManager.playBackgroundSound("hit");
		}
	}

}
