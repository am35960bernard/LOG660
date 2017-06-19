package Controllers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import Model.Film;
import UI.ActorCriteriaPanel;
import UI.CountryCriteriaPanel;
import UI.DirectorCriteriaPanel;
import UI.GenreCriteriaPanel;
import UI.IntervalCriteriaPanel;
import UI.LanguageCriteriaPanel;
import UI.LoginWindow;
import UI.SearchWindow;
import UI.TitleCriteriaPanel;

public class FilmController implements java.awt.event.ActionListener{


	GestionnaireDeFilm model;
	SearchWindow view;	
	
	
	public void addModel(GestionnaireDeFilm model){
		this.model = model;
	} 


	public void addView(SearchWindow view){
		this.view = view;
	} 
	public void actionPerformed(java.awt.event.ActionEvent e){
		
		switch(e.getActionCommand())
		{
		case "Louer ce film" :
			Film f ;
			break;
			
		case "Rechercher":
			List<String> titles = new ArrayList<String>();
			List<String> intervals = new ArrayList<String>();
			List<String> pays = new ArrayList<String>();
			List<String> langues = new ArrayList<String>();
			List<String> genres = new ArrayList<String>();
			List<String> realisators = new ArrayList<String>();
			List<String> actors = new ArrayList<String>();
			Component[] myComponents = this.view.getSearchComponents();
			for(Component element : myComponents)
			{
				if(element instanceof TitleCriteriaPanel)
				{
					titles.add(((TitleCriteriaPanel) element).getTitle());
				}
				else if(element instanceof IntervalCriteriaPanel)
				{
					intervals.add(((IntervalCriteriaPanel) element).getMin());
					intervals.add(((IntervalCriteriaPanel) element).getMax());
				}
				else if(element instanceof CountryCriteriaPanel)
				{
					pays.add(((CountryCriteriaPanel) element).getCountry());
				}
				else if(element instanceof LanguageCriteriaPanel)
				{
					langues.add(((LanguageCriteriaPanel) element).getLangue());
				}
				else if(element instanceof GenreCriteriaPanel)
				{
					genres.add(((GenreCriteriaPanel) element).getGenre());
				}
				
				else if(element instanceof DirectorCriteriaPanel)
				{
					realisators.add(((DirectorCriteriaPanel) element).getRealisator());
				}
				else if(element instanceof ActorCriteriaPanel)
				{
					actors.add(((ActorCriteriaPanel) element).getActor());
				}
			}
			
			model.chercher(titles, intervals, pays, langues, genres, realisators, actors);
			break;
		}
		
	} 
}
