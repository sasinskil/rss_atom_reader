package info.sasinski.rss_atom_reader_server;

import info.sasinski.rss_atom_reader_server.service.NewsService;
import info.sasinski.rss_atom_reader_server.service.RssAtomFeedHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbSeeder implements CommandLineRunner {

    @Autowired
    private NewsService newsService;

    @Autowired
    private RssAtomFeedHelper rssAtomFeedHelper;

    private static final String URL = "https://fakty.interia.pl/feed";

    @Override
    public void run(String... args) {

        this.rssAtomFeedHelper.setNewsService(newsService);

        this.rssAtomFeedHelper.consumeUrl(URL);

    }
}
