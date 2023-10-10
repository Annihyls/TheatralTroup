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
    this.sb.append("Statement for ");
    this.sb.append(this.invoice.customer);
    this.sb.append("\n");

    for (Performance perf : this.invoice.performances) {
      Play play = perf.play;
      float thisAmount = 0;

      switch (play.type) {
        case TRAGEDY:
          thisAmount = 400;
          if (perf.audience > 30) {
            thisAmount += 10 * (perf.audience - 30);
          }
          break;
        case COMEDY:
          thisAmount = 300;
          if (perf.audience > 20) {
            thisAmount += 100 + 5 * (perf.audience - 20);
          }
          thisAmount += 3 * perf.audience;
          break;
        default:
          throw new Error("unknown type: ${play.type}");
      }

      // add volume credits
      this.volumeCredits += Math.max(perf.audience - 30, 0);
      // add extra credit for every ten comedy attendees
      if (COMEDY.equals(play.type)) this.volumeCredits += Math.floor(perf.audience / 5);

      // print line for this order
      this.sb.append("  ");
      this.sb.append(play.name);
      this.sb.append(": ");
      this.sb.append(this.frmt.format(thisAmount));
      this.sb.append(" (");
      this.sb.append(perf.audience);
      this.sb.append(" seats)\n");
      this.totalAmount += thisAmount;
    }
    this.sb.append("Amount owed is ");
    this.sb.append(this.frmt.format(this.totalAmount));
    this.sb.append("\n");
    this.sb.append("You earned ");
    this.sb.append(volumeCredits);
    this.sb.append(" credits\n");
    return this.sb.toString();
  }

}
