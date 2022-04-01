import aima.search.framework.GoalTest;
import aima.search.framework.HeuristicFunction;
import aima.search.framework.StepCostFunction;

public class MCinterfaccia implements GoalTest, StepCostFunction, HeuristicFunction{

	//variabili che rappresentano lo stato
	private final int totMissionari;
	private final int totCannibali;
	private int missionari;
	private int cannibali;
	private boolean posBarca;
	
	// moves
	static public final String mc = "MC";
	static public final String mm = "MM";
	static public final String cc = "CC";
	static public final String m = "M";
	static public final String c = "C";
	
	//metodi per ottenere stato
	public int getMissionari() {
		return missionari;
	}
	
	public int getCannibali() {
		return cannibali;
	}
	
	public boolean getPosBarca() {
		return posBarca;
	}
	
	public int getTotCannibali() {
		return totCannibali;
	}
	
	public int getTotMissionari() {
		return totMissionari;
	}
	@Override
	public double getHeuristicValue(Object state) {
		// TODO Auto-generated method stub
		if (state instanceof MCinterfaccia) {
 			MCinterfaccia mcState = (MCinterfaccia) state;
 			int hVal = mcState.missionari + mcState.cannibali - (mcState.getPosBarca()? 1:0);
 			return hVal;
 		}
 		else return Integer.MAX_VALUE;
	}

	@Override
	public Double calculateStepCost(Object arg0, Object arg1, String arg2) {
		// TODO Auto-generated method stub
		return new Double(1);
	}

	//quando si raggiunge il goal?
	@Override
	public boolean isGoalState(Object state) { 
		if(state instanceof MCinterfaccia) {
			MCinterfaccia mcinterfaccia=(MCinterfaccia) state;
			if(mcinterfaccia.missionari==0 && mcinterfaccia.cannibali==0 && mcinterfaccia.posBarca==false) {
				return true;
			}
			
		}else {
			return false;
		}
		return false;	
	}
	
	
	//costruttori
	//costruttore generico
	public MCinterfaccia(int totMissionari, int totCannibali, int missionariSullaCosta, int cannibaliSullaCosta, boolean posBarca) {
		this.totMissionari=totMissionari;
		this.totCannibali=totCannibali;
		this.missionari=missionariSullaCosta;
		this.cannibali=cannibaliSullaCosta;
		this.posBarca=posBarca;	
	}
	
	public MCinterfaccia() {
		this(3,3,3,3,true);
	}
	

	
	public String toString() {
		return( "M: "+this.missionari+" C: "+this.cannibali+" B: "+this.posBarca);
	}
	




}
