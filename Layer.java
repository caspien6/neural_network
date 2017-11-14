package main;

import java.util.ArrayList;
import java.util.Random;


public class Layer {
	public static int indexer = 0;
	public int index;
	
	public Layer before, after;
	public ArrayList<Neuron> neurons;
	public MyMatrix weights;
	private MyMatrix inputs;//only for the first
	
	public Layer(int NeuronCount) {
		index=indexer;
		++indexer;
		neurons = new ArrayList<Neuron>();
		for (int i = 0; i < NeuronCount; i++) {
			neurons.add(new Neuron());
		}
	}
	
	
	public Layer(int NeuronCount, MyMatrix biases) {
		index=indexer;
		++indexer;
		neurons = new ArrayList<Neuron>(NeuronCount);
		int i = 0;
		for (Neuron neuron : neurons) {
			neuron.bias = biases.tarolo[i][0];
			i++;
		}
	}
	
	public void initWeights(int previousNeuronCount) {
		weights = new MyMatrix(neurons.size(),  previousNeuronCount);
		Random r = new Random();
		for (int i = 0; i < weights.RowCount; i++) {
			for (int j = 0; j < weights.ColumnCount; j++) {
				weights.tarolo[i][j] = r.nextGaussian()*0.1;
			}
		}
	}
	
	public void printWeightsAndBiases() {
		for (int i = 0; i < weights.RowCount; i++) {
			for (int j = 0; j < weights.ColumnCount; j++) {
				System.out.print(weights.tarolo[i][j] + ",");
			}
			System.out.println(neurons.get(i).bias);
			
		}
	}
	
	public MyMatrix getOutputMatrix() {
		MyMatrix output;
		MyMatrix biases = new MyMatrix(neurons.size(), 1);
		MyMatrix inp;
		if (index == 1) {
			inp = before.getInputs();
		}
		else
		{
			inp = before.getOutputMatrix();
		}
		
		for (int i = 0; i < neurons.size(); i++) {
			biases.tarolo[i][0] = neurons.get(i).bias;
		}
		
		output = biases.plus(weights.times(inp));
		if (index == indexer-1)
			return output;
		return f(output);
		
	}
	
	private MyMatrix f(MyMatrix target) {
		for (int i = 0; i < target.RowCount; i++) {
			//ReLu
			double ertek = target.tarolo[i][0];
			if (ertek > 0) 
				target.tarolo[i][0] = ertek;
			else 
				target.tarolo[i][0] = 0;
		}
		return target;
	}
	
	private MyMatrix getInputs() {
		if (index == 0) {
			return inputs;
		}else {
			throw new RuntimeException("Nincs inputja ennek a layernek");
		}
	}
	
	public void setInputs(MyMatrix inp) {
		if (index == 0) {
			this.inputs = inp;
		}else {
			throw new RuntimeException((index) +  ". layernek nem lehet inputot megadni!!");
		}
		
	}
	
}	
