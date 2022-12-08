package day02;

public class RockPaperScissorPart2 {

	public static final String WIN = "Z", DRAW = "Y", LOST = "X";
	public static final int WIN_VALUE = 6, DRAW_VALUE = 3, LOST_VALUE = 0;

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
		case "A" -> RPS.ROCK;
		case "B" -> RPS.PAPER;
		case "C" -> RPS.SCISSOR;

		default -> throw new IllegalArgumentException("Unexpected value: " + shortCut);
		};
	}

	public static int result(RPS they, String todo) {
		return switch (they) {
		case ROCK -> todo.equals(WIN) ? RPS.PAPER.getValue() + WIN_VALUE
				: todo.equals(LOST) ? RPS.SCISSOR.getValue() + LOST_VALUE : RPS.ROCK.getValue() + DRAW_VALUE;
		case PAPER -> todo.equals(WIN) ? RPS.SCISSOR.getValue() + WIN_VALUE
				: todo.equals(LOST) ? RPS.ROCK.getValue() + LOST_VALUE : RPS.PAPER.getValue() + DRAW_VALUE;
		case SCISSOR -> todo.equals(WIN) ? RPS.ROCK.getValue() + WIN_VALUE
				: todo.equals(LOST) ? RPS.PAPER.getValue() + LOST_VALUE : RPS.SCISSOR.getValue() + DRAW_VALUE;
		};
	}

}
