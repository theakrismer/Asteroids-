import edu.digipen.GameObject;
import edu.digipen.ObjectManager;

/**
 * Created by david.krismer on 6/24/2015.
 */
public class HealthBar extends GameObject
{
	public float newWidth;
	int maxWidth = 0;
	public HealthBar(String name_, int width_, int height_, String textureName_)
	{
		super(name_, width_, height_, textureName_);
		maxWidth = width_;
		setZOrder(99999);
	}

	@Override
	public void update(float dt)
	{
		Ship player = (Ship)ObjectManager.getGameObjectByName("Ship");

		if (player != null)
		{
			newWidth = ((float) player.ShipHealth / player.MaxHealth);
			setScaleX(newWidth);
			//System.out.println("new width: " + newWidth);
		}
		if (player == null)
		{
			kill();
		}
	}
}
