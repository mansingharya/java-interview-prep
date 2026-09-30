package LLD.MeetingRoomBookingSystem;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;


class MeetingRoom {
    private final String roomId;
    private final String roomName;
    private final int capacity;

    public MeetingRoom(String roomId, String roomName, int capacity) {
        // TODO - Add validations on all params - roomId, roomName, capacity

        if (capacity <= 0) {
            throw new IllegalArgumentException("Must be positive");
        }

        this.roomId = roomId;
        this.roomName = roomName;
        this.capacity = capacity;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "\nMeetingRoom{" +
                "roomId='" + roomId + '\'' +
                ", roomName='" + roomName + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}


class Booking {

    private final String bookingId;
    private final String roomId;
    private final String bookedBy;
    private final int numberOfAttendees;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public Booking(String bookingId, String roomId, String bookedBy, int numberOfAttendees, LocalDateTime startTime, LocalDateTime endTime) {
        // TODO - Add Validations on all params.

        if (numberOfAttendees <= 0) {
            throw new IllegalArgumentException("Must be positive");
        }

        if (startTime == null || endTime == null || !startTime.isBefore(endTime)) {
            throw new IllegalArgumentException("StartTime must be before endTime");
        }

        this.bookingId = bookingId;
        this.roomId = roomId;
        this.bookedBy = bookedBy;
        this.numberOfAttendees = numberOfAttendees;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getBookedBy() {
        return bookedBy;
    }

    public int getNumberOfAttendees() {
        return numberOfAttendees;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "\nBooking{" +
                "bookingId='" + bookingId + '\'' +
                ", roomId='" + roomId + '\'' +
                ", bookedBy='" + bookedBy + '\'' +
                ", numberOfAttendees=" + numberOfAttendees +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}


interface MeetingRoomRepository {
    void addMeetingRoom(MeetingRoom room);
    Optional<MeetingRoom> getMeetingRoomByRoomId(String roomId);
    List<MeetingRoom> getAllMeetingRooms();
}

class InMemoryMeetingRoomRepository implements MeetingRoomRepository {
    private final Map<String, MeetingRoom> rooms = new ConcurrentHashMap<>();

    @Override
    public void addMeetingRoom(MeetingRoom room) {
        rooms.putIfAbsent(room.getRoomId(), room);
    }

    @Override
    public Optional<MeetingRoom> getMeetingRoomByRoomId(String roomId) {
        return Optional.ofNullable(rooms.get(roomId));
    }

    @Override
    public List<MeetingRoom> getAllMeetingRooms() {
        return new ArrayList<>(rooms.values());
    }
}


interface BookingRepository {
    void addBooking(Booking booking);
    boolean removeBooking(String bookingId);
    Optional<Booking> getBookingById(String bookingId);
    List<Booking> getBookingsByRoomId(String roomId);
    List<Booking> getAllBookings();
}

class InMemoryBookingRepository implements BookingRepository {
    private final Map<String, Booking> bookings = new ConcurrentHashMap<>();

    private final Map<String, List<Booking>> bookingsByRoom = new ConcurrentHashMap<>();

    @Override
    public void addBooking(Booking booking) {
        bookings.putIfAbsent(booking.getBookingId(), booking);
        bookingsByRoom.computeIfAbsent(booking.getRoomId(), k -> new ArrayList<>()).add(booking);
    }

    @Override
    public boolean removeBooking(String bookingId) {
        Booking removedBooking  = bookings.remove(bookingId);
        if (removedBooking == null) {
            return false;
        }
        List<Booking> roomBookings = bookingsByRoom.get(removedBooking.getRoomId());
        if (roomBookings != null) {
            roomBookings.remove(removedBooking);
        }
        return true;
    }

    @Override
    public Optional<Booking> getBookingById(String bookingId) {
        return Optional.ofNullable(bookings.get(bookingId));
    }

    @Override
    public List<Booking> getBookingsByRoomId(String roomId) {
        return bookingsByRoom.getOrDefault(roomId, Collections.emptyList());
    }

    @Override
    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings.values());
    }
}


class MeetingRoomService {

