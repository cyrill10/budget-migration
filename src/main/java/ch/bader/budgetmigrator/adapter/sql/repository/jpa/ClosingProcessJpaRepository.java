package ch.bader.budgetmigrator.adapter.sql.repository.jpa;

import ch.bader.budgetmigrator.adapter.sql.entity.ClosingProcessDboSql;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClosingProcessJpaRepository extends JpaRepository<ClosingProcessDboSql, Integer> {

    ClosingProcessDboSql findClosingProcessByYearAndMonth(Integer year, Integer month);

}
