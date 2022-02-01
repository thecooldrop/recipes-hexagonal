package udarnicka.ingredients.crud.persistence.jpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ingredients")
@Setter
@Getter
class IngredientJpaEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;
}
