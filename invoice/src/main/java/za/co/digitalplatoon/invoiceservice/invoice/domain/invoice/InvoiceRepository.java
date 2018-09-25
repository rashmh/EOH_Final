package za.co.digitalplatoon.invoiceservice.invoice.domain.invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository {

    Invoice save(Invoice invoice);

    List<Invoice> findAll();

    Optional<Invoice> findById(Long id);
}
