package com.example.fileupload.domain;

import lombok.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadDTO {
    private Integer no;               //번호
    private String title;             //제목
    private String content;           //내용
    private Date wdate;               //작성날짜
    private Boolean isDeleted;        //자동생성

    /*
         isDeleted 필드는 @Data 애노테이션이 적용되어 있어서 자동으로 getter/setter 메서드와
         equals(), hashCode(), toString() 메서드가 생성됩니다.
     */

    public FileUploadDTO(Integer no, String title, String content, Date wdate) {
        this.no = no;
        this.title = title;
        this.content = content;
        this.wdate = wdate;
        this.isDeleted = false;
    }
}