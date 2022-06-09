package ch.bader.budgetmigrator;

import ch.bader.budgetmigrator.adapter.mongo.entity.RealAccountDbo;
import ch.bader.budgetmigrator.adapter.mongo.entity.ValueEnumDbo;
import ch.bader.budgetmigrator.adapter.sql.entity.RealAccountDboSql;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    RealAccountDbo mapRealAccount(RealAccountDboSql accountDboSql) {
        return RealAccountDbo.builder()
                             .name(accountDboSql.getName())
                             .accountType(ValueEnumDbo.builder()
                                                      .name(accountDboSql.getAccountType().getName())
                                                      .value(accountDboSql.getAccountType().getValue())
                                                      .build())
                             .build();
    }
}
