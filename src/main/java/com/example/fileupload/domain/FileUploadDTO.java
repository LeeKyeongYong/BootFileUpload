package com.example.fileupload.domain;

import lombok.*;
import java.util.Date;
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Data // getter setter
@ToString
public class FileUploadDTO {
    private Integer no;               //번호
    private String title;             //제목
    private String content;           //내용
    private Date wdate;               //작성날짜
}