    private final MeetingRoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    private final ReentrantLock bookingLock = new ReentrantLock();

    public MeetingRoomService(MeetingRoomRepository roomRepository, BookingRepository bookingRepository) {
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }

    public void addMeetingRoom(MeetingRoom room) {
        roomRepository.addMeetingRoom(room);
    }

    public MeetingRoom getMeetingRoomByRoomId(String roomId) {
        return roomRepository.getMeetingRoomByRoomId(roomId).orElseThrow(() -> new IllegalArgumentException("Room not found"));
    }

    public List<MeetingRoom> getAllMeetingRooms() {
        return roomRepository.getAllMeetingRooms();
    }

    private boolean isRoomAvailable(MeetingRoom room, LocalDateTime start, LocalDateTime end) {
        return bookingRepository.getBookingsByRoomId(room.getRoomId()).stream()
                .noneMatch(b -> b.getStartTime().isBefore(end) && b.getEndTime().isAfter(start));
    }

    private MeetingRoom findBestAvailableRoom(LocalDateTime start, LocalDateTime end, int attendees) {
        return roomRepository.getAllMeetingRooms().stream()
                .filter(r -> r.getCapacity() >= attendees)
                .filter( r -> isRoomAvailable(r, start, end))
                .min(Comparator.comparingInt(MeetingRoom::getCapacity))
                .orElse(null);
    }

    public List<MeetingRoom> getAvailableMeetingRooms(LocalDateTime start, LocalDateTime end, int attendees) {
        return roomRepository.getAllMeetingRooms().stream()
                .filter(r -> r.getCapacity() >= attendees)
                .filter(r -> isRoomAvailable(r, start, end))
                .sorted(Comparator.comparingInt(MeetingRoom::getCapacity))
                .toList();
    }

    public Booking bookMeetingRoom(LocalDateTime start, LocalDateTime end, int attendees, String bookedBy) {
        bookingLock.lock();
        try {
            MeetingRoom bestRoom = findBestAvailableRoom(start, end, attendees);
            if (bestRoom == null) {
                throw new RuntimeException("No Meeting Room is available");
            }

            String bookingId = "BKG_" + UUID.randomUUID();
            Booking booking = new Booking(bookingId, bestRoom.getRoomId(), bookedBy, attendees, start, end);
            bookingRepository.addBooking(booking);
            return booking;
        } finally {
            bookingLock.unlock();
        }
    }

    public void cancelBooking(String bookingId) {
        if ( !bookingRepository.removeBooking(bookingId)) {
            throw new RuntimeException("Booking Room not found");
        }
    }
}



public class MeetingRoomBookingSystem {

    public static void main(String[] args) {

        MeetingRoomRepository roomRepository = new InMemoryMeetingRoomRepository();
        BookingRepository bookingRepository = new InMemoryBookingRepository();

        MeetingRoomService service = new MeetingRoomService(roomRepository, bookingRepository);

        service.addMeetingRoom(new MeetingRoom("R1", "Room A", 10));
        service.addMeetingRoom(new MeetingRoom("R2", "Room B", 20));

        LocalDateTime start = LocalDateTime.of(2026, 10, 1, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 10, 1, 11, 0);

        Booking b1 = service.bookMeetingRoom(start, end, 8, "Man");
        System.out.println(b1);

        List<MeetingRoom> available = service.getAvailableMeetingRooms(start, end, 8);
        System.out.println(available);

        LocalDateTime oStart = LocalDateTime.of(2026, 10, 1, 10, 30);
        LocalDateTime oEnd = LocalDateTime.of(2026, 10, 1, 11, 30);
        Booking b2 = service.bookMeetingRoom(oStart, oEnd, 8, "Singh");
        System.out.println(b2);

        try {
            Booking b3 = service.bookMeetingRoom(start, end, 10, "Aryan");
            System.out.println(b3);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        service.cancelBooking(b1.getBookingId());

        Booking b3 = service.bookMeetingRoom(start, end, 10, "Aryan");
        System.out.println(b3);

        System.out.println(service.getAllMeetingRooms());
        System.out.println(bookingRepository.getAllBookings());

    }

}

