import edu.digipen.GameObject;
import edu.digipen.ObjectManager;
import edu.digipen.math.Vec2;

/**
 * Created by david.krismer on 7/2/2015.
 */
public class AimedShot extends GameObject
{

    public float generalSpeed = 4;
    int doOnce = 0;
    Vec2 playerPos = new Vec2();

    public AimedShot() {
        super("AimedShot", 10, 10, "GeneralEnemyBullet.png");
        setCircleCollider(5);
    }

    @Override
    public void update(float dt)
    {
        if (!isInViewport())
        {
            kill();
        }



        Ship ship = (Ship) ObjectManager.getGameObjectByName("Ship");

        if (ship != null)
         {
            while (doOnce == 0 )
            {

                doOnce++;
                playerPos.setX(ship.getPositionX() - getPositionX());
                playerPos.setY(ship.getPositionY() - getPositionY());
                playerPos.normalize();
            }
             setPositionX(getPositionX() + playerPos.getX() * generalSpeed);
             setPositionY(getPositionY() + playerPos.getY() * generalSpeed);

         }
    }
}

