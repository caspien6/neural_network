package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.Layer;
import main.MyMatrix;
import main.Structure;

class first_test {
	static Structure st;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ArrayList<Layer> layers = new ArrayList<Layer>();
		layers.add(new Layer(2));
		MyMatrix l1_weights = new MyMatrix(3,2);
		l1_weights.tarolo[0][0]=1;l1_weights.tarolo[0][1]=0;
		l1_weights.tarolo[1][0]=0;l1_weights.tarolo[1][1]=1;
		l1_weights.tarolo[2][0]=1;l1_weights.tarolo[2][1]=1;
		Layer l = new Layer(3);
		l.initWeights(l1_weights);
		l.neurons.get(0).bias=-0.5;
		l.neurons.get(1).bias=-0.5;
		l.neurons.get(2).bias=-1;
		layers.add(l);
		
		MyMatrix l2_weights = new MyMatrix(1,3);
		l2_weights.tarolo[0][0]=2;l2_weights.tarolo[0][1]=2;l2_weights.tarolo[0][2]=-2;
		Layer l2 = new Layer(1);
		l2.initWeights(l2_weights);
		l2.neurons.get(0).bias=0;
		layers.add(l2);
		st = new Structure(layers);
		
	}
	

	@Test
	void test() {
		System.out.println(st.getOutput().tarolo[0][0]);
		assertEquals(0.0, 0.0);
	}

}
