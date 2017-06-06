package Main;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.Acteur;
import Model.Client;
import Model.Film;
import Model.HibernateUtil;
import Model.PersonnageDuCinema;

public class Main {
	public static void main(String[] args) {

		Session sessionHome = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = sessionHome.beginTransaction();


			String hql = "from Client where idClient = :unNombre";
			Query query = sessionHome.createQuery(hql);
			query.setInteger("unNombre",2431);
			List results =  (List) query.list();
			System.out.println( ((Client)results.get(0)).getPrenom());			


			
/*******	EXEMPLE DE CODE D'INSSERSSION ICI   *******
		
			Random randomGenerator = new Random();
			int randomInt = randomGenerator.nextInt(100000) + 4000000;
			Date aujourdhui = new Date();
			Film nouveauFilm = new Film(randomInt,"Le nouveau film",2017,"Canada","English",111,"C'est l'histoire du petit castor. Le plus petit mais le plus fort. Dans la for�t au milieu de tous ses amis. Il est heureux, il s'amuse, il joue et il rit.","https://i.ytimg.com/vi/7XEDDitQimM/hqdefault.jpg");			
			PersonnageDuCinema nouveauActeur = new Acteur(randomInt,"Bob l'acteur castor",aujourdhui,"Long Island, New York, USA","Trapu et rondelet, le castor se d�place lentement et est gauche sur le sol. Toutefois, ce n�est pas le cas dans l�eau. L�, le castor est un nageur habile et tr�s gracieux, sous l�eau comme � la surface, et atteint une vitesse de pr�s de 7 km/h lorsqu�il est en �tat d�alerte.","https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/290px-American_Beaver.jpg");
			System.out.println("Un nouveau acteur vient d'�tre ins�r� dans la base de donn�es");			
			System.out.println("Un nouveau film vient d'�tre ins�r� dans la base de donn�es");		
			transaction.commit();		 
*********************************************************/
			
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			sessionHome.close();
		}

	}
}

