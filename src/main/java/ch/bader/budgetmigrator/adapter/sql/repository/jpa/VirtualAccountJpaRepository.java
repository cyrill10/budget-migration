package ch.bader.budgetmigrator.adapter.sql.repository.jpa;

import ch.bader.budgetmigrator.adapter.sql.entity.VirtualAccountDboSql;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VirtualAccountJpaRepository extends JpaRepository<VirtualAccountDboSql, Integer> {

    List<VirtualAccountDboSql> findAllByUnderlyingAccountId(int id);

}
