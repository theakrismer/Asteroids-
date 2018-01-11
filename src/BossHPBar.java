import edu.digipen.GameObject;
import edu.digipen.ObjectManager;
import edu.digipen.math.Vec2;

/**
 * Created by david.krismer on 7/1/2015.
 */
public class BossHPBar extends GameObject
{
	public float newWidth;
	public float  maxWidth = 439;
	public float pixelsHighAfter;
	public float pixelsHighBefore;
	public Vec2 startPos;

	public BossHPBar()
	{
		super("BossHPBar", 63, 439, "RectVertical.png");
		//System.out.println("Boss Constructor");
		setPosition(358, -18);
		startPos = getPosition();
		pixelsHighAfter = maxWidth;
		pixelsHighBefore = maxWidth;
	}

	@Override
	public void update (float dt)
	{
		//System.out.println("Before: " + pixelsHighBefore);
		//System.out.println("After: " + pixelsHighAfter);

	}

	public void TakeDamage()
	{
		BossFollower boss = (BossFollower)ObjectManager.getGameObjectByName("BossFollower");
		newWidth = ((float) boss.bossHealth / boss.bossMaxHealth);
		setScaleY(newWidth);
		pixelsHighBefore = pixelsHighAfter;
		pixelsHighAfter = maxWidth * newWidth;
		if (boss.bossHealth <= 0)
		{
			kill();
		}

	}
}
