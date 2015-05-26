package lambda.freespace.math;

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
        this.x *= n;
        this.y *= n;
        this.z *= n;
        return this;
    }
    public Vector3 mult(final Vector3 v) {
        this.x *= v.x;
        this.y *= v.y;
        this.z *= v.z;
        return this;
    }
    public Vector3 add(final Vector3 v) {
        this.x += v.x;
        this.y += v.y;
        this.z += v.z;
        return this;
    }
    public Vector3 sub(final Vector3 v) {
        this.x -= v.x;
        this.y -= v.y;
        this.z -= v.z;
        return this;
    }
    public double len() {
        return Math.sqrt(this.len2());
    }
    public double len2() {
        return x*x+y*y+z*z;
    }
    public Vector3 norm() {
        final double l = len();
        if (l != 0) {
            this.mult(1/l);
        }
        return this;
    }
    public Vector3 lerp(Vector3 v, double a) {
        mult(1-a).add(Vector3.mult(v,a));
        return this;
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
    public static Vector3 cross(final Vector3 v1, final Vector3 v2) {
        return v1.cross(v2);
    }
    public static double dot(Vector3 v1, Vector3 v2) {
        return v1.dot(v2);
    }
    public static Vector3 mult(Vector3 v, double s) {
        return new Vector3(v).mult(s);
    }
    public static Vector3 mult(Vector3 v1, Vector3 v2) {
        return new Vector3(v1).mult(v2);
    }
    public static Vector3 add(Vector3 v1, Vector3 v2) {
        return new Vector3(v1).add(v2);
    }
    public static Vector3 sub(Vector3 v1, Vector3 v2) {
        return new Vector3(v1).sub(v2);
    }
    public static Vector3 normalized(Vector3 v) {
        return new Vector3(v).norm();
    }
    public String toString() {
        return "vector3["+x+" , "+y+" , "+z+"]";
    }
}