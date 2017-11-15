package main;


public class MyMatrix {
	public int RowCount;
	public int ColumnCount;
	public double[][] tarolo;
	
	public MyMatrix(int R, int C) {
		RowCount = R;
		ColumnCount = C;
		tarolo = new double[R][C];
		for(int i = 0; i < RowCount; ++i) {
			for (int j = 0; j < ColumnCount; j++) {
				tarolo[i][j] = 0;
			}
		}
	}
	
	public MyMatrix scalarMultiplication(double scalar) {
		MyMatrix A = this;
		for (int i = 0; i < A.RowCount; i++) {
			for (int j = 0; j < A.ColumnCount; j++) {
				A.tarolo[i][j] = A.tarolo[i][j]*scalar;
			}
		}
		return A;
	}
	
	
    public MyMatrix transpose() {
    	MyMatrix A = new MyMatrix(ColumnCount, RowCount);
        for (int i = 0; i < RowCount; i++)
            for (int j = 0; j < ColumnCount; j++)
                A.tarolo[j][i] = this.tarolo[i][j];
        return A;
    }
	
	
    public MyMatrix times(MyMatrix B) {
    	MyMatrix A = this;
        if (A.ColumnCount != B.RowCount) throw new RuntimeException("Illegal matrix dimensions." + A.ColumnCount+ ", " + B.RowCount);
        
        MyMatrix C = new MyMatrix(A.RowCount, B.ColumnCount);
        for (int i = 0; i < C.RowCount; i++)
            for (int j = 0; j < C.ColumnCount; j++)
                for (int k = 0; k < A.ColumnCount; k++)
                    C.tarolo[i][j] += (A.tarolo[i][k] * B.tarolo[k][j]);
        return C;
    }
    
    public void show() {
    	for(int i = 0; i < RowCount; ++i) {
			for (int j = 0; j < ColumnCount; j++) {
				System.out.print(tarolo[i][j] );
				System.out.print(' ');
			}
			System.out.println();
		}
    }
 // return C = A + B
    public MyMatrix plus(MyMatrix B) {
        MyMatrix A = this;
        if (B.RowCount != A.RowCount || B.ColumnCount != A.ColumnCount) throw new RuntimeException("Illegal matrix dimensions.");
        MyMatrix C = new MyMatrix(RowCount, ColumnCount);
        for (int i = 0; i < RowCount; i++)
            for (int j = 0; j < ColumnCount; j++)
                C.tarolo[i][j] = A.tarolo[i][j] + B.tarolo[i][j];
        return C;
    }


    // return C = A - B
    public MyMatrix minus(MyMatrix B) {
        MyMatrix A = this;
        if (B.RowCount != A.RowCount || B.ColumnCount != A.ColumnCount) throw new RuntimeException("Illegal matrix dimensions.");
        MyMatrix C = new MyMatrix(RowCount, ColumnCount);
        for (int i = 0; i < RowCount; i++)
            for (int j = 0; j < ColumnCount; j++)
                C.tarolo[i][j] = A.tarolo[i][j] - B.tarolo[i][j];
        return C;
    }
	
}
