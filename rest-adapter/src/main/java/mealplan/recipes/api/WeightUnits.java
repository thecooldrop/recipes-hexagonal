package mealplan.recipes.api;

import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;

public enum WeightUnits {

    KILOGRAM {

        public Quantity<Mass> quantity(Integer quantity) {
            return Quantities.getQuantity(quantity, Units.KILOGRAM);
        }
    },

    GRAM {
        public Quantity<Mass> quantity(Integer quantity) {
            return Quantities.getQuantity(quantity, Units.GRAM);
        }
    };

    public abstract Quantity<Mass> quantity(Integer quantity);

}
