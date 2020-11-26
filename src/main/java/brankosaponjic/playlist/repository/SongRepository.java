package brankosaponjic.playlist.repository;

import brankosaponjic.playlist.model.Song;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends MongoRepository<Song, String> {

    List<Song> findByTitleContainsOrAuthorContainsAllIgnoreCase(String title, String author);

    Optional<Song> findByOrderNo(int orderNo);

    List<Song> findAByAuthorContainsAllIgnoreCase(String author);

}
