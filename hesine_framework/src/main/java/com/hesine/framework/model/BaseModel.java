package com.hesine.framework.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: jason
 * Date: 13-11-12
 * Time: 上午11:28
 * To change this template use File | Settings | File Templates.
 */
public class BaseModel implements Serializable {
    // 默认页
    public final static Integer DEFAULT_PAGE_NO = 1;
    public final static Integer DEFAULT_PAGE_SIZE = 10;
    //其实索引
    private Integer beginIndex;
    //一页显示多少条
    private Integer pageSize = 1;
    private Integer pageNo = 1;

    /**
     * 获取分页开始索引
     * @return
     */
    public Integer getBeginIndex() {
        if ( this.pageNo <= 1){
            beginIndex = 0;
        }else{
            beginIndex = (this.pageNo - 1) * this.pageSize;
        }
        return beginIndex;
    }

    /**
     * 设置分页开始索引
     * @param beginIndex
     */
    public void setBeginIndex(Integer beginIndex) {
        this.beginIndex = beginIndex;
    }

    /**
     * 获得分页size
     * @return
     */
    public Integer getPageSize() {
        if (this.pageSize < 1){
            this.pageSize = 1;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        if (this.pageNo < 1){
            this.pageNo = 1;
        }
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
}
