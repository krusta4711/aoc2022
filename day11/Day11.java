package day11;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import vkruse.adventofcode.helper.FileToList;

public class Day11 {

	@Test
	public void dayEleven() {
		processPart1();
		processPart2();
	}

	private void processPart1() {
		List<String> monkeyStrings = FileToList.fileToListSeparatedByNewLines("src/test/resources/day11.txt");
		var monkeys = monkeyStrings.stream().map(Monkey::new).toArray(Monkey[]::new);
		// needed for part 2 but works with part 1 too
		int superModulo = Arrays.stream(monkeys).mapToInt(Monkey::getDivide).reduce(1, (a, b) -> a * b);

		System.out.println("----- Day 11 part 1 ----");
		IntStream.range(0, 20)
				.forEach(i -> Arrays.stream(monkeys).forEach(m -> m.processRound(monkeys, superModulo, true)));
		var inspected = Arrays.stream(monkeys).mapToInt(monkey -> monkey.getInspect()).sorted().toArray();
		System.out.println("result part1= " + (inspected[monkeys.length - 1] * inspected[monkeys.length - 2]));
	}

	private void processPart2() {
		List<String> monkeyStrings = FileToList.fileToListSeparatedByNewLines("src/test/resources/day11.txt");
		var monkeys = monkeyStrings.stream().map(Monkey::new).toArray(Monkey[]::new);
		// needed for part 2
		int superModulo = Arrays.stream(monkeys).mapToInt(Monkey::getDivide).reduce(1, (a, b) -> a * b);

		System.out.println("----- Day 11 part 2 ----");
		IntStream.range(0, 10_000)
				.forEach(i -> Arrays.stream(monkeys).forEach(m -> m.processRound(monkeys, superModulo, false)));
		var inspected = Arrays.stream(monkeys).mapToLong(monkey -> monkey.getInspect()).sorted().toArray();
		System.out.println("result part 2= " + (inspected[monkeys.length - 1] * inspected[monkeys.length - 2]));
	}
}
