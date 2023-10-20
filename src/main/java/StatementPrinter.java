import java.text.NumberFormat;
import java.util.*;

public class StatementPrinter {
  private final Invoice invoice;
  private final StringBuilder sb;
  private final Facturation fact;
  private final NumberFormat frmt;


  public StatementPrinter(Invoice invoice) {
    this.invoice = invoice;
    this.sb = new StringBuilder();
    this.frmt = NumberFormat.getCurrencyInstance(Locale.US);
    this.fact = new Facturation(invoice);
  }
  public String print() {
    printClient();
    this.fact.calculFacture();
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
    this.sb.append(this.fact.volumeCredits);
    this.sb.append(" credits\n");
  }
}
