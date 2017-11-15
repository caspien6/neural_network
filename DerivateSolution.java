package main;

public class DerivateSolution {
	public MyMatrix derivateWeights, derivateBiases;
	
	public void showResults() {
		for (int i = 0; i < derivateWeights.RowCount; i++) {
			for (int j = 0; j < derivateWeights.ColumnCount; j++) {
				if (j == derivateWeights.ColumnCount-1) {
					System.out.println(derivateWeights.tarolo[i][j] + "," +
							derivateBiases.tarolo[i][0]);
				}
				else {
					System.out.print(derivateWeights.tarolo[i][j] + ",");
				}
			}
		}
	}
}
