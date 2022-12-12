package day12;

import java.util.Deque;
import java.util.LinkedList;

public class Node {

	private Deque<Node> reachableSiblings;
	private char value;
	private int count;

	private Node(char value) {
		this.value = value;
		resetCount();
		reachableSiblings = new LinkedList<>();
	}

	public Node(String entry) {
		this(entry.charAt(0));
	}

	public void addReachable(Node sibling) {
		if (isReachable(sibling))
			reachableSiblings.addLast(sibling);
	}

	public boolean isReachable(Node otherNode) {
		return otherNode.getValue() - getValue() <= 1;
	}

	public char getValue() {
		return value;
	}

	public Deque<Node> getReachableSiblings() {
		return reachableSiblings;
	}

	public int getCount() {
		return count;
	}

	public void updateCount(int value) {
		count = value + 1;
	}

	public void resetCount() {
		count = 0;
	}

	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}
}
