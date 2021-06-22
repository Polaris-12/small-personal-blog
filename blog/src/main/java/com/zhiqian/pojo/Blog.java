package com.zhiqian.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    private Long id;
    private String title;
    //分类
    private Type type;
    //用户
    private User user;

    private String content;
    //首图地址
    private String firstPicture;
    //浏览次数
    private Integer views;
    //赞赏是否开启
    private boolean appreciation;
    //评论是否开启
    private boolean commentabled;
    //是否推荐
    private boolean recommened;
    //是否发布
    private boolean published;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

}
