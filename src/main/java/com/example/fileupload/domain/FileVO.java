package com.example.fileupload.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileVO implements Serializable {
    private Integer no;               //번호
    private String title;             //제목
    private String content;           //내용
    private Date wdate;               //작성일자
    private MultipartFile picture;    //파일명
    private Boolean isDeleted;        //자동생성
     /*
         isDeleted 필드는 @Data 애노테이션이 적용되어 있어서 자동으로 getter/setter 메서드와
         equals(), hashCode(), toString() 메서드가 생성됩니다.
     */

    public FileVO(Integer no, String title, String content, Date wdate,MultipartFile picture) {
        this.no = no;
        this.title = title;
        this.content = content;
        this.wdate = wdate;
        this.picture = picture;
        this.isDeleted = false;
    }

    public FileVO(Integer no, String title, String content, Date wdate) {
        this.no = no;
        this.title = title;
        this.content = content;
        this.wdate = wdate;
        this.isDeleted = false;
    }

}
