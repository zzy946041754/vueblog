package com.zzy.vueblog.service.impl;

import com.zzy.vueblog.entity.Blog;
import com.zzy.vueblog.mapper.BlogMapper;
import com.zzy.vueblog.service.BlogService;
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
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
