import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
public class EntityManage {
  EntityManagerFactory emf = Persistence.createEntityManagerFactory( "Calendar" );
	@PersistenceContext
	private EntityManager entityManager=emf.createEntityManager();
  public Event saveEvent(String text,SimpleDateFormat sdf,int row,int column){
		Event ev = new Event();
		  try {
		       entityManager.getTransaction().begin();
		      ev.setText(text);
		      ev.setTime(sdf);
		      ev.setColumn(column);
		      ev.setRow(row);
		      ev =  entityManager.merge(ev);
		       entityManager.getTransaction().commit();
		    } catch (Exception e) {
		       entityManager.getTransaction().rollback();
		    }
		    return ev;
		  }
	
  public Event getEvent(int row,int column) {
	    try {
	       entityManager.getTransaction().begin();
	      @SuppressWarnings("unchecked")
	      List<Event> events = entityManager.createQuery("from Event").getResultList();
	      for (Iterator<Event> it = events.iterator(); it.hasNext();) {
	        Event ev = (Event) it.next();
	        if(ev.getColumn()==column&& ev.getRow()==row)
	        	return ev;
	      }
	      entityManager.getTransaction().commit();
	    } catch (Exception e) {
	       entityManager.getTransaction().rollback();
	    }
		return null;
	  }
	
	  public void updateEvent(int id,String text,SimpleDateFormat time) {
		    try {
		       entityManager.getTransaction().begin();
		      Event event = (Event)  entityManager.find(Event.class, id);
		      event.setText(text);
		      event.setTime(time);
		       entityManager.getTransaction().commit();
		    } catch (Exception e) {
		      entityManager.getTransaction().rollback();
		    }
		  }

		  public void deleteEvent(int id) {
		    try {
		       entityManager.getTransaction().begin();
		      Event event = (Event) entityManager.find(Event.class, id);
		       entityManager.remove(event);
		       entityManager.getTransaction().commit();
		    } catch (Exception e) {
		      entityManager.getTransaction().rollback();
		    }
	}
}