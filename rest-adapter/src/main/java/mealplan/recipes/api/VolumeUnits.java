package mealplan.recipes.api;

import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import javax.measure.Quantity;
import javax.measure.quantity.Volume;

public enum VolumeUnits {
    LITER {
        public Quantity<Volume> quantity(Integer quantity) {
            return Quantities.getQuantity(quantity, Units.LITRE);
        }
    },
    MILLILITER {
        public Quantity<Volume> quantity(Integer quantity) {
            return Quantities.getQuantity(quantity, Units.LITRE).divide(1000);
        }
    };

    public abstract Quantity<Volume> quantity(Integer quantity);

}
