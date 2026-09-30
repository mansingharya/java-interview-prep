package Interview.Service;

import Interview.Entity.Booking;
import Interview.Entity.MeetingRoom;
import Interview.Repo.BookingRepository;
import Interview.Repo.MeetingRoomRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class BookingService {

    AtomicInteger bookingCounter = new AtomicInteger(1);

    private final BookingRepository bookingRepository;
    private final MeetingRoomRepository meetingRoomRepository;


    public BookingService(BookingRepository bookingRepository, MeetingRoomRepository meetingRoomRepository) {
        this.bookingRepository = bookingRepository;
        this.meetingRoomRepository = meetingRoomRepository;
    }

    public Optional<Booking> getBookingByBookingId(String bookingId) {
        return bookingRepository.findByBookingId(bookingId);
    }


    public String bookRoom(LocalDateTime startTime, LocalDateTime endTime, int requiredCapacity) {

        String bookingId = "BOOKING-" + bookingCounter.getAndIncrement();

        MeetingRoom meetingRoom = findMeetingRoom(startTime, endTime, requiredCapacity);

        Booking booking = new Booking(bookingId, meetingRoom.getRoomId(), requiredCapacity, startTime, endTime);

        bookingRepository.save(booking);

        return booking.getBookingId();


    }

    private MeetingRoom findMeetingRoom(LocalDateTime startTime, LocalDateTime endTime, int requiredCapacity) {
        MeetingRoom room = null;
        for (MeetingRoom r : meetingRoomRepository.findAll()) {
            if (r.getCapacity() >= requiredCapacity && isRoomAvailable(r, startTime, endTime)) {
                room = r;
            }
        }
        return room;
    }

    private boolean isRoomAvailable(MeetingRoom room, LocalDateTime startTime, LocalDateTime endTime) {
        return bookingRepository.getBookingsByRoomId(room.getRoomId()).stream()
                .noneMatch(b -> b.getStartTime().isBefore(endTime) && b.getEndTime().isAfter(startTime));
    }



}
