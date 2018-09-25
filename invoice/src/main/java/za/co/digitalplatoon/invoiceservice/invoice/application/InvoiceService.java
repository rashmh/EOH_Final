package za.co.digitalplatoon.invoiceservice.invoice.application;

import za.co.digitalplatoon.invoiceservice.invoice.domain.invoice.Invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {

    void addInvoice(Invoice invoice);

    List<Invoice> viewAllInvoices();

    Optional<Invoice> viewInvoice(Long id);
}
