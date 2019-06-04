package info.sasinski.rss_atom_reader_server.service;

import info.sasinski.rss_atom_reader_server.model.News;
import info.sasinski.rss_atom_reader_server.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService {

    private NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepo) {
        this.newsRepository = newsRepo;
    }

    public List<News> findAll() {
        List<News> items = new ArrayList<>();
        items = this.newsRepository.findAll();

        if(items != null)
        {
            return items;
        }

        return new ArrayList<>();
    }

    public News insertOne(News item) {

        this.newsRepository.save(item);

        return item;
    }

    public List<News> findByCategory(String category) {
        List<News> items = new ArrayList<>();
        items = this.newsRepository.findByCategory(category);

        if(items != null)
        {
            return items;
        }

        return new ArrayList<>();
    }

    public void deleteAll() {
        this.newsRepository.deleteAll();
    }
}
