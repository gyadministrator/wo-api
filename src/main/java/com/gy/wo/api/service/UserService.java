package com.gy.wo.api.service;
import com.gy.wo.api.entity.User;

/**
 * @author gaoyun
 * 2018/4/18 10:57
 * 描述:
 */
public interface UserService extends BaseService<User, String> {
    /**
     * 通过id查询用户
     *
     * @param userName 用户名
     * @return
     */
    User findByName(String userName);

    /**
     * 解析token
     *
     * @param token 用户登录返回的token
     * @return
     */
    String parseToken(String token);
}
