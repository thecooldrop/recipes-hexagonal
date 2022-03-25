package udarnicka.recipes.crud.persistence.jpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "recipe_steps")
@Setter
public class RecipeStepJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Getter
    private String step;

    @Column(name = "recipe_id")
    private Integer recipeId;

    @NotNull
    @Column(name = "step_order_index")
    private Integer stepOrderIndex;
}
