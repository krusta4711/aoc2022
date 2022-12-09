package day08;

public class Tree implements Treeable {

	private int height;
	private Treeable left, right, top, bottom;

	public Tree(int height) {
		this.height = height;
		left = new AbsentTree();
		right = new AbsentTree();
		top = new AbsentTree();
		bottom = new AbsentTree();
	}

	public boolean allLeftLower() {
		return left.allLeftLower(height);
	}

	public boolean allRightLower() {
		return right.allRightLower(height);
	}

	public boolean allTopLower() {
		return top.allTopLower(height);
	}

	public boolean allBottomLower() {
		return bottom.allBottomLower(height);
	}

	@Override
	public boolean allLeftLower(int startTreeHight) {
		return isLower(startTreeHight) && left.allLeftLower(startTreeHight);
	}

	@Override
	public boolean allRightLower(int startTreeHight) {
		return isLower(startTreeHight) && right.allRightLower(startTreeHight);
	}

	@Override
	public boolean allTopLower(int startTreeHight) {
		return isLower(startTreeHight) && top.allTopLower(startTreeHight);
	}

	@Override
	public boolean allBottomLower(int startTreeHight) {
		return isLower(startTreeHight) && bottom.allBottomLower(startTreeHight);
	}

	private boolean isLower(int startTreeHight) {
		return height < startTreeHight;
	}

	@Override
	public int viewToLeft(int startTreeHight) {
		return isHigherOrSameHight(startTreeHight) ? 1 : left.viewToLeft(startTreeHight) + 1;
	}

	@Override
	public int viewToRight(int startTreeHight) {
		return isHigherOrSameHight(startTreeHight) ? 1 : right.viewToRight(startTreeHight) + 1;
	}

	@Override
	public int viewToTop(int startTreeHight) {
		return isHigherOrSameHight(startTreeHight) ? 1 : top.viewToTop(startTreeHight) + 1;
	}

	@Override
	public int viewToBottom(int startTreeHight) {
		return isHigherOrSameHight(startTreeHight) ? 1 : bottom.viewToBottom(startTreeHight) + 1;
	}

	public int scenicScore() {
		int viewToLeft = left.viewToLeft(height);
		int viewToRight = right.viewToRight(height);
		int viewToTop = top.viewToTop(height);
		int viewToBottom = bottom.viewToBottom(height);

		return viewToLeft * viewToRight * viewToTop * viewToBottom;
	}

	private boolean isHigherOrSameHight(int startHeight) {
		return height > startHeight || height == startHeight;
	}

	public int getHeight() {
		return height;
	}

	public void setLeft(Tree left) {
		this.left = left;
	}

	public void setRight(Tree right) {
		this.right = right;
	}

	public void setTop(Tree top) {
		this.top = top;
	}

	public void setBottom(Tree bottom) {
		this.bottom = bottom;
	}
}
