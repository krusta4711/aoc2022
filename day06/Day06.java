package day06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class Day06 {

	@Test
	public void daySixPart() throws IOException {

		String input = Files.readAllLines(Paths.get("src/test/resources/day06.txt")).get(0);

		System.out.println("----- Day 6  -----");
		System.out.println("result part 1= " + findDistinctChars(input, 4));
		System.out.println("result part 2= " + findDistinctChars(input, 14));
		System.out.println("------------------");
	}

	private int findDistinctChars(String input, int charAmount) {
		int result = -1;
		for (int i = 0; i < input.length() - charAmount; i++) {
			String substring = input.substring(i, i + charAmount);
			if (substring.chars().distinct().count() == charAmount) {
				result = i + charAmount;
				break;
			}
		}
		return result;
	}
}
