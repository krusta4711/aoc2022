package day14;

import java.util.List;

public class CavePart2 extends Cave {

	public CavePart2(List<String> input) {
		super(input);
	}

	@Override
	boolean isAbyssReached(Point point) {
		return false;
	}

	@Override
	boolean isBottom(int y) {
		return y == getHighestY() + 2 ? true : false;
	}

}
