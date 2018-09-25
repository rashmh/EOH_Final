package za.co.digitalplatoon.invoiceservice.invoice.application.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.digitalplatoon.invoiceservice.invoice.application.InvoiceService;
import za.co.digitalplatoon.invoiceservice.invoice.domain.invoice.Invoice;
import za.co.digitalplatoon.invoiceservice.invoice.domain.invoice.InvoiceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultInvoiceService implements InvoiceService {

    @Autowired
    InvoiceRepository repository;

    @Override
    public void addInvoice(Invoice invoice) {
        repository.save(invoice);
    }

    @Override
    public List<Invoice> viewAllInvoices() {
        return repository.findAll();
    }

    @Override
    public Optional<Invoice> viewInvoice(Long id) {
        return repository.findById(id);
    }
}
