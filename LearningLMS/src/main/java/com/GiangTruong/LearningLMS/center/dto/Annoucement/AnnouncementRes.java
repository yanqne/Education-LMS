package com.GiangTruong.LearningLMS.center.dto.Annoucement;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Setter
@Getter
public class AnnouncementRes {
    private Long id;
    private Long authorId;
    private Long classId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
}
