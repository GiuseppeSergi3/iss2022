package it.unibo.radarSystem22.domain.broker;

import java.util.ArrayList;
import java.util.List;

import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.IObserver;

public class BrokerObserver {
	
	List<IObserver> list=new ArrayList<IObserver>();
	
	public  void register( IObserver obs ) {
		list.add(obs);
	}
	
	public void unregister( IObserver obs ) {
		list.remove(obs);
	}
	
	public void publish(IDistance value) {
		for(IObserver o : list) {
			o.update(value);
		}
	}
}
