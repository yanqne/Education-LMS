package com.GiangTruong.LearningLMS.center.service.Announcement;

import com.GiangTruong.LearningLMS.center.dto.Annoucement.AnnouncementReq;
import com.GiangTruong.LearningLMS.center.dto.Annoucement.AnnouncementRes;
import com.GiangTruong.LearningLMS.center.entity.Announcement;

import java.util.List;

public interface AnnouncementService {
    AnnouncementRes create(AnnouncementReq req);

    List<AnnouncementRes> getAll();

    List<AnnouncementRes> getByClass(Long classId);

    List<AnnouncementRes> getGlobal();
}
