package adeo.leroymerlin.cdp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    public void delete(Long id) {
        eventRepository.delete(id);
    }

    public List<Event> getFilteredEvents(String query) {
        List<Event> events = eventRepository.findAll();
        List<Event> filteredEvents = filterEvents(query, events);
        return filteredEvents;
    }

    public List<Event> filterEvents(String query, List<Event> events) {
        // Filter the events list in pure JAVA here
        return events.stream().filter(e ->
                e.getBands().stream()
                        .anyMatch(b -> b.getMembers().stream()
                                .anyMatch(member -> member.getName().toUpperCase().contains(query.toUpperCase())))).collect(Collectors.toList());
    }

    public void saveEvent(Long id, Event event) {
        eventRepository.save(event);
    }
}
