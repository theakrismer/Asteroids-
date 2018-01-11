import edu.digipen.GameObject;
import edu.digipen.ObjectManager;
import edu.digipen.math.Vec2;

/**
 * Created by david.krismer on 6/25/2015.
 */
public class FollowBullet extends GameObject
{
	public FollowBullet()
	{
		super("FollowBullet", 32, 32, "Ball.png");
		setCircleCollider(16);
		setZOrder(50);
	}

	float bulletSpeed = 3f;

	@Override
	public void update (float dt)
	{
		ScreenWrap.Wrapper(this);
		GameObject player = ObjectManager.getGameObjectByName("Ship");
		Vec2 playerPos = new Vec2();
		if (player != null)
		{
			playerPos.setX(player.getPositionX() - getPositionX());
			playerPos.setY(player.getPositionY() - getPositionY());
			playerPos.normalize();

			setPositionX(getPositionX() + playerPos.getX() * bulletSpeed);
			setPositionY(getPositionY() + playerPos.getY() * bulletSpeed);
		}
		else
		{
			setPosition(getPositionX(), getPositionY() - 2);
		}








	}

	public void collisionReaction(GameObject collidedWith)
	{
		if ("Bullet".equals(collidedWith.getName()))
		{
			collidedWith.kill();
			kill();
		}
	}
}
