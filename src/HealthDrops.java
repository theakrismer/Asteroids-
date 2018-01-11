import edu.digipen.GameObject;
import edu.digipen.math.PFRandom;

/**
 * Created by david.krismer on 6/24/2015.
 */
public class HealthDrops extends GameObject
{
	int existanceTimer = 0;
	float randomXDirection = PFRandom.randomRange(-0.3f, 0.3f);
	float randomYDirection = PFRandom.randomRange(-0.3f, 0.3f);
	public HealthDrops()
	{
		super("HealthDrop", 15, 15, "Point.png");
		setCircleCollider(5);
	}

	public void update (float dt)
	{
		ScreenWrap.Wrapper(this);
		existanceTimer++;
		if (existanceTimer >= 1600)
		{
			kill();
		}

		setPositionX(getPositionX() + randomXDirection);
		setPositionY(getPositionY() + randomYDirection);

		if(isInViewport() == false)
		{
			kill();
		}
	}
}
