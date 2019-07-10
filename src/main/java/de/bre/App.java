package de.bre;

import de.bre.datatypes.Matrices;
import de.bre.datatypes.Result;
import de.bre.threadpool.MyThreadPoolExecutor;

public class App {

  public static void main(String[] args) {
    double[][] matrix2x3 = new double[][]{{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}};
    double[][] matrix3x3 = new double[][]{{1.0, 4.0, 5.0}, {2.0, 5.0, 8.0}, {3.0, 6.0, 7.0}};

    double[][] randMatrixOne = createRandomMatrix(4000, 10000, 100, 0);
    //printlnMatrix(randMatrixOne);
    double[][] randMatrixTwo = createRandomMatrix(10000, 40000, 100, 0);
    //printlnMatrix(randMatrixTwo);

    try {
      multiplyMatrices(randMatrixOne, randMatrixTwo);
      printlnMatrix(multiplyMatrices(matrix2x3, matrix3x3));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static double[][] multiplyMatrices(double[][] matrixOne, double[][] matrixTwo)
      throws InterruptedException {
    new Matrices();
    Matrices.matrixOne = matrixOne;
    Matrices.matrixTwo = matrixTwo;
    int countResultsExpected = Matrices.matrixOne.length * Matrices.matrixTwo[0].length;

    final int noOfThreads = 20;
    final int maxNoOfTasks = countResultsExpected + 100;
    MyThreadPoolExecutor mtpe = new MyThreadPoolExecutor(noOfThreads, maxNoOfTasks);
    CalculationResults calculationResults = new CalculationResults(countResultsExpected);

    for (int i = 0; i < matrixOne.length; i++) {
      for (int j = 0; j < matrixTwo[0].length; j++) {
        final MultiplyRowAndColumn pn = new MultiplyRowAndColumn(i, j, calculationResults);
        mtpe.execute(pn);
      }
    }
    double resultMatrix[][] = new double[Matrices.matrixOne.length][Matrices.matrixTwo[0].length];

    Result[] scalarResults = calculationResults.waitForResults();
    for (Result scalarResult : scalarResults) {
      resultMatrix[scalarResult.getRow()][scalarResult.getColumn()] = scalarResult.getResult();
    }

    mtpe.stop();
    return resultMatrix;
  }

  private static void printlnMatrix(double[][] matrix) {
    for (int column = 0; column < matrix.length; column++) {
      for (int row = 0; row < matrix[0].length; row++) {
        System.out.print(matrix[column][row] + " ");
      }
      System.out.println();
    }
    System.out.println("--------------------");
  }

  private static double[][] createRandomMatrix(int rowLen, int columnLen, double range,
      double min) {
    double[][] matrix = new double[columnLen][rowLen];
    for (int i = 0; i < matrix[0].length; i++) {
      for (int j = 0; j < matrix.length; j++) {
        matrix[j][i] = Math.random() * range + min;
      }
    }
    return matrix;
  }
}
