package es.geeksusma.warehouse.item;

class Item {
    final String serialNumber;
    final String name;
    final String description;
    final Integer stock;

    public Item(String serialNumber, String name, String description, Integer stock) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.description = description;
        this.stock = stock;
    }

    boolean isEmpty() {
        return this.serialNumber == null || "".equals(this.serialNumber);
    }

    public static class ItemBuilder {
        private String serialNumber;
        private String name;
        private String description;
        private Integer stock;

        static ItemBuilder builder() {
            return new ItemBuilder();
        }

        ItemBuilder serialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }

        ItemBuilder name(String name) {
            this.name = name;
            return this;
        }

        ItemBuilder description(String description) {
            this.description = description;
            return this;
        }

        ItemBuilder stock(Integer stock) {
            this.stock = stock;
            return this;
        }

        Item build() {
            return new Item(this.serialNumber, this.name, this.description, this.stock);
        }
    }
}
