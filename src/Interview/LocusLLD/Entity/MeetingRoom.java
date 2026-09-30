package Interview.Entity;

import java.util.Objects;

public class MeetingRoom {

    private final String roomId;
    private final int capacity;

    public MeetingRoom(String roomId, int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Room Capacity cannot be negative");
        }

        if (roomId == null || roomId.isBlank()) {
            throw new IllegalArgumentException("Room ID cannot be empty");
        }

        this.roomId = roomId;
        this.capacity = capacity;
    }

    public String getRoomId() {
        return roomId;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MeetingRoom that = (MeetingRoom) o;
        return capacity == that.capacity && Objects.equals(roomId, that.roomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, capacity);
    }

    @Override
    public String toString() {
        return "MeetingRoom{" +
                "roomId='" + roomId + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
