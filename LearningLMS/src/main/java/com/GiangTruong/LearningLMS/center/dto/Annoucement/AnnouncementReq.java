package com.GiangTruong.LearningLMS.center.dto.Annoucement;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class AnnouncementReq {
    private Long authorId;
    private Long classId;
    private String title;
    private String content;
}
