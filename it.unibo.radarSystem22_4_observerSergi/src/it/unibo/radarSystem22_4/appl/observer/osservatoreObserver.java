package it.unibo.radarSystem22_4.appl.observer;

import java.util.Observable;
import java.util.Observer;

public class osservatoreObserver implements Observer {
	private SonarObservable sonar;
	
	public osservatoreObserver(SonarObservable sonar) {
		this.sonar=sonar;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		System.out.println("Cambiamento sostanziale nuova distanza "+ arg1.toString());
	}
	
}
