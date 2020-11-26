package brankosaponjic.playlist.repository;

import brankosaponjic.playlist.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class SongDAO {
    private final SongRepository songRepository;

    public SongDAO(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Collection<Song> findAll() {
        return songRepository.findAll();
    }

    public Optional<Song> findById(String id) {
        return songRepository.findById(id);
    }

    public Optional<Song> findByOrderNo(int orderNo) {
        return songRepository.findByOrderNo(orderNo);
    }

    public Song createSong(Song song) {
        if (song.getId() != null) {
            return songRepository.insert(song);
        }
        throw new RuntimeException("Song cannot be null!");
    }

    public Optional<Song> updateSong(int orderNo, Song newSong) {
        Optional<Song> song = songRepository.findByOrderNo(orderNo);
        song.ifPresent(s -> s.setTitle(newSong.getTitle()));
        song.ifPresent(s -> s.setLength(newSong.getLength()));
        song.ifPresent(s -> s.setAuthor(newSong.getAuthor()));
        song.ifPresent(s -> s.setLink(newSong.getLink()));
        song.ifPresent(songRepository::save);
        return song;
    }

    public Optional<Song> deleteSong(String id) {
        Optional<Song> song = songRepository.findById(id);
        song.ifPresent(songRepository::delete);
        return song;
    }

    public List<Song> findAByAuthorContainsAllIgnoreCase(String author) {
        return songRepository.findAByAuthorContainsAllIgnoreCase(author);
    }

    public List<Song> searchBy(String search) {
        List<Song> result = null;
        if (search != null && search.trim().length()>0) {
            result = songRepository.findByTitleContainsOrAuthorContainsAllIgnoreCase(search, search);
        } else {
            result = findAByAuthorContainsAllIgnoreCase(search);
        }
        return result;
    }

    public Page<Song> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.songRepository.findAll(pageable);
    }

}
