package day18;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.google.common.collect.Lists;

public class Day18 {

	private record Cube(int x, int y, int z) {}

	private List<Cube> cubes;
	private List<Cube> displacedAir = Lists.newArrayList();
	private Deque<Cube> canditatesQueue = new LinkedList<>();

	private int min;
	private int max = Integer.MIN_VALUE;

	@Test
	public void dayEighteen() throws IOException {
		var inputList = Files.readAllLines(Paths.get("src/test/resources/day18.txt"));

		System.out.println("----- Day 18 part 1 -----");
		cubes = parse(inputList);
		System.out.println("result= " + (cubes.size() * 6 - countAllConnected()));

		System.out.println("----- Day 18 part 2 -----");
		markOuterBoxBorders();
		flood();
		cubes.addAll(findTrappedAir());
		System.out.println("result= " + (cubes.size() * 6 - countAllConnected()));
	}

	private List<Cube> parse(List<String> inputList) {
		return inputList.stream().map(e -> e.split(",")).map(
				array -> new Cube(Integer.parseInt(array[0]), Integer.parseInt(array[1]), Integer.parseInt(array[2])))
				.collect(Collectors.toList());
	}

	private int countAllConnected() {
		return cubes.stream().mapToInt(c -> countOneConnected(c)).sum();
	}

	private int countOneConnected(Cube cube) {
		int cubeConnections = 0;
		for (int i = -1; i <= 1; i += 2) { // -1 and +1
			if (cubes.contains(new Cube(cube.x + i, cube.y, cube.z)))
				cubeConnections++;
			if (cubes.contains(new Cube(cube.x, cube.y + i, cube.z)))
				cubeConnections++;
			if (cubes.contains(new Cube(cube.x, cube.y, cube.z + i)))
				cubeConnections++;
		}
		return cubeConnections;
	}

	private void markOuterBoxBorders() {
		for (Cube oneCube : cubes) {
			max = Math.max(max, oneCube.x());
			max = Math.max(max, oneCube.y());
			max = Math.max(max, oneCube.z());
		}
		max += 1;// add a little bit of air around the droplet
		min = -1; // we know zero is the smallest coordinate
	}

	/** iterate over all coordinates in the marked box **/
	private List<Cube> findTrappedAir() {
		List<Cube> airList = Lists.newArrayList();
		for (int x = min + 1; x < max; x++) {
			for (int y = min + 1; y < max; y++) {
				for (int z = min + 1; z < max; z++) {
					var airCandidate = new Cube(x, y, z);
					if (neitherLavaNorDisplaced(airCandidate))
						airList.add(airCandidate);
				}
			}
		}
		return airList;
	}

	private void flood() {
		canditatesQueue.addLast(new Cube(min, min, min)); // start with smallest corner
		while (!canditatesQueue.isEmpty()) {
			Cube current = canditatesQueue.pollFirst();
			if (neitherLavaNorDisplaced(current)) {
				displacedAir.add(current);
			} else {
				continue; // lava and displaced air is not to be handled (again)
			}

			int x = current.x();
			int y = current.y();
			int z = current.z();

			if (x < max)
				handleNewFloodCandidate(x + 1, y, z);
			if (x > min)
				handleNewFloodCandidate(x - 1, y, z);
			if (y < max)
				handleNewFloodCandidate(x, y + 1, z);
			if (y > min)
				handleNewFloodCandidate(x, y - 1, z);
			if (z < max)
				handleNewFloodCandidate(x, y, z + 1);
			if (z > min)
				handleNewFloodCandidate(x, y, z - 1);
		}
	}

	private void handleNewFloodCandidate(int x, int y, int z) {
		Cube newCandiate = new Cube(x, y, z);
		if (neitherLavaNorDisplaced(newCandiate)) {
			canditatesQueue.addLast(newCandiate);
		}
	}

	private boolean neitherLavaNorDisplaced(Cube current) {
		return !cubes.contains(current) && !displacedAir.contains(current);
	}
}
