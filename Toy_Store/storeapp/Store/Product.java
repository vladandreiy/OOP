package storeapp.Store;

public class Product {
    private final String uniqueId;
    private final String name;
    private final Manufacturer manufacturer;
    private double price;
    private final int quantity;

    private Product(ProductBuilder builder) {
        this.uniqueId = builder.uniqueId;
        this.name = builder.name;
        this.manufacturer = builder.manufacturer;
        this.price = builder.price;
        this.quantity = builder.quantity;
    }

    public static class ProductBuilder {
        private final String uniqueId;
        private String name;
        private Manufacturer manufacturer;
        private double price;
        private int quantity;

        public ProductBuilder(String uniqueId) {
            this.uniqueId = uniqueId;
        }

        public ProductBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public ProductBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder setManufacturer(Manufacturer manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        public ProductBuilder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Product build() {
            return new Product(this);
        }

    }

    @Override
    public String toString() {
        return uniqueId + ',' +
                name + ',' +
                manufacturer.getName() + "," +
                price + ',' +
                quantity;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public String getName() {
        return name;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }
}
