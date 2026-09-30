package Interview.Service;

import Interview.Entity.MeetingRoom;
import Interview.Repo.MeetingRoomRepository;

import java.util.concurrent.atomic.AtomicInteger;

public class MeetingRoomService {

    AtomicInteger counter = new AtomicInteger(1);

    private final MeetingRoomRepository meetingRoomRepository;


    public MeetingRoomService(MeetingRoomRepository meetingRoomRepository) {
        this.meetingRoomRepository = meetingRoomRepository;
    }


    public MeetingRoom registerRoom(int capacity) {
        String roomId = "ROOM-" + counter.getAndIncrement();
        MeetingRoom room = new MeetingRoom(roomId, capacity);
        meetingRoomRepository.addRoom(room);
        return room;
    }

}
