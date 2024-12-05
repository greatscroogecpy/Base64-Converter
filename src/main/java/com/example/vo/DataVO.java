package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DataVO {

    private long competitionID;

    private String videoCoverStr;

    private long videoTimeLength;

    private String videoName;
}
