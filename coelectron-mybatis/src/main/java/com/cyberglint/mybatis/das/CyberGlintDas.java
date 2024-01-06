package com.cyberglint.mybatis.das;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyberglint.common.eo.CyberGlintBaseEo;
import com.cyberglint.common.execption.CyberGlintBizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * 基础数据层
 */
@Slf4j
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
    public <V> V convert(T entity, Class<V> voClass) {
        if (entity == null) {
            CyberGlintBizException.throwErrorWithMessage("转换实体为空");
        }
        V vo = null;
        try {
            vo = voClass.getConstructor().newInstance();
            BeanUtils.copyProperties(entity, vo);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            log.error("转换实体异常:",e);
        }
        return vo;
    }
    
}
