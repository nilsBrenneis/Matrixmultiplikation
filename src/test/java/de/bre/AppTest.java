package de.bre;

import static de.bre.App.multiplyMatrices;
import static de.bre.App.printlnMatrix;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for matrix multiplication App.
 */
public class AppTest {

  /**
   * Rigorous Test :-)
   */
  @Test
  public void assertThatArraysAreEqualGivenSmallerFirstMatrix() {
    double[][] matrix2x3 = new double[][]{{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}};
    double[][] matrix3x4 = new double[][]{{1.0, 4.0, 5.0, 8.0}, {2.0, 5.0, 8.0, 3.0}, {3.0, 6.0, 7.0, 9.0}};

    try {
      double[][] result = multiplyMatrices(matrix2x3, matrix3x4);
      double[][] expectedResult = new double[][]{{14.0, 32.0, 42.0, 41.0}, {32.0, 77.0, 102.0, 101.0 }};
      assertArrayEquals(result, expectedResult);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void assertThatArraysAreEqualGivenSmallerSecondMatrix() {
    double[][] matrix4x3 = new double[][]{{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}, {5.0, 11.0, 12.0}, {15.0, 70.0, 6.0}};
    double[][] matrix3x2 = new double[][]{{1.0, 4.0,}, {2.0, 5.0}, {3.0, 6.0}};

    try {
      double[][] result = multiplyMatrices(matrix4x3, matrix3x2);
      double[][] expectedResult = new double[][]{{14.0, 32.0}, {32.0, 77.0}, {63.0, 147.0}, {173.0, 446.0},};
      assertArrayEquals(result, expectedResult);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void assertThatArraysAreEqualGivenEqualSizedMatrices() {
    double[][] matrix3x3 = new double[][]{{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}, {8.0, 15.0, 5.0}};
    double[][] scndMatrix3x3 = new double[][]{{1.0, 4.0, 5.0}, {2.0, 5.0, 8.0}, {3.0, 6.0, 7.0}};

    try {
      double[][] result = multiplyMatrices(matrix3x3, scndMatrix3x3);
      double[][] expectedResult = new double[][]{{14.0, 32.0, 42.0}, {32.0, 77.0, 102.0}, {53.0, 137.0, 195.0}};
      assertArrayEquals(result, expectedResult);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
