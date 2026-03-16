package com.GiangTruong.LearningLMS.center.mapper;


import com.GiangTruong.LearningLMS.center.dto.RoomDTO;
import com.GiangTruong.LearningLMS.center.entity.Room;

public class RoomMapper {

    public static RoomDTO toDTO(Room room) {

        RoomDTO dto = new RoomDTO();

        dto.setId(room.getId());
        dto.setName(room.getName());
        dto.setCapacity(room.getCapacity());

        return dto;
    }

    public static Room toEntity(RoomDTO dto) {

        Room room = new Room();

        room.setName(dto.getName());
        room.setCapacity(dto.getCapacity());

        return room;
    }
}