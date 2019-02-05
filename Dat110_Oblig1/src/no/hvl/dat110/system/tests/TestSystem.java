package no.hvl.dat110.system.tests;


import no.hvl.dat110.system.controller.Controller;
import no.hvl.dat110.system.display.DisplayDevice;
import no.hvl.dat110.system.sensor.SensorDevice;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestSystem {

	@Test
	public void test() {

		System.out.println("System starting ...");

		Runnable display = () -> DisplayDevice.main(null);
		Runnable sensor = () -> SensorDevice.main(null);
		Runnable controller = () -> Controller.main(null);

		Thread displaythread = new Thread(display);
		Thread sensorthread = new Thread(sensor);
		Thread controllerthread = new Thread(controller);

		displaythread.start();
		sensorthread.start();
		controllerthread.start();

		try {
			
			displaythread.join();
			sensorthread.join();
			controllerthread.join();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// we check only termination here
		assertTrue(true);
			
		System.out.println("System stopping ...");
	}

}
