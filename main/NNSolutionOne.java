package main;

import java.util.ArrayList;
import java.util.Scanner;

public class NNSolutionOne {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Layer> layers = new ArrayList<Layer>();
		String dimension = sc.nextLine();
		
		String[] tmp = dimension.split(",");
		for (int i = 0; i < tmp.length; i++) {
			layers.add(new Layer(Integer.parseInt(tmp[i])));
			
		} 
			
		
		Structure st = new Structure(layers);
		st.initWeights();
		st.printData();

	}

}
