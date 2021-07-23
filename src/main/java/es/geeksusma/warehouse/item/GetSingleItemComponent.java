package es.geeksusma.warehouse.item;

class GetSingleItemComponent implements GetSingleItem {
    private final GetItemRepository getItemRepository;

    GetSingleItemComponent(GetItemRepository getItemRepository) {

        this.getItemRepository = getItemRepository;
    }

    @Override
    public Item byId(Long id) {
        assertId(id);
        return getItemRepository.getById(id).orElseThrow(() -> new ItemNotFound(String.format("The item %s was not found in the system", id)));
    }

    private void assertId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("The id is mandatory to retrieve the item");
        }
    }
}
