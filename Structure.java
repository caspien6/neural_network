package main;

import java.util.ArrayList;

public class Structure {
	ArrayList<Layer> layers;
	MyMatrix inputs, expected_outputs;
	private ArrayList<MyMatrix> iterated_weights = new ArrayList<MyMatrix>();
	private ArrayList<MyMatrix> iterated_biases = new ArrayList<MyMatrix>();
	public double mu = 0.1;
	
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
	
	public DerivateSolution get_layout_derivates_witherror(int index) {
		return layers.get(index).getDerivatesError();
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
	
	public void iterateOnceLearn(MyMatrix inp, MyMatrix expected_out) {
		setInputs(inp);
		setOutputs(expected_out);
		
		//kiiras miatt
		//MyMatrix real_output = getOutput();
		//real_output.show();
		for (int i = 1; i < layers.size(); i++) {
			DerivateSolution layer_derivates = get_layout_derivates_witherror(i);
			iterated_weights.add(layers.get(i).weights.minus(layer_derivates.derivateWeights.scalarMultiplication(mu)));
			iterated_biases.add(layers.get(i).getBiases().minus(layer_derivates.derivateBiases.scalarMultiplication(mu)));
		}
		
		
		for (int i = 1; i < layers.size(); i++) {
			layers.get(i).weights = iterated_weights.get(i-1);
			layers.get(i).setBiases(iterated_biases.get(i-1));
		}
		iterated_weights.clear();
		iterated_biases.clear();
		
		//kiiras miatt
		//printData();
	}
	
	public double iterateOnce(MyMatrix inp, MyMatrix expected_out) {
		setInputs(inp);
		setOutputs(expected_out);
		
		MyMatrix real_output = getOutput();
		
		MyMatrix tmp = expected_out.minus(real_output);
		
		double avg = tmp.transpose().times(tmp).tarolo[0][0]/expected_out.RowCount;
		return avg;
	}
	
	
	
	
	
	
	
}
