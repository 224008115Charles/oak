package cn.zhangxd.oak.uaa.client.fallback;

import cn.zhangxd.oak.uaa.client.UserService;
import cn.zhangxd.oak.uaa.client.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author zhangxd
 */
@Service
public class UserServiceFallbackImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public UserVO findUserByLogin(String login) {
        logger.error("调用 {} 异常: {}", "findUserByLogin", login);
        return null;
    }

}
