package com.bigcenter.app.payloads.common;

import com.bigcenter.app.entities.Student;
import com.bigcenter.app.entities.Teacher;
import com.bigcenter.app.entities.User;
import com.bigcenter.app.repositories.StudentRepository;
import com.bigcenter.app.repositories.TeacherRepository;
import com.bigcenter.app.repositories.UserRepository;
import com.bigcenter.app.services.cognito.CognitoService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.UserType;

@Component
@RequiredArgsConstructor
public class CognitoUserInit implements ApplicationRunner {

    private final CognitoService cognitoUserService;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Load STUDENT group
        for (UserType user : cognitoUserService.getUserTypes("STUDENT")) {
            // Lấy email từ attributes
            String email = null;
            String phone = null;
            for (AttributeType attr : user.attributes()) {
                if ("email".equals(attr.name())) {
                    email = attr.value();
                } else if ("phone_number".equals(attr.name())) {
                    phone = attr.value();
                }
            }

            if (email == null) continue; // Bỏ qua nếu không có email

            User us = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if (!studentRepository.existsByUser(us)) {
                Student student = new Student();
                student.setUser(us);
                student.setPhone(phone);
                studentRepository.save(student);
            }
        }


        // Load TEACHER group
        for (UserType user : cognitoUserService.getUserTypes("TEACHER")) {
            String email = null;
            String phone = null;

            for (AttributeType attr : user.attributes()) {
                if ("email".equals(attr.name())) {
                    email = attr.value();
                } else if ("phone_number".equals(attr.name())) {
                    phone = attr.value();
                }
            }

            if (email == null) continue;

            // Tìm hoặc tạo User nếu chưa có
            User us = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if (studentRepository.existsByUser(us)) {
                studentRepository.deleteByUser(us);
            }
            // Nếu Teacher chưa tồn tại với user này thì tạo
            if (!teacherRepository.existsByUser(us)) {
                Teacher teacher = new Teacher();
                teacher.setUser(us);
                teacher.setPhone(phone);
                teacherRepository.save(teacher);
            }
        }

    }
}

