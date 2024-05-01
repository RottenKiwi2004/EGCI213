package Lab_Ch3;

class w3_2_Matrix  
{
    public static void printMatrix(String name, int[][] matrix)  
    {
	System.out.println(name);
	for (int i=0; i < matrix.length; i++) 
	{
            for (int j=0; j < matrix[i].length; j++ )  
		System.out.printf("%3d ", matrix[i][j]);
            System.out.printf("\n");
        }
    }

    public static void main(String[] args)  
    {
	//----- (1) 2D-array with all initial values
	int[][] A = { {1,2,3}, {4,5,6} };

	//----- (2) allocate both dimensions at the same time
	int[][] B = new int[3][2];
	for (int i=0; i < B.length; i++)
            for (int j=0; j < B[i].length; j++)
		B[i][j] = A[j][i];                          // transpose

	//----- (3) allocate one dimension at a time
	int[][] C = new int[2][];
	for (int i=0; i < C.length; i++) 
	{
            C[i] = new int[3];
            for (int j=0; j < C[i].length; j++)  
		C[i][j] = A[i][j] * 10;
	}
		
	printMatrix("Original", A);
	printMatrix("Transpose", B);
	printMatrix("Increment", C);
    }
}
