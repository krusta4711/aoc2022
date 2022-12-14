package day14;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import com.google.common.collect.Sets;

public abstract class Cave {

	private Set<Point> blockedCoordinates;
	private int lowestX, highestX, highestY;

	record Point(int x, int y) {};

	public Cave(List<String> input) {
		lowestX = Integer.MAX_VALUE;
		highestX = highestY = Integer.MIN_VALUE;
		blockedCoordinates = Sets.newHashSet();
		parseInput(input);
	}

	abstract boolean isOutOfEdges(Point point);

	abstract boolean isBottom(int currentY);

	public int startSandDesaster() {
		Point start = new Point(500, 0);
		int count = 0;
		boolean tryNext = true;
		Point sandLocation;
		do {
			sandLocation = start;
			sandLocation = nextLandingSpot(sandLocation);
			if (sandLocation == null) {
				tryNext = false; // no more locations: end reached part 1
			}
			if (sandLocation != null) {
				count++;
				blockedCoordinates.add(sandLocation);
				if (sandLocation.equals(start)) {
					tryNext = false; // point cannot move anymore: end reach part 2
				}
			}
		} while (tryNext);
		return count;
	}

	private Point nextLandingSpot(Point currentPoint) {
		Point landingSpot = currentPoint;
		while (true) {
			Point interims = nextFreeCoordinate(landingSpot);
			if (interims != null && isOutOfEdges(interims))
				return null;
			if (interims == null)
				return landingSpot;

			landingSpot = interims;
		}
	}

	/** @return next free coordinate or null if there is no next **/
	private Point nextFreeCoordinate(Point currentPoint) {
		int currentX = currentPoint.x();
		int currentY = currentPoint.y();

		if (isBottom(currentY + 1))
			return null; // floor of cave is reached (part 2)

		// one step down
		Point nextPoint = new Point(currentX, currentY + 1);
		if (!blockedCoordinates.contains(nextPoint)) {
			return nextPoint;
		}

		// step bottom left
		nextPoint = new Point(currentX - 1, currentY + 1);
		if (!blockedCoordinates.contains(nextPoint)) {
			return nextPoint;
		}

		// step bottom right
		nextPoint = new Point(currentX + 1, currentY + 1);
		if (!blockedCoordinates.contains(nextPoint)) {
			return nextPoint;
		}

		return null; // nothing to fall to anymore
	}

	private void parseInput(List<String> input) {
		List<String[]> tupleList = input.stream().map(e -> e.split(" -> ")).toList();
		for (String[] oneLine : tupleList) {
			Point previousPoint = null;
			for (int i = 0; i < oneLine.length; i++) {
				int[] p = Arrays.stream(oneLine[i].split(",")).mapToInt(Integer::parseInt).toArray();
				Point point = new Point(p[0], p[1]);
				createRocks(previousPoint, point);
				setCaveEdges(point);
				previousPoint = point;
			}
		}
	}

	private void createRocks(Point previousPoint, Point point) {
		if (previousPoint == null)
			return;
		if (previousPoint.x() == point.x()) {
			int[] sortedY = sort(previousPoint.y(), point.y()); // we need to know the order
			IntStream.range(sortedY[0], sortedY[1] + 1).forEach(y -> blockedCoordinates.add(new Point(point.x(), y)));
		} else {
			int[] sortedX = sort(previousPoint.x(), point.x()); // we need to know the order
			IntStream.range(sortedX[0], sortedX[1] + 1).forEach(x -> blockedCoordinates.add(new Point(x, point.y())));
		}
	}

	private void setCaveEdges(Point point) {
		lowestX = point.x() < lowestX ? point.x() : lowestX;
		highestX = point.x() > highestX ? point.x() : highestX;
		highestY = point.y() > highestY ? point.y() : highestY;
	}

	private int[] sort(int a, int b) {
		int[] array = new int[] { a, b };
		Arrays.sort(array);
		return array;
	}

	public int getLowestX() {
		return lowestX;
	}

	public int getHighestX() {
		return highestX;
	}

	public int getHighestY() {
		return highestY;
	}
}
