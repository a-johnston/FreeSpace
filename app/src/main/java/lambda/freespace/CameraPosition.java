package lambda.freespace;

import lambda.freespace.math.*;

public class CameraPosition {
	public Quaternion orientation;
	public Vector3    position;
	
	public CameraPosition(Vector3 position, Quaternion orientation) {
		this.position    = position;
		this.orientation = orientation;
	}
}
