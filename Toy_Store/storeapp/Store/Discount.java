package storeapp.Store;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Discount {
    private String name;
    private final DiscountType discountType;
    private final double value;
    private LocalDateTime lastDateApplied = null;

    public Discount(String name, DiscountType discountType, double value) {
        this.name = name;
        this.discountType = discountType;
        this.value = value;
    }

    public Discount(DiscountType discountType, double value) {
        this.discountType = discountType;
        this.value = value;
    }

    public void setAsAppliedNow() {
        lastDateApplied = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Discount)) return false;

        Discount discount = (Discount) o;

        if (Double.compare(discount.getValue(), getValue()) != 0) return false;
        return getDiscountType() == discount.getDiscountType();
    }

    @Override
    public String toString() {
        if(name != null) {
            if (lastDateApplied != null)
                return discountType + ", " +
                        value + ", " +
                        name + ", " +
                        lastDateApplied;
            return discountType + ", " +
                    value + ", " +
                    name;
        }
        return discountType + ", " +
                value;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public double getValue() {
        return value;
    }

}
