/*************************************************************************
 *  Compilation:  javac Matrix.java
 *  Execution:    java Matrix
 *
 *  A bare-bones collection of static methods for manipulating
 *  matrices.
 *
 *************************************************************************/

package lambda.freespace.math;

public class Matrix {

    // return a random m-by-n matrix with values between 0 and 1
    public static double[][] random(int m, int n) {
        double[][] C = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = Math.random();
        return C;
    }

    // return n-by-n identity matrix I
    public static double[][] identity(int n) {
        double[][] I = new double[n][n];
        for (int i = 0; i < n; i++)
            I[i][i] = 1;
        return I;
    }

    // return x^T y
    public static double dot(double[] x, double[] y) {
        if (x.length != y.length) throw new RuntimeException("Illegal vector dimensions.");
        double sum = 0.0;
        for (int i = 0; i < x.length; i++)
            sum += x[i] * y[i];
        return sum;
    }

    // return C = A^T
    public static double[][] transpose(double[][] A) {
        int m = A.length;
        int n = A[0].length;
        double[][] C = new double[n][m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                C[j][i] = A[i][j];
        return C;
    }

    // return C = A + B
    public static double[][] add(double[][] A, double[][] B) {
        int m = A.length;
        int n = A[0].length;
        double[][] C = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] + B[i][j];
        return C;
    }

    // return C = A - B
    public static double[][] subtract(double[][] A, double[][] B) {
        int m = A.length;
        int n = A[0].length;
        double[][] C = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] - B[i][j];
        return C;
    }

    // return C = A * B
    public static double[][] multiply(double[][] A, double[][] B) {
        int mA = A.length;
        int nA = A[0].length;
        int mB = B.length;
        int nB = B[0].length;
        if (nA != mB) throw new RuntimeException("Illegal matrix dimensions.");
        double[][] C = new double[mA][nB];
        for (int i = 0; i < mA; i++)
            for (int j = 0; j < nB; j++)
                for (int k = 0; k < nA; k++)
                    C[i][j] += (A[i][k] * B[k][j]);
        return C;
    }

    // matrix-vector multiplication (y = A * x)
    public static double[] multiply(double[][] A, double[] x) {
        int m = A.length;
        int n = A[0].length;
        if (x.length != n) throw new RuntimeException("Illegal matrix dimensions.");
        double[] y = new double[m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                y[i] += (A[i][j] * x[j]);
        return y;
    }


    // vector-matrix multiplication (y = x^T A)
    public static double[] multiply(double[] x, double[][] A) {
        int m = A.length;
        int n = A[0].length;
        if (x.length != m) throw new RuntimeException("Illegal matrix dimensions.");
        double[] y = new double[n];
        for (int j = 0; j < n; j++)
            for (int i = 0; i < m; i++)
                y[j] += (A[i][j] * x[i]);
        return y;
    }
    
    // scalar-matrix multiplication
    public static double[][] multiply(double x, double[][] A) {
    	return multiply(A, x);
    }
    
    public static double[][] multiply(double[][] A, double x) {
    	int m = A.length;
    	int n = A[0].length;
    	double[][] B = new double[m][n];
    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n; j++) {
    			B[i][j] = A[i][j] * x;
    		}
    	}
    	return B;
    }
    
    public static double determinant(double[][] A) {
    	int m = A.length;
    	int n = A[0].length;
    	if (m != n) throw new RuntimeException("Illegal matrix dimensions.");
    	double det=0;
        if (n == 1) {
            det = A[0][0];
        } else if (n == 2) {
            det = A[0][0]*A[1][1] - A[1][0]*A[0][1];
        } else {
            det = 0;
            for (int j1 = 0; j1 < n; j1++) {
                double[][] B = new double[n-1][];
                for(int k = 0; k < n-1; k++) {
                    B[k] = new double[n-1];
                }
                for(int i = 1; i<n; i++) {
                    int j2 = 0;
                    for(int j = 0; j < n; j++) {
                        if(j == j1) continue;
                        B[i-1][j2] = A[i][j];
                        j2++;
                    }
                }
                det += Math.pow(-1.0, 1.0+j1+1.0) * A[0][j1] * determinant(B);
            }
        }
        return det;
    }
    
    // NOTE: assumes 3x3 matrix only
    public static double[][] inverse(double[][] A) {
    	double a = determinant(new double[][] {{A[2][2],A[3][2]},{A[2][3],A[3][3]}});
    	double b = determinant(new double[][] {{A[2][3],A[3][3]},{A[2][1],A[3][1]}});
    	double c = determinant(new double[][] {{A[2][1],A[3][1]},{A[2][2],A[3][2]}});
    	double d = determinant(new double[][] {{A[1][3],A[3][3]},{A[1][2],A[3][2]}});
    	double e = determinant(new double[][] {{A[1][1],A[3][1]},{A[1][3],A[3][3]}});
    	double f = determinant(new double[][] {{A[1][2],A[3][2]},{A[1][1],A[3][1]}});
    	double g = determinant(new double[][] {{A[1][2],A[2][2]},{A[1][3],A[2][3]}});
    	double h = determinant(new double[][] {{A[1][3],A[2][3]},{A[1][1],A[2][1]}});
    	double i = determinant(new double[][] {{A[1][1],A[2][1]},{A[1][2],A[2][2]}});
    	double[][] B = {{a,b,c},{d,e,f},{g,h,i}};
    	B = multiply(B, 1.0/(determinant(A)));
    	return B;
    }
    
    public static void print(double[][] A) {
    	int m = A.length;
    	int n = A[0].length;
    	System.out.print("{");
    	for (int i = 0; i < m; i++) {
    		System.out.print("{");
    		for (int j = 0; j < n; j++) {
    			System.out.print(A[i][j]);
    			if (j < n-1) {
        			System.out.print(", ");
        		}
    		}
    		System.out.print("}");
    		if (i < m-1) {
    			System.out.print(", ");
    		}
    	}
    	System.out.print("}");
    }

    // test client
    public static void main(String[] args) {
        System.out.println("D");
        System.out.println("--------------------");
        double[][] d = { { 1, 2, 3 }, { 4, 5, 6 }, { 9, 1, 3} };
        print(d);
        System.out.println();

        System.out.println("I");
        System.out.println("--------------------");
        double[][] c = Matrix.identity(5);
        print(c);
        System.out.println();

        System.out.println("A");
        System.out.println("--------------------");
        double[][] a = Matrix.random(5, 5);
        print(a);
        System.out.println();

        System.out.println("A^T");
        System.out.println("--------------------");
        double[][] b = Matrix.transpose(a);
        print(b);
        System.out.println();

        System.out.println("A + A^T");
        System.out.println("--------------------");
        double[][] e = Matrix.add(a, b);
        print(e);
        System.out.println();

        System.out.println("A * A^T");
        System.out.println("--------------------");
        double[][] f = Matrix.multiply(a, b);
        print(f);
        System.out.println();
    }
}