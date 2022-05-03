package udarnicka.recipes.crud.persistence.jpa;

import lombok.Getter;

import javax.persistence.*;

// TODO: I need to complete the implementation of this entity. It is supposed to map to recipe_ingredient_types table
@Entity
@Embeddable
@Table(name = "recipe_ingredient_types")
@Getter
public class IngredientTypeJpaEntity {

    @Id
    private Integer id;

    @Enumerated(EnumType.STRING)
    private IngredientType type;
}
