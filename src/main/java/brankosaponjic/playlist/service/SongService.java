package brankosaponjic.playlist.service;

import brankosaponjic.playlist.model.Song;
import brankosaponjic.playlist.repository.SongDAO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    private final SongDAO songDAO;

    public SongService(SongDAO songDAO) {
        this.songDAO = songDAO;
    }

    public Collection<Song> findAll() {
        return songDAO.findAll();
    }

    public Optional<Song> findById(String id) {
        return songDAO.findById(id);
    }

    public Optional<Song> findByOrderNo(int orderNo) { return songDAO.findByOrderNo(orderNo); }

    public Song createSong(Song song) {
        return songDAO.createSong(song);
    }

    public Optional<Song> updateSong(int orderNo, Song newSong) {
        return songDAO.updateSong(orderNo, newSong);
    }

    public Optional<Song> deleteSong(String id) {
        return songDAO.deleteSong(id);
    }

    public List<Song> findAByAuthorContainsAllIgnoreCase(String author) {
        return songDAO.findAByAuthorContainsAllIgnoreCase(author);
    }

    public List<Song> searchBy(String search) {
        return songDAO.searchBy(search);
    }

    public Page<Song> findPaginated(int pageNo, int pageSize) {
        return songDAO.findPaginated(pageNo, pageSize);
    }
}
