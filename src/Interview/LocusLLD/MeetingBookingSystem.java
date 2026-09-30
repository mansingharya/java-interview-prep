package Interview;

import Interview.Controller.MeetingBookingController;
import Interview.Entity.Booking;
import Interview.Entity.MeetingRoom;
import Interview.Repo.BookingRepository;
import Interview.Repo.InMemoryBookingRepo;
import Interview.Repo.InMemoryMeetingRoomRepo;
import Interview.Repo.MeetingRoomRepository;
import Interview.Service.BookingService;
import Interview.Service.MeetingRoomService;

import java.time.LocalDateTime;
import java.util.Optional;

public class MeetingBookingSystem {

    static void main(String[] args) {

        MeetingRoomRepository meetingRoomRepository = new InMemoryMeetingRoomRepo();
        BookingRepository bookingRepository = new InMemoryBookingRepo();

        MeetingRoomService meetingRoomService = new MeetingRoomService(meetingRoomRepository);
        BookingService bookingService = new BookingService(bookingRepository, meetingRoomRepository);

        MeetingBookingController controller = new MeetingBookingController(bookingService, meetingRoomService);

        MeetingRoom roomId20 = controller.registerRoom(20);
        System.out.println(roomId20);

        MeetingRoom roomId10 = controller.registerRoom(10);
        System.out.println(roomId10);

        LocalDateTime startAt10 = LocalDateTime.of(2026, 9, 26, 10, 0);
        LocalDateTime endAt11 = LocalDateTime.of(2026, 9, 26, 11, 0);

        try {
            String bookingId = controller.bookRoom(startAt10, endAt11, 15);
            System.out.println(bookingId);

            Optional<Booking> booking = controller.getBookingDetailsByBookingId(bookingId);
            System.out.println(booking);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            String bookingId = controller.bookRoom(startAt10, endAt11, 5);
            System.out.println(bookingId);

            Optional<Booking> booking = controller.getBookingDetailsByBookingId(bookingId);
            System.out.println(booking);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
