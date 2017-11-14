package main;

import java.util.ArrayList;
import java.util.Scanner;

public class NNSolutionTwo {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ArrayList<Layer> layers = new ArrayList<Layer>();

		layers.add(new Layer(2));
		layers.add(new Layer(3));
		layers.add(new Layer(1));
		
		
		Structure st = new Structure(layers);
		st.initWeights();
		MyMatrix inputs = new MyMatrix(2,1);
		inputs.tarolo[0][0] = 0;//inputs.tarolo[0][1] = 0;inputs.tarolo[0][2] = 0;inputs.tarolo[0][3] = 0;
		inputs.tarolo[1][0] = 0;//inputs.tarolo[1][1] = 0;inputs.tarolo[1][2] = 0;inputs.tarolo[1][3] = 0;
		st.setInputs(inputs);
		
		MyMatrix m = st.getOutput();
		System.out.println(m.tarolo[0][0]);
		
		//st.printData();
	}

}
