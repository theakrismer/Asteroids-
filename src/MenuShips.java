import edu.digipen.GameObject;
import edu.digipen.math.PFRandom;

/**
 * Created by david.krismer on 6/26/2015.
 */
public class MenuShips extends GameObject
{
	public MenuShips()
	{
		super("MenuShip", 50, 50, "Ship.png", 3, 1, 3, 0.5f);
	}


	@Override
	public void update(float dt)
	{
		setPositionX(getPositionX() + PFRandom.randomRange(8.0f, 18.0f));

		if(getPositionX() >= 10000)
		{
			setPosition(PFRandom.randomRange(-1050, -1010), PFRandom.randomRange(-40, -10));
		}
	}
}
