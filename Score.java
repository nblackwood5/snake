import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Score implements Serializable {
	private String nick;
	private int score;
	private Date date;

	public Score(String n, int s) {
		this.score = s;
		this.nick = n;
		this.date =  Calendar.getInstance().getTime();
	}
	
	public String getNickName() {
        return nick;
    }
	
	public int getScore() {
		return score;
	}
	
	public Date getDate() {
		return date;
	}
	
	public String getDateString() {
		return date.toString();
	}
	
	
}
