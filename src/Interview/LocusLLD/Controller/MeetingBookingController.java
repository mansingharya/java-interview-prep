package Interview.Controller;

import Interview.Entity.Booking;
import Interview.Entity.MeetingRoom;
import Interview.Service.BookingService;
import Interview.Service.MeetingRoomService;

import java.time.LocalDateTime;
import java.util.Optional;

public class MeetingBookingController {

    private final BookingService bookingService;

    private final MeetingRoomService meetingRoomService;

    public MeetingBookingController(BookingService bookingService, MeetingRoomService meetingRoomService) {
        this.bookingService = bookingService;
        this.meetingRoomService = meetingRoomService;
    }

    public MeetingRoom registerRoom(int capacity) {
        return meetingRoomService.registerRoom(capacity);
    }

    public String bookRoom(LocalDateTime startTime, LocalDateTime endTime, int requiredCapacity) {
        return bookingService.bookRoom(startTime, endTime, requiredCapacity);
    }

    public Optional<Booking> getBookingDetailsByBookingId(String bookingId) {
        return bookingService.getBookingByBookingId(bookingId);
    }


}
