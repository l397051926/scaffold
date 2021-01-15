
package com.lmx.scaffold.api.dto;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Getter;

import java.util.List;

/**
 * page info
 *
 * @param <T> model
 */
@Getter
public class PageInfo<T> {

    /**
     * list
     */
    private List<T> totalList;
    /**
     * total count
     */
    private Long total = 0L;
    /**
     * page size
     */
    private Integer totalPage = 20;
    /**
     * current page
     */
    private Long currentPage = 0L;

    public PageInfo(IPage<T> page) {
        this.totalList = page.getRecords();
        this.total = page.getTotal();
        this.currentPage = page.getCurrent();
    }
}
