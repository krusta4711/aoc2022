package day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

public class Day10 {

	private int registerX, cycle, result, pos;

	@Test
	public void dayTenPart() throws IOException {

		var inputList = Files.readAllLines(Paths.get("src/test/resources/day10.txt"));

		dayTenMainLoop("1", inputList, doCyclePart1);
		System.out.println("result part 1= " + result);
		dayTenMainLoop("2", inputList, doCyclePart2);
	}

	private void dayTenMainLoop(String part, List<String> inputList, Runnable doCycle) throws IOException {
		init();
		System.out.println("----- Day 10 " + "part " + part + " -----");
		for (String oneLine : inputList) {
			var command = oneLine.split(" ");
			if ("noop".equals(command[0])) {
				doCycle.run();
			} else {
				doCycle.run();
				doCycle.run();
				registerX += Integer.valueOf(command[1]);
			}
		}
	}

	private Runnable doCyclePart1 = () -> {
		cycle++;
		if (cycle == 20 || (cycle - 20) % 40 == 0) {
			result += cycle * registerX;
		}
	};

	private Runnable doCyclePart2 = () -> {
		if (cycle > 0 && cycle % 40 == 0) {
			pos = 0;
			System.out.println(); // new line
		}
		System.out.print(pos == registerX || pos == registerX - 1 || pos == registerX + 1 //
				? "#"
				: ".");
		pos++;
		cycle++;
	};

	private void init() {
		registerX = 1;
		cycle = result = pos = 0;
	}
}
