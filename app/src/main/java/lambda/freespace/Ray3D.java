package lambda.freespace;

import lambda.freespace.math.*;

public class Ray3D {
	public Vector3 position;
	public Vector3 vector;
	
	public Ray3D(Vector3 position, Vector3 vector) {
		this.position = position;
		this.vector = vector;
	}
	
	public Ray3D(CameraPosition cameraPosition, Vector3 vector) {
		this.position = cameraPosition.position;
		this.vector = cameraPosition.orientation.mult(new Quaternion(vector)).mult(cameraPosition.orientation.conjugate()).forward(); //TODO wat does this do??
	}
	
	public Vector3 midpointTo(Ray3D other) {
		Vector3 perpVector = this.vector.cross(other.vector);
		// a*vector + b*perpVector - c*other.vector == other.position - position
		// [a,b,c] = Matrix(vector, perpVector, -other.vector).inverse().multiply(Matrix(other.position - position)); // TODO: optimization here
		double[][] matrix = {{vector.x, perpVector.x, other.vector.x},
				             {vector.y, perpVector.y, other.vector.y},
				             {vector.z, perpVector.z, other.vector.z}};
		double[] v = other.position.sub(position).toArray();
		double[] abc = Matrix.multiply(Matrix.inverse(matrix), v);
		double a = abc[0];
		double c = abc[2];
		Vector3 point1 = vector.mult(a);
		Vector3 point2 = other.vector.mult(c);
		Vector3 midpoint = point1.mean(point2);
		return midpoint;
	}
	
	public Vector3 midpointTo(Vector3 point) {
		Vector3 pointToPoint = point.sub(position);
		Vector3 vectorToClosestPoint = vector.normalize().mult(pointToPoint.dot(vector.normalize())); // TODO: easy optimization here
		Vector3 closestPoint = position.add(vectorToClosestPoint);
		Vector3 midpoint = closestPoint.mean(point);
		return midpoint;
	}
}
