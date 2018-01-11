import edu.digipen.GameObject;

/**
 * Created by david.krismer on 7/3/2015.
 */
public class AimedShotLazer extends GameObject
{
	int lifetime = 0;
	public AimedShotLazer()
	{
		super("AimedShot", 10, 10, "AimedLazer.png");
		setCircleCollider(5);
	}

	@Override
	public void update(float dt)
	{
		setPosition(getPositionX(), getPositionY() - 5);

		if (lifetime >= 180)
			kill();

		if (!isInViewport())
		{
			kill();
		}
	}
}
