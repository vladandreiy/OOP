package storeapp.Store;

public class Manufacturer {
    private final String name;
    private int countProducts;

    public Manufacturer(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + "," +
                countProducts;
    }

    public String getName() {
        return name;
    }

    public int getCountProducts() {
        return countProducts;
    }

    public void setCountProducts(int countProducts) {
        this.countProducts = countProducts;
    }
}
