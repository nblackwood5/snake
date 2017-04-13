import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class ReplayCourt extends JPanel {

	Replay arr;
	private static int time = 0;
	Timer timer;

	public ReplayCourt() {
		setTimer();
		time = 0;
		arr = GameCourt.getReplay();
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		timer.start(); 
		setFocusable(true);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		arr.draw(g);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT);
	}

	public static int getTime() {
		return time;
	}

	public void reset() {
		time = 0;
		timer.restart();;
	}
	
	public void setTimer() {
		timer = new Timer(GameCourt.INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (time <= GameCourt.getFinalTime()) {
					time++;
					repaint();
				}
				
			}
		});
	}
}
