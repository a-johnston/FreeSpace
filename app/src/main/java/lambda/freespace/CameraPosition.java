
public class CameraPosition {
	public Quaternion orientation;
	public Point3D position;
	
	public CameraPosition(Point3D position, Quaternion orientation) {
		this.position = position;
		this.orientation = orientation;
	}
}
