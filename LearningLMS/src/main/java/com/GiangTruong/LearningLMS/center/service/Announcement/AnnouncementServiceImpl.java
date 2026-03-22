package com.GiangTruong.LearningLMS.center.service.Announcement;

import com.GiangTruong.LearningLMS.center.config.NotFoundException;
import com.GiangTruong.LearningLMS.center.dto.Annoucement.AnnouncementReq;
import com.GiangTruong.LearningLMS.center.dto.Annoucement.AnnouncementRes;
import com.GiangTruong.LearningLMS.center.entity.Announcement;
import com.GiangTruong.LearningLMS.center.entity.ClassEntity;
import com.GiangTruong.LearningLMS.center.entity.User;
import com.GiangTruong.LearningLMS.center.mapper.AnnouncementMapper;
import com.GiangTruong.LearningLMS.center.repository.AnnouncementRepository;
import com.GiangTruong.LearningLMS.center.repository.ClassRepository;
import com.GiangTruong.LearningLMS.center.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final UserRepository userRepository;
    private final ClassRepository classRepository;
    private final AnnouncementMapper announcementMapper;

    @Override
    public AnnouncementRes create(AnnouncementReq req) {

        User author = userRepository.findById(req.getAuthorId())
                .orElseThrow(() -> new NotFoundException("Author not found"));

        Announcement entity = announcementMapper.toEntity(req);
        entity.setAuthor(author);

        if (req.getClassId() != null) {
            ClassEntity classEntity = classRepository.findById(req.getClassId())
                    .orElseThrow(() -> new NotFoundException("Class not found"));
            entity.setClassEntity(classEntity);
        }

        return announcementMapper.toResponse(
                announcementRepository.save(entity)
        );
    }

    @Override
    public List<AnnouncementRes> getAll() {
        return announcementRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(announcementMapper::toResponse)
                .toList();
    }

    @Override
    public List<AnnouncementRes> getByClass(Long classId) {
        return announcementRepository.findByClassEntityId(classId)
                .stream()
                .map(announcementMapper::toResponse)
                .toList();
    }

    @Override
    public List<AnnouncementRes> getGlobal() {
        return announcementRepository.findByClassEntityIsNull()
                .stream()
                .map(announcementMapper::toResponse)
                .toList();
    }
}
