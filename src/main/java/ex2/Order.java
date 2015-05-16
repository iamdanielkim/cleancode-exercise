package ex2;

import java.util.List;

public class Order {

    private Catalog catalog;

    private boolean isInUsersCatalog(String albumId, Language language) {
        for (Catalog.Entry entry : catalog.getEntries())
            if (entry.getAlbumId().equals(albumId))
                return entry.languages().isEmpty() || entry.languages().contains(language);
        return false;
    }

}


class Catalog {
    public List<Entry> getEntries() {
        return null;
    }

    static class Entry {
        public List<Language> languages() {
            return null;
        }

        public String getAlbumId() {
            return null;
        }
    }
}

interface Language {

}