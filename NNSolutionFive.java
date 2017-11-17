package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class NNSolutionFive {

	public static void main(String[] args) {
		Random r = new Random();
		int layercounts = 2 + 2;
		int[] architektura = new int[layercounts];
		try {
			
			for (int i = 0; i < 50; i++) {
				
				architektura[0] = 57;
				architektura[1] = r.nextInt(5)+4;
				for (int j = 2; j < layercounts-1; j++) {
					architektura[j] = architektura[j-1] - (r.nextInt(1) +1);
				}
				architektura[layercounts-1] = 1;
				PrintWriter writer = new PrintWriter("the-file-name " + i+".txt", "UTF-8");
				do_a_test(writer,10,2200,0.05 + r.nextDouble()*0.01, architektura);
				writer.close();
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void do_a_test(PrintWriter writer,int ep, int tanitas_sz, double bator, int[] architra) throws FileNotFoundException {
		writer.println("epoch: "+ep+", tanitas szama: "+ tanitas_sz+ ", batorsag: "+bator);
		ArrayList<Layer> layers = new ArrayList<Layer>();
		int epoch = ep;
		int tanitas_szama = tanitas_sz;
		int sorok_szama = 4500;
		double batorsag = bator;
		
		int[] architektura = architra;
		
		Structure st = null;
		
		double validaciok_szama = 0;
		double avg = 0;
		try {
			Random r = new Random();
			Scanner sc = new Scanner(new File("F:\\Eclipse\\keszprogramok\\NeuralNetwork\\spambase_train.csv"));
			
			
			

			
			for (int i = 0; i < architektura.length; i++) {
				if(i == 0) {
					MyMatrix layer_weights = new MyMatrix(architektura[0], architektura[0]);
					for (int j = 0; j < layer_weights.RowCount; j++) {
						layer_weights.tarolo[j][j] = 1;
					}
					layers.add(new Layer(architektura[0]));
					layers.get(0).weights = layer_weights;
					layers.get(0).isInput = true;
				}
				else if(i == architektura.length-1) {
					layers.add(new Layer(architektura[i]));
					layers.get(architektura.length-1).isOutput = true;
				}
				else {
					layers.add(new Layer(architektura[i]));
				}
			 
				
				MyMatrix biases = layers.get(i).getBiases();
				for (int j = 0; j < biases.RowCount; j++) {
					biases.tarolo[j][0] = r.nextDouble()*2.0-1.0;
				}
				
			}
			
			st = new Structure(layers);
			st.initWeights();
			st.mu = batorsag;
			
			ArrayList<InputOutputMatrices> teaching_patterns = new ArrayList<InputOutputMatrices>();
			ArrayList<InputOutputMatrices> validate_patterns = new ArrayList<InputOutputMatrices>();
			
			for (int i = 0; i < sorok_szama; i++) {
				String[] inputandexpectedoutput = sc.nextLine().split(",");
				MyMatrix inputs = new MyMatrix(inputandexpectedoutput.length-1, 1);
				MyMatrix outputs = new MyMatrix(1, 1);
				for (int j = 0; j < inputandexpectedoutput.length; j++) {
					if (j < inputs.RowCount) {
						inputs.tarolo[j][0] = Double.parseDouble(inputandexpectedoutput[j]);
					}else {
						outputs.tarolo[j-inputs.RowCount][0] = Double.parseDouble(inputandexpectedoutput[j]);
					}
				}
				InputOutputMatrices iomatrix = new InputOutputMatrices();
				iomatrix.inputMatrix = inputs;
				iomatrix.expectedOutputMatrix = outputs;
				if (i < tanitas_szama) {
					teaching_patterns.add(iomatrix);
				}
				else
				{
					validate_patterns.add(iomatrix);
				}
			}
			
			for (int i = 0; i < epoch; i++) {
				for (int j = 0; j < teaching_patterns.size(); j++) {
					st.iterateOnceLearn(teaching_patterns.get(j).inputMatrix, teaching_patterns.get(j).expectedOutputMatrix);
				}
				validaciok_szama = 0;
				avg = 0;
				for (int j = 0; j < validate_patterns.size(); j++) {
					validaciok_szama++;
					avg += st.iterateOnce(validate_patterns.get(j).inputMatrix, validate_patterns.get(j).expectedOutputMatrix);
				}
				writer.println(avg/validaciok_szama);
								
			}
			
			
		}finally {
			
			for (int i = 0; i < architektura.length; i++) {
				if (i == architektura.length-1) {
					writer.println(architektura[i]);
				}else {
					writer.print(architektura[i] + ",");
				}
			}
			for (int j = 1; j < st.layers.size(); j++) {
				st.layers.get(j).printWeightsAndBiases(writer);
			}
		}
		

	}

}
