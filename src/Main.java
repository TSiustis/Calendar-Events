import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingCalendar sc = new SwingCalendar();
		EntityManage em= new EntityManage();
		em.listEvent();
	}
	
}

