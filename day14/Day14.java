package day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class Day14 {

	@Test
	public void dayFourteen() throws IOException {

		var inputList = Files.readAllLines(Paths.get("src/test/resources/day14.txt"));

		System.out.println("----- Day 14 part -----");
		var cave1 = new CavePart1(inputList);
		System.out.println("result part 1= " + (cave1.startSandDesaster()));
		var cave2 = new CavePart2(inputList);
		System.out.println("result part 2= " + (cave2.startSandDesaster()));
	}

}
