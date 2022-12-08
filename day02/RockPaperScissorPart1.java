package day02;

public class RockPaperScissorPart1 {

	public static final int WIN = 6, DRAW = 3, LOST = 0;

	public enum RPS {
		ROCK(1), PAPER(2), SCISSOR(3);

		private int value;

		RPS(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	};

	public static RPS of(String shortCut) {
		return switch (shortCut) {
		case "A", "X" -> RPS.ROCK;
		case "B", "Y" -> RPS.PAPER;
		case "C", "Z" -> RPS.SCISSOR;

		default -> throw new IllegalArgumentException("Unexpected value: " + shortCut);
		};
	}

	public static int result(RPS they, RPS me) {
		return switch (they) {
		case ROCK -> me == RPS.ROCK ? DRAW : me == RPS.PAPER ? WIN : LOST;
		case PAPER -> me == RPS.PAPER ? DRAW : me == RPS.ROCK ? LOST : WIN;
		case SCISSOR -> me == RPS.SCISSOR ? DRAW : me == RPS.ROCK ? WIN : LOST;
		};
	}

}
