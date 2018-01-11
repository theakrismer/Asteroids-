import edu.digipen.Game;
import edu.digipen.GameObject;
import edu.digipen.InputManager;
import edu.digipen.ObjectManager;
import edu.digipen.math.Vec2;

import java.awt.event.KeyEvent;

/**
 * Created by david.krismer on 6/24/2015.
 */
public class SpecialShoot extends Game
{


	public static void SecondShoot()
	{
		if (InputManager.isTriggered(KeyEvent.VK_SHIFT))
		{
			GameObject player = ObjectManager.getGameObjectByName("Ship");
			Vec2 playerPos = player.getPosition();


			GameObject bullet = new Bullet2();
			bullet.setPosition(playerPos);
			ObjectManager.addGameObject(bullet);


			GameObject bullet2 = new Bullet3();
			bullet2.setPosition(playerPos);
			ObjectManager.addGameObject(bullet2);


			GameObject bullet3 = new Bullet4();
			bullet3.setPosition(playerPos);
			ObjectManager.addGameObject(bullet3);

		}
	}
}
