package day08;

public class AbsentTree implements Treeable {

	@Override
	public boolean allLeftLower(int startTreeHight) {
		return true;
	}

	@Override
	public boolean allRightLower(int startTreeHight) {
		return true;
	}

	@Override
	public boolean allTopLower(int startTreeHight) {
		return true;
	}

	@Override
	public boolean allBottomLower(int startTreeHight) {
		return true;
	}

	@Override
	public int viewToLeft(int startTreeHight) {
		return 0;
	}

	@Override
	public int viewToRight(int startTreeHight) {
		return 0;
	}

	@Override
	public int viewToTop(int startTreeHight) {
		return 0;
	}

	@Override
	public int viewToBottom(int startTreeHight) {
		return 0;
	}

}
