import edu.digipen.GameObject;
import edu.digipen.ObjectManager;
import edu.digipen.math.Vec2;

/**
 * Created by david.krismer on 7/2/2015.
 */
public class Target extends GameObject
{
	public boolean targetSecured = false;
	float targetSpeed = 4f;
	int timerToFire = 0;
	int firingLimitTimer = 0;
	int lifetime = 0;

	public Target()
	{
		super("Target", 32, 32, "target.png");
		setCircleCollider(1);
	}

	@Override
	public void update (float dt)
	{
		if (lifetime >= 300)
		{
			kill();
		}


		if(targetSecured == true)
		{
			lifetime++;
			GameObject boss = ObjectManager.getGameObjectByName("BossFollower");
			timerToFire++;
			if (timerToFire >= 60)
			{
				firingLimitTimer++;
				if (firingLimitTimer >= 20) {
					GameObject bullet = new GeneralEnemyBullet();
					bullet.setPosition(boss.getPositionX() + 5, boss.getPositionY() - 200);
					ObjectManager.addGameObject(bullet);
					firingLimitTimer = 0;
				}
			}

		}


		ScreenWrap.Wrapper(this);
		GameObject player = ObjectManager.getGameObjectByName("Ship");
		Vec2 playerPos = new Vec2();
		if (player != null)
		{

				playerPos.setX(player.getPositionX() - getPositionX());
				playerPos.setY(player.getPositionY() - getPositionY());
				playerPos.normalize();


			setPositionX(getPositionX() + playerPos.getX() * targetSpeed);
			setPositionY(getPositionY() + playerPos.getY() * targetSpeed);
		}
		else
		{
			setPosition(getPositionX(), getPositionY());
		}

	}

	public void collisionReaction (GameObject collidedWith)
	{
		if ("Ship".equals(collidedWith.getName()))
		{
			targetSpeed = 0;
			targetSecured = true;
			setModulationColor(0f,1f,0f,1);
		}
	}
}
