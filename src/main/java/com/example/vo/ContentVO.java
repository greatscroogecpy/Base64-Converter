package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContentVO {
    private String cmd;
    private String sessionId;
    private String dataType;
    private DataVO data;
}
