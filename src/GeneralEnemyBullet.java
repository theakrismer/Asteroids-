import edu.digipen.GameObject;
import edu.digipen.ObjectManager;
import edu.digipen.math.Vec2;

/**
 * Created by david.krismer on 7/2/2015.
 */
public class GeneralEnemyBullet extends GameObject
{
	int lazerTimer = 0;
	public float speedY = 0;
	public float speedX = 0;
	public float generalSpeed = 6;
		Target target;
	Vec2 targetPos = new Vec2();
	int targetLifetime = 0;

	int doOnce = 0;

	public GeneralEnemyBullet()
	{
		super("GeneralEnemyBullet", 10, 10, "GeneralEnemyBullet.png");
		setCircleCollider(5);

	}

	public void initialize ()
	{

	}


	@Override
	public void update(float dt)
	{


		if (!isInViewport())
		{
			kill();
		}

		setPosition(getPositionX() + speedX, getPositionY() + speedY);


		target = (Target)ObjectManager.getGameObjectByName("Target");

		if (target != null)
		{

			if (target.targetSecured == true)
			{
				targetLifetime++;

				lazerTimer++;
				if (lazerTimer >= 5)
				{
					lazerTimer = 0;

					while (doOnce == 0 ) {

						doOnce++;
						targetPos.setX(target.getPositionX() - getPositionX());
						targetPos.setY(target.getPositionY() - getPositionY());
						targetPos.normalize();
					}


					setPositionX(getPositionX() + targetPos.getX() * generalSpeed);
					setPositionY(getPositionY() + targetPos.getY() * generalSpeed);
				}
				if (targetLifetime >= 300)
				{
					target.targetSecured = false;
					targetLifetime = 0;
				}

			}
		}
	}


	public void collisionReaction(GameObject collidedWith)
	{
		if ("Target".equals(collidedWith.getName()))
		{
			kill();
		}
	}
}
