
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.SpinnerDateModel;
import java.awt.event.ActionListener;
import java.awt.Window;
import java.awt.event.ActionEvent;

public class EventViewer extends JFrame{
	private JButton save,cancel;
	private JSpinner spinner;
	private JLabel lblTime;
	private JLabel lblEventDetails;
	private JTextArea textArea;
	DateFormat format = new SimpleDateFormat("MMddyyHHmmss");
	@PersistenceUnit
    private EntityManagerFactory emf=Persistence.createEntityManagerFactory( "CRM" );
	public EventViewer(int row, int column, Event ev) throws ParseException{
		Date date = format.parse("022310141505");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JFrame frame = this;
	    this.setSize(434,286);
		setTitle("Event");
		getContentPane().setLayout(new MigLayout("", "[][grow][][][][][][][][][][][][][]", "[][][][][][grow][][][]"));
		EntityManager em = emf.createEntityManager();
		JLabel time = new JLabel();
        List<Event> events = (List<Event>)em.createQuery("SELECT e FROM Event e", Event.class)
                              .getResultList(); 
        System.out.println("List of all customers: "+"<br/>");
        Iterator i = events.iterator();
        Event event;
        while (i.hasNext()) {
            event = (Event) i.next();
            if(event.getColumn()==column && event.getRow()==row){
            	textArea.setText(event.getText());
            	time.setText(event.getTime().toString());
            }
            System.out.println(event.getText()+"<br/>");
            System.out.println(event.getTime()+"<br/>");
            System.out.println(event.getRow()+"<br/>");
            System.out.println(event.getColumn()+"<br/>");
            System.out.println("----------------" + "<br/>");
        }
		lblTime = new JLabel("Time:");
		lblTime.setBackground(UIManager.getColor("CheckBox.foreground"));
		getContentPane().add(lblTime, "cell 0 1");
		
		//time.setText(ev.getTime().toString());
		
		//getContentPane().add(spinner, "cell 1 1 3 1,grow");
		
		lblEventDetails = new JLabel("Event Details");
		getContentPane().add(lblEventDetails, "cell 1 3");
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		//textArea.setText(ev.getText());
		getContentPane().add(textArea, "cell 1 4 13 4,grow");
		
		
	}
}

