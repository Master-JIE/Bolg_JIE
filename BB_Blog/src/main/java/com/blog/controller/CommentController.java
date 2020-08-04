package com.blog.controller;

import com.blog.entity.Blog;
import com.blog.entity.Comment;
import com.blog.service.BlogService;
import com.blog.service.CommentService;
import com.blog.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 前端用户提交评论
 * @author 6JIE
 * @create 2020-04-27 16:59
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Resource
    private CommentService commentService;
    @Resource
    private BlogService blogService;
    /**提交评论*/
    @RequestMapping("/save")
    public String save(Comment comment, @RequestParam("imageCode")String imageCode,
                       HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String sRand = (String) session.getAttribute("sRand");
        JSONObject result = new JSONObject();
        int resultTotal = 0;
        if (!imageCode.equals(sRand)) {
            result.put("success",Boolean.FALSE);
            result.put("erroInfo","验证码填写错误！");
        }else {
            String userIp = request.getRemoteAddr();
            comment.setUserIp(userIp);
            if(comment.getId()==null){
                resultTotal = commentService.add(comment);

                //给对应的博客评论数+1
                Blog blog = blogService.findById(comment.getBlog().getId());
                blog.setReplyHit(blog.getReplyHit()+1);
                blogService.update(blog);
            }
        }
        if(resultTotal>0){
            result.put("success",Boolean.TRUE);
        }else {
            result.put("success",Boolean.FALSE);
        }
        ResponseUtil.write(response,result);
        return null;
    }
}
