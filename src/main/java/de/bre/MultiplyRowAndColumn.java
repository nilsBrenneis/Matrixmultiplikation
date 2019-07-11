package de.bre;

import de.bre.datatypes.Matrices;
import de.bre.datatypes.Result;

public class MultiplyRowAndColumn implements Runnable {

  private int row;
  private int column;
  private CalculationResults primesResult;


  MultiplyRowAndColumn(int row, int column, CalculationResults primesResult) {
    this.row = row;
    this.column = column;
    this.primesResult = primesResult;
  }

  @Override
  public void run() {
    double scalarResult = 0;
    for (int i = 0; i < Matrices.matrixOne[0].length; i++) {
      scalarResult += Matrices.matrixOne[row][i] * Matrices.matrixTwo[i][column];
    }
    primesResult.addResult(new Result(row, column, scalarResult));
  }
}
