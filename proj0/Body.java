public class Body {
    public double xxPos, yyPos, xxVel, yyVel, mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV,
                double yV, double m, String img) {
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


    /**
     * calculates the distance between two Bodys.
     */
    public double calcDistance(Body b) {
        return Math.hypot(this.xxPos - b.xxPos, this.yyPos - b.yyPos);
    }

    /**
     * the force exerted on this body by the given body.
     */
    public double calcForceExertedBy(Body b) {
        double G = 6.67e-11;
        return G * this.mass * b.mass / Math.pow(this.calcDistance(b), 2);
    }

    /**
     * the force exerted in the X and Y directionsi.
     */
    public double calcForceExertedByX(Body b) {
        double dx = b.xxPos - this.xxPos;
        return this.calcForceExertedBy(b) * dx / this.calcDistance(b);
    }

    public double calcForceExertedByY(Body b) {
        double dy = b.yyPos - this.yyPos;
        return this.calcForceExertedBy(b) * dy / this.calcDistance(b);
    }

    /**
     * calculates the net X and net Y force exerted
     * by all bodies in that array upon the current Body.
     */
    public double calcNetForceExertedByX(Body[] allBody) {
        double fxNet = 0;
        for (Body b : allBody) {
            if (!b.equals(this)) {
                fxNet += this.calcForceExertedByX(b);
            }
        }
        return fxNet;
    }

    public double calcNetForceExertedByY(Body[] allBody) {
        double fyNet = 0;
        for (Body b : allBody) {
            if (!b.equals(this)) {
                fyNet += this.calcForceExertedByY(b);
            }
        }
        return fyNet;
    }

    /**
     * the resulting change in the body’s velocity
     * and position in a small period of time dt.
     */
    public void update(double dt, double fX, double fY) {
        double ax = fX / this.mass;
        double ay = fY / this.mass;
        this.xxVel = this.xxVel + dt * ax;
        this.yyVel = this.yyVel + dt * ay;
        this.xxPos = this.xxPos + dt * xxVel;
        this.yyPos = this.yyPos + dt * yyVel;
    }

    /**
     * draw itself at its appropriate positioni.
     */
    public void draw() {
        StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
    }
}
