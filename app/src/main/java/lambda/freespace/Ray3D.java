
public class Ray3D {
	public Point3D position;
	public Point3D vector;
	
	public Ray3D(Point3D position, Point3D vector) {
		this.position = position;
		this.vector = vector;
	}
	
	public Ray3D(CameraPosition cameraPosition, Point3D vector) {
		this.position = cameraPosition.position;
		this.vector = cameraPosition.orientation.times(new Quaternion(vector)).times(cameraPosition.orientation.inverse()).toVector();
	}
	
	public Point3D midpointTo(Ray3D other) {
		Point3D perpVector = this.vector.crossProduct(other.vector);
		// a*vector + b*perpVector - c*other.vector == other.position - position
		// [a,b,c] = Matrix(vector, perpVector, -other.vector).inverse().multiply(Matrix(other.position - position)); // TODO: optimization here
		double[][] matrix = {{vector.x, perpVector.x, other.vector.x},
				             {vector.y, perpVector.y, other.vector.y},
				             {vector.z, perpVector.z, other.vector.z}};
		double[] v = other.position.subtract(position).toArray();
		double[] abc = Matrix.multiply(Matrix.inverse(matrix), v);
		double a = abc[0];
		double c = abc[2];
		Point3D point1 = vector.scalarMultiply(a);
		Point3D point2 = other.vector.scalarMultiply(c);
		Point3D midpoint = point1.mean(point2);
		return midpoint;
	}
	
	public Point3D midpointTo(Point3D point) {
		Point3D pointToPoint = point.subtract(position);
		Point3D vectorToClosestPoint = vector.toUnitVector().scalarMultiply(pointToPoint.dotProduct(vector.toUnitVector())); // TODO: easy optimization here
		Point3D closestPoint = position.add(vectorToClosestPoint);
		Point3D midpoint = closestPoint.mean(point);
		return midpoint;
	}
}
