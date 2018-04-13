package com.reunion.common;

public class ReunionPagingInfo {

    private int page = 1;
    private int pageVal = 0;
    private int pageSize = 3;
    private int totCount;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageVal() {
        return pageVal;
    }

    public void setPageVal(int pageVal) {
        this.pageVal = pageVal;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotCount() {
        return totCount;
    }

    public void setTotCount(int totCount) {
        this.totCount = totCount;
    }
}
