import java.text.NumberFormat;
import java.util.*;

public class StatementPrinter {
  private Invoice invoice;
  private StringBuilder sb;
  private Facturation fact;
  private NumberFormat frmt;
  public static final String TRAGEDY = "tragedy";
  public static final String COMEDY = "comedy";


  public StatementPrinter(Invoice invoice) {
    this.invoice = invoice;
    this.sb = new StringBuilder();
    this.frmt = NumberFormat.getCurrencyInstance(Locale.US);
    fact = new Facturation(invoice);
  }
  public String print() {
    printClient();
    fact.calculFacture();
    printPlayAndPerfAudience();
    printTotalAmountAndCredits();
    return this.sb.toString();
  }

  private void printClient() {
    this.sb.append("Statement for ");
    this.sb.append(this.invoice.customer);
    this.sb.append("\n");
  }
  private void printPlayAndPerfAudience() {
    for(Performance perf : this.invoice.performances) {
      this.sb.append("  ");
      this.sb.append(perf.play.name);
      this.sb.append(": ");
      this.sb.append(this.frmt.format(fact.amounts.get(perf)));
      this.sb.append(" (");
      this.sb.append(perf.audience);
      this.sb.append(" seats)\n");
    }
  }

  private void printTotalAmountAndCredits() {
    this.sb.append("Amount owed is ");
    this.sb.append(this.frmt.format(fact.totalAmount));
    this.sb.append("\n");
    this.sb.append("You earned ");
    this.sb.append(fact.volumeCredits);
    this.sb.append(" credits\n");
  }
}
