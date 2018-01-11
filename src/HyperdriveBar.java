import edu.digipen.GameObject;
import edu.digipen.ObjectManager;

/**
 * Created by david.krismer on 6/26/2015.
 */
public class HyperdriveBar extends GameObject
{
	int maxWidth = 0;
	public float newWidth;
	public HyperdriveBar(String name_, int width_, int height_, String textureName_)
	{
		super(name_, width_, height_, textureName_);
		maxWidth = width_;
		setZOrder(99999);

	}


	@Override
	public void  update(float dt)
	{
		Ship player = (Ship)ObjectManager.getGameObjectByName("Ship");
		if (player != null)
		{

			newWidth = ((float) player.score / player.warpScore);
			if (newWidth > 1)
			{
				newWidth = 1;
			}
			setScaleX(newWidth);
		}
		//System.out.println("new width: " + newWidth);


	}
}
