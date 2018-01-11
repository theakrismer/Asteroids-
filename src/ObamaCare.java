import edu.digipen.GameLevel;
import edu.digipen.GameObject;
import edu.digipen.math.PFRandom;

/**
 * Created by david.krismer on 7/1/2015.
 */
public class ObamaCare extends GameLevel
{
	GameObject obama1;
	GameObject obama2;
	GameObject obama3;
	GameObject obama4;
	GameObject obama5;
	@Override public void create()
	{
		obama1 = new GameObject("obama1", 750, 600, "obama1.png");
		obama2 = new GameObject("obama2", 270, 270, "obama2.png");
		obama3 = new GameObject("obama3", 252, 200, "obama3.png");
		obama4 = new GameObject("obama4", 500, 332, "obama4.png");
		obama5 = new GameObject("obama5", 320, 256, "obama5.png");

		obama1.setPosition(PFRandom.randomRange(-250, 250),PFRandom.randomRange(-250,250));
		obama2.setPosition(PFRandom.randomRange(-250, 250),PFRandom.randomRange(-250,250));
		obama3.setPosition(PFRandom.randomRange(-250, 250),PFRandom.randomRange(-250,250));
		obama4.setPosition(PFRandom.randomRange(-250, 250),PFRandom.randomRange(-250,250));
		obama5.setPosition(PFRandom.randomRange(-250, 250),PFRandom.randomRange(-250,250));

		obama1.setScale(0.5f, 0.5f);
		obama2.setScale(0.5f, 0.5f);
		obama3.setScale(0.5f, 0.5f);
		obama4.setScale(0.5f, 0.5f);
		obama5.setScale(0.5f, 0.5f);
	}

	@Override public void initialize()
	{

	}

	@Override public void update(float v)
	{
		obama1.setPosition(obama1.getPositionX() + PFRandom.randomRange(-0.3f, 0.3f),obama1.getPositionY() + PFRandom.randomRange(-0.3f, 0.3f));
		obama2.setPosition(obama2.getPositionX() + PFRandom.randomRange(-0.3f, 0.3f),obama2.getPositionY() + PFRandom.randomRange(-0.3f, 0.3f));
		obama3.setPosition(obama3.getPositionX() + PFRandom.randomRange(-0.3f, 0.3f),obama3.getPositionY() + PFRandom.randomRange(-0.3f, 0.3f));
		obama4.setPosition(obama4.getPositionX() + PFRandom.randomRange(-0.3f, 0.3f),obama4.getPositionY() + PFRandom.randomRange(-0.3f, 0.3f));
		obama5.setPosition(obama5.getPositionX() + PFRandom.randomRange(-0.3f, 0.3f),obama5.getPositionY() + PFRandom.randomRange(-0.3f, 0.3f));

		obama1.setRotation(obama1.getRotation() + PFRandom.randomRange(-1f, 1f));
		obama2.setRotation(obama1.getRotation() + PFRandom.randomRange(-1f, 1f));
		obama3.setRotation(obama1.getRotation() + PFRandom.randomRange(-1f, 1f));
		obama4.setRotation(obama1.getRotation() + PFRandom.randomRange(-1f, 1f));
		obama5.setRotation(obama1.getRotation() + PFRandom.randomRange(-1f, 1f));

		ScreenWrap.YWrapper(obama1);
		ScreenWrap.Wrapper(obama1);
		ScreenWrap.YWrapper(obama2);
		ScreenWrap.Wrapper(obama2);
		ScreenWrap.YWrapper(obama3);
		ScreenWrap.Wrapper(obama3);
		ScreenWrap.YWrapper(obama4);
		ScreenWrap.Wrapper(obama4);
		ScreenWrap.YWrapper(obama5);
		ScreenWrap.Wrapper(obama5);
	}

	@Override public void uninitialize()
	{

	}
}
