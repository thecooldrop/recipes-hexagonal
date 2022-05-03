package udarnicka.recipes.crud.persistence.jpa;


import lombok.Getter;

import javax.persistence.*;

@Embeddable
@Table
@Getter
public class IngredientMeasurementUnitJpaEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private IngredientTypeJpaEntity typeId;

    private String unit;
}
