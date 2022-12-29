package day21;

import java.beans.PropertyChangeListener;

public interface ObserverMonkey extends PropertyChangeListener {

	void setMonkeyA(Monkey monkeyA);

	void setMonkeyB(Monkey monkeyB);
}
