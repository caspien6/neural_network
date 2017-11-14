package main;

import java.util.ArrayList;

public class testelo {
static Structure st;
	public static void main(String[] args) {
		ArrayList<Layer> layers = new ArrayList<Layer>();
		Layer first = new Layer(2);
		first.isInput = true;
		first.initWeights(2);
		layers.add(first);
		MyMatrix l1_weights = new MyMatrix(3,2);
		l1_weights.tarolo[0][0]=1;l1_weights.tarolo[0][1]=0;
		l1_weights.tarolo[1][0]=0;l1_weights.tarolo[1][1]=1;
		l1_weights.tarolo[2][0]=1;l1_weights.tarolo[2][1]=1;
		Layer l = new Layer(3);
		l.initWeights(l1_weights);
		l.neurons.get(0).bias=-0.5;
		l.neurons.get(1).bias=-0.5;
		l.neurons.get(2).bias=-1;
		layers.add(l);
		
		MyMatrix l2_weights = new MyMatrix(1,3);
		l2_weights.tarolo[0][0]=2;l2_weights.tarolo[0][1]=2;l2_weights.tarolo[0][2]=-2;
		Layer l2 = new Layer(1);
		l2.isOutput=true;
		l2.initWeights(l2_weights);
		l2.neurons.get(0).bias=0;
		layers.add(l2);
		st = new Structure(layers);
		
		
		
		MyMatrix inputs = new MyMatrix(2,1);
		inputs.tarolo[0][0]=0;
		inputs.tarolo[1][0]=0;
		
		st.setInputs(inputs);
		System.out.println(st.getOutput().tarolo[0][0]);
		
		inputs = new MyMatrix(2,1);
		inputs.tarolo[0][0]=0;
		inputs.tarolo[1][0]=1;
		
		st.setInputs(inputs);
		System.out.println(st.getOutput().tarolo[0][0]);
		
		inputs = new MyMatrix(2,1);
		inputs.tarolo[0][0]=1;
		inputs.tarolo[1][0]=0;
		
		st.setInputs(inputs);
		System.out.println(st.getOutput().tarolo[0][0]);
		
		inputs = new MyMatrix(2,1);
		inputs.tarolo[0][0]=1;
		inputs.tarolo[1][0]=1;
		
		st.setInputs(inputs);
		System.out.println(st.getOutput().tarolo[0][0]);

	}

}
