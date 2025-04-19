package it.unipd.pdp2024.ex1;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/** Main class */
public class Main {

  public static void main(String[] args) throws InterruptedException {

    AtomicInteger eidx = new AtomicInteger(0);
    List<Element> program =
            Stream.of(
                            "4S",
                            "4T",
                            "3F",
                            "FCCoSp4",
                            "StSq3",
                            "4S+3T",
                            "4T+REP",
                            "3A+1Lo+3S",
                            "3Lo",
                            "3Lz",
                            "FCSSp4",
                            "ChSq1",
                            "CCoSp4")
                    .map(s -> new Element(eidx.incrementAndGet(), s))
                    .toList();

    // set up the rink
    var rink = new LinkedBlockingQueue<Element>();

    // set up the athlete
    Athlete hanyuYusuru = new Athlete(rink, program);

    // set up the rink video system and vote collection
    VideoSystem video = new VideoSystem(rink);
    var votes = new LinkedBlockingQueue<Vote>();

    // set up the judges and technical panel
    AtomicInteger jidx = new AtomicInteger(0);
    var judges =
            List.of(Nation.values()).stream()
                    .map(n -> new Judge(n, jidx.incrementAndGet(), video.screen(), votes))
                    .toList();
    var techPanel = new TechnicalPanel(video.screen(), votes);

    // set up the score board
    var score = new ScoreBoard(votes);

    // Avvio lo scoreboard per primo
    score.start();

    // Inizializza e avvia il printer
    var printer = new Printer(score);
    printer.start();

    // Avvia gli altri componenti
    judges.forEach(Judge::start);
    techPanel.start();
    video.start();
    hanyuYusuru.start();

    // check if exercise and scoring is over
    do {
      Thread.sleep(5000);
    } while (!(hanyuYusuru.done() && judges.stream().allMatch(Judge::done) && techPanel.done() && video.done()));

    // stop components that don't stop themselves
    printer.stop();
    score.stop();

    System.out.println("Esercizio completato. Punteggio finale: " + score.result.get().total());
  }
}