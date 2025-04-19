package it.unipd.pdp2024.ex1;

/** Prints the partial score at regular intervals. */
public class Printer implements Runnable {

  private final ScoreBoard score;
  private boolean stop;
  private Thread printing;

  Printer(ScoreBoard score) {
    this.score = score;
    printing = new Thread(this);
  }

  @Override
  public void run() {
    try {
      while (!stop) {
        // Print the result. Wait 1 second.
        if (score.result != null) {
          CurrentResult currentResult = score.result.get();
          if (currentResult != null) {
            System.out.println(ScoreBoard.format(currentResult.elements(), currentResult.total()));
          }
        }

        // Attesa di un secondo prima della prossima stampa
        Thread.sleep(1000);
      }
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    }
  }

  /** Starts the printer */
  void start() {
    this.printing.start();
  }

  /** Asks the printer to stop */
  void stop() {
    this.stop = true;
  }
}