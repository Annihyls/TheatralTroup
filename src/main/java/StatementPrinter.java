import java.text.NumberFormat;
import java.util.*;

public class StatementPrinter {
  public static final String TRAGEDY = "tragedy";
  public static final String COMEDY = "comedy";

  public String print(Invoice invoice, HashMap<String, Play> plays) {
    int totalAmount = 0;
    int volumeCredits = 0;
    StringBuilder sb = new StringBuilder();
    sb.append("Statement for ");
    sb.append(invoice.customer);
    sb.append("\n");

    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

    for (Performance perf : invoice.performances) {
      Play play = plays.get(perf.playID);
      int thisAmount = 0;

      switch (play.type) {
        case TRAGEDY:
          thisAmount = 40000;
          if (perf.audience > 30) {
            thisAmount += 1000 * (perf.audience - 30);
          }
          break;
        case COMEDY:
          thisAmount = 30000;
          if (perf.audience > 20) {
            thisAmount += 10000 + 500 * (perf.audience - 20);
          }
          thisAmount += 300 * perf.audience;
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
      sb.append(frmt.format(thisAmount / 100));
      sb.append(" (");
      sb.append(perf.audience);
      sb.append(" seats)\n");
      totalAmount += thisAmount;
    }
    sb.append("Amount owed is ");
    sb.append(frmt.format(totalAmount / 100));
    sb.append("\n");
    sb.append("You earned ");
    sb.append(volumeCredits);
    sb.append(" credits\n");
    return sb.toString();
  }

}
