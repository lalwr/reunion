package com.reunion.domain;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
public class Condition implements Serializable {

    private static final long serialVersionUID = 1L;

    private int page = 1;
    private int pageVal = 0;
    private int pageSize = 3;
    private int totCount;

    private String linkFunc;

    public void setPage(int page) {
        this.page = page;
        this.pageVal = (page - 1) * pageSize;
    }

}
