package application;

public class HNode<T> {

	private T data;
	private char f;

	public HNode(T data,char f) {
		super();
		this.data = data;
		this.f = f;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public char getF() {
		return f;
	}

	public void setF(char f) {
		this.f = f;
	}

	@Override
	public String toString() {
		return "HNode [ data=" + data + ", f = " + f + " ]";
	}
  
}
