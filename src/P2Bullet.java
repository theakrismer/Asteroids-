import edu.digipen.GameObject;

/**
 * Created by david.krismer on 7/3/2015.
 */
public class P2Bullet extends GameObject
{
	public P2Bullet()
	{
		super("Bullet", 32 , 32, "Ball.png");
		setCircleCollider(2);
		setScale(0.2f, 0.2f);
	}

	@Override public void update(float dt)
	{
		setPosition(getPositionX(), getPositionY() + 4);
	}

	public void collisionReaction(GameObject collidedWith)
	{
		if ("Asteroid".equals(collidedWith.getName()))
		{
			kill();
		}
	}
}
