import java.text.NumberFormat;
import java.util.*;

public class StatementPrinter {
  private Invoice invoice;
  private StringBuilder sb;
  private float totalAmount;
  private int volumeCredits;
  private NumberFormat frmt;
  public static final String TRAGEDY = "tragedy";
  public static final String COMEDY = "comedy";


  public StatementPrinter(Invoice invoice) {
    this.invoice = invoice;
    this.sb = new StringBuilder();
    this.frmt = NumberFormat.getCurrencyInstance(Locale.US);
    this.totalAmount = 0;
    this.volumeCredits = 0;
  }
  public String print() {
    printClient();
    for (Performance perf : this.invoice.performances) {
      Play play = perf.play;
      float amountForThisPlay = 0;
      switch (play.type) {
        case TRAGEDY:
          amountForThisPlay = 400;
          if (perf.audience > 30) {
            amountForThisPlay += 10 * (perf.audience - 30);
          }
          break;
        case COMEDY:
          amountForThisPlay = 300;
          if (perf.audience > 20) {
            amountForThisPlay += 100 + 5 * (perf.audience - 20);
          }
          amountForThisPlay += 3 * perf.audience;
          break;
        default:
          throw new Error("unknown type: ${play.type}");
      }
      // add volume credits
      this.volumeCredits += Math.max(perf.audience - 30, 0);
      // add extra credit for every ten comedy attendees
      if (COMEDY.equals(play.type)) this.volumeCredits += Math.floor(perf.audience / 5);

      printPlayAndPerfAudience(play, perf, amountForThisPlay);
      this.totalAmount += amountForThisPlay;
    }
    printTotalAmountAndCredits();
    return this.sb.toString();
  }

  private void printClient() {
    this.sb.append("Statement for ");
    this.sb.append(this.invoice.customer);
    this.sb.append("\n");
  }
  private void printPlayAndPerfAudience(Play play, Performance perf, float amountForThisPlay) {
    this.sb.append("  ");
    this.sb.append(play.name);
    this.sb.append(": ");
    this.sb.append(this.frmt.format(amountForThisPlay));
    this.sb.append(" (");
    this.sb.append(perf.audience);
    this.sb.append(" seats)\n");
  }

  private void printTotalAmountAndCredits() {
    this.sb.append("Amount owed is ");
    this.sb.append(this.frmt.format(this.totalAmount));
    this.sb.append("\n");
    this.sb.append("You earned ");
    this.sb.append(volumeCredits);
    this.sb.append(" credits\n");
  }
}
