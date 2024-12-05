package com.example;

import com.example.vo.ContentVO;
import com.example.vo.DataVO;
import com.example.vo.UploadStarterVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

public class UploadStarterVOGenerator {

    public static void main(String[] args) {
        // 输入参数
        long competitionId = 12345L;
        String videoFileName = "example_video.mp4";
        String imageFilePath = "image.jpg"; // 图片路径
        //String imageFilePath = UploadStarterVOGenerator.class.getClassLoader().getResource("example_image.jpg").getPath();
        long videoTimeLength = 3600L;

        try {
            // 将图片转换为 Base64
            String videoCoverStr = encodeImageToBase64(imageFilePath);

            // 构建数据对象
            DataVO dataVO = new DataVO(competitionId, videoCoverStr, videoTimeLength, videoFileName);
            ContentVO content = new ContentVO("uploadStart", "12345da", "json", dataVO);
            UploadStarterVO uploadStarterVO = new UploadStarterVO("json", content);

            // 转换为 JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonOutput = objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(uploadStarterVO);

            // 输出 JSON
            System.out.println(jsonOutput);

            File outputFile = new File("output.json");
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, uploadStarterVO);

        } catch (IOException e) {
            System.err.println("Error while generating JSON: " + e.getMessage());
        }
    }

    // 将图片转换为 Base64 字符串
    private static String encodeImageToBase64(String imagePath) throws IOException {
        File file = new File(imagePath);
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] imageBytes = new byte[(int) file.length()];
            fileInputStream.read(imageBytes);
            return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytes);
        }
    }



}



