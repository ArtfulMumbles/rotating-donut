//*******************************************************************
//  Orthogonal2DProjectable
//
//  Copyright © 2022 Michael Scutari
//*******************************************************************

public class Orthogonal2DProjectable {

    double x, y, z;

    public Orthogonal2DProjectable(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double x2D() {
        return x;
    }
    public double y2D() {
        return y;
    }
    public double zDepth() {
        return z;
    }
}