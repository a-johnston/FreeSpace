
public class PixelVectorConvertor {
	private final int screenWidth;
	private final int screenHeight;
	private final double horzDist;
	private final double vertDist;
	
	public PixelVectorConvertor(int screenWidth, int screenHeight, double horzFOV, double vertFOV) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.horzDist = 1.0 * Math.tan(horzFOV);
		this.vertDist = 1.0 * Math.tan(vertFOV);
	}
	
	public Point3D pixelToVector(Pixel pixel) {
		double x = ((((double)pixel.x - (screenWidth / 2.0)) / (screenWidth / 2.0)) * horzDist);
		double y = ((((double)pixel.y - (screenHeight / 2.0)) / (screenHeight / 2.0)) * vertDist);
		return new Point3D(x, y, 1.0);
	}
	
	/*
	public Pixel VectorToPixel(Point3D vector) {
		return null;
	}
	*/
}
