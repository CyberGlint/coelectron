package com.cyberglint.mybatis.util;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyberglint.common.CyberGlintBaseEo;
import org.springframework.beans.BeanUtils;

/**
 * 基础数据层
 */
public class CyberGlintDas<M extends BaseMapper<T>, T extends CyberGlintBaseEo> extends ServiceImpl<M, T> {
    /**
     * 判断是否存在该条件数据
     */
    public boolean exist(LambdaQueryWrapper<T> queryWrapper) {
        queryWrapper.last("limit 1");
        queryWrapper.select(T::getId);
        return super.getOne(queryWrapper) != null;
    }
    
    
    /**
     * 将查询出的数据转换为给定的 VO 类型
     */
    public <V> V convertEntityToVo(T entity, Class<V> voClass) {
        if (entity == null) {
            return null;
        }
        V vo = null;
        try {
            vo = voClass.newInstance();
            BeanUtils.copyProperties(entity, vo);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace(); // 建议使用更合适的错误处理逻辑
        }
        return vo;
    }
    
}
