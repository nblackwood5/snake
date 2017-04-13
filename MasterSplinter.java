import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MasterSplinter extends Food {
	
	public static int INIT_X = GameCourt.COURT_WIDTH / 2 - SIZE;
	public static int INIT_Y = GameCourt.COURT_HEIGHT / 2 - SIZE;
	public static final int INIT_VEL_X = 0;
	public static final int INIT_VEL_Y = 0;
	public static final String img_file = "master.jpg";
	private static BufferedImage img; 
	
	public MasterSplinter(int courtWidth, int courtHeight) {
		super(courtWidth, courtHeight);
		changeSize(40);
		this.width = SIZE;
		this.height = SIZE;
		this.inc = 200;

		try {
			if (img == null) {
				img = ImageIO.read(new File(img_file));
			}
		} catch (IOException e) {
			System.out.println("Internal Error:" + e.getMessage());
		}
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, pos_x, pos_y, width, height, null);
	}
}
