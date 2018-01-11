import edu.digipen.GameObject;

/**
 * Created by david.krismer on 6/24/2015.
 */
public class Bullet2 extends GameObject
{

	public Bullet2()
	{
		super("Bullet", 16 , 16, "Ball.png");
		setCircleCollider(8);
	}

	@Override
	public void update (float dt)
	{
		setPositionX(getPositionX() + 10);
		setPositionY(getPositionY() + 10);
	}

	public void collisionReaction(GameObject collidedWith)
	{
		if ("Asteroid".equals(collidedWith))
		{
			kill();
		}
	}
}
