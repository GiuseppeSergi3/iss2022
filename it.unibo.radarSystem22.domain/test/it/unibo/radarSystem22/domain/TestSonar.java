package it.unibo.radarSystem22.domain;

import static org.junit.Assert.assertTrue;
import org.junit.*;

import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.mock.LedMock;
import it.unibo.radarSystem22.domain.mock.SonarMock;
//import it.unibo.radarSystem22.domain.utils.BasicUtils;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;
 
public class TestSonar {
 
	@Before
	public void up() {
		System.out.println("inizioTest sonar");
	}
	
	@After
	public void down() {
		System.out.println("fineTest sonar");		
	}	
	
	@Test 
	public void testSonarMock() {
		
		System.out.println("testSonarMock");
		DomainSystemConfig.simulation = true; 
		
		ISonar sonar = new SonarMock();
		assertTrue(!sonar.isActive());
		
		sonar.activate();
		assertTrue(sonar.isActive());
		
		sonar.deactivate();
		assertTrue(!sonar.isActive());
	}	
	
	@Test
	public void testSonarMockDistance() throws InterruptedException {
		System.out.println("testSonarMock");
		DomainSystemConfig.simulation = true; 
		
		ISonar sonar = new SonarMock();
		sonar.activate();
		assertTrue(sonar.getDistance().getVal()==90);
		
		Thread.sleep(250);
		assertTrue(sonar.getDistance().getVal()==89);
		
		Thread.sleep(2500);
		assertTrue(sonar.getDistance().getVal()==80);
		
		sonar.deactivate();
		assertTrue(sonar.getDistance().getVal()==80);
		
		
		
		
		
	}
	

}
