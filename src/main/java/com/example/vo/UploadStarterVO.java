package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadStarterVO {
    private String type;

    private ContentVO content;

}
