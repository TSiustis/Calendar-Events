import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

public class EventScheduler extends JFrame{
	private JButton save,cancel;
	private JSpinner spinner;
	private JLabel lblTime;
	private JLabel lblEventDetails;
	private JTextArea textArea;
	private JButton btnSave;
	private JButton btnCancel;
	DateFormat format = new SimpleDateFormat("MMddyyHHmmss");
	
	public EventScheduler(int row, int column) throws ParseException{
		Date date = format.parse("022310141505");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JFrame frame = this;
	    this.setSize(434,286);
		setTitle("Event");
		getContentPane().setLayout(new MigLayout("", "[][grow][][][][][][][][][][][][][]", "[][][][][][grow][][][]"));
		
		lblTime = new JLabel("Time:");
		lblTime.setBackground(UIManager.getColor("CheckBox.foreground"));
		getContentPane().add(lblTime, "cell 0 1");
		SpinnerDateModel model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		spinner= new JSpinner();
		spinner.setModel(model);
		spinner.setEditor(new JSpinner.DateEditor(spinner, "h:mm a"));
		getContentPane().add(spinner, "cell 1 1 3 1,grow");
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EntityManage em = new EntityManage();
				em.saveEvent(textArea.getText(), ((JSpinner.DateEditor) spinner.getEditor()).getFormat(),row,column);
				frame.dispose();
			}

			
		});
		getContentPane().add(btnSave, "cell 6 1");
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		getContentPane().add(btnCancel, "cell 8 1");
		
		lblEventDetails = new JLabel("Event Details");
		getContentPane().add(lblEventDetails, "cell 1 3");
		
		textArea = new JTextArea();
		getContentPane().add(textArea, "cell 1 4 13 4,grow");
		
		
	}
}
