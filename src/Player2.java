import edu.digipen.GameObject;
import edu.digipen.InputManager;
import edu.digipen.ObjectManager;

import java.awt.event.KeyEvent;

/**
 * Created by david.krismer on 7/3/2015.
 */
public class Player2 extends GameObject
{
	public Player2()
	{
		super("Ship2", 50, 50, "Ship.png", 3, 1, 3, 0.5f);
		setRotation(90);
	}


	@Override
	public void update(float dt)
	{
		if(InputManager.isPressed(KeyEvent.VK_UP))
		{
			setPositionY(getPositionY() + 3);
		}
		if(InputManager.isPressed(KeyEvent.VK_DOWN))
		{
			setPositionY(getPositionY() - 3);
		}
		if(InputManager.isPressed(KeyEvent.VK_LEFT))
		{
			setPositionX(getPositionX() - 3);
		}
		if(InputManager.isPressed(KeyEvent.VK_RIGHT))
		{
			setPositionX(getPositionX() + 3);
		}

		if(InputManager.isTriggered(KeyEvent.VK_NUMPAD0))
		{
			GameObject bullet = new P2Bullet();
			bullet.setPosition(getPosition());
			ObjectManager.addGameObject(bullet);
		}

	}
}
