package com.pedEdt.frontEnd.client.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class TimeInterval{
	public int nbSeances;
	public SeanceWidget[] tab;
	
	public TimeInterval(){
		nbSeances = 0;
	}
	
	public void reset(){
		nbSeances = 0;
		tab = null;
	}
	
	public void initTab(){
		//TODO: remove 5, arbitrary chosen
		tab = new SeanceWidget[5];
		for(int i=0; i< 5; i++)
			tab[i] = null;
	}
	
	public int getFirstFreePos(){
		int i = 0;
		while(tab[i] != null)
			i++;
		return i;
	}
	
	public int getPos(SeanceWidget seance){
		//TODO: remove 5, arbitrary chosen
		for(int i=0; i<5;i++){
			if( tab[i] == seance)
				return i;
		}
		return -1;
	}
}


public class SeanceWidgetsManager {

	private List<SeanceWidget> seances;
	private Grid grid;
	private int gridX;
	private int gridY;
	private int nbIntervals;
	private TimeInterval[] timeIntervals;
	
	public SeanceWidgetsManager(Grid grid){
		seances = new ArrayList<SeanceWidget>();
		this.grid = grid;
		nbIntervals = grid.getNbDays()*grid.getNbIntervals();
		timeIntervals = new TimeInterval[nbIntervals];
		for(int i=0;i<nbIntervals;i++)
			timeIntervals[i] = new TimeInterval();
	}
	
	public void addSeance(SeanceWidget seance){
		if( seances.contains(seance))
			seances.remove(seance);
		int i = 0;
		int currentH;
		int currentV;
		int currentLength;
		while(true){
			if( i >= seances.size())
				break;
			currentH = seances.get(i).getPosH();
			currentV = seances.get(i).getPosV();
			currentLength = seances.get(i).getLength();
			if( currentH > seance.getPosH() 
					|| (currentH == seance.getPosH() && currentV > seance.getPosV()))
				break;
			if( currentH == seance.getPosH() && currentV == seance.getPosV())
				if( currentLength >= seance.getLength())
					break;
			i++;			
		}
		seances.add(i,seance);
	}
	
	private void addSeanceToGrid(SeanceWidget seance){
		gridX = grid.getDroppableArea().getOffsetWidth()/grid.getNbDays();
		gridY = grid.getDroppableArea().getOffsetHeight()/(grid.getNbIntervals()+1);
		
		int startInterval = seance.getPosH()*grid.getNbIntervals() + seance.getPosV();
		int pos = timeIntervals[startInterval].getPos(seance);
		int length = seance.getLength();
		int nbNeighs = -1;
		for(int i=0;i<seance.getLength();i++){
			nbNeighs = nbNeighs < timeIntervals[startInterval+i].nbSeances ? 
					timeIntervals[startInterval+i].nbSeances
					: nbNeighs;
		}
		
		//TODO
		int width = gridX/nbNeighs-1;
		seance.setWidth(width+"px");
		//seance.setWidth("100px");
		seance.setHeight((gridY*length+length)+"px");
		//seance.setHeight("100px");
		
		int left = seance.getPosH() * gridX+1;
		left = Math.max(0,left);
		int top = seance.getPosV() * gridY;
		top = Math.max(0,top);
		
		// border correction
		top += top/10;
		
		grid.getDroppableArea().add(seance, left+ pos*width+pos, top);
	}
	
	public void doPositionning(){
		for(int i=0;i<nbIntervals;i++)
			timeIntervals[i].reset();
		
		for(Iterator<SeanceWidget> it = seances.iterator();it.hasNext(); ){
			SeanceWidget seance = it.next();
			if(seance.isRemoved())
				it.remove();
		}
	
		for (SeanceWidget seance : seances) {
			int startInterval = seance.getPosH()*grid.getNbIntervals() + seance.getPosV();
			for(int i = 0; i< seance.getLength(); i++)
				timeIntervals[startInterval+i].nbSeances++;
		}
		for(int i=0;i<nbIntervals;i++)
			timeIntervals[i].initTab();
		
		for (SeanceWidget seance : seances) {
			int startInterval = seance.getPosH()*grid.getNbIntervals() + seance.getPosV();
			int pos = timeIntervals[startInterval].getFirstFreePos();
			for(int i=0;i<seance.getLength();i++){
				timeIntervals[startInterval+i].tab[pos] = seance;
			}
			addSeanceToGrid(seance);
		}
	}
	
	public void removeAllSeances(){
		for (SeanceWidget seance : seances) {
			seance.removeFromParent();
		}
		seances.clear();
	}
}
