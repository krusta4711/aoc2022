package day21;

import java.beans.PropertyChangeEvent;
import java.util.function.LongBinaryOperator;

public class MathMonkey extends Monkey implements ObserverMonkey {

	private static final LongBinaryOperator MULTIPLY = (a, b) -> (a * b);
	private static final LongBinaryOperator DIVIDE = (a, b) -> (a / b);
	private static final LongBinaryOperator SUM = (a, b) -> (a + b);
	private static final LongBinaryOperator DIFFERENCE = (a, b) -> (a - b);

	private Monkey monkeyA, monkeyB;
	private boolean aYelled = false;
	private boolean bYelled = false;
	private LongBinaryOperator operator;

	public MathMonkey(String name, String operation) {
		super(name);
		operator = fetchOperation(operation);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getNewValue() == monkeyA)
			aYelled = true;
		else if (evt.getNewValue() == monkeyB)
			bYelled = true;
		else
			throw new IllegalArgumentException("Got event from unexpected monkey: " + evt.getNewValue());

		if (aYelled && bYelled)
			this.yell();
	}

	@Override
	public long getNumber() {
		return operator.applyAsLong(monkeyA.getNumber(), monkeyB.getNumber());
	}

	@Override
	public void setNumber(long number) {
		throw new IllegalStateException("setNumber is not provided for MathMonkeys");
	}

	@Override
	public long yell() {
		return yell(operator.applyAsLong(monkeyA.getNumber(), monkeyB.getNumber()));
	}

	@Override
	public void setMonkeyA(Monkey monkeyA) {
		this.monkeyA = monkeyA;
		monkeyA.addPropertyChangeListener(this);
	}

	@Override
	public void setMonkeyB(Monkey monkeyB) {
		this.monkeyB = monkeyB;
		monkeyB.addPropertyChangeListener(this);
	}

	private LongBinaryOperator fetchOperation(String operation) {
		return switch (operation) {
			case "*" -> MULTIPLY;
			case "/" -> DIVIDE;
			case "+" -> SUM;
			case "-", "=" -> DIFFERENCE;
			default -> throw new IllegalArgumentException("Unexpected operation: " + operation);
		};
	}
}
