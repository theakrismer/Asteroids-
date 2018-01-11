import edu.digipen.GameObject;
import edu.digipen.InputManager;
import edu.digipen.math.Vec2;

import java.awt.event.KeyEvent;

/**
 * Created by Chad on 6/23/2015.
 */
public class Movement extends GameObject
{
	// Direction in which ship is facing (starts out facing down positive x axis)
	private Vec2 Acceleration = new Vec2(1, 0);
	private Vec2 Velocity = new Vec2(0, 0);
	private float Speed = 200.0f;



	/*public Movement(String name_, int width_, int height_, String textureName_)
	{
		super(name_, width_, height_, textureName_);
		Acceleration.scale(Speed);
	}*/

	public Movement(String name_, int width_, int height_, final String textureName_, int totalFrames, int numberOfRows, int numberOfCols, float frameLifetime)
	{
		super(name_, width_, height_, textureName_, totalFrames, numberOfRows, numberOfCols, frameLifetime);
		updateDir(0);

	}

	public Vec2 getAcceleration()
	{
		return Acceleration;
	}

	public Vec2 getVelocity()
	{
		return Velocity;
	}

	public float getSpeed()
	{
		return Speed;
	}

	public void setSpeed(float newSpeed)
	{
		Speed = newSpeed;
	}

	public void checkInput(float dt)
	{
		boolean moving = false;

		// Rotate Left
		if (InputManager.isPressed(KeyEvent.VK_A))
		{
			updateDir(3.0f);
		}

		// Rotate Right
		if (InputManager.isPressed(KeyEvent.VK_D))
		{
			updateDir(-3.0f);
		}

		boolean forward = InputManager.isPressed(KeyEvent.VK_W);
		boolean backward = InputManager.isPressed(KeyEvent.VK_S);

		// Are we supposed to move
		if (forward || backward)
		{
			// Go Forward
			if (forward)
			{
				Velocity.setX(Acceleration.getX() * dt + Velocity.getX());
				Velocity.setY(Acceleration.getY() * dt + Velocity.getY());

				Velocity.scale(0.99f);
			}

			// Go Backward
			if (backward)
			{
				Velocity.setX(-Acceleration.getX() * dt + Velocity.getX());
				Velocity.setY(-Acceleration.getY() * dt + Velocity.getY());

				Velocity.scale(0.99f);
			}
		}

		// Apply friction
		else
		{
			Velocity.setX(Velocity.getX() * 0.98f);
			Velocity.setY(Velocity.getY() * 0.98f);
		}

		setPositionX(getPositionX() + Velocity.getX() * dt);
		setPositionY(getPositionY() + Velocity.getY() * dt);
	}

	public void updateDir(float angleToChangBy)
	{
		// We want to get Rotation so we can update it
		float rot = getRotation();
		rot += angleToChangBy;
		setRotation(rot);

		// Update the direction of ship
		// Need to change to Radians for cos and sin
		rot = (float)Math.toRadians(rot);

		// Getting the unit length vector using angle (a vector of length one in the dir in which ship is facing)
		float x = (float)Math.cos(rot);
		float y = (float)Math.sin(rot);

		Acceleration.set(x, y);
		Acceleration.scale(Speed);
	}
}
