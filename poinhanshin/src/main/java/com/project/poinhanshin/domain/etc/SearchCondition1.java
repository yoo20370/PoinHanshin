package com.project.poinhanshin.domain.etc;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@Setter
@ToString
public class SearchCondition1 {
     private Integer page = 1;
    private Integer pageSize = 10;
    private String keyword = "";
    private String option = "";
    private boolean protectboard_ani_category = true;

    public SearchCondition1(){}
    public SearchCondition1(Integer page, Integer pageSize, String keyword, String option ) {
        this.page = page;
        this.pageSize = pageSize;
        this.keyword = keyword;
        this.option = option;
    }
    public String getQueryString(){
        return getQueryString(page);
    }
    public String getQueryString(Integer page){
        return UriComponentsBuilder.newInstance()
                .queryParam("page" , page)
                .queryParam("pageSize" , pageSize)
                .queryParam("keyword" , keyword)
                .queryParam("option" , option)
                .queryParam("protectboard_ani_category" , protectboard_ani_category)
                .build().toString();
    }
    public int getOffset() {
        return (page - 1) * pageSize;
    }

    @Override
    public String toString() {
        return "SearchCondition{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", offset=" + getOffset() +
                ", keyword='" + keyword + '\'' +
                ", option='" + option + '\'' +
                ", protectboard_ani_category='" + protectboard_ani_category + '\'' +
                '}';
    }
}
