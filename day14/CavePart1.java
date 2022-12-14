package day14;

import java.util.List;

public class CavePart1 extends Cave {

	public CavePart1(List<String> input) {
		super(input);
	}

	@Override
	boolean isOutOfEdges(Point point) {
		return point.x() < getLowestX() || point.x() > getHighestX() || point.y() > getHighestY();
	}

	@Override
	boolean isBottom(int currentY) {
		return false;
	}

}
