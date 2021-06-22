package com.zhiqian.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchBlog {
    private String title;
    private Long typeId;
    private Boolean recommened;
}
