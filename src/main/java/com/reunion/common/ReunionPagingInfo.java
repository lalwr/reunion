package com.reunion.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReunionPagingInfo {

    private int page = 1;
    private int pageSize = 3;
    private int totCount;

}
