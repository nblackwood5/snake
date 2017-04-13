import java.util.Comparator;

public class Comparer implements Comparator<Score> {
	public int compare(Score s1, Score s2) {
		int hiScore1 = s1.getScore();
		int hiScore2 = s2.getScore();

		if (hiScore1 > hiScore2){
			return -1;
		}else if (hiScore1 < hiScore2){
			return 1;
		}else{
			return 0;
		}
	}
}