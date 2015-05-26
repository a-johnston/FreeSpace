package lambda.freespace.math;

public class Quaternion {
    public double x, y, z, w;
    public static final Quaternion IDENTITY = new Quaternion();
    public static final Quaternion ONE      = new Quaternion(1.0, 1.0, 1.0, 1.0);
    public static final Quaternion ZERO     = new Quaternion(0.0, 0.0, 0.0, 0.0);
    public Quaternion() {
        set(0.0, 0.0, 0.0, 1.0);
    }
    public Quaternion(final double x, final double y, final double z, final double w) {
        set(x, y, z, w);
    }
    public Quaternion(final Quaternion q) {
        set(q);
    }
    public Quaternion(final Vector3 v) {
        set(v.x, v.y, v.z, 1.0);
    }
    public Quaternion(final Vector3 axis, final double angle) {
        set(axis, angle);
    }
    public Quaternion(final double yaw, final double pitch, final double roll) {
        set(yaw, pitch, roll);
    }
    public Quaternion set(final double x, final double y, final double z, final double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        return this;
    }
    public Quaternion set(final Quaternion q) {
        this.set(q.x, q.y, q.z, q.w);
        return this;
    }
    public Quaternion set(final Vector3 axis, final double angle) {
        final double half = angle/2.0;
        final double sin  = Math.sin(half);
        return this.set(axis.x*sin, axis.y*sin, axis.z*sin, Math.cos(half));
    }
    public Quaternion set(final double yaw, final double pitch, final double roll) {
        final double hy  = yaw   * 0.5;
        final double hp  = pitch * 0.5;
        final double hr  = roll  * 0.5;
        final double shy = Math.sin(hy);
        final double chy = Math.cos(hy);
        final double shp = Math.sin(hp);
        final double chp = Math.cos(hp);
        final double shr = Math.sin(hr);
        final double chr = Math.cos(hr);
        final double chy_shp = chy * shp;
        final double shy_chp = shy * chp;
        final double chy_chp = chy * chp;
        final double shy_shp = shy * shp;

        x = (chy_shp * chr) + (shy_chp * shr);
        y = (shy_chp * chr) - (chy_shp * shr);
        z = (chy_chp * shr) - (shy_shp * chr);
        w = (chy_chp * chr) + (shy_shp * shr);
        return this;
    }
    public Quaternion normalize() {
        final double l = this.norm();
        if (l == 0) return Quaternion.ZERO;
        return this.mult(1.0/l);
    }
    public Quaternion conjugate() {
        return new Quaternion(-x, -y, -z, w);
    }
    public Quaternion mult(final double n) {
        return new Quaternion(x*n, y*n, z*n, w*n);
    }
    public Quaternion mult(final Quaternion q) {
        final double nx = this.w * q.x + this.x * q.w + this.y * q.z - this.z * q.y;
        final double ny = this.w * q.y + this.y * q.w + this.z * q.x - this.x * q.z;
        final double nz = this.w * q.z + this.z * q.w + this.x * q.y - this.y * q.x;
        final double nw = this.w * q.w - this.x * q.x - this.y * q.y - this.z * q.z;
        return new Quaternion(nx, ny, nz, nw);
    }
    public Quaternion multLeft (Quaternion q) {
        final double nx = q.w * this.x + q.x * this.w + q.y * this.z - q.z * y;
        final double ny = q.w * this.y + q.y * this.w + q.z * this.x - q.x * z;
        final double nz = q.w * this.z + q.z * this.w + q.x * this.y - q.y * x;
        final double nw = q.w * this.w - q.x * this.x - q.y * this.y - q.z * z;
        return new Quaternion(nx, ny, nz, nw);
    }
    public Vector3 forward() {
        return new Vector3( 2 * (x * z + w * y),
                            2 * (y * x - w * x),
                            1 - 2 * (x * x + y * y));
    }

    public Vector3 up() {
        return new Vector3( 2 * (x * y - w * z),
                            1 - 2 * (x * x + z * z),
                            2 * (y * z + w * x));
    }

    public Vector3 right() {
        return new Vector3( 1 - 2 * (y * y + z * z),
                            2 * (x * y + w * z),
                            2 * (x * z - w * y));
    }
    public double norm() {
        return Math.sqrt(norm2());
    }
    public double norm2() {
        return x*x + y*y + z*z + w*w;
    }
    public void toMatrix (final float[] m) {
        if (m.length != 16) {
            return;
        }

        final double xx = x * x;
        final double xy = x * y;
        final double xz = x * z;
        final double xw = x * w;
        final double yy = y * y;
        final double yz = y * z;
        final double yw = y * w;
        final double zz = z * z;
        final double zw = z * w;

        m[0]  = (float)(1 - 2 * (yy + zz));
        m[1]  = (float)(2 * (xy + zw));
        m[2]  = (float)(2 * (xz - yw));
        m[3]  = 0;

        m[4]  = (float)(2 * (xy - zw));
        m[5]  = (float)(1 - 2 * (xx + zz));
        m[6]  = (float)(2 * (yz + xw));
        m[7]  = 0;

        m[8]  = (float)(2 * (xz + yw));
        m[9]  = (float)(2 * (yz - xw));
        m[10] = (float)(1 - 2 * (xx + yy));
        m[11] = 0;

        m[12] = 0;
        m[13] = 0;
        m[14] = 0;
        m[15] = 1;
    }
    public float[] toMatrix() {
        float[] m = new float[16];
        toMatrix(m);
        return m;
    }
    public Vector3 transform(final Vector3 v) {
        final Quaternion q1 = new Quaternion(this).conjugate();
        final Quaternion q2 = new Quaternion(v);
        q1.multLeft(q2.multLeft(this));
        return new Vector3(q1.x, q1.y, q1.z);
    }
    @Override
    public boolean equals(Object other) {
        if (other.getClass() != Quaternion.class) {
            return false;
        }
        Quaternion q = (Quaternion)other;
        return ((this.x == q.x) && (this.y == q.y) && (this.z == q.z) && (this.w == q.w));
    }
    public String toString() {
        return "quaternion[" + x + " , " + y + " , " + z + " , " + w + "]";
    }
}
