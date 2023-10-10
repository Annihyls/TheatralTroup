import java.text.NumberFormat;
import java.util.*;

public class StatementPrinter {
  public static final String TRAGEDY = "tragedy";
  public static final String COMEDY = "comedy";

  public String print(Invoice invoice, HashMap<String, Play> plays) {
    float totalAmount = 0;
    int volumeCredits = 0;
    StringBuilder sb = new StringBuilder();
    sb.append("Statement for ");
    sb.append(invoice.customer);
    sb.append("\n");

    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

    for (Performance perf : invoice.performances) {
      Play play = plays.get(perf.playID);
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
      volumeCredits += Math.max(perf.audience - 30, 0);
      // add extra credit for every ten comedy attendees
      if (COMEDY.equals(play.type)) volumeCredits += Math.floor(perf.audience / 5);

      // print line for this order
      sb.append("  ");
      sb.append(play.name);
      sb.append(": ");
      sb.append(frmt.format(thisAmount));
      sb.append(" (");
      sb.append(perf.audience);
      sb.append(" seats)\n");
      totalAmount += thisAmount;
    }
    sb.append("Amount owed is ");
    sb.append(frmt.format(totalAmount));
    sb.append("\n");
    sb.append("You earned ");
    sb.append(volumeCredits);
    sb.append(" credits\n");
    return sb.toString();
  }

}
