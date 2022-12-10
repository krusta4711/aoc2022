package day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class Day10 {

	private int registerX = 1;
	private int cycle = 0, resultPart1 = 0, pos = 0;
	private String resultPart2 = "";

	@Test
	public void dayTen() throws IOException {
		var inputList = Files.readAllLines(Paths.get("src/test/resources/day10.txt"));

		for (String oneLine : inputList) {
			var command = oneLine.split(" ");
			if ("noop".equals(command[0])) {
				doCycle();
			} else {
				doCycle();
				doCycle();
				registerX += Integer.valueOf(command[1]);
			}
		}

		System.out.println("----- Day 10 -----");
		System.out.println("result part 1= " + resultPart1);
		System.out.println("result part 2:");
		System.out.println(resultPart2);
	}

	private void doCycle() {
		if (cycle > 0 && cycle % 40 == 0) {
			pos = 0;
			resultPart2 += "\n";
		}
		resultPart2 += //
				pos == registerX || pos == registerX - 1 || pos == registerX + 1 //
						? "#"
						: ".";
		pos++;
		cycle++;
		if (cycle == 20 || (cycle - 20) % 40 == 0) {
			resultPart1 += cycle * registerX;
		}
	};
}
