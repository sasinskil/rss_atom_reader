package info.sasinski.rss_atom_reader_server.controller;

import info.sasinski.rss_atom_reader_server.model.News;
import info.sasinski.rss_atom_reader_server.service.NewsService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/news")
public class NewsController {

    private NewsService newsService;

    public NewsController(NewsService service) {
        this.newsService = service;
    }

    @GetMapping
    public HttpEntity<List<News>> getAll() {

       return ResponseEntity
               .status(HttpStatus.OK)
               .body(this.newsService.findAll());
    }

    @GetMapping("/{category}")
    public HttpEntity<List<News>> getByCategory(@PathVariable("category") String category) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.newsService.findByCategory(category));
    }
}
