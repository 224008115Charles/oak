package cn.zhangxd.oak.uaa.client;

import cn.zhangxd.oak.uaa.client.fallback.SysUserServiceFallbackImpl;
import cn.zhangxd.oak.uaa.client.vo.SysUserVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zhangxd
 */
@AuthorizedFeignClient(name = "sys", fallback = SysUserServiceFallbackImpl.class)
public interface SysUserService {
    /**
     * 通过用户名查询用户、角色信息
     *
     * @param login 用户名
     * @return UserVo
     */
    @GetMapping("/user/findUserByLogin/{login}")
    SysUserVO findUserByLogin(@PathVariable("login") String login);

}
