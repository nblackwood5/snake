/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game implements Runnable {
	private boolean ready= false;
	private static String nickName = "Anon";
	static Scorer sc = new Scorer();
	final static ReplayCourt re = new ReplayCourt();

	public void run() {
		if (!ready) {
			final JFrame page = new JFrame("Instructions");
			page.setLocation(0, 0);
			final JPanel pan = new JPanel();
			page.add(pan, BorderLayout.SOUTH);
			final JTextField namer = new JTextField(20);
			page.add(namer,BorderLayout.NORTH);
			final JButton play = new JButton("Play");
			play.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ready = true;
					nickName = namer.getText();
					run();
					page.dispose();
				}
			});
			pan.add(play);
			String wordz = ("<html>This is Snake.<br>"
					+ "<html>Use the arrow keys to collect food without dying. <br>"
					+ "<html>Press the keyboard to pause the game. <br>"
					+ "<html>Each food you eat adds length to your snake and makes it harder to manage.<br>"
					+ "<html>A mouse is the most common and adds 5 length to your snake.<br>"
					+ "<html>An M and M is less common and adds 10 length to your snake.<br>"
					+ "<html>Master Splinter is the rarest and adds a lot of length to your snake.<br>"
					+ "<html>You lose by running into yourself or going out of bounds. <br>"
					+ "<html>You win by eating food until your snake covers the whole board <br>"
					+ "<html>Enter a nickname above and press play or enter to begin. ");
			final JLabel instructionWords = new JLabel (wordz); 
			final JPanel instructions = new JPanel();
			page.add(instructions, BorderLayout.CENTER);
			instructions.add(instructionWords);
			namer.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						ready = true;
						nickName = namer.getText();
						run();
						page.dispose();
					}
				}
			});

			page.pack();
			page.setVisible(true);
		} else {
			final JFrame frame = new JFrame("Snake");
			frame.setLocation(0, 0);
			final JPanel status_panel = new JPanel();
			frame.add(status_panel, BorderLayout.SOUTH);
			final JLabel status = new JLabel("Running...");
			status_panel.add(status);
			final JLabel lengthStat = new JLabel("                       Length: 1");
			status_panel.add(lengthStat);
			final GameCourt court = new GameCourt(status, lengthStat);
			frame.add(court, BorderLayout.CENTER);
			final JPanel control_panel = new JPanel();
			frame.add(control_panel, BorderLayout.NORTH);

			final JButton reset = new JButton("Reset");
			reset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					court.reset();
				}
			});
			control_panel.add(reset);
			frame.pack();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			court.reset();
		}
	}

	public static String getNick() {
		return nickName;
	}

	public static void showScoreboard(int score) {
		sc.add(getNick(), score);
		final JFrame scoreFrame = new JFrame("Scores");
		scoreFrame.setLocation(0, 0);
		String ss = sc.getString();
		final JLabel scoreString = new JLabel (ss); 
		final JPanel scorePanel = new JPanel();
		scoreFrame.add(scorePanel, BorderLayout.CENTER);
		scorePanel.add(scoreString);

		final JButton scoreReplay = new JButton("Instant Replay");
		scoreReplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showReplay();
			}
		});
		scoreFrame.add(scoreReplay, BorderLayout.SOUTH);
		scoreFrame.pack();
		scoreFrame.setVisible(true);

	}

	public static void showReplay() {
		final JFrame replay = new JFrame("Replay");
		replay.setLocation(0, 0);
		replay.add(re, BorderLayout.CENTER);
		String wordz2pointOH = ("<html>This is a replay of your attempt.<br>"
				+ "<html>It shows the path of your snake. <br>"
				+ "<html>When your snake dies it will turn red. <br>"
				+ "<html>Exit to continue playing. ");
		final JLabel instructionWords = new JLabel (wordz2pointOH); 
		final JPanel instructions = new JPanel();
		instructions.add(instructionWords);
		replay.add(instructions, BorderLayout.NORTH);
		re.reset();
		replay.pack();
		replay.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		replay.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
	}


}
