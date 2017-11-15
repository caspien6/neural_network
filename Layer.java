package main;

import java.util.ArrayList;
import java.util.Random;




public class Layer {
	
	public boolean isOutput,isInput;
	
	public Layer before=null, after=null;
	public ArrayList<Neuron> neurons;
	public MyMatrix weights;
	private MyMatrix inputs, expected_output;//only for the first
	
	public Layer(int NeuronCount) {
		
		isOutput = false;
		isInput = false;
		neurons = new ArrayList<Neuron>();
		for (int i = 0; i < NeuronCount; i++) {
			neurons.add(new Neuron());
		}
		
	}
	
	
	public Layer(int NeuronCount, MyMatrix biases) {
		isOutput = false;
		isInput = false;
		neurons = new ArrayList<Neuron>(NeuronCount);
		int i = 0;
		for (Neuron neuron : neurons) {
			neuron.bias = biases.tarolo[i][0];
			i++;
		}
	}
	
	public void initWeights(int previousNeuronCount) {
		weights = new MyMatrix( neurons.size(), previousNeuronCount);
		Random r = new Random();
		for (int i = 0; i < weights.RowCount; i++) {
			for (int j = 0; j < weights.ColumnCount; j++) {
				weights.tarolo[i][j] = r.nextGaussian()*0.1;
			}
		}
	}
	
	public void initWeights(MyMatrix m) {
		weights = m;
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
		if (before == null) {
			inp = getInputs();
			for (int i = 0; i < neurons.size(); i++) {
				biases.tarolo[i][0] = neurons.get(i).bias;
				
			}
			output = inp;
		}
		else
		{
			inp = before.getOutputMatrix();
			for (int i = 0; i < neurons.size(); i++) {
				biases.tarolo[i][0] = neurons.get(i).bias;
				
			}
			output = biases.plus(weights.times(inp));
		}
		
		
		//inp.show();
		//weights.show();
		//biases.show();
		
		
		
		if (isOutput)
			return output;
		else if(isInput)
			return output;
		return f(output);
		
	}
	
	
	
	private MyMatrix f(MyMatrix target) {
		for (int i = 0; i < target.RowCount; i++) {
			//ReLu
			for (int j = 0; j < target.ColumnCount; j++) {
				double ertek = target.tarolo[i][j];
				if (ertek > 0) 
					target.tarolo[i][j] = ertek;
				else 
					target.tarolo[i][j] = 0;
			}
			
		}
		return target;
	}
	
	private MyMatrix f_derivate(MyMatrix target) {
		for (int i = 0; i < target.RowCount; i++) {
			//ReLu derivalt
			for (int j = 0; j < target.ColumnCount; j++) {
				double ertek = target.tarolo[i][j];
				if (ertek > 0) 
					target.tarolo[i][j] = 1;
				else 
					target.tarolo[i][j] = 0;
			}
			
		}
		return target;
	}
	
	private MyMatrix getInputs() {
		if (isInput) {
			return inputs;
		}else {
			throw new RuntimeException("Nincs inputja ennek a layernek");
		}
	}
	
	public void setInputs(MyMatrix inp) {
		if (isInput) {
			this.inputs = inp;
		}else {
			throw new RuntimeException(". layernek nem lehet inputot megadni!!");
		}
		
	}
	
	public void setOutputs(MyMatrix out) {
		if (isOutput) {
			this.expected_output = out;
		}else {
			throw new RuntimeException(". layernek nem lehet inputot megadni!!");
		}
		
	}
	
	public MyMatrix getError() {
		if (this.isOutput) {
			return expected_output.minus(this.getOutputMatrix());
		}
		else {
			throw new RuntimeException("Nem az output matrixban vagyunk");
		}
		
	}
	
	public MyMatrix getSumMatrix(int melyseg) {
		MyMatrix output, output2;
		MyMatrix biases = new MyMatrix(neurons.size(), 1);
		MyMatrix inp;
		if (before.isInput) {
			inp = before.getInputs();
		}
		else
		{
			inp = before.getSumMatrix(melyseg+1);
			MyMatrix tmp = new MyMatrix(inp.RowCount, 1);
			for (int i = 0; i < inp.RowCount; i++) {
				tmp.tarolo[i][0] = inp.tarolo[i][i];
			}
			inp = tmp;
		}
		
		for (int i = 0; i < neurons.size(); i++) {
			biases.tarolo[i][0] = neurons.get(i).bias;
		}
		
		output = biases.plus(weights.times(inp));
		output2 = new MyMatrix(output.RowCount, output.RowCount);
		for (int i = 0; i < output.RowCount; i++) {
			output2.tarolo[i][i] = output.tarolo[i][0];
		}
		
		
		if (isOutput)
			return output2;
		else if(melyseg == 0 || isInput)
			return output2;
		return f(output2);
		
	}
	
	public MyMatrix getLayerDelta() {
		if (isOutput) {
			return getError().transpose();
		}
		else {
			return after.getLayerDelta().times(after.weights).times(f_derivate(this.getSumMatrix(0)) );
		}
	}
	
	public MyMatrix getLayerDeltawithoutError() {
		if (isOutput) {
			MyMatrix m = new MyMatrix(1, 1);
			m.tarolo[0][0] = 1;
			return m;
		}
		else {
			return after.getLayerDeltawithoutError().times(after.weights).times(f_derivate(this.getSumMatrix(0)) );
		}
	}
	
	public DerivateSolution getDerivates() {//error nelkuli itt nem kell a *-2
		MyMatrix delta_transpose = getLayerDeltawithoutError().transpose();
		DerivateSolution solution = new DerivateSolution();
		solution.derivateWeights = delta_transpose.times(before.getOutputMatrix().transpose());//*-2
		solution.derivateBiases = delta_transpose;//*-2
		return solution;
	}
	
}	
