package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class DecodeJsonToImage {

    public static void main(String[] args) {
        String jsonFilePath = "output.json"; // JSON 文件路径
        String outputImagePath = "output.jpg"; // 输出图片路径

        try {
            // 读取 JSON 文件
            ObjectMapper objectMapper = new ObjectMapper();
            UploadStarterVO uploadStarterVO = objectMapper.readValue(new File(jsonFilePath), UploadStarterVO.class);

            // 打印解析的信息
            System.out.println("Competition ID: " + uploadStarterVO.getCompetitionId());
            System.out.println("Video Name: " + uploadStarterVO.getVideoName());
            System.out.println("Video Time Length: " + uploadStarterVO.getVideoTimeLength());

            // 解码 Base64 并输出图片
            String base64Image = uploadStarterVO.getVideoCoverStr();
            if (base64Image != null && base64Image.startsWith("data:image/jpeg;base64,")) {
                base64Image = base64Image.replace("data:image/jpeg;base64,", "");
            }
            byte[] imageBytes = Base64.getDecoder().decode(base64Image);

            try (FileOutputStream fos = new FileOutputStream(outputImagePath)) {
                fos.write(imageBytes);
            }

            System.out.println("图片已成功输出到: " + outputImagePath);

        } catch (IOException e) {
            System.err.println("解析 JSON 或输出图片时发生错误: " + e.getMessage());
        }
    }

    // 数据类
    static class UploadStarterVO {
        private Long competitionId;
        private String videoCoverStr;
        private Long videoTimeLength;
        private String videoName;

        // Getters and Setters
        public Long getCompetitionId() {
            return competitionId;
        }

        public void setCompetitionId(Long competitionId) {
            this.competitionId = competitionId;
        }

        public String getVideoCoverStr() {
            return videoCoverStr;
        }

        public void setVideoCoverStr(String videoCoverStr) {
            this.videoCoverStr = videoCoverStr;
        }

        public Long getVideoTimeLength() {
            return videoTimeLength;
        }

        public void setVideoTimeLength(Long videoTimeLength) {
            this.videoTimeLength = videoTimeLength;
        }

        public String getVideoName() {
            return videoName;
        }

        public void setVideoName(String videoName) {
            this.videoName = videoName;
        }
    }
}
