package com.cyberglint.springboot.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author shushun
 * Created  on 2020/7/5.
 */

@Data
@AllArgsConstructor
public class CyberGlintPageResponse<T>  {
    
    private List<T> list;
    private Long total;
    private Long pageNum;
    private Long pageSize;
    private Map<String, Object> extra;

    public CyberGlintPageResponse() {
    }

    public static <T> CyberGlintPageResponse<T> create(List<T> data) {
        return create(data, 0L);
    }

    public static <T> CyberGlintPageResponse<T> createEmpty() {
        return create(Collections.emptyList(), (Long) null);
    }

    public static <T> CyberGlintPageResponse<T> create(List<T> data, Long total) {
        return create(data, total, null, null);
    }

    public static <T> CyberGlintPageResponse<T> create(List<T> data, Long total, Long pageNum, Long pageSize) {
        return create(data, total, pageNum, pageSize, null);
    }

    public static <T> CyberGlintPageResponse<T> create(List<T> data, Long total, Long pageNum, Long pageSize, Map extra) {
        return new CyberGlintPageResponse(data, total, pageNum, pageSize, extra);
    }

    public static <T> CyberGlintPageResponse<T> create(List<T> data, IPage<?> iPage) {
        return new CyberGlintPageResponse(data, iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), null);
    }
}
