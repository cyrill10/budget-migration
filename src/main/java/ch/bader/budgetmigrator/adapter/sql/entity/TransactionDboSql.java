package ch.bader.budgetmigrator.adapter.sql.entity;

import ch.bader.budgetmigrator.type.EnumUtil;
import ch.bader.budgetmigrator.type.PaymentStatus;
import ch.bader.budgetmigrator.type.PaymentType;
import ch.bader.budgetmigrator.type.TransactionIndication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "transaction")
public class TransactionDboSql implements Comparable<TransactionDboSql> {

    @Id
    @SequenceGenerator(name = "transaction_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
    private Integer id;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "credited_account_id")
    private VirtualAccountDboSql creditedAccount;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "debited_account_id")
    private VirtualAccountDboSql debitedAccount;

    @NonNull
    private LocalDate date;

    @NonNull
    private String description;

    @Basic
    private int paymentStatusValue;

    @Transient
    private PaymentStatus paymentStatus;

    @Basic
    private int indicationValue;

    @Transient
    private TransactionIndication indication;

    @Basic
    private int paymentTypeValue;

    @Transient
    private PaymentType paymentType;

    private BigDecimal budgetedAmount;

    private BigDecimal effectiveAmount;

    private LocalDateTime creationDate;

    private LocalDateTime updateDate;

    @PostLoad
    void fillTransient() {
        if (paymentStatusValue > 0) {
            this.paymentStatus = EnumUtil.getEnumForValue(PaymentStatus.class, paymentStatusValue);
        }
        if (paymentTypeValue > 0) {
            this.paymentType = EnumUtil.getEnumForValue(PaymentType.class, paymentTypeValue);
        }
        if (indicationValue > 0) {
            this.indication = EnumUtil.getEnumForValue(TransactionIndication.class, indicationValue);
        }
    }

    @PrePersist
    void fillPersistent() {
        if (paymentStatus != null) {
            this.paymentStatusValue = paymentStatus.getValue();
        }
        if (paymentType != null) {
            this.paymentTypeValue = paymentType.getValue();
        }
        if (indication != null) {
            this.indicationValue = indication.getValue();
        }
        creationDate = LocalDateTime.now();
    }

    @PreUpdate
    void fillUpdate() {
        updateDate = LocalDateTime.now();
    }

    @Override
    public int compareTo(TransactionDboSql o) {
        return this.id.compareTo(o.id);

    }

    public void updateEnums() {
        fillPersistent();
    }

    public void reloadEnums() {
        fillTransient();
    }
}
