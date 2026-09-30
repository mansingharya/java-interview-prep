package Interview.Repo;

import Interview.Entity.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingRepository {
    void save(Booking booking);
    Optional<Booking> findByBookingId(String bookingId);
    List<Booking> findAll();
    List<Booking> getBookingsByRoomId(String roomId);
}
