import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.swing.JButton;

import org.hibernate.annotations.GenericGenerator;
@Entity(name="Event")
@Table(name="Event")
public class Event {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "id")
	private int ID;
	@Column(name="text")
	private String text;
	
	@Temporal(TemporalType.DATE)
	@Column(name="time")
	private  Date time;
	@Column(name="row")
	private int row;
	@Column(name="column")
	private int column;
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getTime()  {
	    
	    return time;
	}

	public void setTime(SimpleDateFormat sdf) throws ParseException {
		Date date = sdf.parse("yyyy-MM-dd hh:mm");
	    this.time=date;
	}

	

	
	
	

}
