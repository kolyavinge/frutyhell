package frutyhell.shit;

public class Tuple2<Type1, Type2> {

	private Type1 item1;
	private Type2 item2;

	public Tuple2(Type1 item1, Type2 item2) {
		this.item1 = item1;
		this.item2 = item2;
	}

	public Tuple2() {
	}

	public Type1 getItem1() {
		return item1;
	}

	public void setItem1(Type1 item1) {
		this.item1 = item1;
	}

	public Type2 getItem2() {
		return item2;
	}

	public void setItem2(Type2 item2) {
		this.item2 = item2;
	}
}
