package za.co.digitalplatoon.invoiceservice.invoice;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.digitalplatoon.invoiceservice.invoice.domain.invoice.Invoice;
import za.co.digitalplatoon.invoiceservice.invoice.domain.invoice.LineItem;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class InvoiceUnitTests {

    private Invoice invoice;

    @Before
    public void setup() {
        invoice = new Invoice();
        invoice.setId(1L);
        invoice.setVatRate(15L);

        LineItem lineItem1 = new LineItem();
        lineItem1.setId(1L);
        lineItem1.setQuantity(1L);
        lineItem1.setDescription("Pencil");
        lineItem1.setUnitPrice(BigDecimal.valueOf(2.00));

        LineItem lineItem2 = new LineItem();
        lineItem2.setId(2L);
        lineItem2.setQuantity(1L);
        lineItem2.setDescription("Pencil");
        lineItem2.setUnitPrice(BigDecimal.valueOf(2.00));

        invoice.getLineItems().add(lineItem1);
        invoice.getLineItems().add(lineItem2);
    }

    @Test
    public void givenInvoice_testThatSubTotalAndTotalAndVatIsCorrect() {
        assertThat(invoice.getVat().toPlainString()).isEqualTo("0.60");
        assertThat(invoice.getSubTotal().toPlainString()).isEqualTo("4.00");
        assertThat(invoice.getTotal().toPlainString()).isEqualTo("4.60");
    }

}
