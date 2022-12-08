package day08;

public class Tree {

	private int height;
	private Tree left, right, top, bottom;

	public Tree(int height) {
		this.height = height;
	}

	public boolean allLeftLower() {
		return left == null ? true : left.allLeftLower(height);
	}

	public boolean allRightLower() {
		return right == null ? true : right.allRightLower(height);
	}

	public boolean allTopLower() {
		return top == null ? true : top.allTopLower(height);
	};

	public boolean allBottomLower() {
		return bottom == null ? true : bottom.allBottomLower(height);
	}

	private boolean allLeftLower(int startTreeHight) {
		return isLower(startTreeHight) && (left == null || left.allLeftLower(startTreeHight));
	}

	private boolean allRightLower(int startTreeHight) {
		return isLower(startTreeHight) && (right == null || right.allRightLower(startTreeHight));
	}

	private boolean allTopLower(int startTreeHight) {
		return isLower(startTreeHight) && (top == null || top.allTopLower(startTreeHight));
	}

	private boolean allBottomLower(int startTreeHight) {
		return isLower(startTreeHight) && (bottom == null || bottom.allBottomLower(startTreeHight));
	}

	private boolean isLower(int startTreeHight) {
		return height < startTreeHight;
	}

	private int viewToLeft(int startTreeHight) {
		if (left == null || isHigherOrSameHight(startTreeHight)) // no more (lower) trees
			return 0;
		else
			return 1 + left.viewToLeft(startTreeHight); // +1 and ask the next visible tree
	}

	private int viewToRight(int startTreeHight) {
		if (right == null || isHigherOrSameHight(startTreeHight))
			return 0;
		else
			return 1 + right.viewToRight(startTreeHight);
	}

	private int viewToTop(int startTreeHight) {
		if (top == null || isHigherOrSameHight(startTreeHight))
			return 0;
		else
			return 1 + top.viewToTop(startTreeHight);
	}

	private int viewToBottom(int startTreeHight) {
		if (bottom == null || isHigherOrSameHight(startTreeHight))
			return 0;
		else
			return 1 + bottom.viewToBottom(startTreeHight);
	}

	public int scenicScore() {
		int viewToLeft = left == null ? 0 : 1 + left.viewToLeft(height);
		int viewToRight = right == null ? 0 : 1 + right.viewToRight(height);
		int viewToTop = top == null ? 0 : 1 + top.viewToTop(height);
		int viewToBottom = bottom == null ? 0 : 1 + bottom.viewToBottom(height);

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
