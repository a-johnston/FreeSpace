package lambda.freespace;

import lambda.freespace.math.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Motion3D {
	private int frameNum = 0;
	private HashMap<Integer, Vector3> points = new HashMap<>();
	private ScreenSpec screenSpec;
	private HashMap<Integer, Ray3D> firstFrameRays = new HashMap<>();
	private LinkedList<CameraPosition> cameraHistory = new LinkedList<>();
	
	public Motion3D(ScreenSpec screenSpec) {
		this.screenSpec = screenSpec;
	}
	
	public void addFrame(CameraPosition cameraPosition, HashMap<Integer, Pixel> pixels) {
		for (int blobID : pixels.keySet()) {
			Pixel blobPixel = pixels.get(blobID);
			Vector3 blobVector = screenSpec.pixelToVector(blobPixel);
			Ray3D blobRay = new Ray3D(cameraPosition, blobVector);
			if (firstFrameRays.containsKey(blobID)) {
				Ray3D blobRayOld = firstFrameRays.remove(blobID);
				points.put(blobID, blobRay.midpointTo(blobRayOld));
			} else {
				firstFrameRays.put(blobID, blobRay);
			}
		}
		cameraHistory.addLast(cameraPosition);
		frameNum++;
	}
	
	public int numFrames() {
		return frameNum;
	}
	
	public boolean blobHasPosition(int blobID) {
		return points.containsKey(blobID);
	}
	
	public List<CameraPosition> getCameraPositionHistory() {
		return cameraHistory;
	}
	
	public CameraPosition getCameraPosition() {
		return cameraHistory.getLast();
	}
	
	public Vector3 getBlobPosition(int blobID) {
		return points.get(blobID);
	}
}
