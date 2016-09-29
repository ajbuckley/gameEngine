/**
 * 
 */
package gameObject;

/**
 * @author Drew
 *
 */
public class Rectangle implements GravityAffected {
	private boolean visible;
	private int x;
	private int y;
	private int width;
	private int height;
	private int[] fill = { 175, 0, 100, 255 };
	private int[] stroke = { 0, 50, 100, 255 };
	private float[] velocity = null;
	private float gravity;
	private boolean inAir;
	private VisibleObject ground;
	private final static float TERMINAL_VELOCITY = 10;

	/**
	 * 
	 */
	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.visible = true;
	}

	/**
	 * @see gameObject.VisibleObject#setLoc(int, int, int)
	 */
	@Override
	public void setLoc(int x, int y, int z) {
		this.x = x;
		this.y = y;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameObject.Locatable#getX()
	 */
	@Override
	public int getX() {

		return x;
	}

	/**
	 * @see gameObject.VisibleObject#getY()
	 */
	@Override
	public int getY() {

		return y;
	}

	/**
	 * @see gameObject.VisibleObject#getZ()
	 */
	@Override
	public int getZ() {

		return 0;
	}

	/**
	 * @see gameObject.VisibleObject#drawType()
	 */
	@Override
	public int drawType() {

		return RECT_DRAW_VAL;
	}

	/**
	 * @see gameObject.VisibleObject#update()
	 */
	@Override
	public void update() {

		if (velocity != null) {
			if (this.inAir()) {
				this.fall();
			}
			if (velocity[0] > TERMINAL_VELOCITY) {
				velocity[0] = TERMINAL_VELOCITY;
			}
			this.x += velocity[0];
			if (velocity[1] > TERMINAL_VELOCITY) {
				velocity[1] = TERMINAL_VELOCITY;
			}
			this.y += velocity[1];
		}

	}

	/**
	 * @see gameObject.GravityAffected#fall()
	 */
	@Override
	public void fall() {
		velocity[1] += gravity / 60;

	}

	/**
	 * @see gameObject.GravityAffected#inAir()
	 */
	@Override
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
	 * @see gameObject.GravityAffected#getLoc()
	 */
	@Override
	public int[] getLoc() {
		int loc[] = new int[2];
		loc[0] = x;
		loc[1] = y;
		return loc;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see gameObject.GravityAffected#setVisible(boolean)
	 */
	@Override
	public void setVisible(boolean visible) {
		this.visible = visible;

	}

	/**
	 * @see gameObject.GravityAffected#isVisible()
	 */
	@Override
	public boolean isVisible() {

		return visible;
	}

	/**
	 * @see gameObject.GravityAffected#setGravity(float)
	 */
	public void setGravity(float grav) {

		this.gravity = grav;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameObject.GravityAffected#getGravity()
	 */
	public float getGravity() {
		// TODO Auto-generated method stub
		return gravity;
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
	public void setColor(int[] stroke, int[] fill) {
		this.stroke = stroke;
		this.fill = fill;

	}

	public int getWidth() {
		return width;

	}

	public int getHeight() {

		return height;
	}

	@Override
	public void setVelocity(float[] vel) {
		this.velocity[0] = vel[0];
		this.velocity[1] = vel[1];
		this.velocity[2] = vel[2];

	}

	@Override
	public float[] getVelocity() {
		return velocity;
	}

	public void handleCollisions(VisibleObject o) {

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

	public void setGround(VisibleObject o) {
		this.ground = o;

	}

	public void setAir(boolean b) {
		this.inAir = b;

	}

}
