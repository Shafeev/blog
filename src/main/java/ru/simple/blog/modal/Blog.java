package ru.simple.blog.modal;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.Date;

@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 200)
    private String title;

    @Column(name = "sub_url", length = 200)
    private String subUrl;

    @Column(name ="cover_image", length = 200)
    private String coverImage;

    @Column(columnDefinition = "TEXT")
    private String content;

//    private String categoryName;
//    private Long categoryId;

    @Column(length = 200)
    private String tags;

    private boolean status;

    private BigInteger views;

    @Column(name = "enable_comment", updatable = true)
    private boolean enableComment;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_time", updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    private Date updateTime;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }
}
