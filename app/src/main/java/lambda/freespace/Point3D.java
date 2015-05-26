
public class Point3D {
	public final double x;
	public final double y;
	public final double z;
	public Point3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	// point methods
	
	public Point3D subtract(Point3D other) {
		return new Point3D(this.x-other.x, this.y-other.y, this.z-other.z);
	}
	
	public Point3D add(Point3D vector) {
		return new Point3D(this.x+vector.x, this.y+vector.y, this.z+vector.z);
	}
	
	public Point3D mean(Point3D other) {
		return new Point3D((this.x+other.x)/2.0, (this.y+other.y)/2.0, (this.z+other.z)/2.0);
	}
	
	// vector methods
	
	public double dotProduct(Point3D other) {
		return this.x*other.x + this.y*other.y + this.z*other.z;
	}
	
	public Point3D crossProduct(Point3D other) {
		return new Point3D(this.y*other.z - this.z*other.y, this.x*other.z - this.z*other.x, this.x*other.y - this.y*other.x);
	}
	
	public Point3D scalarMultiply(double factor) {
		return new Point3D(x*factor, y*factor, z*factor);
	}
	
	public Point3D scalarDivide(double divisor) {
		return new Point3D(x/divisor, y/divisor, z/divisor);
	}
	
	public double magnitude() {
		return Math.sqrt(this.dotProduct(this));
	}
	
	public Point3D toUnitVector() {
		return this.scalarDivide(this.magnitude());
	}
	
	public double[] toArray() {
		return new double[]{x, y, z};
	}
}
