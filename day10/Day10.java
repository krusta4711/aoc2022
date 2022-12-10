package day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class Day10 {

	public static final String NOOP = "noop";
	public static final String ADD_X = "addx";

	private int registerX = 1;
	private int cycle = 0;
	private int result = 0;

	@Test
	public void dayTenPartOne() throws IOException {

		var inputList = Files.readAllLines(Paths.get("src/test/resources/day10.txt"));

		System.out.println("----- Day 10 part 1 -----");
		for (String oneLine : inputList) {
			var command = oneLine.split(" ");
			if (NOOP.equals(command[0])) {
				doCyclePart1();
			} else {
				doCyclePart1();
				doCyclePart1(); // addx to be called twice
				registerX += Integer.valueOf(command[1]);
			}
		}
		System.out.println("result part 1= " + result);
	}

	private void doCyclePart1() {
		cycle++;
		if (cycle == 20 || (cycle - 20) % 40 == 0) {
			result += cycle * registerX;
		}
	}

	private int pos = 0;

	@Test
	public void dayTenPartTwo() throws IOException {

		var inputList = Files.readAllLines(Paths.get("src/test/resources/day10.txt"));

		System.out.println("----- Day 10 part 2 -----");
		for (String oneLine : inputList) {
			var command = oneLine.split(" ");
			if (NOOP.equals(command[0])) {
				doCyclePart2();
			} else {
				doCyclePart2();
				doCyclePart2(); // addx to be called twice
				registerX += Integer.valueOf(command[1]);
			}
		}

	}

	private void doCyclePart2() {
		if (cycle > 0 && cycle % 40 == 0) {
			pos = 0;
			System.out.println(); // new line
		}
		System.out.print(pos == registerX || pos == registerX - 1 || pos == registerX + 1 //
				? "#"
				: ".");

		pos++;
		cycle++;
	}

}
