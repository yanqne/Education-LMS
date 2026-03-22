package com.GiangTruong.LearningLMS.center.service.Room;

import com.GiangTruong.LearningLMS.center.config.NotFoundException;
import com.GiangTruong.LearningLMS.center.dto.RoomDTO;
import com.GiangTruong.LearningLMS.center.entity.Room;
import com.GiangTruong.LearningLMS.center.mapper.RoomMapper;
import com.GiangTruong.LearningLMS.center.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<RoomDTO> getAllRooms() {

        return roomRepository.findAll()
                .stream()
                .map(RoomMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RoomDTO getRoomById(Long id) {

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Room not found"));

        return RoomMapper.toDTO(room);
    }

    @Override
    public RoomDTO createRoom(RoomDTO roomDTO) {

        Room room = RoomMapper.toEntity(roomDTO);

        Room savedRoom = roomRepository.save(room);

        return RoomMapper.toDTO(savedRoom);
    }

    @Override
    public RoomDTO updateRoom(Long id, RoomDTO roomDTO) {

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Room not found"));

        room.setName(roomDTO.getName());
        room.setCapacity(roomDTO.getCapacity());

        Room updatedRoom = roomRepository.save(room);

        return RoomMapper.toDTO(updatedRoom);
    }

    @Override
    public void deleteRoom(Long id) {

        roomRepository.deleteById(id);
    }
}
