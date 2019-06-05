package info.sasinski.rss_atom_reader_server.repository;

import info.sasinski.rss_atom_reader_server.model.News;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends MongoRepository<News,String> {

    List<News> findByCategory(String category);
}
