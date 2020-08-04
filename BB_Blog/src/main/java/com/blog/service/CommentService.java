package com.blog.service;

import com.blog.entity.Comment;

import java.util.List;
import java.util.Map;

/**
 * 评论
 * @author 6JIE
 * @create 2020-04-22 0:45
 */
public interface CommentService {
    /**添加一条评论*/
    public int add(Comment comment);

    /**更新一条评论*/
    public int update(Comment comment);

    /**评论查询*/
    public List<Comment> list(Map<String,Object> map);

    /**评论数量*/
    public Long getTotal(Map<String,Object> map);

    /**删除评论*/
    public Integer delete(Integer id);
}
