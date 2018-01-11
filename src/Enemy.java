import edu.digipen.GameObject;
import edu.digipen.ObjectManager;
import edu.digipen.SoundManager;
import edu.digipen.math.PFRandom;
import edu.digipen.math.Vec2;

/**
 * Created by david.krismer on 6/25/2015.
 */
public class Enemy extends GameObject
{
	int lives = 3;
	public Enemy()
	{
		super("Enemy", 32, 32, "Particle.png");
		setCircleCollider(16);
		SoundManager.addBackgroundSound("Explode2", "EnemyDeath.wav", false);
		SoundManager.addBackgroundSound("EnemyShoot", "EnemyShoot.wav", false);
	}


	int shootingTimer;
	public void update(float dt)
	{
		ScreenWrap.Wrapper(this);

		if(lives <= 0)
		{
			SoundManager.playBackgroundSound("Explode2");
			Vec2 deathSpot = getPosition();
			kill();
			int healthdropNum = 0;
			while(healthdropNum <= 10)
			{
				healthdropNum++;
				GameObject enemyHealthDrop = new EnemyHealthDrops();
				enemyHealthDrop.setPosition(deathSpot.getX() + PFRandom
						.randomRange(-5, 5), deathSpot.getY() + PFRandom.randomRange(-5, 5));
				ObjectManager.addGameObject(enemyHealthDrop);

			}
		}
		shootingTimer++;
		if (shootingTimer >= 60)
		{
			SoundManager.playBackgroundSound("EnemyShoot");
			shootingTimer = 0;
			GameObject homingBullet = new FollowBullet();
			homingBullet.setPosition(getPosition());
			ObjectManager.addGameObject(homingBullet);
		}
		setPositionX(getPositionX() - 1);
	}

	public void collisionReaction (GameObject collidedWith)
	{
		if ("Bullet".equals(collidedWith.getName()))
		{
			lives -= 1;
			collidedWith.kill();



		}
	}
}
