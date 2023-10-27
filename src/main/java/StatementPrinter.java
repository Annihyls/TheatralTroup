import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.NumberFormat;
import java.util.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class StatementPrinter {
  private final StringBuilder sb;
  private final Facturation fact;
  private final NumberFormat frmt;


  public StatementPrinter(Invoice invoice) {
    this.sb = new StringBuilder();
    this.frmt = NumberFormat.getCurrencyInstance(Locale.US);
    this.fact = new Facturation(invoice);
  }
  public String toText() {
    printClient();
    this.fact.calculFacture();
    printPlayAndPerfAudience();
    printTotalAmountAndCredits();
    printReduction();
    return this.sb.toString();
  }

  public String toHTML() throws TemplateException, IOException {
    this.fact.calculFacture();
    Configuration configuration = _FreeMarkerInitializer.getContext();
    Map<String, Object> input = new HashMap<>();
    input.put("facturation", this.fact);
    Writer output = new StringWriter();
    Template template = configuration.getTemplate("/htmlPrint.ftl");
    template.setOutputEncoding("UTF-8");
    template.process(input, output);
    return output.toString();
  }

  private void printClient() {
    this.sb.append("Statement for ");
    this.sb.append(this.fact.getCustomerName());
    this.sb.append("\n");
  }
  private void printPlayAndPerfAudience() {
    for(Performance perf : this.fact.getPerformances()) {
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
    this.sb.append(this.frmt.format(fact.getTotalAmount()));
    this.sb.append("\n");
    this.sb.append("You earned ");
    this.sb.append(this.fact.getVolumeCredits());
    this.sb.append(" credits\n");
  }

  private void printReduction(){
    if(this.fact.getWasAvailableForAReduction()) {
      this.sb.append("Your credit after the reduction: ");
      this.sb.append(this.fact.getCustomerCredits());
      this.sb.append("\n");
      this.sb.append("Amount you really paid: ");
      this.sb.append(this.frmt.format(fact.getTotalAmountAfterReduction()));
      this.sb.append("\n");
    } else {
      this.sb.append("Your total credits: ");
      this.sb.append(this.fact.getCustomerCredits());
      this.sb.append("\n");
    }
  }
}
