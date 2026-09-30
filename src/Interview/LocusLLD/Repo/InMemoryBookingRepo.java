package Interview.Repo;

import Interview.Entity.Booking;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryBookingRepo implements BookingRepository {

    private final Map<String, Booking> bookingMap = new ConcurrentHashMap<>();

    private  final Map<String, List<Booking>> bookingsByRoom = new ConcurrentHashMap<>();


    @Override
    public void save(Booking booking) {
        bookingMap.putIfAbsent(booking.getBookingId(), booking);
        bookingsByRoom.computeIfAbsent(booking.getRoomId(), k -> new ArrayList<>())
                .add(booking);
    }

    @Override
    public Optional<Booking> findByBookingId(String bookingId) {
        return Optional.ofNullable(bookingMap.get(bookingId));
    }

    @Override
    public List<Booking> findAll() {
        return new ArrayList<>(bookingMap.values());
    }

    @Override
    public List<Booking> getBookingsByRoomId(String roomId) {
        return bookingsByRoom.getOrDefault(roomId, Collections.emptyList());
    }
}
