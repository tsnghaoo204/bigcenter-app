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
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        // Xử lý STUDENT
        for (UserType user : cognitoUserService.getUserTypes("STUDENT")) {
            final String email = user.attributes().stream()
                    .filter(attr -> "email".equals(attr.name()))
                    .map(AttributeType::value)
                    .findFirst()
                    .orElse(null);

            final String phone = user.attributes().stream()
                    .filter(attr -> "phone_number".equals(attr.name()))
                    .map(AttributeType::value)
                    .findFirst()
                    .orElse(null);

            if (email == null) continue;

            User us = userRepository.findByEmail(email).orElse(null);
            if (us == null) continue;

            if (!studentRepository.existsByUser(us)) {
                Student student = new Student();
                student.setUser(us);
                student.setPhone(phone);
                studentRepository.save(student);
            }
        }

        // Xử lý TEACHER
        for (UserType user : cognitoUserService.getUserTypes("TEACHER")) {
            final String email = user.attributes().stream()
                    .filter(attr -> "email".equals(attr.name()))
                    .map(AttributeType::value)
                    .findFirst()
                    .orElse(null);

            final String phone = user.attributes().stream()
                    .filter(attr -> "phone_number".equals(attr.name()))
                    .map(AttributeType::value)
                    .findFirst()
                    .orElse(null);

            if (email == null) continue;

            User us = userRepository.findByEmail(email).orElse(null);
            if (us == null) continue;

            // Xóa Student bằng native query (tránh TransientObjectException)
            studentRepository.deleteByUserId(us.getId());

            // Tạo Teacher
            if (!teacherRepository.existsByUser(us)) {
                Teacher teacher = new Teacher();
                teacher.setUser(us);
                teacher.setPhone(phone);
                teacherRepository.save(teacher);
            }
        }
    }
}