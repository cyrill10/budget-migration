package ch.bader.budgetmigrator.adapter.sql.entity;

import ch.bader.budgetmigrator.type.ClosingProcessStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "closing_process", uniqueConstraints = {@UniqueConstraint(name = "UniqueYearAndMonth", columnNames = {"year", "month"})})
public class ClosingProcessDboSql {

    @Id
    @SequenceGenerator(name = "closing_process_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "closing_process_seq")
    private Integer id;

    private int year;

    private int month;

    @PreUpdate
    void fillUpdate() {
        updateDate = LocalDateTime.now();
    }

    private ClosingProcessStatus uploadStatus;

    private ClosingProcessStatus manualEntryStatus;

    private LocalDateTime creationDate;

    private LocalDateTime updateDate;
}
