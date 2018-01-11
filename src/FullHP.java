import edu.digipen.GameObject;

/**
 * Created by david.krismer on 6/24/2015.
 */
public class FullHP extends GameObject
{
	public FullHP()
	{
		super("HealthPickup", 32, 32, "CircleAnimation.png", 8, 1, 8, 0.2f);
		setCircleCollider(16);
		animationData.play();
	}
	int lifetimeTimer = 0;

	@Override
	public void initialize ()
	{

	}
	@Override
	public void update(float dt)
	{
		lifetimeTimer++;

		if (lifetimeTimer >= 1000)
		{

		}
	}
}
