package com.gouwo.service.impl;

import com.gouwo.model.CommentModel;
import com.gouwo.mapper.CommentMapper;
import com.gouwo.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 图文评论表 服务实现类
 * </p>
 *
 * @author asky
 * @since 2020-07-12
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, CommentModel> implements CommentService {

}
