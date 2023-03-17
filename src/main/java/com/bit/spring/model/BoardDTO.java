package com.bit.spring.model;

import lombok.Data;

import java.sql.Date;

@Data
public class BoardDTO {
    private int id;
    private  int writerId;
    private String title;
    private String content;
    private String writerNickname;
    private Date entrydate;
    private Date modifydate;

}
