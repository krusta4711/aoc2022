package day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import day02.RockPaperScissorPart2.RPS;

public class Day02 {

	@Test
	public void dayTwoPartOne() throws IOException {

		var inputList = Files.readAllLines(Paths.get("src/test/resources/day02.txt"));
		System.out.println("----- Day 02 part 1 ----");
		int result = 0;

		for (String oneLine : inputList) {
			var split = oneLine.split(" ");
			RockPaperScissorPart1.RPS they = RockPaperScissorPart1.of(split[0]);
			RockPaperScissorPart1.RPS me = RockPaperScissorPart1.of(split[1]);
			result += RockPaperScissorPart1.result(they, me) + me.getValue();
		}

		System.out.println("result part 1= " + result);
	}

	@Test
	public void dayTwoPartTwo() throws IOException {

		var inputList = Files.readAllLines(Paths.get("src/test/resources/day02.txt"));
		System.out.println("----- Day 02 part 2 ----");
		int result = 0;

		for (String oneLine : inputList) {
			var split = oneLine.split(" ");
			RPS they = RockPaperScissorPart2.of(split[0]);
			String me = split[1];
			result += RockPaperScissorPart2.result(they, me);
		}

		System.out.println("result part 2= " + result);

	}

}
