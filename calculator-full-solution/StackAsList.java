import java.util.NoSuchElementException;

public class StackAsList {
	public Node first;
	public int totalNumberOfNodes = 0;
	
	// F?gt an der ersten Stelle des Stacks ein Objekt hinzu
	public void addFirstNode(Object object) {
		Node last = new Node(object, first);
		first = last;
		totalNumberOfNodes ++;
	}
	
	// ?berpr?ft, ob die Liste leer ist
	public boolean isEmpty() {
		if (first == null) { return true; }
		else return false;
	}
	
	// Gibt den Inhalt des ersten Nodes wieder
	public Object peek() {
		if (first == null) { throw new NoSuchElementException(); }
		Object object = first.data;
		return object;
	}
	
	//Gib mir ein peek aus, das in ein String umgewandelt wurde
	public String peekAsString() {
		if (first == null) { throw new NoSuchElementException(); }
		String string = "";
		string = string + toString(first.data);
		return string;
	}
	
	// Druckt alle Objekte im Node aus.
	// Startet mit der ersten Position.
	// Jedes Objekt erh?lt eine neue Zeile.
	public void printAllNodes() {
		Node currentNode = first;		
		while (currentNode.next != null)
		{
			System.out.println(toString(currentNode.data));
			currentNode = currentNode.next;
		}
		if (currentNode.next == null) {
			System.out.println(toString(currentNode.data));
		}
	}
	public String allNodesToString() {
		Node currentNode = first;
		String string = "";
		while (currentNode.next != null)
		{
			string = string + toString(currentNode.data);
			currentNode = currentNode.next;
		}
		if (currentNode.next == null) {
			string = string + toString(currentNode.data);
		}
		return string;
	}
	
	// Entfernt das Objekt an der ersten Position im Stack.
	public Object removeFirst() {
		if(first == null) { throw new NoSuchElementException();}
		Object object = first.data;
		first = first.next;
		return object;
	}
	
	// Initialisiert den Stack ohne Inhalt.
	public StackAsList() {
		first = null;
	}
	
	// Erstellt einen Listen-Iterator
	public StackListIterator listIterator() {
		return new StackListIterator();
	}
	
	// Macht aus einem Objekt einen String
	public String toString(Object object) {
		String newString = "" + object;
		return newString;
	}
	
	// Definiert die Klasse "Node"
	private class Node{
		Object data;
		Node next;
		private Node() {
		}
		private Node(Object object) {
			data = object;
		}
		private Node(Object object, Node nextNode) {
			data = object;
			next = nextNode;
		}
	}
	
	// Definiert die Klasse "StackListIterator"
	class StackListIterator {
		private Node position;
		private Node previous;
		private boolean isAfterNext;
		
		// Initialisiert den Iterator bevore der ersten Stelle.
		public StackListIterator() {
			position = null;
			previous = null;
			isAfterNext = false;
		}
		
		// Bewegt den Iterator zur nachfolgenden Stelle.
		public Object next() {
			if (!hasNext()) { throw new NoSuchElementException();}
			previous = position;
			isAfterNext = true;
			
			if (position == null){
				position = first; 
			}
			else {
				position = position.next;
			}
			return position.data;
		}
		
		// Boolean, der pr?ft, ob das ausgew?hlte Objekt ein nachfolgendes hat
		public boolean hasNext() {
			if (position == null) {
				return first != null;
			}
			else {
				return position.next != null;
			}
		}
		
		// F?gt ein Objekt an der ausgew?hlten Stelle hinzu
		public void add(Object object) {
			if (position == null) {
				addFirstNode(object);
				position = first;
			}
			else {
				Node newNode = new Node(object, position.next);
				position.next = newNode;
				position = newNode;
			}
			totalNumberOfNodes ++;
			isAfterNext = false;
		}
		
		// Entfernt ein Objekt an der ausgew?hlten Stelle
		public void remove() {
			if(!isAfterNext) { throw new IllegalStateException();}
			if(position == first) {
				removeFirst();
			}
			else {
				previous.next = position.next;
			}
			position = previous;
			totalNumberOfNodes --;
			isAfterNext = false;
		}
		
		// Ver?ndert das Objekt an der ausgew?hlten Stelle
		public void set(Object object) {
			if(!isAfterNext) { throw new IllegalStateException();}
			position.data = object;
		}
	}
	

}