package it.unibo.radarSystem22.domain;
import static org.junit.Assert.assertTrue;
import org.junit.*;

import it.unibo.radarSystem22.domain.broker.BrokerObserver;
import it.unibo.radarSystem22.domain.concrete.ObserverConcrete;
import it.unibo.radarSystem22.domain.concrete.SonarObservableConcrete;
import it.unibo.radarSystem22.domain.interfaces.IObserver;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.interfaces.ISonarObservable;
import it.unibo.radarSystem22.domain.mock.SonarObservableMock;
import it.unibo.radarSystem22.domain.models.SonarObservable;
import it.unibo.radarSystem22.domain.utils.BasicUtils;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;
 

public class TestObserver {
	private int DTESTING1=20;
	private int DTESTING2=70;
	
	@Before
	public void up() {
		System.out.println("up");
	}
	
	@After
	public void down() {
		System.out.println("down");		
	}	
	
	@Test 
	public void testObserver() {
		
		DomainSystemConfig.simulation = true;
		DomainSystemConfig.testing    = false;
		DomainSystemConfig.sonarDelay = 10;		//quite fast generation ...
		int delta = 1;
		IObserver obs1 = new ObserverConcrete();
		IObserver obs2 = new ObserverConcrete();
		//ISonar sonar = DeviceFactory.createSonar();
		ISonar sonar=new SonarObservableMock();
		BrokerObserver broker=((SonarObservableMock) sonar).getBroker();
		
		broker.register(obs1);
		broker.register(obs2);
		
		((SonarObservableMock) sonar).setDistance(DTESTING1);
		assertTrue(obs1.getVal().getVal() == DTESTING1);
		assertTrue(obs2.getVal().getVal() == DTESTING1);
		
		((SonarObservableMock) sonar).setDistance(DTESTING2);
		assertTrue(obs1.getVal().getVal() == DTESTING2);
		assertTrue(obs2.getVal().getVal() == DTESTING2);
		
 	}
	
 
}
