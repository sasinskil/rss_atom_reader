package info.sasinski.rss_atom_reader_server.service;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import info.sasinski.rss_atom_reader_server.model.News;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class RssAtomFeedHelper {

    @Autowired
    private NewsService newsService;

    Logger logger = LoggerFactory.getLogger(RssAtomFeedHelper.class);

    public RssAtomFeedHelper() {}

    public void consumeUrl(String url) {

        logger.info("try to read " + url);
        try (XmlReader reader = new XmlReader(new URL(url))) {

            SyndFeed feed = new SyndFeedInput().build(reader);

            logger.info(feed.getTitle());

            this.newsService.deleteAll();

            logger.info("start mapping to model");
            for(SyndEntry entry : feed.getEntries()) {

                String title = entry.getTitle();
                String link = entry.getLink();
                String publishDate = changeDateFormat(entry.getPublishedDate());
                String description = htmlToPlainText(entry.getDescription().getValue());
                String imageUrl = entry.getEnclosures().get(0).getUrl();
                String category = entry.getCategories().get(0).getName();

                News model = new News(title,link,publishDate,description,imageUrl,category);

                logger.info("save some data " + model);
                this.newsService.insertOne(model);

            }

            logger.info("Done");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String changeDateFormat(Date current) {

        Date transform = current;
        DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
        Date date = new Date();

        try {
            date = formatter.parse(transform.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int minutes = cal.get(Calendar.MINUTE);
        String formatedDate = cal.get(Calendar.DATE) + "/" +
                (cal.get(Calendar.MONTH) + 1) +
                "/" +         cal.get(Calendar.YEAR) + " " +
                cal.get(Calendar.HOUR_OF_DAY) + ":" +
                String.format("%02d",minutes);

        return formatedDate;
    }

    public String htmlToPlainText(String html) {

        String result = html.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", "");
        return result;
    }

    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }
}
