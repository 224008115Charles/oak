package cn.zhangxd.oak.core.service.impl;

import cn.zhangxd.oak.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

public abstract class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {

}
