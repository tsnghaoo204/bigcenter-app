package com.bigcenter.app.dtos.requests.grade;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GradeDto {
    private BigDecimal score;
    private BigDecimal midtermScore;
    private BigDecimal finalScore;

}
