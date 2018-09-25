package za.co.digitalplatoon.invoiceservice.invoice.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import za.co.digitalplatoon.invoiceservice.invoice.application.InvoiceService;
import za.co.digitalplatoon.invoiceservice.invoice.domain.invoice.Invoice;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("invoices")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addInvoice(@RequestBody @NotNull Invoice invoice) {
        invoiceService.addInvoice(invoice);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Invoice> viewAllInvoices() {
        return invoiceService.viewAllInvoices();
    }

    @GetMapping(value = "/{invoiceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Invoice viewInvoice(@PathVariable("invoiceId") Long id) {
        return invoiceService.viewInvoice(id)
                .orElse(null);
    }
}
