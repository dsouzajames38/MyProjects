package MyPracticals.Practical3;

import MatrixC.IntArray;
import MatrixC.MatrixInterface;
import MatrixC.MatrixServiceService;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class MatrixClient {
	
        /** Main function **/

        public static void main (String[] args) 

        {

            Scanner scan = new Scanner(System.in);

            System.out.println("Strassen Multiplication Algorithm Test\n");

            /** Make an object of Strassen class **/

            //Strassen s = new Strassen();

            System.out.println("Enter order n :");

            int N = scan.nextInt();

            /** Accept two 2d matrices **/

            System.out.println("Enter N order matrix 1\n");

            int[][] A = new int[N][N];

            for (int i = 0; i < N; i++)

                for (int j = 0; j < N; j++)

                    A[i][j] = scan.nextInt();
     

            System.out.println("Enter N order matrix 2\n");

            int[][] B = new int[N][N];

            for (int i = 0; i < N; i++)

                for (int j = 0; j < N; j++)

                    B[i][j] = scan.nextInt();

     

            //call the service multiply which accepts A and B
			//int[][] C = s.multiply(A, B);
			MatrixServiceService service = new MatrixServiceService();
			MatrixInterface port = service.getMatrixServicePort();
		
			
			List<IntArray> liaC = port.multiply(ToListIntArray(A,N),ToListIntArray(B,N));
			int[][] C  = toIntArray(liaC,N);
			
            System.out.println("\nProduct of matrices A and  B : ");

            for (int i = 0; i < N; i++)
            {

                for (int j = 0; j < N; j++)

                    System.out.print(C[i][j] +" ");

                System.out.println();

            }

        }
		
		public static int[][] toIntArray(List<IntArray> liaC, int dimension)
		{
			int[][] arr = new int[dimension][dimension];
			for (int i = 0; i < dimension; i++)
            {
				IntArray ia = liaC.get(i);
				for (int j = 0; j < dimension; j++)
                    arr[i][j] = ia.getItem().get(j);

            }
			return arr;
		}
		
		public static List<IntArray> ToListIntArray(int[][] arr, int dimension)
		{
			List<IntArray> lia = new ArrayList<IntArray>(dimension);
			
			for (int i = 0; i < dimension; i++)
            {
                IntArray ia = new IntArray();
				for (int j = 0; j < dimension; j++)
                    ia.getItem().add(arr[i][j]);

                lia.add(ia);

            }
			
			return lia;
		}

}