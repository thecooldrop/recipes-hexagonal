package udarnicka.recipes.crud.persistence.jpa;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import udarnicka.common.CanonicalName;
import udarnicka.common.HasCanonicalName;
import udarnicka.common.HasId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "recipes")
class RecipeJpaEntity implements HasId<Integer>, HasCanonicalName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private Integer id;

    @Column(name = "name", nullable = false)
    @NotBlank
    @Getter
    private String name;

    @Column(name = "canonical_name", nullable = false)
    @NotBlank
    private String canonicalName;

    @OneToMany
    @OrderBy("stepOrderIndex ASC")
    @Getter
    private List<RecipeStepJpaEntity> recipeSteps;

    @OneToMany
    @Getter
    private Set<RecipeMeasuredIngredientJpaEntity> recipeMeasuredIngredients;

    private transient CanonicalName transientCanonicalName;

    @Override
    public CanonicalName getCanonicalName() {
        if(name == null) {
            return null;
        }

        if(transientCanonicalName == null) {
            transientCanonicalName = new CanonicalName(name);
        }
        return transientCanonicalName;
    }

    @Override
    public Integer getId() {
        return id;
    }

    void setName(@NonNull String name) {
        if(name.isBlank()) {
            throw new IllegalArgumentException("The name of the recipe may not be null or blank");
        }
        if(this.name != null) {
            if(!transientCanonicalName.equals(new CanonicalName(name))) {
                throw new IllegalArgumentException("The name of the recipe can not be changed. Only the casing of the letters in the recipe name may change.");
            }
        }
        transientCanonicalName = transientCanonicalName == null ? new CanonicalName(name) : transientCanonicalName;
        this.name = transientCanonicalName.getOriginal();
        this.canonicalName = transientCanonicalName.getCanonical();
    }

}
