package cn.zhangxd.oak.uaa.client;

import cn.zhangxd.oak.core.feign.AuthorizedFeignClient;
import cn.zhangxd.oak.uaa.client.fallback.UserServiceFallbackImpl;
import cn.zhangxd.oak.uaa.client.vo.UserVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zhangxd
 */
@AuthorizedFeignClient(name = "account", fallback = UserServiceFallbackImpl.class)
public interface UserService {
    /**
     * 通过用户名查询用户、角色信息
     *
     * @param login 用户名
     * @return UserVo
     */
    @GetMapping("/api/user/findUserByLogin/{login}")
    UserVO findUserByLogin(@PathVariable("login") String login);

}
