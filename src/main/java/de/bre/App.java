package de.bre;

import de.bre.datatypes.Matrices;
import de.bre.datatypes.Result;
import de.bre.threadpool.MyThreadPoolExecutor;
import java.util.Random;

public class App {

  public static void main(String[] args) {

    double[][] randMatrixOne = createRandomMatrix(3000, 4000, 100, 0);
    double[][] randMatrixTwo = createRandomMatrix(4000, 3000, 100, 0);

    try {
      multiplyMatrices(randMatrixOne, randMatrixTwo);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  static double[][] multiplyMatrices(double[][] matrixOne, double[][] matrixTwo)
      throws InterruptedException {
    new Matrices();
    Matrices.matrixOne = matrixOne;
    Matrices.matrixTwo = matrixTwo;
    int countResultsExpected = Matrices.matrixOne.length * Matrices.matrixTwo[0].length;

    final int noOfThreads = 16;
    final int maxNoOfTasks = countResultsExpected + 100;
    MyThreadPoolExecutor mtpe = new MyThreadPoolExecutor(noOfThreads, maxNoOfTasks);
    CalculationResults calculationResults = new CalculationResults(countResultsExpected);

    for (int i = 0; i < matrixOne.length; i++) {
      for (int j = 0; j < matrixTwo[0].length; j++) {
        final MultiplyRowAndColumn pn = new MultiplyRowAndColumn(i, j, calculationResults);
        mtpe.execute(pn);
      }
    }
    double[][] resultMatrix = new double[Matrices.matrixOne.length][Matrices.matrixTwo[0].length];

    Result[] scalarResults = calculationResults.waitForResults();
    for (Result scalarResult : scalarResults) {
      resultMatrix[scalarResult.getRow()][scalarResult.getColumn()] = scalarResult.getResult();
    }

    mtpe.stop();
    return resultMatrix;
  }

  static void printlnMatrix(double[][] matrix) {
    for (double[] doubles : matrix) {
      for (int row = 0; row < matrix[0].length; row++) {
        System.out.print(doubles[row] + " ");
      }
      System.out.println();
    }
    System.out.println("--------------------");
  }

  private static double[][] createRandomMatrix(int rowLen, int columnLen, double range,
      double min) {
    Random random = new Random(42);
    double[][] matrix = new double[columnLen][rowLen];
    for (int i = 0; i < matrix[0].length; i++) {
      for (int j = 0; j < matrix.length; j++) {
        matrix[j][i] = random.nextDouble() * range + min;
      }
    }
    return matrix;
  }
}
