/**
 * simulate a universe specified in one of the data files.
 */
public class NBody {

    /**
     * get corresponding to the radius of the universe in that file.
     */
    public static double readRadius(String file) {
        In in = new In(file);
        in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    /**
     * an array of Bodies corresponding to the bodies in the file.
     */
    public static Body[] readBodies(String file) {
        In in = new In(file);
        int size = in.readInt();
        Body[] bodies = new Body[size];
        int i = 0;
        in.readDouble();

        while (i < size) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            bodies[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
            i++;
        }
        return bodies;
    }

    /**
     * Drawing the Initial Universe State.
     * @param args
     */
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double time = 0;
        double xForces;
        double yForces;

        double radius = readRadius(filename);
        Body[] planets = readBodies(filename);

        String imageDrow = "./images/starfield.jpg";
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        StdAudio.play("./audio/2001.mid");

        /**
         * Creating an Animation.
         */
        while (time < T) {
            StdDraw.clear();
            StdDraw.picture(0, 0, imageDrow);
            for (Body planet : planets) {
                planet.draw();
                xForces = planet.calcNetForceExertedByX(planets);
                yForces = planet.calcNetForceExertedByY(planets);
                planet.update(dt, xForces, yForces);
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }

        /**
         * Printing the Universe.
         */
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
