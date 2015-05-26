package lambda.freespace.test;
import android.test.InstrumentationTestCase;

import java.util.HashMap;

import lambda.freespace.CameraPosition;
import lambda.freespace.Motion3D;
import lambda.freespace.Pixel;
import lambda.freespace.ScreenSpec;
import lambda.freespace.math.*;

public class TestMotion3D extends InstrumentationTestCase{

	public void testMotion3D() {
		ScreenSpec screen = new ScreenSpec(200, 200, 2, 2);
		Motion3D m = new Motion3D(screen);
		HashMap<Integer, Pixel> points = new HashMap<>();
		points.put(1, new Pixel(100, 100));
		m.addFrame(new CameraPosition(new Vector3(0, 0, 0), new Quaternion(1, 0, 0, 0)), points);
		m.addFrame(new CameraPosition(new Vector3(1, 0, 0), new Quaternion(1, 0, .5, 0)), points);
		Vector3 pos = m.getBlobPosition(1);
		System.out.println(pos);
	}

	public void testAddFrame() {
		fail("Not yet implemented");
	}

	public void testNumFrames() {
		fail("Not yet implemented");
	}

	public void testBlobHasPosition() {
		fail("Not yet implemented");
	}

	public void testGetCameraPositionHistory() {
		fail("Not yet implemented");
	}

	public void testGetCameraPosition() {
		fail("Not yet implemented");
	}

	public void testGetBlobPosition() {
		fail("Not yet implemented");
	}

}
