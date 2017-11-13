package main;

import java.util.ArrayList;

public class Structure {
	ArrayList<Layer> layers;
	
	public Structure(ArrayList<Layer> l) {
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
	
}
