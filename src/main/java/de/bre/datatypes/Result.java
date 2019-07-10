package de.bre.datatypes;

public class Result {
  private int row;
  private int column;
  private double result;

  public Result(int row, int column, double result) {
    this.row = row;
    this.column = column;
    this.result = result;
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }

  public double getResult() {
    return result;
  }
}
