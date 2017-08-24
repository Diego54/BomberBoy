package com.uqbar.vainilla.colissions;

import com.uqbar.vainilla.GameComponent;

import static java.awt.geom.Point2D.distanceSq;

public class CollisionDetector {

	public static final CollisionDetector INSTANCE = new CollisionDetector();

	public String TOP = "TOP";
	public String BOT = "BOT";
	public String LEFT = "LEFT";
	public String RIGHT = "RIGHT";

	protected CollisionDetector() {
	}

	/**
	 * Determina si un c�rculo colisiona con un rect�ngulo.
	 * 
	 * @param cx
	 *            - Coordenada x de la esquina superior izquierda del c�rculo
	 *            (centroX - radio)
	 * @param cy
	 *            - Coordenada y de la esquina superior izquierda del c�rculo
	 *            (centroY - radio)
	 * @param cratio
	 *            - Radio del c�rculo
	 * @param rx
	 *            - Coordenada x de la esquina superior izquierda del rect�ngulo
	 * @param ry
	 *            - Coordenada y de la esquina superior izquierda del rect�ngulo
	 * @param rwidth
	 *            - Ancho del rect�ngulo
	 * @param rheight
	 *            - Alto del rect�ngulo
	 * @return Verdadero si colisionan, falso si no.
	 */
	public boolean collidesCircleAgainstRect(double cx, double cy, double cratio, double rx, double ry, double rwidth,
			double rheight) {
		Bounds circleBounds = new Bounds(cx, cy, cratio * 2, cratio * 2);
		Bounds rectBounds = new Bounds(rx, ry, rwidth, rheight);
		double rectLeft = rectBounds.getLeft();
		double rectRight = rectBounds.getRight();
		double rectTop = rectBounds.getTop();
		double rectBottom = rectBounds.getBottom();
		double extendedTargetTop = rectTop - cratio;
		double extendedTargetRight = rectRight + cratio;
		double extendedTargetBottom = rectBottom + cratio;
		double extendedTargetLeft = rectLeft - cratio;
		double radiusSq = cratio * cratio;
		double centerX = circleBounds.getCenterX();
		double centerY = circleBounds.getCenterY();

		if (centerX >= rectLeft) {
			if (centerX <= rectRight) {
				return centerY > extendedTargetTop && centerY < extendedTargetBottom;
			}
			if (centerY >= rectTop) {
				if (centerY <= rectBottom) {
					return centerX < extendedTargetRight;
				}
				return distanceSq(centerX, centerY, rectRight, rectBottom) < radiusSq;
			}
			return distanceSq(centerX, centerY, rectRight, rectTop) < radiusSq;
		}

		if (centerY >= rectTop) {
			if (centerY <= rectBottom) {
				return centerX > extendedTargetLeft;
			}
			return distanceSq(centerX, centerY, rectLeft, rectBottom) < radiusSq;
		}

		return distanceSq(centerX, centerY, rectLeft, rectTop) < radiusSq;
	}

	public boolean collidesRectAgainstRect(double x1, double y1, int width1, int height1, double x2, double y2,
			int width2, int height2) {
		double right1 = x1 + width1;
		double right2 = x2 + width2;
		double bottom1 = y1 + height1;
		double bottom2 = y2 + height2;

		return (x1 <= x2 && x2 < right1 || x2 <= x1 && x1 < right2)
				&& (y1 <= y2 && y2 < bottom1 || y2 <= y1 && y1 < bottom2);
	}

	public String collidesRectAgainstRect(GameComponent gc1, GameComponent gc2){
		if(gc2==null){
			return "null";
		}
		double w = 0.5 * (gc1.getAppearance().getWidth() + gc2.getAppearance().getWidth());
		double h = 0.5 * (gc1.getAppearance().getHeight()+ gc2.getAppearance().getHeight());;
		double dx = (gc1.getX()+(gc1.getAppearance().getWidth() * 0.5)) - (gc2.getX()+(gc2.getAppearance().getWidth() * 0.5));
		double dy = (gc1.getY()+(gc1.getAppearance().getHeight() * 0.5)) - (gc2.getY()+(gc2.getAppearance().getHeight() * 0.5));

		if (Math.abs(dx) <= w && Math.abs(dy) <= h){
           /* collision! */
			double wy = w * dy;
			double hx = h * dx;
			if (wy > hx) {
				if (wy > -hx) {
					return TOP;
				} else {
					return RIGHT;
				}
			}else{
				if (wy > -hx) {
					return LEFT;
				}else{
					return BOT;
				}
			}
		}
		return "null";

	}

	public boolean collidesCircleAgainstCircle(double x1, double y1, int ratio1, double x2, double y2, int ratio2) {
		double ratioSum = ratio1 + ratio2;
		double distanceSq = distanceSq(x1, y1, x2, y2);

		return distanceSq < ratioSum * ratioSum;
	}

	public boolean collidesPointAgainstTriangle(double x, double y, double vertexX1, double vertexY1, double vertexX2,
			double vertexY2, double vertexX3, double vertexY3) {

		double total = this.triangleArea(vertexX1, vertexY1, vertexX2, vertexY2, vertexX3, vertexY3);
		
		double t1 = this.triangleArea(x, y, vertexX2, vertexY2, vertexX3, vertexY3);
		double t2 = this.triangleArea(x, y, vertexX1, vertexY1, vertexX2, vertexY2);
		double t3 = this.triangleArea(x, y, vertexX1, vertexY1, vertexX3, vertexY3);

		return (t1 + t2 + t3) == total;
	}

	public double triangleArea(double vertexX1, double vertexY1, double vertexX2, double vertexY2, double vertexX3,
			double vertexY3) {
		double a = vertexX1 - vertexX3;
		double b = vertexY1 - vertexY3;
		double c = vertexX2 - vertexX3;
		double d = vertexY2 - vertexY3;
		
		return 0.5 * Math.abs((a * d) - (b * c));
	}

}