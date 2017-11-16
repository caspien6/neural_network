package main;

import java.util.ArrayList;
import java.util.Scanner;

public class NNSolutionFour {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Layer> layers = new ArrayList<Layer>();
		
		String[] teaching_params = sc.nextLine().split(",");
		//TODO elso es harmadik parameter meg nincs kesz.
		
		
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
		st.mu = Double.parseDouble(teaching_params[1]);
		
		/*MyMatrix inp = new MyMatrix(2, 1);
		inp.tarolo[0][0] = 0;
		inp.tarolo[1][0] = 0;
		MyMatrix expected_output = new MyMatrix(1,1);
		expected_output.tarolo[0][0] = 0;
		st.iterateOnceLearn(inp, expected_output);
		
		
		inp.tarolo[0][0] = 0;
		inp.tarolo[1][0] = 1;
		expected_output.tarolo[0][0] = 1;
		st.iterateOnceLearn(inp, expected_output);
		
		inp.tarolo[0][0] = 1;
		inp.tarolo[1][0] = 0;
		expected_output.tarolo[0][0] = 1;
		st.iterateOnceLearn(inp, expected_output);
		
		inp.tarolo[0][0] = 1;
		inp.tarolo[1][0] = 1;
		expected_output.tarolo[0][0] = 0;
		st.iterateOnceLearn(inp, expected_output);
		
		double avg = 0;
		inp.tarolo[0][0] = 0.2;
		inp.tarolo[1][0] = 0.2;
		expected_output.tarolo[0][0] = 0;
		avg += st.iterateOnce(inp, expected_output);
		inp.tarolo[0][0] = 0.2;
		inp.tarolo[1][0] = 0.8;
		expected_output.tarolo[0][0] = 1;
		avg += st.iterateOnce(inp, expected_output);
		inp.tarolo[0][0] = 0.8;
		inp.tarolo[1][0] = 0.2;
		expected_output.tarolo[0][0] = 1;
		avg += st.iterateOnce(inp, expected_output);
		inp.tarolo[0][0] = 0.8;
		inp.tarolo[1][0] = 0.8;
		expected_output.tarolo[0][0] = 0;
		avg += st.iterateOnce(inp, expected_output);*/
		
		MyMatrix inp = new MyMatrix(1, 1);
		inp.tarolo[0][0] = 3;
		MyMatrix expected_output = new MyMatrix(2,1);
		expected_output.tarolo[0][0] = 4;
		expected_output.tarolo[1][0] = 7;
		st.iterateOnceLearn(inp, expected_output);
		
		System.out.println();
		double avg = 0;
		inp.tarolo[0][0] = 2;
		expected_output.tarolo[0][0] = 3;
		expected_output.tarolo[1][0] = 5;
		avg += st.iterateOnce(inp, expected_output);
		
		//az atlag meg nem jo, valamit csinalni kell vele....TODO
		System.out.println("Az atlagos hiba elvileg: " + avg/1.0);

	}

}
