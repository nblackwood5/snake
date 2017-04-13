import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Replay {
	public ArrayList<Ghost> list;

	public Replay() {
		list = new ArrayList<Ghost>();
		list.add(new Ghost(0,0));
	}

	public void record(int x, int y) {
		list.add(new Ghost(x, y));
	}

	public void draw(Graphics g) {
		for (int i = 0; (i <= ReplayCourt.getTime() && i <= GameCourt.getFinalTime()); i++) {
			if (i == GameCourt.getFinalTime()) {
				for (int z = 0; z <= GameCourt.getFinalTime(); z++) {
					g.setColor(Color.RED);
					g.fillRect(list.get(z).getXCord(), list.get(z).getYCord(), Snake.SIZE, Snake.SIZE);
				}
			} else {
				if (i == ReplayCourt.getTime()) {
					g.setColor(Color.black);
				} else {
					g.setColor(Color.GRAY);
				}
				g.fillRect(list.get(i).getXCord(), list.get(i).getYCord(), Snake.SIZE, Snake.SIZE);
			}
		}
	}

	public void reset() {
		list.clear();
		list.add(new Ghost(0,0));
	}
}
