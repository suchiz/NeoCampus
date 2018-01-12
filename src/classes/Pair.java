package classes;

public class Pair<T, U> {

	private T left;
	private U right;

	public Pair(T left, U right) {
		this.left = left;
		this.right = right;
	}

	public T getLeft() {
		return left;
	}

	public U getRight() {
		return right;
	}

	@Override
	public int hashCode() {
		return left.hashCode() ^ right.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Pair))
			return false;
		Pair<T, U> paire = (Pair<T, U>) o;
		return this.left.equals(paire.getLeft()) && this.right.equals(paire.getRight());
	}

}
