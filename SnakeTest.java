import static org.junit.Assert.*;

import org.junit.Test;

public class SnakeTest {

	Snake s = new Snake(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT);

	//getLength
	@Test
	public void testLengthInitial() {
		assertEquals(s.getLength(), 1);
	}

	@Test
	public void testLengthNull() {
		Snake s2 = null;
		try{
			s2.getLength();
			fail("null snake");
		} catch (NullPointerException e) {

		} finally {

		}
	}

	@Test
	public void testLengthExtended() {
		Snake s2 = new Snake(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT);
		s2.extend(5);
		assertEquals(s2.getLength(), 6);
	}

	//getCoverage 
	@Test
	public void testGetCoverage() {
		Snake s2 = new Snake(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT);
		s2.extend(10);
		assertEquals(s2.getCoverage(), 1);
		s2.xy[0][0] = 10;
		assertEquals(s2.getCoverage(), 2);
	}

	@Test
	public void testCoverageNull() {
		Snake s2 = null;
		try{
			s2.getCoverage();
			fail("null snake");
		} catch (NullPointerException e) {

		} finally {

		}
	}


	//isOB
	@Test
	public void testisOB() {
		Snake s2 = new Snake(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT);
		s2.extend(2);
		s2.direction = Direction.RIGHT;
		s2.move();
		assertFalse(s2.isOB());
		s2.xy[0][0] = 402;
		assertTrue(s2.isOB());
		s2.xy[0][0] = -10;
		assertTrue(s2.isOB());
	}

	//equals
	@Test
	public void testEquals() {

		Snake s2 = new Snake(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT);
		assertTrue(s2.equals(s));
		s2.xy[0][0] = 5;
		assertFalse(s2.equals(s));
		s.xy[0][0] = 5;
		assertTrue(s2.equals(s));
		s2.extend(1);
		assertFalse(s2.equals(s));

		Snake s3 = new Snake(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT);
		s3.extend(2);
		s3.direction = Direction.RIGHT;
		s3.move();
		Snake s4 = new Snake(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT);
		s4.extend(2);
		s4.direction = Direction.RIGHT;
		s4.move();
		assertTrue(s3.equals(s4));
		s4.move();
		assertFalse(s3.equals(s4));
	}

	//intersect
	@Test
	public void testIntersect() {
		Snake s2 = new Snake(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT);
		s2.extend(2);
		s2.xy[0][0] = 3;
		s2.xy[0][1] = 2;
		s2.xy[0][2] = 1;
		Food food = new MnM(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT);
		food.pos_x = 3;
		food.pos_y = 0;
		assertTrue(s2.intersects(food));
	}

	//getx,y , size
	@Test
	public void testGetters() {
		s.extend(2);
		s.xy[0][0] = 3;
		s.xy[0][1] = 2;
		s.xy[0][2] = 1;
		s.xy[1][2] = 100;
		assertEquals(3, s.getX(0));
		assertEquals(100, s.getY(2));
		assertEquals(10, Snake.getSize());
	}

	//selfintersect, 
	@Test
	public void testSelf() {
		Snake s2 = new Snake(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT);
		s2.extend(2);
		s2.xy[0][0] = 3;
		s2.xy[0][1] = 2;
		s2.xy[0][2] = 1;
		//3,0 2,0 1,0
		s2.xy[0][0] = 2;
		assertTrue(s2.selfIntersect());
		//5,0 2,0 1,0
		s2.xy[0][0] = 5;
		assertFalse(s2.selfIntersect());

	}

	//foodon

	@Test
	public void testFoodOn() {
		Snake s2 = new Snake(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT);
		s2.extend(2);
		s2.xy[0][0] = 3;
		s2.xy[0][1] = 2;
		s2.xy[0][2] = 1;
		//3,0 2,0 1,0
		Food food = new Mouse(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT);
		food.pos_x = 2;
		food.pos_y = 0;
		assertTrue(s2.foodOn(food.pos_x, food.pos_y));
		food.pos_x = 2;
		food.pos_y = 2;
		assertFalse(s2.foodOn(food.pos_x, food.pos_y));
		food.pos_x = -2;
		assertFalse(s2.foodOn(food.pos_x, food.pos_y));
	}

	@Test
	public void testMove() {
		Snake s2 = new Snake(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT);
		s2.extend(2);
		s2.xy[0][0] = 0;
		s2.xy[0][1] = 0;
		s2.xy[0][2] = 0;
		s2.direction = Direction.RIGHT;
		s2.move();
		//10,0 0,0 0,0
		assertEquals(s2.getX(0), 10);
		assertEquals(s2.getY(0), 0);
		assertEquals(s2.getX(1), 0);
		assertEquals(s2.getY(1), 0);
		s2.direction = Direction.DOWN;
		s2.move();
		//10,10 10,0 0,0
		assertEquals(s2.getX(0), 10);
		assertEquals(s2.getY(0), 10);
		assertEquals(s2.getX(1), 10);
		assertEquals(s2.getY(1), 0);
	}
}
