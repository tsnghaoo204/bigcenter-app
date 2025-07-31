package com.bigcenter.app.dtos.requests.subject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto {
    private String subjectName;
    private UUID teacherId;
}
