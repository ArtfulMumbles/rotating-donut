//*******************************************************************
//  Donut
//
//  Copyright © 2022 Michael Scutari
//*******************************************************************

import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Color;
import java.awt.Graphics2D;

public class Donut {

    private final double WIDTH = 150.0;
    private final double THICKNESS = 50.0;
    private final double N = 20;
    private final double L = 100;

    private ArrayList<Point> points;
    private double depth;

    public Donut() {
        this(0);
    }

    public Donut(double depth) {
        points = new ArrayList<Point>();

        this.depth = depth;
        makeDonut();
    }

    private void makeDonut() {
        for(double i = 0; i < L; i++) {

            double a = 2 * Math.PI * (i / L);
            Matrix2D rotate = Matrix2D.rotateY(a);

            for(double j = 0; j < N; j++) {
                double t = 2 * Math.PI * (j / N);
                Point point = new Point(WIDTH + THICKNESS * Math.sin(t), 0 + THICKNESS * Math.cos(t), 0);
                point.rotate(rotate);
                points.add(point);
            }
        }
    }

    public void rotate(double a, double b) {
        Matrix2D rotate = Matrix2D.rotate(a, 0, b);
        for(Point point : points) {
            point.rotate(rotate);
        }
    }

    public void paint(Graphics2D g, int screenX, int screenY) {
        
        for(Point p : points) {
            g.setColor(mapColor(p.zDepth()));
            g.fillOval((int) p.x2D() + screenX / 2, (int) p.y2D() + screenY / 2, 5, 5);
        }
    }

    private Color mapColor(double input) {
        double c = (((1.0) / (200)) * (input + 100));
        c = c < 0.5 ? 0.5 : c;
        c = c > 1.0 ? 1.0 : c;

        
        return new Color(1f, 1f, 1f, (float) c );
    }

    private class Point extends Orthogonal2DProjectable {

        private Point(double x, double y, double z) {
            super(x, y, z);
            
        }

        private Point rotate(Matrix2D rot) { 
            double tempX = rot.data[0][0] * x + rot.data[0][1] * y + rot.data[0][2] * z;
            double tempY = rot.data[1][0] * x + rot.data[1][1] * y + rot.data[1][2] * z;
            double tempZ = rot.data[2][0] * x + rot.data[2][1] * y + rot.data[2][2] * z;

            x = tempX;
            y = tempY;
            z = tempZ;

            return this;
        }

        private void setDepth(double d) {
            z = d;
        }

        @Override
        public String toString() {
            return "[" + x + ", " + y + ", " + z + "]";
        }


    }

    public static class Matrix2D {
        double[][] data;

        public Matrix2D(int x, int y) {
            this(new double[x][y]);
        }

        public Matrix2D(double[][] data) {
            this.data = data;
        }

        public static Matrix2D add(Matrix2D a, Matrix2D b) {
            Matrix2D ret = new Matrix2D(a.data.length, a.data.length);
            for(int i = 0; i < a.data.length; i++) {
                for (int j = 0; j < a.data[0].length; j++) {
                    ret.data[i][j] = a.data[i][j] + b.data[i][j];
                }
            }
            return ret;
        }

        public static Matrix2D rotate(double a, double b, double c) {
            return new Matrix2D(new double[][]{
    
                {Math.cos(a) * Math.cos(b), Math.cos(a) * Math.sin(b) * Math.sin(c) - Math.sin(a) * Math.cos(c), Math.cos(a) * Math.sin(b) * Math.cos(c) + Math.sin(a) * Math.sin(c)},
                {Math.sin(a) * Math.cos(b), Math.sin(a) * Math.sin(b) * Math.sin(c) + Math.cos(a) * Math.cos(c), Math.sin(a) * Math.sin(b) * Math.cos(c) - Math.cos(a) * Math.sin(c)},
                {-1 * Math.sin(b)         , Math.cos(b) * Math.sin(c)                                          , Math.cos(b) * Math.cos(c)                                          }
            
            });
        }

        private static Matrix2D rotateX(double a) {
            return new Matrix2D(new double[][]{
    
                {1, 0          , 0               },
                {0, Math.cos(a), -1 * Math.sin(a)},
                {0, Math.sin(a), Math.cos(a)     }
            
            });
        }
    
        private static Matrix2D rotateY(double a) {
            return new Matrix2D(new double[][]{
    
                {Math.cos(a)     , 0, Math.sin(a)},
                {0               , 1, 0          },
                {-1 * Math.sin(a), 0, Math.cos(a)}
            
            });
        }
    
        private static Matrix2D rotateZ(double a) {
            return new Matrix2D(new double[][]{
    
                {Math.cos(a), -1 * Math.sin(a), 0},
                {Math.sin(a), Math.cos(a)     , 0},
                {0          , 0               , 1}
            
            });
        }

        @Override
        public String toString() {
            return Arrays.deepToString(data);
        }

    }
}
