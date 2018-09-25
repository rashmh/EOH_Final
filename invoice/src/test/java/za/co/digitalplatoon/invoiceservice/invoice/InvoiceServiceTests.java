package za.co.digitalplatoon.invoiceservice.invoice;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.digitalplatoon.invoiceservice.invoice.application.InvoiceService;
import za.co.digitalplatoon.invoiceservice.invoice.application.internal.DefaultInvoiceService;
import za.co.digitalplatoon.invoiceservice.invoice.domain.invoice.Invoice;
import za.co.digitalplatoon.invoiceservice.invoice.domain.invoice.InvoiceRepository;
import za.co.digitalplatoon.invoiceservice.invoice.domain.invoice.LineItem;
import za.co.digitalplatoon.invoiceservice.invoice.interfaces.InvoiceController;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class InvoiceServiceTests {

    @MockBean
    private InvoiceRepository invoiceRepository;

    private Invoice invoice;

    @TestConfiguration
    static class InvoiceServiceContextConfiguration {

        @Bean
        public InvoiceService invoiceService() {
            return new DefaultInvoiceService();
        }
    }

    @Autowired
    private InvoiceService invoiceService;

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

        List<Invoice> invoiceList = Collections.singletonList(invoice);

        Mockito.when(invoiceRepository.save(invoice))
                .thenReturn(invoice);

        Mockito.when(invoiceRepository.findById(invoice.getId()))
                .thenReturn(Optional.of(invoice));

        Mockito.when(invoiceRepository.findAll())
                .thenReturn(invoiceList);
    }

    @Test
    public void whenViewAllInvoice_AllInvoicesShouldBeFound() {
        assertThat(invoiceService.viewAllInvoices()).hasSize(1).contains(invoice);
    }

    @Test
    public void whenViewInvoice_InvoiceShouldBeFound() {
        Long invoiceId = 1L;
        Optional<Invoice> optionalInvoice = invoiceService.viewInvoice(invoiceId);
        assertThat(optionalInvoice.isPresent()).isTrue();
        assertThat(optionalInvoice.get().getId()).isEqualTo(invoiceId);
    }
}
