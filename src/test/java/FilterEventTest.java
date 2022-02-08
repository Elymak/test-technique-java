import adeo.leroymerlin.cdp.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class FilterEventTest {

    @Autowired
    EventService eventService;

    @Test
    public void filterEventTest() {

        Set<Member> members1 = new HashSet<>();
        members1.add(buildMember("Frah"));
        members1.add(buildMember("Sam"));
        members1.add(buildMember("CC"));
        members1.add(buildMember("Steve"));
        members1.add(buildMember("Mandris"));
        members1.add(buildMember("Ion"));
        members1.add(buildMember("Goz"));

        Set<Member> members2 = new HashSet<>();
        members2.add(buildMember("Joe"));
        members2.add(buildMember("Mario"));
        members2.add(buildMember("Christian"));
        members2.add(buildMember("Jean-Christophe"));

        Set<Member> members3 = new HashSet<>();
        members3.add(buildMember("Steve"));
        members3.add(buildMember("Bruce"));
        members3.add(buildMember("Dave"));
        members3.add(buildMember("Nicko"));
        members3.add(buildMember("Adrian"));

        Set<Member> members4 = new HashSet<>();
        members4.add(buildMember("Maynard"));
        members4.add(buildMember("Adam"));
        members4.add(buildMember("Danny"));
        members4.add(buildMember("Justin"));

        Set<Member> members5 = new HashSet<>();
        members5.add(buildMember("Serj"));
        members5.add(buildMember("Daron"));
        members5.add(buildMember("Shavo"));
        members5.add(buildMember("John"));

        Band band1 = new Band();
        band1.setMembers(members1);

        Band band2 = new Band();
        band2.setMembers(members2);

        Band band3 = new Band();
        band3.setMembers(members3);

        Band band4 = new Band();
        band4.setMembers(members4);

        Band band5 = new Band();
        band5.setMembers(members5);

        Event event1 = new Event();
        event1.setTitle("Hellfest");
        event1.setBands(new HashSet<>());
        event1.getBands().add(band1);
        event1.getBands().add(band2);

        Event event2 = new Event();
        event2.setTitle("Alcatraz");
        event2.setBands(new HashSet<>());
        event2.getBands().add(band3);

        Event event3 = new Event();
        event3.setTitle("Graspop");
        event3.setBands(new HashSet<>());
        event3.getBands().add(band1);
        event3.getBands().add(band4);
        event3.getBands().add(band5);

        List<Event> events = new ArrayList<>();
        events.add(event1);
        events.add(event2);
        events.add(event3);

        List<Event> filteredEvents = eventService.filterEvents("io", events);

        assertNotNull(filteredEvents);
        assertEquals(2, filteredEvents.size());
        assertEquals("Hellfest", filteredEvents.get(0).getTitle());
        assertEquals("Graspop", filteredEvents.get(1).getTitle());

    }

    @TestConfiguration
    static class EventConfiguration {
        @Bean
        public EventService eventService() {
            return new EventService(new EventRepository() {
                @Override
                public void delete(Long eventId) {

                }

                @Override
                public List<Event> findAll() {
                    return null;
                }

                @Override
                public Event save(Event event) {
                    return null;
                }

                @Override
                public <S extends Event> Iterable<S> save(Iterable<S> iterable) {
                    return null;
                }

                @Override
                public Event findOne(Long aLong) {
                    return null;
                }

                @Override
                public boolean exists(Long aLong) {
                    return false;
                }

                @Override
                public Iterable<Event> findAll(Iterable<Long> iterable) {
                    return null;
                }

                @Override
                public long count() {
                    return 0;
                }

                @Override
                public void delete(Event event) {

                }

                @Override
                public void delete(Iterable<? extends Event> iterable) {

                }

                @Override
                public void deleteAll() {

                }
            }) {

            };
        }
    }


    public Member buildMember(String name) {
        Member m = new Member();
        m.setName(name);
        return m;
    }
}
