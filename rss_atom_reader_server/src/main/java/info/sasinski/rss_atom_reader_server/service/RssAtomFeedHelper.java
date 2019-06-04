package info.sasinski.rss_atom_reader_server.service;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;

@Component
public class RssAtomFeedHelper {

    @Autowired
    private NewsService newsService;

    public RssAtomFeedHelper() {}

    public void consumeUrl(String url) {

        try (XmlReader reader = new XmlReader(new URL(url))) {

            SyndFeed feed = new SyndFeedInput().build(reader);
            System.out.println(feed.getTitle());
            System.out.println("#######");
            for(SyndEntry entry : feed.getEntries()) {
                System.out.println(entry.getTitle());
                System.out.println(entry.getLink());
                System.out.println(entry.getPublishedDate());
                System.out.println(entry.getDescription().getValue());
                System.out.println(entry.getEnclosures().get(0).getUrl());
                System.out.println(entry.getCategories().get(0).getName());
                System.out.println("#########");
            }

            System.out.println("Done");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public NewsService getNewsService() {
        return newsService;
    }

    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }
}
