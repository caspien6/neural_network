package main;

import java.util.ArrayList;
import java.util.Random;

public class Layer {
	public ArrayList<Neuron> neurons;
	public MyMatrix weights;
	
	public Layer(int NeuronCount) {
		neurons = new ArrayList<Neuron>();
		for (int i = 0; i < NeuronCount; i++) {
			neurons.add(new Neuron());
		}
	}
	
	
	public Layer(int NeuronCount, MyMatrix biases) {
		neurons = new ArrayList<Neuron>(NeuronCount);
		int i = 0;
		for (Neuron neuron : neurons) {
			neuron.bias = biases.tarolo[i][0];
			i++;
		}
	}
	
	public void initWeights(int previousNeuronCount) {
		weights = new MyMatrix(previousNeuronCount, neurons.size());
		Random r = new Random();
		for (int i = 0; i < weights.RowCount; i++) {
			for (int j = 0; j < weights.ColumnCount; j++) {
				weights.tarolo[i][j] = r.nextGaussian()*0.1;
			}
		}
	}
	
	public void printWeightsAndBiases() {
		for (int i = 0; i < neurons.size(); i++) {
			for (int j = 0; j < weights.RowCount; j++) {
				System.out.print(weights.tarolo[j][i] + ",");
			}
			System.out.println(neurons.get(i).bias);
		}
		
	}
	
}	
