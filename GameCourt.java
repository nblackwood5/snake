/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

import java.awt.*;

import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class GameCourt extends JPanel {
	private Snake snake; 
	private Food food;
	public boolean playing = false; 
	private JLabel status; 
	private JLabel lStat;
	private boolean moved = false;
	public static final int COURT_WIDTH = 400;
	public static final int COURT_HEIGHT = 400;
	public static final int INTERVAL = 20;
	public static final int TOTAL_BLOCKS = (COURT_WIDTH * COURT_HEIGHT) / Snake.getSize();
	public static Replay replay = new Replay();
	private static int ticker = 0;
	private static int finalTime;

	public GameCourt(JLabel status, JLabel lengthStat) {
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (playing) {
					tick();
				}
			}
		});
		timer.start(); 
		setFocusable(true);
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					if (playing) {
						snake.pauseColor(getGraphics());
						playing = false;
					}
					else {
						playing = true;
					}
				}
				if(playing){
					if (!moved) {
						if (e.getKeyCode() == KeyEvent.VK_LEFT) {
							if(snake.direction.equals(Direction.RIGHT)) {

							} else {
								snake.direction = Direction.LEFT;
								moved = true;
							}
						}
						else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
							if(snake.direction.equals(Direction.LEFT)) {

							} else {
								snake.direction = Direction.RIGHT;
								moved = true;
							}
						}
						else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
							if(snake.direction.equals(Direction.UP)) {

							} else {
								snake.direction = Direction.DOWN;
								moved = true;
							}
						}
						else if (e.getKeyCode() == KeyEvent.VK_UP) {
							if(snake.direction.equals(Direction.DOWN)) {

							} else {
								snake.direction = Direction.UP;
								moved = true;
							}
						}
					}
				}
			}
		});
		this.status = status;
		this.lStat = lengthStat;
	}

	public void reset() {
		snake = new Snake(COURT_WIDTH, COURT_HEIGHT);
		replay.reset();
		ticker = 0;
		pickFood();
		lStat.setText("                       Length:1");
		playing = true;
		status.setText("Running...");
		requestFocusInWindow();
	}

	void tick() {
		if (playing) {
			ticker++;
			snake.move();
			replay.record(snake.getX(0), snake.getY(0));
			moved = false;
			if (snake.selfIntersect()) {
				die();
			}
			if (snake.isOB()) {
				die();
			}
			if(snake.intersects(food)) {
				if ((snake.getCoverage()) >= 
						(TOTAL_BLOCKS/ Snake.getSize()) ) {
					win();
				}
				snake.extend(food.getIncr());
				lStat.setText("                       Length:" + snake.getLength());
				pickFood();
				food.pos_x = ((int) (Math.random() * (COURT_WIDTH/food.getSize() - 1))) * food.getSize();
				food.pos_y = ((int) (Math.random() * (COURT_HEIGHT/food.getSize() - 1))) * food.getSize();
				if (snake.foodOn(food.pos_x, food.pos_y)) {
					checkFood();
				}
			}
			repaint();
		}
	}

	private void die() {
		snake.dieColor(getGraphics());
		playing = false;
		finalTime = ticker;
		status.setText("You lose!");
		Game.showScoreboard(snake.getLength());
	}

	private void win() {
		playing = false;
		status.setText("You win!!!");
		finalTime = ticker;
		Game.showScoreboard(snake.getLength());
	}

	private void checkFood() {
		if (snake.foodOn(food.pos_x, food.pos_y) && playing) {
			food.pos_x = ((int) (Math.random() * (COURT_WIDTH/food.getSize() ))) * food.getSize() ;
			food.pos_y = ((int) (Math.random() * (COURT_HEIGHT/food.getSize() ))) * food.getSize() ;
			checkFood();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		food.draw(g);
		if (playing) {
			snake.draw(g);
		} else if (snake.isOB() || snake.selfIntersect()) {
			snake.dieColor(g);
		} else {
			snake.draw(g);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}

	private void pickFood() {
		int rand = (int) (Math.random() * 40);
		if (rand == 4) {
			food = new MasterSplinter(COURT_WIDTH, COURT_HEIGHT);
		} else if (rand == 0 || rand == 1 || rand == 2 || rand == 3 ) {
			food = new MnM(COURT_WIDTH, COURT_HEIGHT);
		} else {
			food = new Mouse(COURT_WIDTH, COURT_HEIGHT);
		}
	}
	public static Replay getReplay() {
		return replay;
	}

	public static int getFinalTime() {
		return finalTime;
	}

}
