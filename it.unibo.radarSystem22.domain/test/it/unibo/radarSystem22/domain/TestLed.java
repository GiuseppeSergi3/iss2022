package it.unibo.radarSystem22.domain;

import static org.junit.Assert.assertTrue;
import org.junit.*;

import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.mock.LedMock;
//import it.unibo.radarSystem22.domain.utils.BasicUtils;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;
 
public class TestLed {
 
	@Before
	public void up() {
		System.out.println("up");
	}
	
	@After
	public void down() {
		System.out.println("down");		
	}	
	
	@Test 
	public void testLedMockOn() {
		
		System.out.println("testLedMockOn");
		DomainSystemConfig.simulation = true; 
		
		ILed led = new LedMock();
		assertTrue( ! led.getState() );
		
 		led.turnOn();
		assertTrue(  led.getState() );
		
	}	
	
	@Test 
	public void testLedMockOff() {
		
		System.out.println("testLedMockOff");
		DomainSystemConfig.simulation = true; 
		
		ILed led = new LedMock();
		assertTrue( ! led.getState() );
		
 		led.turnOff();
		assertTrue(  ! led.getState() );
		
	}	

}
