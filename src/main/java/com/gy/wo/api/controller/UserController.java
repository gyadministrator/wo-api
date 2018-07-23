package com.gy.wo.api.controller;
import com.gy.wo.api.entity.User;
import com.gy.wo.api.jwt.utils.AccessToken;
import com.gy.wo.api.jwt.utils.Audience;
import com.gy.wo.api.jwt.utils.JwtUtils;
import com.gy.wo.api.service.UserService;
import com.gy.shop.shopapi.utils.Md5Utils;
import com.gy.wo.api.utils.response.SimpleResponse;
import com.gy.wo.api.config.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.gy.wo.api.utils.response.HttpResponseAndStatus.simpleResponse;

/**
 * @author gaoyun
 * 2018/4/18 12:46
 * 描述:
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户管理")
public class UserController {

    private final UserService userService;

    private final Audience audienceEntity;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserController(UserService userService, Audience audienceEntity) {
        this.userService = userService;
        this.audienceEntity = audienceEntity;
    }

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public SimpleResponse login(@RequestBody User user) {
        logger.info("登录的用户为:" + user.toString());
        User u = this.userService.findByName(user.getAccount());
        String accessToken = null;
        if (u != null) {
            String pwd = u.getPassword();
            String md5Pwd = Md5Utils.MD5(user.getPassword() + u.getUserCode());
            if (pwd.equals(md5Pwd)) {
                accessToken = JwtUtils.createJWT(
                        u.getAccount(),
                        u.getUserCode(),
                        audienceEntity.getClientId(),
                        audienceEntity.getName(),
                        audienceEntity.getExpiresSecond() * 1000,
                        audienceEntity.getBase64Secret());
                //返回accessToken
                AccessToken accessTokenEntity = new AccessToken();
                accessTokenEntity.setAccess_token(accessToken);
                accessTokenEntity.setExpires_in(audienceEntity.getExpiresSecond());
                accessTokenEntity.setToken_type(Constants.BEARER);
            }
        }
        assert u != null;
        return simpleResponse(200, "", accessToken);
    }
}
