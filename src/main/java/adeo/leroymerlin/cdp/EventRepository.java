package adeo.leroymerlin.cdp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface EventRepository extends CrudRepository<Event, Long> {

    void delete(Long eventId);

    List<Event> findAll();

    Event save(Event event);
}
