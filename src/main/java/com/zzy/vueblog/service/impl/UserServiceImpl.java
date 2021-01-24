package com.zzy.vueblog.service.impl;

import com.zzy.vueblog.entity.User;
import com.zzy.vueblog.mapper.UserMapper;
import com.zzy.vueblog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2021-01-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
