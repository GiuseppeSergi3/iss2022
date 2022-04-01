import java.util.ArrayList;
import java.util.List;

import aima.search.framework.*;
import aima.search.framework.SuccessorFunction;

public class MCimplementazioni implements SuccessorFunction{
	@Override
	public List getSuccessors(Object state) {
	List result = new ArrayList();

	if (state instanceof MCinterfaccia) {
	MCinterfaccia MCinterfaccia = (MCinterfaccia) state;

	// missionars that are on the shore where there is also the boat
	int numMissionari;
	// cannibals that are on the shore where there is also the boat
	int numCannibali;
	// depending on the shore, I calculate how many missionars/cannibals are
	// on that shore
	if (MCinterfaccia.getPosBarca()) {
	numMissionari = MCinterfaccia.getMissionari();
	numCannibali = MCinterfaccia.getCannibali();
	}
	else {
	numMissionari = MCinterfaccia.getTotMissionari() - MCinterfaccia.getMissionari();
	numCannibali = MCinterfaccia.getTotCannibali() - MCinterfaccia.getCannibali();
	}

	if ((numMissionari > 0) && (numCannibali > 0)) {
	MCinterfaccia newState = spostaMC(MCinterfaccia);
	if (isAllowed(newState))
	result.add(new Successor( MCinterfaccia.mc,newState));
	}
	if (numMissionari > 1) {
	MCinterfaccia newState = spostaMM(MCinterfaccia);
	if (isAllowed(newState))
	result.add(new Successor( MCinterfaccia.mm,newState));
	}
	if (numCannibali > 1) {
	MCinterfaccia newState = spostaCC(MCinterfaccia);
	if (isAllowed(newState))
	result.add(new Successor( MCinterfaccia.cc,newState));
	}
	if (numMissionari > 0) {
	MCinterfaccia newState = spostaM(MCinterfaccia);
	if (isAllowed(newState))
	result.add(new Successor( MCinterfaccia.m,newState));
	}
	if (numCannibali > 0) {
	MCinterfaccia newState = spostaC(MCinterfaccia);
	if (isAllowed(newState))
	result.add(new Successor( MCinterfaccia.c,newState));
	}
	}
	return result;
	}

	/*****************************************************************************
	* MOSSA CONSENTITA
	/*****************************************************************************/



	// check if there are not too much cannibals...
	private boolean isAllowed(MCinterfaccia MCinterfaccia) {

	if ( (MCinterfaccia.getMissionari() == 0) ||
	(MCinterfaccia.getMissionari() == MCinterfaccia.getTotMissionari()) ||
	( (MCinterfaccia.getMissionari() >= MCinterfaccia.getCannibali())) &&
	(((MCinterfaccia.getTotMissionari()-MCinterfaccia.getMissionari()) >=
	(MCinterfaccia.getTotCannibali()-MCinterfaccia.getCannibali()))
	)
	)
	return true;
	else {
	return false;
	}
	}



	/*****************************************************************************
	* Metodi per i diversi movimenti
	/*****************************************************************************/




	private MCinterfaccia spostaMC(MCinterfaccia MCinterfaccia){

	if(MCinterfaccia.getPosBarca()) //spostamento da 1sponda --> 2sponda
	{
	return new MCinterfaccia(MCinterfaccia.getTotCannibali(),MCinterfaccia.getTotMissionari(),MCinterfaccia.getMissionari()-1,MCinterfaccia.getCannibali()-1,! MCinterfaccia.getPosBarca());
	}
	else {
	return new MCinterfaccia(MCinterfaccia.getTotCannibali(),MCinterfaccia.getTotMissionari(),MCinterfaccia.getMissionari()+1,MCinterfaccia.getCannibali()+1,! MCinterfaccia.getPosBarca());
	}

	}

	private MCinterfaccia spostaMM(MCinterfaccia MCinterfaccia){

	if(MCinterfaccia.getPosBarca()) //spostamento da 1sponda --> 2sponda
	{
	return new MCinterfaccia(MCinterfaccia.getTotCannibali(),MCinterfaccia.getTotMissionari(),MCinterfaccia.getMissionari()-2,MCinterfaccia.getCannibali(),! MCinterfaccia.getPosBarca());
	}
	else {
	return new MCinterfaccia(MCinterfaccia.getTotCannibali(),MCinterfaccia.getTotMissionari(),MCinterfaccia.getMissionari()+2,MCinterfaccia.getCannibali(),! MCinterfaccia.getPosBarca());
	}

	}



	private MCinterfaccia spostaCC(MCinterfaccia MCinterfaccia){

	if(MCinterfaccia.getPosBarca()) //spostamento da 1sponda --> 2sponda
	{
	return new MCinterfaccia(MCinterfaccia.getTotCannibali(),MCinterfaccia.getTotMissionari(),MCinterfaccia.getMissionari(),MCinterfaccia.getCannibali()-2,! MCinterfaccia.getPosBarca());
	}
	else {
	return new MCinterfaccia(MCinterfaccia.getTotCannibali(),MCinterfaccia.getTotMissionari(),MCinterfaccia.getMissionari(),MCinterfaccia.getCannibali()+2,! MCinterfaccia.getPosBarca());
	}

	}



	private MCinterfaccia spostaM(MCinterfaccia MCinterfaccia){

	if(MCinterfaccia.getPosBarca()) //spostamento da 1sponda --> 2sponda
	{
	return new MCinterfaccia(MCinterfaccia.getTotCannibali(),MCinterfaccia.getTotMissionari(),MCinterfaccia.getMissionari()-1,MCinterfaccia.getCannibali(),! MCinterfaccia.getPosBarca());
	}
	else {
	return new MCinterfaccia(MCinterfaccia.getTotCannibali(),MCinterfaccia.getTotMissionari(),MCinterfaccia.getMissionari()+1,MCinterfaccia.getCannibali(),! MCinterfaccia.getPosBarca());
	}

	}



	private MCinterfaccia spostaC(MCinterfaccia MCinterfaccia){

	if(MCinterfaccia.getPosBarca()) //spostamento da 1sponda --> 2sponda
	{
	return new MCinterfaccia(MCinterfaccia.getTotCannibali(),MCinterfaccia.getTotMissionari(),MCinterfaccia.getMissionari(),MCinterfaccia.getCannibali()-1,! MCinterfaccia.getPosBarca());
	}
	else {
	return new MCinterfaccia(MCinterfaccia.getTotCannibali(),MCinterfaccia.getTotMissionari(),MCinterfaccia.getMissionari(),MCinterfaccia.getCannibali()+1,! MCinterfaccia.getPosBarca());
	}

	}
}
