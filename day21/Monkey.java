package day21;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Objects;

public abstract class Monkey {

	private PropertyChangeSupport observers;
	private String name;
	static final String YELL_OBS = "yell";

	public Monkey(String name) {
		this.name = name;
		observers = new PropertyChangeSupport(this);
	}

	public abstract long yell();
	public abstract long getNumber();
	public abstract void setNumber(long number);

	long yell(long number) {
		observers.firePropertyChange(YELL_OBS, null, this);
		return number;
	}

	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		observers.addPropertyChangeListener(pcl);
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		observers.removePropertyChangeListener(pcl);
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Monkey [observers=" + observers + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Monkey other = (Monkey) obj;
		return Objects.equals(name, other.name);
	}
}
