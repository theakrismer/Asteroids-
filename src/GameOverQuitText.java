import edu.digipen.GameObject;
import edu.digipen.math.PFRandom;

/**
 * Created by david.krismer on 6/30/2015.
 */
public class GameOverQuitText extends GameObject
{
	float randomRot;
	float randomXDir;
	float randomYDir;
	public GameOverQuitText()
	{
		super("GameOverQuitText", 400, 100, "QuitText.png");
		randomRot = PFRandom.randomRange(-0.1f, 0.1f);
		randomXDir = PFRandom.randomRange(-0.2f, 0.2f);
		randomYDir = PFRandom.randomRange(-0.2f, 0.2f);
	}
	@Override
	public void update(float dt)
	{
		ScreenWrap.Wrapper(this);
		ScreenWrap.YWrapper(this);
		setRotation(getRotation() + randomRot);
		setPosition(getPositionX() + randomXDir, getPositionY() + randomYDir);
	}
}
