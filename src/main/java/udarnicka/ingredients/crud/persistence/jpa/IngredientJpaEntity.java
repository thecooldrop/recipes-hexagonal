package udarnicka.ingredients.crud.persistence.jpa;

import lombok.Getter;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "ingredients")
@Getter
@Embeddable
public class IngredientJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "canonical_name", unique = true, nullable = false)
    @NotBlank
    private String canonicalName;


    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public void setName(@NonNull String name) {
        if(name.isBlank()) {
            throw new IllegalArgumentException("The name of the ingredient may not be blank");
        }
        if(canonicalName != null && !name.equalsIgnoreCase(canonicalName)) {
            throw new IllegalArgumentException("The ingredients can not change names");
        }
        this.name = name;
        this.canonicalName = name.toLowerCase();
    }

}
