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
  EntityManagerFactory emf = Persistence.createEntityManagerFactory( "CRM" );
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
	/*public Event getEvent(int row,int column){
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Event> cq = cb.createQuery(Event.class);
		Root<Event> ev = cq.from(Event.class);
		EntityType<Event> Event_ = ev.getModel();
		cq.where(cb.equal(ev.get("column"),column)
					.and(cb.equal(ev.get("row"), row)));
		 TypedQuery<Event> query =  entityManager.createQuery(cq);
		  List<Event> results = query.getResultList();
	return results.get(0);
	}*/
  public Event getEvent(int row,int column) {
	    try {
	       entityManager.getTransaction().begin();
	      @SuppressWarnings("unchecked")
	      List<Event> events = entityManager.createQuery("from Event").getResultList();
	      for (Iterator<Event> it = events.iterator(); it.hasNext();) {
	        Event ev = (Event) it.next();
	        if(ev.getColumn()==column&& ev.getRow()==row)
	        	return ev;
	        System.out.println(ev.getText());
	      }
	      entityManager.getTransaction().commit();
	    } catch (Exception e) {
	       entityManager.getTransaction().rollback();
	    }
		return null;
	  }
	  public void listEvent() {
		    try {
		       entityManager.getTransaction().begin();
		      @SuppressWarnings("unchecked")
		      List<Event> events = entityManager.createQuery("from Event").getResultList();
		      for (Iterator<Event> it = events.iterator(); it.hasNext();) {
		        Event ev = (Event) it.next();
		        System.out.println(ev.getText());
		      }
		      entityManager.getTransaction().commit();
		    } catch (Exception e) {
		       entityManager.getTransaction().rollback();
		    }
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