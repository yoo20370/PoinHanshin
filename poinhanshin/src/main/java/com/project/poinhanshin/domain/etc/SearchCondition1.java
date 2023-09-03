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
    // 0 - 모두 , 1 - 고양이, 2 - 강아지
    private Integer ani_category = 0;

    public SearchCondition1(){}
    public SearchCondition1(Integer page, Integer pageSize, String keyword, String option , Integer ani_category) {
        this.page = page;
        this.pageSize = pageSize;
        this.keyword = keyword;
        this.option = option;
        this.ani_category = ani_category;
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
                .queryParam("ani_category" , ani_category)
                .build().toString();
    }
    public int getOffset() {
        return (page - 1) * pageSize;
    }

   /* public String toString() {
        return "SearchCondition{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", offset=" + getOffset() +
                ", keyword='" + keyword + '\'' +
                ", option='" + option + '\'' +
                ", ani_category=" + ani_category +
                '}';
    }*/
}
