package it.unibo.radarSystem22.domain.mock;

import it.unibo.radarSystem22.domain.Distance;
import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.models.SonarModel;
import it.unibo.radarSystem22.domain.models.SonarObservable;
import it.unibo.radarSystem22.domain.utils.BasicUtils;
import it.unibo.radarSystem22.domain.utils.ColorsOut;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;

public class SonarObservableMock extends SonarObservable implements ISonar{
private int delta = 1;
	@Override
	protected void sonarSetUp() {
		curVal = new Distance(90);		
		ColorsOut.out("SonarMock | sonarSetUp curVal="+curVal);
		//inizializza model observable
	}
	
	@Override
	public IDistance getDistance() {
		return curVal;
	}	
	
	public void setDistance(int val) {
		curVal=new Distance(val);
		updateDistance( val);	
	}
	
	@Override
	protected void sonarProduce( ) {
		if( DomainSystemConfig.testing ) {	//produces always the same value
			updateDistance( DomainSystemConfig.testingDistance );			      
		}else {
			int v = curVal.getVal() - delta;
			updateDistance( v );			    
			stopped = ( v <= 0 );
		}
		BasicUtils.delay(DomainSystemConfig.sonarDelay);  //avoid fast generation
 	}
}
