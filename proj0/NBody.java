public class NBody {
    public static double readRadius(String file){
        In in = new In(file);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Body[] readBodies(String file) {

        In in = new In(file);
        int numPlanets = in.readInt();
        in.readDouble();
        Body[] bodyArray = new Body[numPlanets];

        for (int i = 0; i< bodyArray.length; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double vX = in.readDouble();
            double vY = in.readDouble();
            double ma = in.readDouble();
            String pic = in.readString();
            bodyArray[i] = new Body(xP, yP, vX, vY, ma, pic);
        }
        return bodyArray;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] nbodies = readBodies(filename);
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();


        for (Body b: nbodies) {
            b.draw();
        }
        StdDraw.enableDoubleBuffering();
        double time = 0;
        while (time < T){
            double[] xForces = new double[nbodies.length];
            double[] yForces = new double[nbodies.length];
            for (int i = 0; i < nbodies.length; i++){
                xForces[i] = nbodies[i].calcNetForceExertedByX(nbodies);
                yForces[i] = nbodies[i].calcNetForceExertedByY(nbodies);
            }

            for (int i = 0; i < nbodies.length; i++) {
                nbodies[i].update(time, xForces[i],yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (int i = 0; i < nbodies.length; i++) {
               nbodies[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(1000);
            time += dt;
        }

        StdOut.printf("%d\n", nbodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < nbodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    nbodies[i].xxPos, nbodies[i].yyPos, nbodies[i].xxVel,
                    nbodies[i].yyVel, nbodies[i].mass, nbodies[i].imgFileName);
        }
    }

}