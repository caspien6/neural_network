package main;

public class Main {

	public static void main(String[] args) {
		MyMatrix m = new MyMatrix(10,10);
		MyMatrix m2 = new MyMatrix(10,10);
		m.show();
		
		MyMatrix m3 = m2.plus(m);
		m3.show();
		
	}

}
