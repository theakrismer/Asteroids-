import edu.digipen.Game;
import edu.digipen.GameObject;

/**
 * Created by david.krismer on 6/24/2015.
 */
public class ScreenWrap extends Game
{
	public static void Wrapper(GameObject obj)
	{
		float LRBorder = getWindowWidth() / 2;
		float xPos = obj.getPositionX();
		if (xPos > LRBorder)
		{
			obj.setPositionX(-LRBorder);
		}
		if (xPos < -LRBorder)
		{
			obj.setPositionX(LRBorder);
		}
	}

	public static void YWrapper(GameObject obj)
	{
		float TBBorder = getWindowHeight() /2;
		float yPos = obj.getPositionY();
		if (yPos < -TBBorder)
		{
			obj.setPositionY(TBBorder);
		}
		if (yPos > TBBorder)
		{
			obj.setPositionY(-TBBorder);
		}
	}
}
