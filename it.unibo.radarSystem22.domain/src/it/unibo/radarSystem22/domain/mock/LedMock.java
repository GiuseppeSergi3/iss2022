package it.unibo.radarSystem22.domain.mock;

import it.unibo.radarSystem22.domain.interfaces.ILed;

public class LedMock implements ILed{  
	boolean stato;

	@Override
	public void turnOn() {
		// TODO Auto-generated method stub
		stato = true;
	}

	@Override
	public void turnOff() {
		// TODO Auto-generated method stub
		stato = false;
		
	}

	@Override
	public boolean getState() {
		// TODO Auto-generated method stub
		return stato;
	}
} 