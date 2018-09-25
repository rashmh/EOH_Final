package za.co.digitalplatoon.invoiceservice.invoice.infrastructure.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.digitalplatoon.invoiceservice.invoice.domain.invoice.Invoice;
import za.co.digitalplatoon.invoiceservice.invoice.domain.invoice.InvoiceRepository;

import java.util.List;
import java.util.Optional;

@Component
public class AdapterForInvoiceRepository implements InvoiceRepository {

    @Autowired
    JpaInvoiceRepository jpaInvoiceRepository;

    @Override
    public Invoice save(Invoice invoice) {
        return jpaInvoiceRepository.save(invoice);
    }

    @Override
    public List<Invoice> findAll() {
        return jpaInvoiceRepository.findAll();
    }

    @Override
    public Optional<Invoice> findById(Long id) {
        return jpaInvoiceRepository.findById(id);
    }
}
