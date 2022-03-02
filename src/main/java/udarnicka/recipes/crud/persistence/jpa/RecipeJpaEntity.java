package udarnicka.recipes.crud.persistence.jpa;

import udarnicka.common.CanonicalName;
import udarnicka.common.HasCanonicalName;
import udarnicka.common.HasId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
class RecipeJpaEntity implements HasId<Integer>, HasCanonicalName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "canonical_name", nullable = false)
    @NotBlank
    private String canonicalName;

    @Override
    public CanonicalName getCanonicalName() {
        return null;
    }

    @Override
    public Integer getId() {
        return null;
    }
}
