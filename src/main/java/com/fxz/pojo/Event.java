package com.fxz.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private Integer id;
    private String name;
    private String url;
    private String type;
    private LocalDate startTime;
    private LocalDate endTime;
}
