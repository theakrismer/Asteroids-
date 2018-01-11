import edu.digipen.GameObject;
import edu.digipen.math.Vec2;

/**
 * Created by david.krismer on 6/24/2015.
 */
public class Bullet extends GameObject
{

	int lifetimeTimer = 0;
	public Vec2 Direction;

	public Bullet(Vec2 direction, Vec2 location)
	{
		super("Bullet", 32 , 32, "Ball.png");
		setCircleCollider(2);

		Direction = new Vec2();
		Direction.setX(direction.getX());
		Direction.setY(direction.getY());
		setScale(0.2f, 0.2f);
		setPosition(location.getX() + Direction.getX() * 10, location.getY() + Direction.getY() * 10);
	}






	@Override
	public void update (float dt)
	{

		lifetimeTimer++;
		if (lifetimeTimer >= 800)
		{
			kill();
		}

		//ScreenWrap.Wrapper(this);
		setPositionX(getPositionX() + Direction.getX() * 5);
		setPositionY(getPositionY() + Direction.getY() * 5);

		if (isInViewport() == false)
		{
			kill();
		}
	}
	public void collisionReaction(GameObject collidedWith)
	{
		if ("Asteroid".equals(collidedWith.getName()))
		{
			kill();
		}

	}
}
