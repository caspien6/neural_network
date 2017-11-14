package main;

import java.util.ArrayList;

public class Structure {
	ArrayList<Layer> layers;
	MyMatrix inputs;
	
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
	
	public void setInputs(MyMatrix m) {
		inputs = m;
		layers.get(0).setInputs(m);
	}
	
	public void setExpectedResult(MyMatrix m) {
		layers.get(0).setInputs(m);
	}
	
	public MyMatrix getOutput() {
		return layers.get(layers.size()-1).getOutputMatrix();
	}
	
	private MyMatrix f(MyMatrix target) {
		for (int i = 0; i < target.RowCount; i++) {
			target.tarolo[i][0] = target.tarolo[i][0];
		}
		return target;
	}
	
}
