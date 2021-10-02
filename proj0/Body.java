public class Body {

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static final double G = 6.67e-11;

	public Body(double xP, double yP, double xV,double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body(Body b) {
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}
    /* calculate distance between two bodies*/
	public double calcDistance(Body b) {
		double xDs = xxPos - b.xxPos;
		double yDs = yyPos - b.yyPos;
		return Math.sqrt(xDs*xDs + yDs*yDs);
	}

	public double calcForceExertedBy(Body b) {
		double distance = calcDistance(b);
		return G*mass*b.mass/(distance*distance);
	}

	public double calcForceExertedByX(Body b) {
		double totDs = calcDistance(b);
		double dx = b.xxPos - xxPos;
		double totF = calcForceExertedBy(b);
		return totF*dx/totDs;
	}


	public double calcForceExertedByY(Body b) {
		double totDs = calcDistance(b);
		double dy = b.yyPos - yyPos;
		double totF = calcForceExertedBy(b);
		return totF*dy/totDs;
	}
	/*calculate the X net force excert by N bodies on current body*/
	public double calcNetForceExertedByX(Body[] arrayBodies) {
		double netForceX = 0;
		for (Body bdd: arrayBodies) {
			if (this.equals(bdd)){
				continue;
			}
			netForceX += this.calcForceExertedByX(bdd);
		}
		return netForceX;
	}

	/*calculate the Y net force excert by N bodies on current body*/
	public double calcNetForceExertedByY(Body[] arrayBodies) {
		double netForceY = 0;
		for (Body bodyY: arrayBodies) {
			if (this.equals(bodyY)){
				continue;
			}
			netForceY += this.calcForceExertedByY(bodyY);
		}
		return netForceY;
	}

	public void update(double dt,double fx, double fy) {
		double acX = fx/mass; /*acceleration on X */
		double acY = fy/mass; /*acceleration on Y */
		xxVel = xxVel + dt*acX;
		yyVel = yyVel + dt *acY;
		xxPos = xxPos + xxVel*dt;
		yyPos = yyPos + yyVel*dt;
	}

	public void draw() {
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}

}
