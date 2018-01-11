import edu.digipen.GameObject;
import edu.digipen.math.Vec2;

/**
 * Created by david.krismer on 7/2/2015.
 */
public class GeneralAimedBullet extends GameObject
{
	int lifetimeTimer = 0;
	public Vec2 Direction;
	public GeneralAimedBullet(Vec2 direction, Vec2 location)
	{
		super("GeneralAimedBullet", 10, 10, "GeneralEnemyBullet.png");
		setCircleCollider(5);

		Direction = new Vec2();
		Direction.setX(direction.getX());
		Direction.setY(direction.getY());
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




}
