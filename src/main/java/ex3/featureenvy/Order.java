package ex3.featureenvy;

public class Order {
    private boolean isInUsersCatalog(String albumId, Language language) {
        for (Catalog.Entry entry : catalog.getEntries())
            if (entry.getAlbumId().equals(albumId))
                return entry.languages().isEmpty() || entry.languages().contains(language);
        return false;
    }

}
