package info.sasinski.rss_atom_reader_server.service;

import info.sasinski.rss_atom_reader_server.model.News;
import info.sasinski.rss_atom_reader_server.repository.NewsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService {

    private NewsRepository newsRepository;

    Logger logger = LoggerFactory.getLogger(NewsService.class);

    public NewsService(NewsRepository newsRepo) {
        this.newsRepository = newsRepo;
    }

    public List<News> findAll() {

        logger.info("select all News from db");
        List<News> items = new ArrayList<>();
        items = this.newsRepository.findAll();

        if(items != null)
        {
            return items;
        }

        return new ArrayList<>();
    }

    public News insertOne(News item) {

        logger.info("insert" + item + " to db");
        this.newsRepository.save(item);

        return item;
    }

    public List<News> findByCategory(String category) {

        logger.info("find News by category - > " + category);
        List<News> items = new ArrayList<>();
        items = this.newsRepository.findByCategory(category);

        if(items != null)
        {
            return items;
        }

        return new ArrayList<>();
    }

    public void deleteAll() {

        logger.info("delete data from db");
        this.newsRepository.deleteAll();
    }
}
