import java.util.*;
import java.io.*;

public class Scorer {
	private ArrayList<Score> highScores;
	private static final String FILE = "hiscores.txt";
	ObjectOutputStream o = null;
	ObjectInputStream i = null;
	public Scorer() {
		highScores = new ArrayList<Score>();
	}

	public ArrayList<Score> getHighScores() {
		load();
		sort();
		return highScores;
	}

	private void sort() {
		Comparer c = new Comparer();
		Collections.sort(highScores, c);
	}

	public void add(String n, int s) {
		load();
		highScores.add(new Score(n, s));
		update();
	}


	public void load() {
		try {
			i = new ObjectInputStream(new FileInputStream(FILE));
			highScores = (ArrayList<Score>) i.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("HighScores file created");
		} catch (IOException e) {
			System.out.println("First highscore");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found while loading: " + e.getMessage());
		} finally {
			try {
				if (o != null) {
					o.flush();
					o.close();
				}
			} catch (IOException e) {
				System.out.println("IO Error loading: " + e.getMessage());
			}
		}
	}

	public void update() {
		try {
			o = new ObjectOutputStream(new FileOutputStream(FILE));
			o.writeObject(highScores);
		} catch (FileNotFoundException e) {
			System.out.println("File not foound ot update: " + e.getMessage() + ",the program will try and make a new file");
		} catch (IOException e) {
			System.out.println("IO error while updating: " + e.getMessage());
		} finally {
			try {
				if (o != null) {
					o.flush();
					o.close();
				}
			} catch (IOException e) {
				System.out.println("Error while updating: " + e.getMessage());
			}
		}
	}

	public String getString() {
		String s = "<HTML> HIGH SCORES <br><br><br>Press replay at the bottom to replay your snake's path.<br><br>"
				+ " Hit exit then reset "
				+ "to continue playing<br><br>";
		int limit = 20;
		ArrayList<Score> hs = getHighScores();
		int size = hs.size();
		size = Math.min(size, limit);
		for (int i = 0; i < size; i++) {
			if (i == 0) {
				s += "<html><table><tr>" + "<th>Rank</th>" + "<th>Name</th>" + "<th>Score</th>" + "<th>Date</th></tr>" + 
							"<tr><td>" + (i + 1)  + "</td>" + 
							"<td>" + hs.get(i).getNickName() + "</td>" + 
							"<td>" + hs.get(i).getScore() +
							"<td>" + hs.get(i).getDateString() + "</td></tr>";
			} else if (i == size - 1) {
				s += "<html><tr><td>" + (i + 1)  + "</td>" + 
						"<td>" + hs.get(i).getNickName() + "</td>" + 
						"<td>" + hs.get(i).getScore() + "</td>" + 
						"<td>" + hs.get(i).getDateString() + "</td></tr></table>";
			} else {
				s += "<html><tr><td>" + (i + 1)  + "</td>" + 
						"<td>" + hs.get(i).getNickName() + "</td>" + 
						"<td>" + hs.get(i).getScore() +
						"<td>" + hs.get(i).getDateString() + "</td></tr>";
			}

		}
		return s;
	}

}

