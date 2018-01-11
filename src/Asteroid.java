import edu.digipen.GameObject;
import edu.digipen.ObjectManager;
import edu.digipen.SoundManager;
import edu.digipen.math.PFRandom;
import edu.digipen.math.Vec2;

import java.util.Random;

/**
 * Created by Chad on 6/24/2015.
 */
public class Asteroid extends GameObject
{
	int dmgMultiplier = 1;
	int lives = 3;
	int baseAsteroidSpeed = PFRandom.randomRange(1,4);
	float asteroidSpeedMultiplier = 1;
	int asteroidMultiplierTimer = 0;
	//get random number
	float getRandomValue(float min, float max)
	{
		//create a new number
		Random r = new Random(/*SEED!!!*/);
		//get a float between 0 and 1 then multiply it by the min/max difference.
		float num = r.nextFloat();
		num = num * (max-min) + min;
		return num;
	}
	//float size = getRandomValue(32, 124);
	//PASSING SIZE FROM THIS CLASS OBSOLETE. SIZE IS IN ASTEROIDLEVEL


	private Vec2 Direction = new Vec2(1, 0);

	public Asteroid(int size)
	{
		super("Asteroid", size, size, "Circle.png");
		Math.round(size);
		setCircleCollider(size / 2);
		lives = 3;
		SoundManager.addBackgroundSound("Explode2", "asteroidExplode2.wav", false);
	}

	// Here you can update the position of the Asteroid
	@Override
	public void update(float dt)
	{

		setRotation(getRotation() + PFRandom.randomRange(-1,1));
		ScreenWrap.Wrapper(this);

		asteroidMultiplierTimer++;
		if (asteroidMultiplierTimer >= 3600)
		{
			asteroidSpeedMultiplier += 1;
			asteroidMultiplierTimer = 0;
		}


		setPositionY(
				getPositionY() - baseAsteroidSpeed * asteroidSpeedMultiplier);
		setPositionX(getPositionX() + PFRandom.randomRange(-1, 1));


	}

	public void collisionReaction(GameObject collidedWith)
	{
		if ("Bullet".equals(collidedWith.getName()))
		{
			lives = lives - (1 * dmgMultiplier);
			if (lives <= 0)
			{
				SoundManager.playBackgroundSound("Explode2");
				kill();
				Vec2 deathSpot = collidedWith.getPosition();
				int healthdropNum = 0;
				while(healthdropNum <= 4)
				{

					healthdropNum++;
					GameObject healthDrop = new HealthDrops();
					healthDrop.setPosition(deathSpot.getX() + PFRandom.randomRange(-5, 5), deathSpot.getY() + PFRandom.randomRange(-5, 5));
					ObjectManager.addGameObject(healthDrop);

				}
			}
		}
	}


}
