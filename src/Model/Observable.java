package Model;

import java.util.ArrayList;
import java.util.List;

import Controllers.Observer;


public class Observable {

	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	
	
	public void addObserver(Observer observer) {
		this.listObserver.add(observer);
	}

	public void removeObservers() {
		listObserver = new ArrayList<Observer>();
	} 	
	
	protected void notifyObserver(String stringToNotify) {

		for(Observer observer : listObserver)
			observer.update(stringToNotify);
		}

	protected void notifyObserver(List<Film> listResult)
	{
		for(Observer observer : listObserver)
			observer.update(listResult);
		
	}
	
}
