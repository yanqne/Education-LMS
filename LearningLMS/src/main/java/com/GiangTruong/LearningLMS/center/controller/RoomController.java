package com.GiangTruong.LearningLMS.center.controller;


import com.GiangTruong.LearningLMS.center.dto.RoomDTO;
import com.GiangTruong.LearningLMS.center.payload.ApiResponse;
import com.GiangTruong.LearningLMS.center.service.Room.RoomService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@Tag(name = "Room API", description = "APIs for managing rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public ApiResponse<List<RoomDTO>> getAllRooms() {

        List<RoomDTO> rooms = roomService.getAllRooms();

        return new ApiResponse<>(
                true,
                "Rooms retrieved successfully",
                rooms
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<RoomDTO> getRoomById(@PathVariable Long id) {

        RoomDTO room = roomService.getRoomById(id);

        return new ApiResponse<>(
                true,
                "Room retrieved successfully",
                room
        );
    }

    @PostMapping
    public ApiResponse<RoomDTO> createRoom(
            @RequestBody RoomDTO roomDTO) {

        RoomDTO room = roomService.createRoom(roomDTO);

        return new ApiResponse<>(
                true,
                "Room created successfully",
                room
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<RoomDTO> updateRoom(
            @PathVariable Long id,
            @RequestBody RoomDTO roomDTO) {

        RoomDTO room = roomService.updateRoom(id, roomDTO);

        return new ApiResponse<>(
                true,
                "Room updated successfully",
                room
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteRoom(@PathVariable Long id) {

        roomService.deleteRoom(id);

        return new ApiResponse<>(
                true,
                "Room deleted successfully",
                null
        );
    }
}