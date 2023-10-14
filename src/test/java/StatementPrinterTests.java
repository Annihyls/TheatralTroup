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
                new Performance(new Play("Hamlet", Play.Type.TRAGEDY), 55),
                new Performance(new Play("As You Like It", Play.Type.COMEDY), 35),
                new Performance(new Play("Othello", Play.Type.TRAGEDY), 40),
                new Performance(new Play("Vincent le médisant", Play.Type.COMEDY), 5),
                new Performance(new Play("Dernière Fantaisie 15", Play.Type.TRAGEDY), 15)));

        StatementPrinter statementPrinter = new StatementPrinter(invoice);
        var result = statementPrinter.print();

        verify(result);
    }

    @Test
    void statementWithNewPlayTypes() {
        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance(new Play("Henry V", Play.Type.HISTORY), 53),
                new Performance(new Play("As You Like It", Play.Type.PASTORAL), 55)));

        StatementPrinter statementPrinter = new StatementPrinter(invoice);
        Assertions.assertThrows(Error.class, statementPrinter::print);
    }
}
