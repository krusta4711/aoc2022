package day20;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import com.google.common.collect.Lists;

public class Day20 {

	private List<Number> data;
	private List<Number> origionalOrder;
	private Number zero;

	private record Number(long number, int origStartIndex) {}

	@Test
	public void dayTwenty() throws Exception {
		var inputList = Files.readAllLines(Paths.get("src/test/resources/day20.txt"));
		System.out.println("----- Day 17 part 1 -----");
		System.out.println("result part 1= " + decrypt(inputList, 1, 1));
		System.out.println("result part 2= " + decrypt(inputList, 10, 811_589_153L));
	}

	private long decrypt(List<String> inputList, int iterations, long decryptionKey) {
		data = Lists.newLinkedList();
		origionalOrder = new ArrayList<>(inputList.size());

		// create lists from input
		for (int i = 0; i < inputList.size(); i++) {
			Number m = new Number(Long.parseLong(inputList.get(i)) * decryptionKey, i);
			data.add(m);
			origionalOrder.add(m);
			if (m.number() == 0)
				zero = m;
		}

		// process the data
		IntStream.range(0, iterations).forEach(i -> {
			for (int round = 0; round < origionalOrder.size(); round++) {
				Number currentNumber = origionalOrder.get(round % inputList.size());
				int currentPosition = data.indexOf(currentNumber);
				movePosition(currentPosition, currentNumber);
			}
		});

		// fetch result
		int currentPositionOfZero = data.indexOf(zero);
		return Stream.iterate(currentPositionOfZero, i -> i + 1000).limit(3)
				.mapToLong(i -> data.get((i + 1000) % origionalOrder.size()).number()).sum();
	}

	private void movePosition(int currentPosition, Number currentNumber) {
		data.remove(currentPosition);
		long newPosition = currentPosition + currentNumber.number();
		newPosition = modulo(newPosition);
		data.add((int) newPosition, currentNumber);
	}

	/* Thanks to RyanFoulds. My own modulo had an one-off problem. */
	private long modulo(final long index) {
		final long maxSize = origionalOrder.size() - 1;
		long modulo = index % maxSize;
		if (modulo < 0) {
			modulo += maxSize;
		}
		return modulo;
	}
}
