package de.bre;

import de.bre.datatypes.Result;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

class CalculationResults {

  private final int initPermits = 0;
  private final boolean fair = true;
  private Semaphore sem = new Semaphore(initPermits, fair);

  private final int countResultsExpected;

  private ConcurrentLinkedQueue<Result> resultsList = new ConcurrentLinkedQueue<>();

  CalculationResults(int countResultsExpected) {
    this.countResultsExpected = countResultsExpected;
  }

  void addResult(Result r) {
    resultsList.add(r);
    sem.release(1);
  }

  Result[] waitForResults() throws InterruptedException {
    sem.acquire(countResultsExpected);
    System.out.println("Anzahl Ergebnisse: " + resultsList.size());
    return resultsList.toArray(new Result[0]);
  }
}
