package edu.vandy.mooc.aad3.framework.net;

/**
 * This class represents a single entry (post) in the XML feed.
 *
 * <p>It includes the data members "title," "link,", "author", and "summary."
 */
public class NetEntry {

    public final String id;
    public final String title;
    public final String link;
    public final String published;
    public final String author;

    NetEntry(String id, String title, String link, String published, String author) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.published = published;
        this.author = author;
    }

    @Override
    public String toString() {
        return "NetEntry{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", published=" + published +
                ", author='" + author + '\'' +
                '}';
    }
}
