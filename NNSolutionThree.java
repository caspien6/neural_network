package main;

import java.util.ArrayList;
import java.util.Scanner;

public class NNSolutionThree {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Layer> layers = new ArrayList<Layer>();

		String dimension = sc.nextLine();
		//struktura beolvasasa
		String[] tmp = dimension.split(",");
		ArrayList<Integer> lay_neurons = new ArrayList<Integer>();
		for (int i = 0; i < tmp.length; i++) {
			lay_neurons.add(Integer.parseInt(tmp[i]));
			Layer l = new Layer(Integer.parseInt(tmp[i]));
			if (i == 0) l.isInput = true;
			else if(i == tmp.length-1) l.isOutput = true;
			layers.add(l);
		}
		//Neuron adatok beolvasasa soronkent
		for (int i = 0; i < lay_neurons.size(); i++) {
			MyMatrix layer_weights;
			if (i == 0) {
				layer_weights = new MyMatrix(lay_neurons.get(i), lay_neurons.get(i));
				for (int j = 0; j < layer_weights.RowCount; j++) {
					layer_weights.tarolo[j][j] = 1;
				}
			}
			else {
				layer_weights = new MyMatrix(lay_neurons.get(i), lay_neurons.get(i-1));
				for (int j = 0; j < lay_neurons.get(i); j++) {
					String[] neuron_params = sc.nextLine().split(",");
					for (int k = 0; k < neuron_params.length; k++) {
						if (k < neuron_params.length-1) {
							layer_weights.tarolo[j][k] = Double.parseDouble(neuron_params[k]);
						}
						else {
							layers.get(i).neurons.get(j).bias = Double.parseDouble(neuron_params[k]);
						}
					}
					
				}
			}
			
			layers.get(i).initWeights(layer_weights);
		}
		
		Structure st = new Structure(layers);
		int input_size = Integer.parseInt(sc.nextLine());
		//inicializalas
		MyMatrix inputs;
		MyMatrix expectedoutputs = new MyMatrix(1,1);
		expectedoutputs.tarolo[0][0] = 1;
		ArrayList<DerivateSolution> outputs = new ArrayList<DerivateSolution>();
		for (int i = 0; i < input_size; i++) {
			String[] inpstr = sc.nextLine().split(",");
			inputs = new MyMatrix(inpstr.length,1);
			for (int j = 0; j < inpstr.length; j++) {
				inputs.tarolo[j][0] = Double.parseDouble(inpstr[j]);
			}
			st.setInputs(inputs);
			st.setOutputs(expectedoutputs);
			
		}
		
		for (int i = 1; i < layers.size(); i++) {
			outputs.add(st.get_layout_derivates(i));
		}
		
		//Layout meretek kiirasa
		for (int i = 0; i < lay_neurons.size(); i++) {
			if (i != lay_neurons.size()-1) {
				System.out.print(lay_neurons.get(i)+ ",");
			}
			else
			{
				System.out.println(lay_neurons.get(i));
			}
			
		}
		
		for (DerivateSolution layer_derivates : outputs) {
			layer_derivates.showResults();
		}

	}

}
