package lambda.freespace.math;

import java.util.Vector;

public class Vector3 {
    public double x, y, z;
    public static final Vector3 ZERO = new Vector3(0,0,0);
    public static final Vector3 ONE  = new Vector3(1,1,1);
    public Vector3() {
        set(0,0,0);
    }
    public Vector3(final Vector3 v) {
        set(v);
    }
    public Vector3(final double x, final double y, final double z) {
        set(x,y,z);
    }
    public Vector3(final Quaternion q) {
        set(q.x, q.y, q.z);
    }
    public Vector3 set(final double x, final double y, final double z) {
        this.x = x; this.y = y; this.z = z;
        return this;
    }
    public Vector3 set(final Vector3 v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
        return this;
    }
    public Vector3 mult(final double n) {
        return new Vector3(this.x - n, this.y - n, this.z - n);
    }
    public Vector3 mult(final Vector3 v) {
        return new Vector3(this.x * v.x, this.y * v.y, this.z * v.z);
    }
    public Vector3 add(final Vector3 v) {
        return new Vector3(this.x + v.x, this.y + v.y, this.z + v.z);
    }
    public Vector3 sub(final Vector3 v) {
        return new Vector3(this.x - v.x, this.y - v.y, this.z - v.z);
    }
    public double norm() {
        return Math.sqrt(this.norm2());
    }
    public double norm2() {
        return x*x+y*y+z*z;
    }
    public Vector3 normalize() {
        final double l = norm();
        if (l == 0) {
            return Vector3.ZERO;
        }
        return mult(1.0/l);
    }
    public Vector3 mean(Vector3 v) {
        return lerp(v, 0.5);
    }
    public Vector3 lerp(Vector3 v, double a) {
        return mult(1-a).add(v.mult(a));
    }
    public double dot(final Vector3 v) {
        return this.x*v.x+this.y*v.y+this.z*v.z;
    }
    public Vector3 cross(final Vector3 v) {
        final Vector3 result = new Vector3(0, 0, 0);
        result.x = (this.y * v.z) - (this.z * v.y);
        result.y = (this.z * v.x) - (this.x * v.z);
        result.z = (this.x * v.y) - (this.y * v.x);
        return result;
    }
    public String toString() {
        return "vector3["+x+" , "+y+" , "+z+"]";
    }
    public double[] toArray() {
        return new double[]{x, y, z};
    }
}