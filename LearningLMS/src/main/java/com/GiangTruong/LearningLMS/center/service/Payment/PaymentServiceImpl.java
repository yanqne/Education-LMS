package com.GiangTruong.LearningLMS.center.service.Payment;

import com.GiangTruong.LearningLMS.center.Enum.PaymentStatus;
import com.GiangTruong.LearningLMS.center.config.BadRequestException;
import com.GiangTruong.LearningLMS.center.config.NotFoundException;
import com.GiangTruong.LearningLMS.center.dto.Payment.PaymentReq;
import com.GiangTruong.LearningLMS.center.dto.Payment.PaymentRes;
import com.GiangTruong.LearningLMS.center.dto.Payment.PaymentSummaryRes;
import com.GiangTruong.LearningLMS.center.entity.ClassEntity;
import com.GiangTruong.LearningLMS.center.entity.Payment;
import com.GiangTruong.LearningLMS.center.entity.Student;
import com.GiangTruong.LearningLMS.center.mapper.PaymentMapper;
import com.GiangTruong.LearningLMS.center.repository.ClassRepository;
import com.GiangTruong.LearningLMS.center.repository.ClassStudentRepository;
import com.GiangTruong.LearningLMS.center.repository.PaymentRepository;
import com.GiangTruong.LearningLMS.center.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;
    private final ClassStudentRepository classStudentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentRes create(PaymentReq req) {

        // check student
        Student student = studentRepository.findById(req.getStudentId())
                .orElseThrow(() -> new NotFoundException("Student not found"));

        // check class
        ClassEntity classEntity = classRepository.findById(req.getClassId())
                .orElseThrow(() -> new NotFoundException("Class not found"));

        // ❌ chưa enroll
        boolean enrolled = classStudentRepository
                .existsByClassEntityIdAndStudentId(req.getClassId(), req.getStudentId());

        if (!enrolled) {
            throw new BadRequestException("Student not enrolled in class");
        }
        double totalPaid = paymentRepository.findByStudentIdAndClassEntityId(student.getId(), classEntity.getId())
                .stream()
                .mapToDouble(Payment::getAmount)
                .sum();
        double courseFee = classEntity.getCourse().getFee().doubleValue();
        //Chặn đóng quá tiền
        if(totalPaid + req.getAmount() > courseFee){
            throw new BadRequestException("Payment exceeds course fee");
        }

        Payment entity = paymentMapper.toEntity(req);
        entity.setStudent(student);
        entity.setClassEntity(classEntity);

        return paymentMapper.toResponse(paymentRepository.save(entity));
    }

    @Override
    public List<PaymentRes> getByStudent(Long studentId) {
        return paymentRepository.findByStudentId(studentId)
                .stream()
                .map(paymentMapper::toResponse)
                .toList();
    }

    @Override
    public List<PaymentRes> getByStudentAndClass(Long studentId, Long classId) {
        return paymentRepository.findByStudentIdAndClassEntityId(studentId, classId)
                .stream()
                .map(paymentMapper::toResponse)
                .toList();
    }

    @Override
    public Double getTotalPaid(Long studentId, Long classId) {
        return paymentRepository
                .findByStudentIdAndClassEntityId(studentId, classId)
                .stream()
                .mapToDouble(Payment::getAmount)
                .sum();
    }
    @Override
    public PaymentSummaryRes getPaymentSummary(Long studentId, Long classId) {

        // check student
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found"));

        // check class
        ClassEntity classEntity = classRepository.findById(classId)
                .orElseThrow(() -> new NotFoundException("Class not found"));

        // check enroll
        boolean enrolled = classStudentRepository
                .existsByClassEntityIdAndStudentId(classId, studentId);

        if (!enrolled) {
            throw new BadRequestException("Student not enrolled in class");
        }

        // 🔥 tổng tiền đã đóng
        Double totalPaid = paymentRepository
                .findByStudentIdAndClassEntityId(studentId, classId)
                .stream()
                .mapToDouble(Payment::getAmount)
                .sum();

        // 🔥 học phí
        Double courseFee = classEntity.getCourse().getFee().doubleValue();

        // 🔥 công nợ
        Double debt = courseFee - totalPaid;

        if (debt < 0) debt = 0.0; // tránh âm

        // 🔥 trạng thái
        PaymentStatus status;

        if (totalPaid == 0) {
            status = PaymentStatus.UNPAID;
        } else if (totalPaid >= courseFee) {
            status = PaymentStatus.PAID;
        } else {
            status = PaymentStatus.PARTIAL;
        }

        // response
        PaymentSummaryRes res = new PaymentSummaryRes();
        res.setStudentId(studentId);
        res.setClassId(classId);
        res.setTotalPaid(totalPaid);
        res.setCourseFee(courseFee);
        res.setDebt(debt);
        res.setStatus(status);

        return res;
    }
}