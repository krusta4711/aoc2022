package day23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class Day23 {

	private enum Direction {
		NORTH, SOUTH, WEST, EAST
	};

	private static Direction[] DIRECTIONS = { Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST };

	private record Point(int x, int y) {}
	private record Pair(Point oldPosition, Point proposedPosition) {}

	private Set<Point> currentPositions = new HashSet<>();
	private Set<Point> nextPositions = new HashSet<>();
	private Map<Point, List<Pair>> proposedPositions;

	@Test
	public void dayTwentyThree() throws IOException {

		var inputList = Files.readAllLines(Paths.get("src/test/resources/day23.txt"));

		initPositions(inputList);
		long resultPart1 = -1;
		int resultPart2 = -1;
		int i = 0;
		loop: while (true) {
			proposedPositions = Maps.newHashMap();
			nextPositions = Sets.newHashSet();
			propose(i);
			if (proposedPositions.size() == 0) {
				resultPart2 = i + 1;
				break loop;
			}
			move();
			if (i == 9)
				resultPart1 = calculateEmptyGrounds();
			i++;
		}
		System.out.println("----- Day 25 -----");
		System.out.println("result part 1=" + resultPart1);
		System.out.println("result part 2=" + resultPart2);
	}

	private void propose(int directionIndex) {
		currentPositions.stream().forEach(point -> proposeOneElve(point, directionIndex));
	}

	private void proposeOneElve(Point currentPosition, int directionIndex) {
		if (areAllPositionsEmpty(currentPosition)) {
			// the elf stays
			nextPositions.add(currentPosition);
			return;
		}
		Point newPosition = null;
		for (int i = 0; i < 4; i++) {
			Direction direction = DIRECTIONS[(i + directionIndex) % 4];
			newPosition = getProposedSpot(currentPosition, direction);
			if (newPosition != null) {
				addToProposedPosition(currentPosition, newPosition);
				break;
			}
		}
		if (newPosition == null)
			// nothing free to move to, stay at old position
			nextPositions.add(currentPosition);
	}

	private void move() {
		for (Map.Entry<Point, List<Pair>> entry : proposedPositions.entrySet()) {
			var pairsAtPosition = entry.getValue();

			if (pairsAtPosition.size() == 1) {
				// no other elf at the position: the elf can move
				nextPositions.add(pairsAtPosition.get(0).proposedPosition());
			} else {
				// more than one elf for the position: the elves stay
				for (Pair pair : pairsAtPosition) {
					nextPositions.add(pair.oldPosition());
				}
			}
		}
		currentPositions = nextPositions;
	}

	private void addToProposedPosition(Point oldPoint, Point newPoint) {
		var newPositionCandidate = proposedPositions.get(newPoint);

		if (newPositionCandidate == null)
			newPositionCandidate = Lists.newArrayList();

		newPositionCandidate.add(new Pair(oldPoint, newPoint));
		proposedPositions.put(newPoint, newPositionCandidate);
	}

	private boolean areAllPositionsEmpty(Point elf) {
		int x = elf.x();
		int y = elf.y();

		return possibleNorth(x, y) != null && possibleSouth(x, y) != null && possibleWest(x, y) != null
				&& possibleEast(x, y) != null;

	}

	/** returns the proposed spot or null if none **/
	private Point getProposedSpot(Point point, Direction direction) {
		int x = point.x();
		int y = point.y();

		return switch (direction) {
			case NORTH -> possibleNorth(x, y);
			case SOUTH -> possibleSouth(x, y);
			case WEST -> possibleWest(x, y);
			case EAST -> possibleEast(x, y);
		};
	}

	private Point possibleNorth(int x, int y) {
		Point a, b, c, result = null;
		a = new Point(x - 1, y - 1);
		b = new Point(x, y - 1);
		c = new Point(x + 1, y - 1);
		if (areSpotsEmpty(a, b, c))
			result = new Point(x, y - 1);

		return result;
	}

	private Point possibleSouth(int x, int y) {
		Point a, b, c, result = null;
		a = new Point(x - 1, y + 1);
		b = new Point(x, y + 1);
		c = new Point(x + 1, y + 1);
		if (areSpotsEmpty(a, b, c))
			result = new Point(x, y + 1);

		return result;
	}

	private Point possibleWest(int x, int y) {
		Point a, b, c, result = null;
		a = new Point(x - 1, y - 1);
		b = new Point(x - 1, y);
		c = new Point(x - 1, y + 1);
		if (areSpotsEmpty(a, b, c))
			result = new Point(x - 1, y);

		return result;
	}

	private Point possibleEast(int x, int y) {
		Point a, b, c, result = null;
		a = new Point(x + 1, y - 1);
		b = new Point(x + 1, y);
		c = new Point(x + 1, y + 1);
		if (areSpotsEmpty(a, b, c))
			result = new Point(x + 1, y);

		return result;
	}

	private boolean areSpotsEmpty(Point a, Point b, Point c) {
		return !currentPositions.contains(a) && !currentPositions.contains(b) && !currentPositions.contains(c);
	}

	private void initPositions(List<String> list) {
		for (int y = 0; y < list.size(); y++) {
			String oneRow = list.get(y);
			for (int x = 0; x < oneRow.length(); x++) {
				String oneChar = oneRow.substring(x, x + 1);
				if (oneChar.equals("#")) {
					currentPositions.add(new Point(x, y));
				}
			}
		}
	}

	private long calculateEmptyGrounds() {
		int smallestX = currentPositions.stream().mapToInt(Point::x).min().getAsInt();
		int biggestX = currentPositions.stream().mapToInt(Point::x).max().getAsInt();
		int smallestY = currentPositions.stream().mapToInt(Point::y).min().getAsInt();
		int biggestY = currentPositions.stream().mapToInt(Point::y).max().getAsInt();

		long xAxis = IntStream.range(smallestX, biggestX + 1).count();
		long yAxis = IntStream.range(smallestY, biggestY + 1).count();
		long squareSize = xAxis * yAxis;

		return squareSize - currentPositions.size();
	}

	private void printGrove() {
		int smallestX = currentPositions.stream().mapToInt(Point::x).min().getAsInt();
		int biggestX = currentPositions.stream().mapToInt(Point::x).max().getAsInt();
		int smallestY = currentPositions.stream().mapToInt(Point::y).min().getAsInt();
		int biggestY = currentPositions.stream().mapToInt(Point::y).max().getAsInt();

		for (int y = smallestY; y <= biggestY; y++) {
			System.out.println();
			for (int x = smallestX; x <= biggestX; x++) {
				Point p = new Point(x, y);
				System.out.print(currentPositions.contains(p) ? "#" : ".");
			}
		}
	}
}
