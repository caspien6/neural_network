package main;

import java.util.ArrayList;

public class Structure {
	ArrayList<Layer> layers;
	MyMatrix inputs, expected_outputs;
	
	public Structure(ArrayList<Layer> l) {
		for (int i = 0; i < l.size(); i++) {
			if(i == 0) {
				l.get(i).before = null;
				l.get(i).after = l.get(i+1);
			}
			else if(i == l.size()-1) {
				l.get(i).before = l.get(i-1);
				l.get(i).after = null;
			}
			else {
				l.get(i).before = l.get(i-1);
				l.get(i).after = l.get(i+1);
			}
		}
		layers = l;
	}
	
	public void initWeights() {
		for (int i = 0; i < layers.size(); i++) {
			if (i != 0) {
				layers.get(i).initWeights(layers.get(i-1).neurons.size());
			}
		}
	}
	
	
	public void printData() {
		for (int i = 0; i < layers.size(); i++) {
			if (i == layers.size()-1) {
				System.out.println(layers.get(i).neurons.size());
			}else {
				System.out.print(layers.get(i).neurons.size() + ",");
			}
		}
		
		for (int i = 0; i < layers.size(); i++) {
			if (i > 0) {
				layers.get(i).printWeightsAndBiases();
			}
		}
		
	}
	
	public DerivateSolution get_layout_derivates(int index) {
		return layers.get(index).getDerivates();
	}
	
	public void setInputs(MyMatrix m) {
		inputs = m;
		layers.get(0).setInputs(m);
	}
	
	public void setOutputs(MyMatrix m) {
		expected_outputs = m;
		layers.get(layers.size()-1).setOutputs(m);
	}
	
	
	
	public MyMatrix getOutput() {
		return layers.get(layers.size()-1).getOutputMatrix();
	}
	
	
	
}
