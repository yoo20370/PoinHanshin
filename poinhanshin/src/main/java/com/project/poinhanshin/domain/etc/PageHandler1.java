package com.project.poinhanshin.domain.etc;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageHandler1 {
    private SearchCondition1 sc; // page , pageSize , keyword , option , offset
    private int totalCnt;
    private int totalPage;
    private int naveSize = 10;
    private int beginPage;
    private int endPage;
    private boolean showPrev;
    private boolean showNext;

    public PageHandler1(){}
    public PageHandler1(int totalCnt , SearchCondition1 sc) {
        this.totalCnt = totalCnt;
        this.sc = sc;

        doPaging(totalCnt , sc);
    }

    void doPaging(int totalCnt  , SearchCondition1 sc){

        totalPage = (int)Math.ceil(totalCnt / (double) sc.getPageSize());
        beginPage = (sc.getPage() - 1) / naveSize * naveSize + 1;
        endPage = Math.min(beginPage + naveSize - 1 , totalPage);
        showPrev = beginPage != 1;
        showNext = endPage != totalPage;
    }


}
