package com.project.poinhanshin.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCondition {
    private Integer page = 1;
    private Integer pageSize = 50;
    private Integer offset = 0;
    private String keyword = "";
    private String option = "";

    public SearchCondition(){}

    public SearchCondition(Integer page, Integer pageSize, String keyword, String option) {
        this.page = page;
        this.pageSize = pageSize;
        this.keyword = keyword;
        this.option = option;
    }
}
