package day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Deque;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.google.common.collect.Lists;

public class Day12 {

	@Test
	public void dayTwelve() throws IOException {

		var inputList = Files.readAllLines(Paths.get("src/test/resources/day12.txt"));

		System.out.println("----- Day 12 -----");
		Node startNodePart1 = null;
		Node endNode = null;
		List<Node> startingNodesPart2 = Lists.newArrayList();
		int rowSize = inputList.size();
		int columnSize = inputList.get(0).length();

		// build Nodes
		var grid = new Node[rowSize][columnSize];
		for (int rowIndex = 0; rowIndex < rowSize; rowIndex++) {
			for (int columnIndex = 0; columnIndex < columnSize; columnIndex++) {
				var entry = inputList.get(rowIndex).substring(columnIndex, columnIndex + 1);
				Node node;
				if ("S".equals(entry)) {
					node = new Node("a");
					startNodePart1 = node;
					startingNodesPart2.add(node);
				} else if ("a".equals(entry)) {
					node = new Node(entry);
					startingNodesPart2.add(node);
				} else if ("E".equals(entry)) {
					node = new Node("z");
					endNode = node;
				} else {
					node = new Node(entry);
				}
				grid[rowIndex][columnIndex] = node;
				setSiblings(grid, rowIndex, columnIndex);
			}
		}

		System.out.println("result part 1= " + bfs(startNodePart1, endNode));

		final Node finalEndNode = endNode; // needed as final for stream parameter
		int smallest = startingNodesPart2.stream().mapToInt(node -> bfs(node, finalEndNode)).min().getAsInt();
		System.out.println("result part 2= " + smallest);
	}

	private int bfs(Node startNode, Node endNode) {

		var touchedNodes = Collections.newSetFromMap(new IdentityHashMap<>());
		Deque<Node> nodesToCheck = new LinkedList<>();

		startNode.resetCount();
		nodesToCheck.addLast(startNode);
		touchedNodes.add(startNode);

		while (!nodesToCheck.isEmpty()) {
			var currentNode = nodesToCheck.pollFirst();
			if (currentNode == endNode) {
				return currentNode.getCount();
			}

			for (Node newNode : currentNode.getReachableSiblings()) {
				if (!touchedNodes.contains(newNode)) {
					newNode.updateCount(currentNode.getCount());
					nodesToCheck.addLast(newNode);
					touchedNodes.add(newNode);
				}
			}
		}
		return Integer.MAX_VALUE;
	}

	private void setSiblings(Node[][] grid, int rowIndex, int columnIndex) {
		var node = grid[rowIndex][columnIndex];
		if (rowIndex > 0) {
			var nodeAbove = grid[rowIndex - 1][columnIndex];
			node.addReachable(nodeAbove);
			nodeAbove.addReachable(node);
		}
		if (columnIndex > 0) {
			var nodeLeft = grid[rowIndex][columnIndex - 1];
			node.addReachable(nodeLeft);
			nodeLeft.addReachable(node);
		}
	}

//	private void printGrid(Node[][] grid) {
//		for (Node[] row : grid) {
//			for (Node x : row) {
//				System.out.print(x.getValue());
//			}
//			System.out.println();
//		}
//	}

}
