package day11;

import java.util.List;
import java.util.Optional;
import java.util.function.LongBinaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Lists;

public class Monkey {

	private int name, divide, throwToTrue, throwToFalse, inspect;
	private Optional<Long> operationValue;
	private LongBinaryOperator operation;
	private List<Long> items;

	private static final int BORED = 3;
	private static final LongBinaryOperator MULTIPLY = (a, b) -> (a * b);
	private static final LongBinaryOperator SUM = (a, b) -> (a + b);
	private static final String NUM_REGEX = "\\d+";

	public Monkey(String fullMonkeyString) {
		items = Lists.newArrayList();
		inspect = 0;
		parse(fullMonkeyString);
	}

	private void parse(String fullMonekyString) {
		var inputArray = fullMonekyString.split("\n");
		parseName(inputArray[0]);
		parseStartingItems(inputArray[1]);
		parseOperation(inputArray[2]);
		parseTest(inputArray[3]);
		parseThrowTo(inputArray[4], inputArray[5]);
	}

	public void processRound(Monkey[] allMonkeys, long superModulo, boolean bored) {
		reduceItems(superModulo); // needed for part 2 works also for part 1
		for (long oneItem : items) {
			inspect++;
			long newWorryLevel = operation.applyAsLong(oneItem, operationValue.orElse(oneItem));
			newWorryLevel = bored ? newWorryLevel / BORED : newWorryLevel; // divide only for part 1
			allMonkeys[throwTo(newWorryLevel)].addItem(newWorryLevel);
		}
		items = Lists.newArrayList();
	}

	private void reduceItems(long superModulo) {
		List<Long> reducedItems = items.stream().map(item -> item % superModulo).toList();
		items = reducedItems;
	}

	private void parseName(String nameLine) {
		name = parseInt(nameLine);
	}

	private void parseStartingItems(String aString) {
		Matcher matcher = createNumberMatcher(aString);
		while (matcher.find()) {
			items.add(Long.valueOf(matcher.group()));
		}
	}

	private void parseOperation(String operationLine) {
		operation = operationLine.contains("*") ? MULTIPLY : SUM;
		operationValue = parseLong(operationLine);
	}

	private void parseTest(String testLine) {
		divide = parseInt(testLine);
	}

	private void parseThrowTo(String trueString, String falseString) {
		throwToTrue = parseInt(trueString);
		throwToFalse = parseInt(falseString);
	}

	private Optional<Long> parseLong(String aString) {
		Matcher matcher = createNumberMatcher(aString);
		return matcher.find() ? Optional.of(Long.valueOf(matcher.group())) : Optional.empty();
	}

	private int parseInt(String aString) {
		Matcher matcher = createNumberMatcher(aString);
		matcher.find();
		return Integer.valueOf(matcher.group());
	}

	private Matcher createNumberMatcher(String aString) {
		return Pattern.compile(NUM_REGEX).matcher(aString);
	}

	private int throwTo(long oneItem) {
		return oneItem % divide == 0 ? throwToTrue : throwToFalse;
	}

	public void addItem(long item) {
		items.add(item);
	}

	public int getName() {
		return name;
	}

	public int getInspect() {
		return inspect;
	}

	public int getDivide() {
		return divide;
	}

}
