import edu.digipen.GameObject;
import edu.digipen.ObjectManager;
import edu.digipen.math.PFRandom;

/**
 * Created by david.krismer on 6/24/2015.
 */
public class Light extends GameObject
{
	public float speedMultiplier;
	public Light()
	{
		super("Light", 5, 80, "LightInBackground.png");
		SpeedDown =  PFRandom.randomRange(5,30);
		//SpeedSideways = PFRandom.randomRange(-0.2,0.2);
		speedMultiplier = 1;
		setZOrder(-20);
	}
	float SpeedDown = 0f;
	//float SpeedSideways = 0f;

	@Override
	public void update (float dt)
	{
		Ship player = (Ship) ObjectManager.getGameObjectByName("Ship");
		if (player != null)
		{
			if (player.hyperdriveActive == true)
			{
				speedMultiplier += 0.19;
			}
		}
			//System.out.println(SpeedDown + ", " + SpeedSideways);
			setPositionY(getPositionY() - (SpeedDown * speedMultiplier));
			//setPositionX(getPositionX() + SpeedSideways);
			ScreenWrap.Wrapper(this);
			//ScreenWrap.YWrapper(this);
			//alternative for Ywrapper
		if (getPositionY() <= -370)
		{
			setPosition(PFRandom.randomRange(-350f, 350f), 370);
			SpeedDown =  PFRandom.randomRange(5,30);
			setZOrder(PFRandom.randomRange(-21, 21));
		}

	}

}
