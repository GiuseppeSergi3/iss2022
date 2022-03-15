package it.unibo.radarSystem22.domain.mock;

import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.models.LedModel;

public class LedMock extends LedModel implements ILed{  
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

	@Override
	protected void ledActivate(boolean val) {
		// TODO Auto-generated method stub
		
	}
} 