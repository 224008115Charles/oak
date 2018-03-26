package cn.zhangxd.oak.account.controller;


import cn.zhangxd.oak.core.controller.BaseController;
import cn.zhangxd.oak.account.service.IUserService;
import cn.zhangxd.oak.account.service.dto.WithPasswordUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author zhangxd
 * @since 2018-03-18
 */
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    /**
     * 通过用户名查询用户及其角色信息
     *
     * @param login 用户名
     * @return UseVo 对象
     */
    @PreAuthorize("#oauth2.hasScope('web-app')")
    @GetMapping("/findUserByLogin/{login}")
    public WithPasswordUserDTO findUserByUsername(@PathVariable String login) {
        return userService.findUserByLogin(login);
    }

}

