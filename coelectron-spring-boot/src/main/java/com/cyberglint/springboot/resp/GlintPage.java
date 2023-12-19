package com.cyberglint.springboot.resp;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * GlintPageDto类 - 用于处理分页请求的数据传输对象（DTO）。
 */
public class GlintPage {
    
    // 起始页码，默认为1。
    public static final Integer PAGE_FIRST = 1;
    
    // 默认分页大小，默认值为10。
    public static final Integer DEFAULT_PAGE_SIZE = 10;
    
    // 页码，最小值为1。
    @Min(value = 1, message = "页码不能小于1。")
    private Integer pageNum;
    
    // 每页显示的记录数，最小值为1，最大值为10000。
    @Min(value = 1, message = "分页大小不能小于1。")
    @Max(value = 10000, message = "分页大小不能大于10000。")
    private Integer pageSize;
    
    /**
     * 获取页码。
     * 如果未设置页码，则返回默认的起始页码。
     * @return 页码
     */
    public Integer getPageNum() {
        return pageNum != null ? pageNum : PAGE_FIRST;
    }
    
    /**
     * 获取每页显示的记录数。
     * 如果未设置分页大小，则返回默认的分页大小。
     * @return 分页大小
     */
    public Integer getPageSize() {
        return pageSize != null ? pageSize : DEFAULT_PAGE_SIZE;
    }
    
    /**
     * 创建分页查询实例。
     * @param <T> 数据类型
     * @return 分页查询实例
     */
    public <T> IPage<T> newPageInstance() {
        return new Page<>(getPageNum(), getPageSize());
    }
    
    /**
     * 创建不需计算总记录数的分页查询实例。
     * 如果在分页查询时不需要获取总记录数，使用此方法可以提高效率。
     * @param <T> 数据类型
     * @return 分页查询实例
     */
    public <T> IPage<T> noCountPageInstance() {
        Page<T> page = new Page<>(getPageNum(), getPageSize());
        page.setSearchCount(false);
        return page;
    }
}