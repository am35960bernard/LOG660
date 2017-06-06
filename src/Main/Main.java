package Main;
import java.util.Date;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.Acteur;
import Model.Film;
import Model.HibernateUtil;
import Model.PersonnageDuCinema;

public class Main {
	public static void main(String[] args) {

		Session sessionHome = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = sessionHome.beginTransaction();


			Random randomGenerator = new Random();
			int randomInt = randomGenerator.nextInt(100000) + 4000000;
			
			Date aujourdhui = new Date();

			
			
			Film nouveauFilm = new Film(randomInt,"Le nouveau film",2017,"Canada","English",111,"C'est l'histoire du petit castor. Le plus petit mais le plus fort. Dans la forêt au milieu de tous ses amis. Il est heureux, il s'amuse, il joue et il rit.","https://i.ytimg.com/vi/7XEDDitQimM/hqdefault.jpg");
			
			PersonnageDuCinema nouveauActeur = new Acteur(randomInt,"Bob l'acteur castor",aujourdhui,"Long Island, New York, USA","Trapu et rondelet, le castor se déplace lentement et est gauche sur le sol. Toutefois, ce n’est pas le cas dans l’eau. Là, le castor est un nageur habile et très gracieux, sous l’eau comme à la surface, et atteint une vitesse de près de 7 km/h lorsqu’il est en état d’alerte.","https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/290px-American_Beaver.jpg");
			
			
			
			System.out.println("Un nouveau acteur vient d'être inséré dans la base de données");			
			System.out.println("Un nouveau film vient d'être inséré dans la base de données");
			
		//	transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			sessionHome.close();
		}

	}
}

