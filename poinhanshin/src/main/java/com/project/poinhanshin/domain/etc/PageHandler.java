package com.project.poinhanshin.domain.etc;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageHandler {
    private int totalCnt; // 총 게시물 개수
    private int pageSize; // 한 페이지의 크기
    private int navSize = 10; // 페이지 내비게이션의 크기
    private int totalPage; // 전체 페이지의 개수
    private int page; // 현재 페이지
    private int beginPage; // 내비게이션의 첫번째 페이지
    private int endPage; // 내비게이션의 마지막 페이지
    private boolean showPrev; // 이전 페이지로 이동하는 링크를 보여줄 것인지의 여부
    private boolean showNext; // 다음 페이지로 이동하는 링크를 보여줄 것인지의 여부

    public PageHandler(int totalCnt, int page) {
        this(totalCnt, page, 50);
    }

    public PageHandler(int totalCnt, int page, int pageSize) {
        this.totalCnt = totalCnt;
        this.page = page;
        this.pageSize = pageSize;

        if (totalCnt <= 0) {
            totalPage = 1;
            beginPage = 1;
            endPage = 1;
            showPrev = false;
            showNext = false;
        } else {
            totalPage = (int) Math.ceil(totalCnt / (double) pageSize);
            beginPage = (page - 1) / navSize * navSize + 1;
            endPage = Math.min(beginPage + navSize - 1, totalPage);
            showPrev = beginPage != 1;
            showNext = endPage != totalPage;
        }
    }

    void print() {
        System.out.println("page = " + page);
        System.out.print(showPrev ? "[PREV]" : "");
        for (int i = beginPage; i <= endPage; i++) {
            System.out.print(i+" ");
        }
        System.out.println(showNext ? " [NEXT]": "");
    }


}
