package za.co.digitalplatoon.invoiceservice.invoice.domain.invoice;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Invoice {

    @Id
    @Column(updatable = false, insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String client;
    @Positive
    private Long vatRate;
    private Date invoiceDate;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LineItem> lineItems = new HashSet<>();

    @PrePersist
    public void prePersist() {
        if (invoiceDate == null) invoiceDate = new Date();
    }

    public BigDecimal getSubTotal() {
        return lineItems.stream()
                .map(itm -> itm.getUnitPrice().multiply(BigDecimal.valueOf(itm.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getVat() {
        return BigDecimal.valueOf(vatRate / 100d)
                .multiply(getSubTotal())
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTotal() {
        return getSubTotal().add(getVat())
                .setScale(2, RoundingMode.HALF_UP);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Long getVatRate() {
        return vatRate;
    }

    public void setVatRate(Long vatRate) {
        this.vatRate = vatRate;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Set<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(Set<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(id, invoice.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

}
