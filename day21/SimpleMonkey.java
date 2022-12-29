package day21;

public class SimpleMonkey extends Monkey {

	private long number;

	public SimpleMonkey(String name, int number) {
		super(name);
		this.number = number;
	}

	@Override
	public long yell() {
		return this.yell(getNumber());
	}

	@Override
	public long getNumber() {
		return number;
	}

	@Override
	public void setNumber(long number) {
		this.number = number;
	}

}
