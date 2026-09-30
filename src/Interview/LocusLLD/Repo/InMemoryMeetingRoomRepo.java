package Interview.Repo;

import Interview.Entity.MeetingRoom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryMeetingRoomRepo implements MeetingRoomRepository {

    private final Map<String, MeetingRoom> meetingRoomMap = new ConcurrentHashMap<>();


    @Override
    public void addRoom(MeetingRoom meetingRoom) {
        meetingRoomMap.putIfAbsent(meetingRoom.getRoomId(), meetingRoom);
    }

    @Override
    public Optional<MeetingRoom> findByRoomId(String roomId) {
        return Optional.ofNullable(meetingRoomMap.get(roomId));
    }

    @Override
    public List<MeetingRoom> findAll() {
        return new ArrayList<>(meetingRoomMap.values());
    }
}
