package za.co.digitalplatoon.invoiceservice.invoice.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.digitalplatoon.invoiceservice.invoice.domain.invoice.Invoice;

public interface JpaInvoiceRepository extends JpaRepository<Invoice, Long> {
}
