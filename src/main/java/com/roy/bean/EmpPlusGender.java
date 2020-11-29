package com.roy.bean;

import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * description：
 * author：dingyawu
 * date：created in 21:47 2020/11/28
 * history:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Accessors(chain = true)
public class EmpPlusGender {
    private Integer id;
    private String lastName;
    private String gender;
    private LocalDateTime birth;

}
