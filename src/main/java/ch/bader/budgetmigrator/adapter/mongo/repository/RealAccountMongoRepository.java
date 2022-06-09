package ch.bader.budgetmigrator.adapter.mongo.repository;

import ch.bader.budgetmigrator.adapter.mongo.entity.RealAccountDbo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RealAccountMongoRepository extends MongoRepository<RealAccountDbo, String> {

}
