import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.approvaltests.Approvals.verify;

public class StatementPrinterTests {

    @Test
    void exampleStatement() {
        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance(new Play("Hamlet", "tragedy"), 55),
                new Performance(new Play("As You Like It", "comedy"), 35),
                new Performance(new Play("Othello", "tragedy"), 40),
                new Performance(new Play("Vincent le médisant", "comedy"), 5),
                new Performance(new Play("Dernière Fantaisie 15", "tragedy"), 15)));

        StatementPrinter statementPrinter = new StatementPrinter();
        var result = statementPrinter.print(invoice);

        verify(result);
    }

    @Test
    void statementWithNewPlayTypes() {
        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance(new Play("Henry V", "history"), 53),
                new Performance(new Play("As You Like It", "pastoral"), 55)));

        StatementPrinter statementPrinter = new StatementPrinter();
        Assertions.assertThrows(Error.class, () -> {
            statementPrinter.print(invoice);
        });
    }
}
