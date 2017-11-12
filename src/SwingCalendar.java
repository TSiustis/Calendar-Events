import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.swing.*;
import javax.swing.table.*;
import net.miginfocom.swing.MigLayout;
 
public class SwingCalendar extends JFrame {
 
  DefaultTableModel model;
  Calendar cal = new GregorianCalendar();
  JLabel label;
  JTable table; 
 EntityManage em = new EntityManage();
  SwingCalendar() {
 
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Swing Calandar");
    this.setSize(434,190);
    this.setVisible(true);
 
    JButton b1 = new JButton("\u2228");
    b1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        cal.add(Calendar.MONTH, -1);
        updateMonth();
      }
    });
 
    JPanel panel = new JPanel();
    panel.setLayout(new MigLayout("", "[41px][76px][41px][][41px][][][][]", "[23px]"));

    panel.add(b1, "cell 0 0,alignx left,aligny top");
    String [] columns = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
    model = new DefaultTableModel(null,columns);
   table = new JTable(model);
    JScrollPane pane = new JScrollPane(table);
    getContentPane().setLayout(new BorderLayout(0, 0));
 
    getContentPane().add(panel);
    
    getContentPane().add(pane);
    
    getContentPane().add(panel,BorderLayout.NORTH);
       
           JButton b2 = new JButton("\u2227");
           b2.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent ae) {
               cal.add(Calendar.MONTH, +1);
               updateMonth();
             }
             
           });
           
                 panel.add(b2, "cell 1 0,alignx left,aligny top");
    
    
       label = new JLabel();
       label.setHorizontalAlignment(SwingConstants.CENTER);
       panel.add(label, "cell 3 0,alignx left,aligny center");
    
    JButton btnViewEvent = new JButton("View Event");
    btnViewEvent.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		int row = table.getSelectedRow();
  	      if (row > -1) {
  	  	      int column = table.getSelectedColumn();
  	      
  	    	  try {
				EventViewer viewer = new EventViewer(row,column,em.getEvent(row, column));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
  	      }
  	     
  	  	  
  	}
    	
    });
    panel.add(btnViewEvent, "cell 6 0");
    JButton b3 = new JButton("+");
    panel.add(b3, "cell 8 0,alignx left,aligny top");
    getContentPane().add(pane,BorderLayout.CENTER);
    b3.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		int row = table.getSelectedRow();
    	      if (row > -1) {
    	  	      int column = table.getSelectedColumn();
    	      try {
				EventScheduler ev = new EventScheduler(row,column);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
    	  	  }
    	}
    });
    this.updateMonth();
 
  }
 
  void updateMonth() {
    cal.set(Calendar.DAY_OF_MONTH, 1);
 
    String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
    int year = cal.get(Calendar.YEAR);
    label.setText(month + " " + year);
 
    int startDay = cal.get(Calendar.DAY_OF_WEEK);
    int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    int weeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
 
    model.setRowCount(0);
    model.setRowCount(weeks);
 
    int i = startDay-1;
    for(int day=1;day<=numberOfDays;day++){
      model.setValueAt(day, i/7 , i%7 );    
      i = i + 1;
    }
    
  }
  
 
 
}