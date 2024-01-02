package com.cyberglint.mybatis.util;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @author gl
 * @description
 * @date 2021/9/18
 */

public class CyberGlintDas<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {

}
