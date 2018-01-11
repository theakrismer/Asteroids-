import edu.digipen.GameObject;
import edu.digipen.InputManager;
import edu.digipen.ObjectManager;
import edu.digipen.SoundManager;

import java.awt.event.KeyEvent;

/**
 * Created by david.krismer on 7/1/2015.
 */
public class BossFollower extends GameObject
{
	//variables and such
	public int bossHealth;
	public int bossMaxHealth = 800;
	GameObject bossHPbarObj;
	BossHPBar bossHPBarVariables;
	public boolean bossIsDead;


	//attack variables
	int plusFrames = 1;
	public int generalAttackTimer = 0;
	public int starBurstTimer = 0;
	public int followBulletTimer = 0;
	public int followBulletBackAndForthTimer = 0;
	public int targetTimer = 0;
	public int aimedBulletTimer = 0;
	int spawnControl = 0;
	int lazerTimer = 0;



	public BossFollower()
	{
		super("BossFollower", 1,1, "InvisObj.png");
		setRectangleCollider(400, 50);
		bossHealth = bossMaxHealth;
		bossHPbarObj = ObjectManager.getGameObjectByName("BossHPBar");
		bossHPBarVariables = (BossHPBar)ObjectManager.getGameObjectByName("BossHPBar");
		SoundManager.addBackgroundSound("FollowBulletSound", "EnemyShoot.wav", false);
	}

	@Override
	public void update (float dt)
	{
		if (InputManager.isTriggered(KeyEvent.VK_8))
		{
			//bossHealth = 0;
		}


		GameObject boss = ObjectManager.getGameObjectByName("Boss");
		if (boss != null)
		{
			setPosition(boss.getPositionX(), boss.getPositionY() + 90);
		}



		//ATTACK PATTERN
		generalAttackTimer += plusFrames;
		if (30 < generalAttackTimer && generalAttackTimer < 1500)
		{
			starBurstTimer++;
			if (starBurstTimer >= 200)
			{
				//spawn a starBurst
				GameObject starBurst = new starBurst();
				starBurst.setPosition(getPositionX() + 5, getPositionY() - 60);
				ObjectManager.addGameObject(starBurst);
				starBurstTimer = 0;
				while (spawnControl <= 3)
				{
					spawnControl++;
					GameObject aimedBullet = new AimedShot();
					aimedBullet.setPosition(getPositionX(), getPositionY() - 70);
					ObjectManager.addGameObject(aimedBullet);
				}
				spawnControl = 0;
			}
		}
		if (1700 <= generalAttackTimer && generalAttackTimer <= 3000)
		{
			spawnControl = 0;
			starBurstTimer = 0;
			followBulletTimer++;
			followBulletBackAndForthTimer++;
			lazerTimer++;
			if(lazerTimer >3)
			{
				lazerTimer = 0;
				GameObject lazer = new AimedShotLazer();
				lazer.setPosition(getPositionX() + 5, getPositionY() - 70);
				ObjectManager.addGameObject(lazer);

				GameObject lazer2 = new AimedShotLazer();
				lazer2.setPosition(getPositionX() + 250, getPositionY() - 50);
				ObjectManager.addGameObject(lazer2);

				GameObject lazer3 = new AimedShotLazer();
				lazer3.setPosition(getPositionX() -250, getPositionY() - 50);
				ObjectManager.addGameObject(lazer3);
			}


			if (followBulletTimer >= 90)
			{
				followBulletTimer = 0;
				if (followBulletBackAndForthTimer >= 0 && followBulletBackAndForthTimer <= 120)
				{
					GameObject followBullet = new FollowBullet();
					followBullet.setPosition(getPositionX() + 200, getPositionY() - 20);
					ObjectManager.addGameObject(followBullet);
					SoundManager.playBackgroundSound("FollowBulletSound");
				}
				else
				{
					GameObject followBullet = new FollowBullet();
					followBullet.setPosition(getPositionX() - 200, getPositionY() - 20);
					ObjectManager.addGameObject(followBullet);
					followBulletBackAndForthTimer = 0;
					SoundManager.playBackgroundSound("FollowBulletSound");
				}
			}
		}
		if (3300 <= generalAttackTimer && generalAttackTimer <= 3500)
		{
			while (targetTimer == 0)
			{
				targetTimer++;
				GameObject target = new Target();
				target.setPosition(getPositionX(), getPositionY() - 70);
				ObjectManager.addGameObject(target);
			}

		}
		if (3700 <= generalAttackTimer && generalAttackTimer <= 4700)
		{

			aimedBulletTimer++;
			if (aimedBulletTimer >= 3)
			{
				GameObject aimedBullet = new AimedShot();
				aimedBullet.setPosition(getPositionX(), getPositionY() - 70);
				ObjectManager.addGameObject(aimedBullet);
				aimedBulletTimer = 0;
			}
		}
		if (generalAttackTimer >= 4800 && generalAttackTimer <= 6000)
		{
			generalAttackTimer = 0;
			plusFrames = 1;
			generalAttackTimer = 0;
			starBurstTimer = 0;
			followBulletTimer = 0;
			followBulletBackAndForthTimer = 0;
			targetTimer = 0;
			aimedBulletTimer = 0;
			spawnControl = 0;
			lazerTimer = 0;
		}


	}

	public void collisionReaction(GameObject collidedWith)
	{
		if("Bullet".equals(collidedWith.getName()))
		{
			collidedWith.kill();

			bossHealth = bossHealth - 2;
			bossHPBarVariables.TakeDamage();
			if(bossHealth <= 0)
			{
				Ship ship = (Ship)ObjectManager.getGameObjectByName("Ship");
				ship.controlsLock = true;
				generalAttackTimer = 8000;
				plusFrames = 0;
				bossIsDead = true;
				SoundManager.stopAllPlayingSounds();

			}
			//System.out.println("BOSS HP: " + bossHealth);

			bossHPBarVariables.setPositionY( bossHPBarVariables.startPos.getY() -((bossHPBarVariables.pixelsHighBefore - bossHPBarVariables.pixelsHighAfter) / 2));
		}
	}
}
