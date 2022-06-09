package ch.bader.budgetmigrator.adapter.sql.repository.jpa;

import ch.bader.budgetmigrator.adapter.sql.entity.ClosingProcessDboSql;
import ch.bader.budgetmigrator.adapter.sql.entity.ScannedTransactionDboSql;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScannedTransactionJpaRepository extends JpaRepository<ScannedTransactionDboSql, Integer> {

    List<ScannedTransactionDboSql> findAllByClosingProcessOrderByDateAsc(ClosingProcessDboSql closingProcess);

}
