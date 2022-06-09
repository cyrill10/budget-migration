package ch.bader.budgetmigrator.adapter.mongo.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValueEnumDbo {
    private String name;
    private Integer value;
}
