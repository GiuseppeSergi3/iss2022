package it.unibo.radarSystem22.domain.concrete;

import java.util.Observable;

import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.IObserver;

public class ObserverConcrete implements IObserver {
	private IDistance value;
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		System.out.println("Cambiamento sostanziale nuova distanza "+ arg1.toString());
		this.value=(IDistance) arg1;
		
	}

	@Override
	public void update(IDistance value) {
		// TODO Auto-generated method stub
		System.out.println("Cambiamento sostanziale nuova distanza "+ value);
		this.value=value;
		
	}
	
	@Override
	public IDistance getVal() {
		return value;
	}

}
