package Interview.Entity;

import java.time.LocalDateTime;

public class Booking {

    private final String bookingId;
    private final String roomId;
    private final int attendees;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public Booking(String bookingId, String roomId, int attendees, LocalDateTime startTime, LocalDateTime endTime) {

        if(roomId == null || roomId.isBlank()) {
            throw new IllegalArgumentException("RoomID cannot be null in a booking");
        }
        if (attendees <= 0) {
            throw new IllegalArgumentException("Attendees should be there in a booking");
        }

        if (startTime == null || endTime == null || !startTime.isBefore(endTime)) {
            throw  new IllegalArgumentException("Start Time should be before end time");
        }


        this.bookingId = bookingId;
        this.roomId = roomId;
        this.attendees = attendees;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getRoomId() {
        return roomId;
    }

    public int getAttendees() {
        return attendees;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", roomId='" + roomId + '\'' +
                ", attendees=" + attendees +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
