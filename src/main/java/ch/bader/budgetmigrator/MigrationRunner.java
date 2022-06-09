package ch.bader.budgetmigrator;

import ch.bader.budgetmigrator.adapter.mongo.entity.RealAccountDbo;
import ch.bader.budgetmigrator.adapter.mongo.repository.RealAccountMongoRepository;
import ch.bader.budgetmigrator.adapter.sql.entity.RealAccountDboSql;
import ch.bader.budgetmigrator.adapter.sql.repository.jpa.RealAccountJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MigrationRunner implements CommandLineRunner {

    @Autowired
    RealAccountMongoRepository realAccountMongoRepository;

    @Autowired
    RealAccountJpaRepository realAccountJpaRepository;

    @Autowired
    Mapper mapper;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("started");

        List<RealAccountDboSql> realAccountList = realAccountJpaRepository.findAll();

        Map<Integer, String> realAccountIdMap = new HashMap<>();

        realAccountList.forEach(sql -> {
            RealAccountDbo dbo = mapper.mapRealAccount(sql);
            dbo = realAccountMongoRepository.save(dbo);
            realAccountIdMap.put(sql.getId(), dbo.getId());
        });
        System.out.println("Map {}" + realAccountIdMap);
    }
}
