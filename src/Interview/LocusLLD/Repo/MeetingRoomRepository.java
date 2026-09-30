package Interview.Repo;

import Interview.Entity.MeetingRoom;

import java.util.List;
import java.util.Optional;

public interface MeetingRoomRepository {
    void addRoom(MeetingRoom meetingRoom);
    Optional<MeetingRoom> findByRoomId(String roomId);
    List<MeetingRoom> findAll();
}
