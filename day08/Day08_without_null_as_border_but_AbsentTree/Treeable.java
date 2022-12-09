package day08;

public interface Treeable {

	boolean allLeftLower(int startTreeHight);

	boolean allRightLower(int startTreeHight);

	boolean allTopLower(int startTreeHight);

	boolean allBottomLower(int startTreeHight);

	int viewToLeft(int startTreeHight);

	int viewToRight(int startTreeHight);

	int viewToTop(int startTreeHight);

	int viewToBottom(int startTreeHight);

}
