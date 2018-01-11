import edu.digipen.GameObject;
import edu.digipen.math.PFRandom;

/**
 * Created by david.krismer on 6/30/2015.
 */
public class ShipPiece2 extends GameObject
{
	float randomRot;
	float randomXDir;
	float randomYDir;
	public ShipPiece2()
	{
		super("Peice2", 50, 50, "ShipPiece2.png");
		randomRot = PFRandom.randomRange(-0.3f, 0.3f);
		randomXDir = PFRandom.randomRange(-0.3f,0.3f);
		randomYDir = PFRandom.randomRange(-0.3f,0.3f);
	}


	@Override public void update(float dt)
	{
		ScreenWrap.Wrapper(this);
		ScreenWrap.YWrapper(this);
		setRotation(getRotation() + randomRot);
		setPosition(getPositionX() + randomXDir, getPositionY() + randomYDir);
	}
}
