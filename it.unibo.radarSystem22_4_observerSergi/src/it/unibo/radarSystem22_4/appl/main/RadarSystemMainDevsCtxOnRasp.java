package it.unibo.radarSystem22_4.appl.main;

import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;
import it.unibo.radarSystem22_4.appl.RadarSystemConfig;
import it.unibo.radarSystem22_4.appl.handler.LedApplHandler;
import it.unibo.radarSystem22_4.appl.handler.SonarApplHandler;
import it.unibo.radarSystem22_4.appl.observer.SonarObservable;
import it.unibo.radarSystem22_4.appl.observer.osservatoreObserver;
import it.unibo.radarSystem22_4.comm.ProtocolType;
import it.unibo.radarSystem22_4.comm.context.ContextMsgHandler;
import it.unibo.radarSystem22_4.comm.enablers.EnablerContext;
import it.unibo.radarSystem22_4.comm.interfaces.IApplMsgHandler;
import it.unibo.radarSystem22_4.comm.interfaces.IApplication;
import it.unibo.radarSystem22_4.comm.interfaces.IContext;
import it.unibo.radarSystem22_4.comm.utils.BasicUtils;
import it.unibo.radarSystem22_4.comm.utils.ColorsOut;
import it.unibo.radarSystem22_4.comm.utils.CommSystemConfig;
import it.unibo.radarSystem22.domain.DeviceFactory;
import it.unibo.radarSystem22.domain.Distance;

public class RadarSystemMainDevsCtxOnRasp implements IApplication{
	private ISonar sonar;
	private ILed  led ;
  	
 	private IContext contextServer;

	
	@Override
	public String getName() {
		return this.getClass().getName() ; 
	}
	@Override
	public void doJob(String domainConfig, String systemConfig ) {
		setup(domainConfig,   systemConfig);
		configure();
		execute();	
		temp();
		BasicUtils.aboutThreads("Thread sul Raspberry: ");
	}
	
	public void setup( String domainConfig, String systemConfig )  {
	    BasicUtils.aboutThreads(getName() + " | Before setup ");
		if( domainConfig != null ) {
			DomainSystemConfig.setTheConfiguration(domainConfig);
		}
		if( systemConfig != null ) {
			RadarSystemConfig.setTheConfiguration(systemConfig);
		}
		if( domainConfig == null && systemConfig == null) {
			DomainSystemConfig.simulation  = true;
	    	DomainSystemConfig.testing     = false;			
	    	DomainSystemConfig.tracing     = false;			
			DomainSystemConfig.sonarDelay  = 200;
	    	DomainSystemConfig.ledGui      = true;		//se siamo su PC	
	    	
		    CommSystemConfig.tracing       = true;

		    RadarSystemConfig.RadarGuiRemote   = true;		
 			RadarSystemConfig.ctxServerPort    = 8756;
 			RadarSystemConfig.protcolType      = ProtocolType.tcp;
		}
 
	}
	protected void configure() {		
 	   led   = DeviceFactory.createLed(); 
	   sonar = DeviceFactory.createSonar();
   
 	   //contextServer  = new TcpContextServer("TcpCtxServer",RadarSystemConfig.ctxServerPort); 	   
	   contextServer = new EnablerContext("ctx",""+RadarSystemConfig.ctxServerPort,
 			                  RadarSystemConfig.protcolType, new ContextMsgHandler("ctxH"));
		//Registrazione dei componenti presso il contesto
 	   IApplMsgHandler sonarHandler = SonarApplHandler.create("sonarH",sonar); 
	   IApplMsgHandler ledHandler   = LedApplHandler.create("ledH",led);		  
	   contextServer.addComponent("sonar", sonarHandler);	//sonar NAME mandatory
	   contextServer.addComponent("led",   ledHandler);		//led NAME mandatory
	}
	
	protected void execute() {	
		
		contextServer.activate();		
	}
	
	public void temp() {
		int valueTemp=0;
		
		String host           = RadarSystemConfig.raspAddr;
		ProtocolType protocol = CommSystemConfig.protcolType;
		String ctxport        = ""+RadarSystemConfig.ctxServerPort;
		SonarObservable sonarObs=new SonarObservable("sonarObs", host, ctxport, protocol);
		
		//aggiunta observer
		osservatoreObserver observer = new osservatoreObserver(sonarObs);
		sonarObs.getObservable().addObserver(observer);
		
		ColorsOut.out("activate the sonar obs");
		sonarObs.activate();
		BasicUtils.delay(1000);
		//BasicUtils.waitTheUser();
		boolean sonarActive = sonarObs.isActive();
		ColorsOut.out("sonarObsActive="+sonarActive);
		if( sonarActive ) {
			for( int i=1; i<=20; i++ ) {
				int d = sonarObs.getDistance().getVal();
				//System.out.println("D: "+d+" temp: "+valueTemp);
				ColorsOut.out("sonar distance="+d);
				BasicUtils.delay(1000);	
				if(Math.abs(d-valueTemp)>10) {
					sonarObs.valueChange(new Distance(d));
				}
				valueTemp=d;
			}
		}
	}
	
	public static void main( String[] args) throws Exception {
		//ColorsOut.out("Please set RadarSystemConfig.pcHostAddr in RadarSystemConfig.json");
		new RadarSystemMainDevsCtxOnRasp().doJob("DomainSystemConfig.json","RadarSystemConfig.json");
		
		
 	}

}
