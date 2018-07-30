import java.util.Arrays;

/**
 * 
 */

/**
 * @author Andrey Suvorov, Kean University
 *
 */
public class DepGradePrediction {

	
	
	/**
	 * take the data samples and create an array with the corresponding sums of the sample clusters
	 * Avg = divide each value by the amount of data cluster samples
	 * create DeviationMatrix
	 * "centralized data" = OriginalData-Avg
	 * create covariance matrix
	 * "transpose of centralized data" * centralized data matrix
	 * 						OR
	 * "transpose of DeviaionMatrix" * DeviaionMatrix
	 * result is the covariance matrix that gives us a correlation between subjects and grades 
	 * positive indicates greater correlation, negative indicates lesser or negative correlation.
	 */
	//{Math, Physics, English, Chemistry, History}
	//{90, 80, 40, 85, 45; 90, 60, 80, 70, 75; 60, 50, 70 55, 80; 30, 40, 70, 50 81; 30, 20, 90, 35 93} students by subject
	public static void main(String[] args) 
	{
		int n=5, b =3;
		double[][] StudentsAndScores  = new double[][]{
							{90, 80, 40},
							{90, 60, 80}, 
							{60, 50, 70}, 
							{30, 40, 70}, 
							{30, 20, 90}};
		double[][] AVGStudentsAndScores  = new double[n][b];
		double[][] CentStudentsAndScores  = new double[n][b];
		
		//may combine the next two steps for efficiency
		//get AVG of data
		for(int i=0; i<StudentsAndScores.length; i++)
		{
			for(int k=0; k<StudentsAndScores[i].length; k++)
			{
				AVGStudentsAndScores[i][k] = StudentsAndScores[i][k]/StudentsAndScores[i].length;
			}
		}
		//centralize the data
		for(int i=0; i<CentStudentsAndScores.length; i++)
		{
			for(int k=0; k<CentStudentsAndScores[i].length; k++)
			{
				CentStudentsAndScores[i][k] = StudentsAndScores[i][k]-AVGStudentsAndScores[i][k];
			}
		}
		
		//generate a covariance matrix
		//(R transpose)*(R)
		double[][] covarianceMatrix = new double[CentStudentsAndScores.length][CentStudentsAndScores.length];
		
		double[][] matrixT = new double[CentStudentsAndScores[0].length][CentStudentsAndScores.length];
		for(int i=0; i<CentStudentsAndScores.length; i++)
		{
			System.out.print("\ni:"+i);
			for(int j=0; j<CentStudentsAndScores[i].length; j++)
			{
				System.out.print("j:"+j);
				matrixT[j][i] = CentStudentsAndScores[i][j];
			}
		}
		//for every value in the scatter matrix
		for(int a=0; a<covarianceMatrix.length; a++)
		{
			System.out.print("\na:"+a);
				//compute corresponding value of RT*R result
				for(int i=0; i<CentStudentsAndScores.length; i++)
				{
					System.out.print("\ni:"+i);
					for(int j=0; j<CentStudentsAndScores[i].length; j++)
					{
						System.out.println("j:"+j);
						System.out.println("matrix  "+CentStudentsAndScores[i][j]);
						System.out.println("matrixT "+matrixT[j][i]);
						covarianceMatrix[a][i] += (CentStudentsAndScores[a][j]*matrixT[j][i]);
					}
				}
		}
		
		for(int i=0; i<CentStudentsAndScores.length; i++)
		{
			System.out.print("\ni:"+i);
			for(int j=0; j<covarianceMatrix[i].length; j++)
			{
				System.out.print("j:"+j);
				covarianceMatrix[i][j] /= CentStudentsAndScores[0].length-1;
			}
		}
		System.out.println();
		for(int i =0; i<covarianceMatrix.length; i++)
		{
			System.out.print("scatterMatrix row "+i+": "+Arrays.toString(covarianceMatrix[i])+"\n");
		}
	
		
	}

}
