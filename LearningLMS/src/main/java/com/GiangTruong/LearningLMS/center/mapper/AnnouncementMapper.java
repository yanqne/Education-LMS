package com.GiangTruong.LearningLMS.center.mapper;

import com.GiangTruong.LearningLMS.center.dto.Annoucement.AnnouncementReq;
import com.GiangTruong.LearningLMS.center.dto.Annoucement.AnnouncementRes;
import com.GiangTruong.LearningLMS.center.entity.Announcement;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AnnouncementMapper {
    public Announcement toEntity(AnnouncementReq req){
        Announcement entity = new Announcement();
        entity.setTitle(req.getTitle());
        entity.setContent(req.getContent());
        entity.setCreatedAt(LocalDateTime.now());
        return entity;
    }
    public AnnouncementRes toResponse(Announcement entity){
        AnnouncementRes res = new AnnouncementRes();
        res.setId(entity.getId());
        res.setAuthorId(entity.getAuthor().getId());
        res.setClassId(
                entity.getClassEntity() != null ? entity.getClassEntity().getId() : null
        );
        res.setTitle(entity.getTitle());
        res.setContent(entity.getContent());
        res.setCreatedAt(entity.getCreatedAt());
        return res;
    }
}
