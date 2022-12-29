package day21;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Day21 implements PropertyChangeListener {

	private List<Monkey> monkeysList = Lists.newArrayList();
	private Map<String, Monkey> monkeysMap = Maps.newHashMap();
	private Map<ObserverMonkey, List<String>> observerMapping = Maps.newHashMap();
	private Monkey root = null;
	private boolean rootYelled = false;
	private boolean partTwoSolutionFound = false;
	private boolean part2 = false;
	long startNumber = 0;

	@Test
	public void dayTwentyOnePart1() throws IOException {

		var inputList = Files.readAllLines(Paths.get("src/test/resources/day21.txt"));

		System.out.println("----- Day 21 part 1 -----");
		part2 = false;
		initMonkeys(inputList, false);
		initObservers(); // observer pattern
		observeRoot();
		startYelling();
	}

	@Test
	public void dayTwentyOnePart2() throws IOException {

		var inputList = Files.readAllLines(Paths.get("src/test/resources/day21.txt"));

		System.out.println("----- Day 21 part 2 -----");
		part2 = true;
		initMonkeys(inputList, true);
		initObservers();
		observeRoot();
		var me = monkeysMap.get("humn");
		// I was too lazy to implement cut in half to find start ;-))
		startNumber = 3759569910000L;
		do {
			startNumber++;
			rootYelled = false;
			me.setNumber(startNumber);
			startYelling();

		} while (!partTwoSolutionFound);

		System.out.println("result part 2= " + startNumber);
	}

	private void startYelling() {
		for (Monkey monkey : monkeysList) {
			if (monkey instanceof SimpleMonkey simple) {
				simple.yell();
				if (rootYelled)
					break;
			}
		}
	}

	private void initMonkeys(List<String> list, boolean specialRoot) {
		for (String oneLine : list) {
			var split = oneLine.split(" ");
			String name;
			Monkey monkey;
			if (split.length == 2) {
				name = fetchName(split[0]);
				monkey = new SimpleMonkey(name, Integer.parseInt(split[1].strip()));
			} else {
				name = fetchName(split[0]);
				var nameMonkeyA = fetchName(split[1]);
				var nameMonkeyB = fetchName(split[3]);
				String operation;
				if (specialRoot && "root".equals(name)) // part 2
					operation = "=";
				else
					operation = split[2].strip();
				monkey = new MathMonkey(name, operation);
				observerMapping.put((ObserverMonkey) monkey, Lists.newArrayList(nameMonkeyA, nameMonkeyB));
			}
			monkeysList.add(monkey);
			monkeysMap.put(name, monkey);
			if ("root".equals(name))
				root = monkey;
		}
	}

	private void initObservers() {
		for (Map.Entry<ObserverMonkey, List<String>> entry : observerMapping.entrySet()) {
			var currentMonkey = (ObserverMonkey) monkeysMap.get(((Monkey) entry.getKey()).getName());
			List<String> waitForMonkeys = entry.getValue();
			currentMonkey.setMonkeyA(monkeysMap.get(waitForMonkeys.get(0)));
			currentMonkey.setMonkeyB(monkeysMap.get(waitForMonkeys.get(1)));
		}
	}

	private String fetchName(String string) {
		return string.replace(":", "").strip();
	}

	private void observeRoot() {
		root.addPropertyChangeListener(this);
	}

	/** observe root **/
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		rootYelled = true;
		if (part2)
			partTwoSolutionFound = root.getNumber() == 0 ? true : false;
		else
			System.out.println("result part 1, root called= " + ((Monkey) evt.getNewValue()).getNumber());
	}

}
