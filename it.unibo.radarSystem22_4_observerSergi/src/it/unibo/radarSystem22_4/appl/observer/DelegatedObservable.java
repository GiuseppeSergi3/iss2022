package it.unibo.radarSystem22_4.appl.observer;

import java.util.Observable;

public class DelegatedObservable extends Observable{
	public void clearChanged() { super.clearChanged(); }
	public void setChanged() { super.setChanged();} //sono priavti cosi li rendo pubblici
}
