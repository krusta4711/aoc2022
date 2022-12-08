package day08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class Day08 {

	@Test
	public void dayEight() throws IOException {

		var inputList = Files.readAllLines(Paths.get("src/test/resources/day08.txt"));

		System.out.println("----- Day 8 part 1 and 2 ----");
		var wood = new Wood(inputList);
		System.out.println("result part 1= " + wood.countPart1());
		System.out.println("result part 2= " + wood.countPart2());
		System.out.println("-----------------------------");
//		wood.print();
	}

}
