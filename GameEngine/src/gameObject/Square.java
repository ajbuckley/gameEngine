/**
 * 
 */
package gameObject;

import processing.core.PApplet;

import java.util.Vector;

/**
 * @author Drew
 *
 */
public class Square implements GravityAffected {
	private final static float TERMINAL_VELOCITY = 10;
	private boolean visible;
	private int x;
	private int y;
	private int width;
	private int[] fill = { 100, 0, 100, 255 };
	private int[] stroke = { 0, 50, 100, 255 };
	private float gravity;
	private float[] velocity = null;
	private boolean inAir;
	private GameObject ground;

	/**
	 * (non-Javadoc)
	 * 
	 * @see gameObject.Locatable#setLoc(java.util.Vector)
	 */
	public Square(int x, int y, int width) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.visible = true;
		this.inAir = true;

	}

	/**
	 * @see gameObject.GameObject#isVisible()
	 **/
	@Override
	public void setVisible(boolean visible) {

		this.visible = visible;
	}

	/**
	 * @see gameObject.GameObject#isVisible()
	 */
	public boolean isVisible() {
		return visible;

	}

	/**
	 * @see gameObject.GravityAffected#fall()
	 */
	public void fall() {

		velocity[1] += gravity / 60;
	}

	/**
	 * @see gameObject.GravityAffected#inAir()
	 */

	public boolean inAir() {
		if (!inAir && ground != null) {
			switch (ground.drawType()) {
			case RECT_DRAW_VAL: {
				Rectangle r = (Rectangle) (ground);
				if (this.x + this.getWidth() < r.getX()
						|| this.x > r.getX() + r.getWidth()) {
					inAir = true;
				}
				break;
			}
			case SQUARE_DRAW_VAL: {
				Square s = (Square) (ground);
				if (this.x + this.getWidth() < s.getX()
						|| this.x > s.getX() + s.getWidth()) {
					inAir = true;
				}
				break;
			}
			default:
				;
			}

		}
		return inAir;
	}

	/**
	 * @see gameObject.GameObject#drawType()
	 */
	public int drawType() {
		return SQUARE_DRAW_VAL;
	}

	/**
	 * @see gameObject.GameObject#setLoc()
	 */
	public void setLoc(int x, int y, int z) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @see gameObject.GameObject#getX()
	 */
	public int getX() {
		return x;
	}

	/**
	 * @see gameObject.GameObject#getY()
	 */
	public int getY() {

		return y;
	}

	/**
	 * @see gameObject.GameObject#getZ()
	 */
	public int getZ() {
		return 0;
	}

	/**
	 * @see gameObject.GameObject#getLoc()
	 */
	public int[] getLoc() {
		int loc[] = new int[2];
		loc[0] = x;
		loc[1] = y;
		return loc;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return width;
	}

	/**
	 * @see gameObject.GameObject#update()
	 */
	public void update() {

		if (velocity != null) {
			if (this.inAir()) {
				this.fall();
			}
			if (velocity[0] > TERMINAL_VELOCITY) {
				velocity[0] = TERMINAL_VELOCITY;
			}
			if (velocity[0] < -TERMINAL_VELOCITY) {
				velocity[0] = -TERMINAL_VELOCITY;
			}
			this.x += velocity[0];
			if (velocity[1] > TERMINAL_VELOCITY) {
				velocity[1] = TERMINAL_VELOCITY;
			}
			if (velocity[1] < -TERMINAL_VELOCITY) {
				velocity[1] = -TERMINAL_VELOCITY;
			}
			this.y += velocity[1];
		}
	}

	@Override
	public void setGravity(float grav) {
		this.gravity = grav;

	}

	@Override
	public float getGravity() {
		return gravity;
	}

	@Override
	public void setColor(int[] stroke, int[] fill) {
		this.stroke = stroke;
		this.fill = fill;

	}

	@Override
	public int[] getStroke() {

		return stroke;
	}

	@Override
	public int[] getFill() {

		return fill;
	}

	@Override
	public void setVelocity(float[] vel) {
		this.velocity = vel;

	}

	@Override
	public float[] getVelocity() {
		return velocity;
	}

	public void handleCollisions(GameObject o) {

		switch (o.drawType()) {
		case RECT_DRAW_VAL:
			handleCollisions((Rectangle) o);
			break;
		case SQUARE_DRAW_VAL:
			handleCollisions((Square) o);
			break;
		default:
			;
		}

	}

	private void handleCollisions(Square s) {
		if (this.velocity != null || s.getVelocity() != null) {
			if (this.x < s.getX() + s.getWidth()
					&& this.x + this.getWidth() > s.getX()) {
				/**
				 * Test collision from the bottom
				 */
				if ((this.getY() < s.getY() + s.getHeight() && this.getY() > s
						.getY())) {
					if (this.velocity != null) {
						if (s.getVelocity() == null) {
							this.setLoc(x, s.getY() + s.getHeight(), 0);

							if ((this.velocity[1] * this.velocity[1] > 5)) {
								float v[] = { velocity[0],
										(float) (velocity[1] * -.3),
										velocity[2] };
								this.setVelocity(v);
							}
						} else {
							this.setLoc(x, s.getY() + s.getHeight(), 0);

							if ((this.velocity[1] * this.velocity[1] > 5)) {
								float v[] = { velocity[0],
										(float) (velocity[1] * -.3),
										velocity[2] };
								this.setVelocity(v);
							}
							if ((s.getVelocity()[1] * s.getVelocity()[1] > 5)) {
								float v[] = { s.getVelocity()[0],
										(float) (s.getVelocity()[1] * -.3),
										s.getVelocity()[2] };
								s.setVelocity(v);
							}

						}
					} else {

						s.setLoc(s.getX(), this.getY() - s.getHeight(), 0);
						if ((s.getVelocity()[1] * s.getVelocity()[1] > 5)) {
							float v[] = { s.getVelocity()[0],
									(float) (s.getVelocity()[1] * -.3),
									s.getVelocity()[2] };
							s.setVelocity(v);
						} else {
							s.setAir(false);
							s.setGround(this);
							float v[] = { s.getVelocity()[0], 0,
									s.getVelocity()[2] };
						}

					}

				}
				/**
				 * Test collision from the top
				 */
				if (this.getY() + this.getHeight() > s.getY()
						&& this.getY() + this.getHeight() < s.getY()
								+ s.getHeight()) {
					if (this.velocity != null) {
						if (s.getVelocity() == null) {
							inAir = false;
							ground = s;
							this.setLoc(x, s.getY() - this.getHeight(), 0);
							if ((this.velocity[1] * this.velocity[1] > 5)) {
								float v[] = { velocity[0],
										(float) (velocity[1] * -.3),
										velocity[2] };
								this.setVelocity(v);
							} else {
								inAir = false;
								ground = s;
								float v[] = { velocity[0], 0, velocity[2] };
								this.setVelocity(v);
							}
						} else {
							this.setLoc(x, s.getY() - this.getHeight(), 0);

							if ((this.velocity[1] * this.velocity[1] > 5)) {
								float v[] = { velocity[0],
										(float) (velocity[1] * -.3),
										velocity[2] };
								this.setVelocity(v);
							}
							if ((s.getVelocity()[1] * s.getVelocity()[1] > 5)) {
								float v[] = { s.getVelocity()[0],
										(float) (s.getVelocity()[1] * -.3),
										s.getVelocity()[2] };
								s.setVelocity(v);
							}

						}
					} else {
						s.setLoc(s.getX(), this.getY() + this.getHeight(), 0);
						if ((s.getVelocity()[1] * s.getVelocity()[1] > 5)) {
							float v[] = { s.getVelocity()[0],
									(float) (s.getVelocity()[1] * -.3),
									s.getVelocity()[2] };
							s.setVelocity(v);
						}

					}
				}
			}

			if (this.y < s.getY() + s.getHeight()
					&& this.y + this.getHeight() > s.getY()) {
				/**
				 * Test collision from the right
				 */
				if ((this.getX() < s.getX() + s.getWidth() && this.getX() > s
						.getX())) {
					if (this.velocity != null) {
						if (s.getVelocity() == null) {
							this.setLoc(s.getX() + s.getWidth(), y, 0);
							if ((this.velocity[0] * this.velocity[0] < .5)) {
								float v[] = { (float) (velocity[0] * -.3),
										velocity[1], velocity[2] };
								this.setVelocity(v);
							}
						} else {
							this.setLoc(s.getX() + s.getWidth(), y, 0);
							if ((this.velocity[0] * this.velocity[0] < .5)) {
								float v[] = { (float) (velocity[0] * -.3),
										velocity[1], velocity[2] };
								this.setVelocity(v);
							}
							if ((s.getVelocity()[1] * s.getVelocity()[1] > 5)) {
								float v[] = {
										(float) (s.getVelocity()[0] * -.3),
										s.getVelocity()[1], s.getVelocity()[2] };
								s.setVelocity(v);
							}

						}
					} else {
						s.setLoc(this.getX() - s.getWidth(), s.getY(), 0);
						if ((s.getVelocity()[0] * s.getVelocity()[0] < .5)) {
							float v[] = { (float) (s.getVelocity()[0] * -.3),
									s.getVelocity()[1], s.getVelocity()[2] };
							s.setVelocity(v);
						}
					}

				}

				/**
				 * Test collision from the left
				 */
				if ((this.getX() + this.getWidth() > s.getX() && this.getX()
						+ this.getWidth() < s.getX() + s.getWidth())) {
					if (this.velocity != null) {
						if (s.getVelocity() == null) {
							this.setLoc(s.getX() - this.getWidth(), y, 0);
							if ((this.velocity[0] * this.velocity[0] < .5)) {
								float v[] = { (float) (velocity[0] * -.3),
										velocity[1], velocity[2] };
								this.setVelocity(v);
							}
						} else {
							this.setLoc(s.getX() - this.getWidth(), y, 0);
							if ((this.velocity[0] * this.velocity[0] < .5)) {
								float v[] = { (float) (velocity[0] * -.3),
										velocity[1], velocity[2] };
								this.setVelocity(v);
							}
							if ((s.getVelocity()[0] * s.getVelocity()[0] < .5)) {
								float v[] = {
										(float) (s.getVelocity()[0] * -.3),
										s.getVelocity()[1], s.getVelocity()[2] };
								s.setVelocity(v);
							}

						}
					} else {
						s.setLoc(this.getX() + this.getWidth(), s.getY(), 0);
						if ((s.getVelocity()[0] * s.getVelocity()[0] < .5)) {
							float v[] = { (float) (s.getVelocity()[0] * -.3),
									s.getVelocity()[1], s.getVelocity()[2] };
							s.setVelocity(v);
						}
					}
				}
			}
		}
	}

	private void handleCollisions(Rectangle s) {
		if (this.velocity != null || s.getVelocity() != null) {
			if (this.x < s.getX() + s.getWidth()
					&& this.x + this.getWidth() > s.getX()) {
				/**
				 * Test collision from the bottom
				 */
				if ((this.getY() < s.getY() + s.getHeight() && this.getY() > s
						.getY())) {
					if (this.velocity != null) {
						if (s.getVelocity() == null) {
							this.setLoc(x, s.getY() + s.getHeight(), 0);

							if ((this.velocity[1] * this.velocity[1] > 5)) {
								float v[] = { velocity[0],
										(float) (velocity[1] * -.3),
										velocity[2] };
								this.setVelocity(v);
							}
						} else {
							this.setLoc(x, s.getY() + s.getHeight(), 0);

							if ((this.velocity[1] * this.velocity[1] > 5)) {
								float v[] = { velocity[0],
										(float) (velocity[1] * -.3),
										velocity[2] };
								this.setVelocity(v);
							}
							if ((s.getVelocity()[1] * s.getVelocity()[1] > 5)) {
								float v[] = { s.getVelocity()[0],
										(float) (s.getVelocity()[1] * -.3),
										s.getVelocity()[2] };
								s.setVelocity(v);
							}

						}
					} else {
						s.setAir(false);
						s.setGround(this);
						s.setLoc(s.getX(), this.getY() - s.getHeight(), 0);
						if ((s.getVelocity()[1] * s.getVelocity()[1] > 5)) {
							float v[] = { s.getVelocity()[0],
									(float) (s.getVelocity()[1] * -.3),
									s.getVelocity()[2] };
							s.setVelocity(v);
						} else {
							s.setAir(false);
							s.setGround(this);
							float v[] = { s.getVelocity()[0], 0,
									s.getVelocity()[2] };
						}

					}

				}
				/**
				 * Test collision from the top
				 */
				if (this.getY() + this.getHeight() > s.getY()
						&& this.getY() + this.getHeight() < s.getY()
								+ s.getHeight()) {
					if (this.velocity != null) {
						if (s.getVelocity() == null) {

							this.setLoc(x, s.getY() - this.getHeight(), 0);
							if ((this.velocity[1] * this.velocity[1] > 5)) {
								float v[] = { velocity[0],
										(float) (velocity[1] * -.3),
										velocity[2] };
								this.setVelocity(v);
							} else {
								inAir = false;
								ground = s;
								float v[] = { velocity[0], 0, velocity[2] };
								this.setVelocity(v);
							}

						} else {
							this.setLoc(x, s.getY() - this.getHeight(), 0);

							if ((this.velocity[1] * this.velocity[1] > 5)) {
								float v[] = { velocity[0],
										(float) (velocity[1] * -.3),
										velocity[2] };
								this.setVelocity(v);
							}
							if ((s.getVelocity()[1] * s.getVelocity()[1] > 5)) {
								float v[] = { s.getVelocity()[0],
										(float) (s.getVelocity()[1] * -.3),
										s.getVelocity()[2] };
								s.setVelocity(v);
							}

						}
					} else {
						s.setLoc(s.getX(), this.getY() + this.getHeight(), 0);
						if ((s.getVelocity()[1] * s.getVelocity()[1] > 5)) {
							float v[] = { s.getVelocity()[0],
									(float) (s.getVelocity()[1] * -.3),
									s.getVelocity()[2] };
							s.setVelocity(v);
						}

					}
				}
			}

			if (this.y < s.getY() + s.getHeight()
					&& this.y + this.getHeight() > s.getY()) {
				/**
				 * Test collision from the right
				 */
				if ((this.getX() < s.getX() + s.getWidth() && this.getX() > s
						.getX())) {
					if (this.velocity != null) {
						if (s.getVelocity() == null) {
							this.setLoc(s.getX() + s.getWidth(), y, 0);
							if ((this.velocity[0] * this.velocity[0] > 5)) {
								float v[] = { (float) (velocity[0] * -.3),
										velocity[1], velocity[2] };
								this.setVelocity(v);
							}
						} else {
							this.setLoc(s.getX() + s.getWidth(), y, 0);
							if ((this.velocity[0] * this.velocity[0] > 5)) {
								float v[] = { (float) (velocity[0] * -.3),
										velocity[1], velocity[2] };
								this.setVelocity(v);
							}
							if ((s.getVelocity()[1] * s.getVelocity()[1] > 5)) {
								float v[] = {
										(float) (s.getVelocity()[0] * -.3),
										s.getVelocity()[1], s.getVelocity()[2] };
								s.setVelocity(v);
							}

						}
					} else {
						s.setLoc(this.getX() - s.getWidth(), s.getY(), 0);
						if ((s.getVelocity()[0] * s.getVelocity()[0] < .5)) {
							float v[] = { (float) (s.getVelocity()[0] * -.3),
									s.getVelocity()[1], s.getVelocity()[2] };
							s.setVelocity(v);
						}
					}

				}

				/**
				 * Test collision from the left
				 */
				if ((this.getX() + this.getWidth() > s.getX() && this.getX()
						+ this.getWidth() < s.getX() + s.getWidth())) {
					if (this.velocity != null) {
						if (s.getVelocity() == null) {
							this.setLoc(s.getX() - this.getWidth(), y, 0);
							if ((this.velocity[0] * this.velocity[0] < .5)) {
								float v[] = { (float) (velocity[0] * -.3),
										velocity[1], velocity[2] };
								this.setVelocity(v);
							}
						} else {
							this.setLoc(s.getX() - this.getWidth(), y, 0);
							if ((this.velocity[0] * this.velocity[0] < .5)) {
								float v[] = { (float) (velocity[0] * -.3),
										velocity[1], velocity[2] };
								this.setVelocity(v);
							}
							if ((s.getVelocity()[0] * s.getVelocity()[0] < .5)) {
								float v[] = {
										(float) (s.getVelocity()[0] * -.3),
										s.getVelocity()[1], s.getVelocity()[2] };
								s.setVelocity(v);
							}

						}
					} else {
						s.setLoc(this.getX() + this.getWidth(), s.getY(), 0);
						if ((s.getVelocity()[0] * s.getVelocity()[0] < .5)) {
							float v[] = { (float) (s.getVelocity()[0] * -.3),
									s.getVelocity()[1], s.getVelocity()[2] };
							s.setVelocity(v);
						}
					}
				}
			}
		}

	}

	public void setGround(GameObject o) {
		this.ground = o;

	}

	public void setAir(boolean b) {
		this.inAir = b;

	}

}
