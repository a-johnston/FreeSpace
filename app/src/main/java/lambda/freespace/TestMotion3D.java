import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;


public class TestMotion3D {

	@Test
	public void testMotion3D() {
		ScreenSpec screen = new ScreenSpec(200, 200, 2, 2);
		Motion3D m = new Motion3D(screen);
		HashMap<Integer, Pixel> points = new HashMap<>();
		points.put(1, new Pixel(100, 100));
		m.addFrame(new CameraPosition(new Point3D(0, 0, 0), new Quaternion(1, 0, 0, 0)), points);
		m.addFrame(new CameraPosition(new Point3D(1, 0, 0), new Quaternion(1, 0, .5, 0)), points);
		Point3D pos = m.getBlobPosition(1);
		System.out.println(pos);
	}

	@Test
	public void testAddFrame() {
		fail("Not yet implemented");
	}

	@Test
	public void testNumFrames() {
		fail("Not yet implemented");
	}

	@Test
	public void testBlobHasPosition() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCameraPositionHistory() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCameraPosition() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBlobPosition() {
		fail("Not yet implemented");
	}

}
