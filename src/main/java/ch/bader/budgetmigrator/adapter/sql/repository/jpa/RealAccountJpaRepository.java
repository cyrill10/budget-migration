package ch.bader.budgetmigrator.adapter.sql.repository.jpa;

import ch.bader.budgetmigrator.adapter.sql.entity.RealAccountDboSql;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealAccountJpaRepository extends JpaRepository<RealAccountDboSql, Integer> {

}
