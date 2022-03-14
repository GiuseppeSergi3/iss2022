package it.unibo.radarSystem22.domain.mock;

import it.unibo.radarSystem22.domain.Distance;
import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.ISonar;

public class SonarMock implements ISonar {
	int distance;
	boolean attivo = false;

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		attivo=true;
		distance=90;
		new Thread() {
			public void run() {
				while(attivo==true && distance>=0) {
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					distance--;
				}
			}
		}.start();
	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		attivo=false;		
	}

	@Override
	public IDistance getDistance() {
		// TODO Auto-generated method stub
		IDistance Idistance=new Distance(distance);
		return Idistance;
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return attivo;
	}

}
