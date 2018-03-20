package cn.zhangxd.oak.uaa.client.fallback;

import cn.zhangxd.oak.uaa.client.SysUserService;
import cn.zhangxd.oak.uaa.client.vo.SysUserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author zhangxd
 */
@Service
public class SysUserServiceFallbackImpl implements SysUserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public SysUserVO findUserByLogin(String login) {
        logger.error("调用 {} 异常: {}", "findUserByLogin", login);
        return null;
    }

}
