package com.example.blogsystemdemo3.controller;

import com.example.blogsystemdemo3.model.Blog;
import com.example.blogsystemdemo3.model.User;
import com.example.blogsystemdemo3.model.UserSession;
import com.example.blogsystemdemo3.searcher.Index;
import com.example.blogsystemdemo3.searcher.model.BlogInfo;
import com.example.blogsystemdemo3.service.BlogService;
import com.example.blogsystemdemo3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: YT
 * @Description: 博客controller
 * @DateTime: 2022/7/27$ - 23:30
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    @RequestMapping("/upload")
    public Map<String,Object> upload(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file, HttpServletRequest request) {
        System.out.println("hello");
        Map<String,Object> resultMap = new HashMap<String,Object>();
        //保存
        try {
            //File imageFolder= new File(request.getServletContext().getRealPath("img/upload"));
            File imageFolder = new File("E:\\Javacode\\javaweb\\BlogSystemDemo3\\tomcat_tmp\\work\\Tomcat\\localhost\\ROOT\\img\\upload");
            //String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
            //String finalName = UUID.randomUUID().toString() + file.getOriginalFilename();
            File targetFile = new File(imageFolder, file.getOriginalFilename());
            if (!imageFolder.exists()) {
                imageFolder.mkdirs();
            }
            if(!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }

            file.transferTo(targetFile);
            System.out.println(targetFile.getAbsolutePath());
            System.out.println(targetFile.getCanonicalPath());

            resultMap.put("success", 1);
            resultMap.put("message", "上传成功！");
            resultMap.put("url","http://localhost:9092/img/upload/"+file.getOriginalFilename());
        } catch (Exception e) {
            resultMap.put("success", 0);
            resultMap.put("message", "上传失败！");
            e.printStackTrace();
        }
        System.out.println(resultMap.get("success"));
        return resultMap;

    }


    @Autowired
    private BlogService blogService;

    static class ReqBlog {
        public String title;
        public String content;
    }

    @RequestMapping("/addBlog")
    public Map<String,Object> addBlog(@RequestBody ReqBlog reqBlog, HttpServletRequest request) {
        Map<String,Object> result = new HashMap<>();
        if (!StringUtils.hasLength(reqBlog.content) || !StringUtils.hasLength(reqBlog.title)) {
            result.put("success", 200);
            result.put("data",0);
            result.put("message","标题或正文不能为空");
            return result;
        }
        HttpSession session = request.getSession(false);
        UserSession userSession = (UserSession) session.getAttribute("userSession");
        Blog blog = new Blog();
        int userId = userSession.getUserId();
        blog.setUserId(userId);
        blog.setTitle(reqBlog.title);
        blog.setContent(reqBlog.content);
        blog.setPostTime(new Timestamp(System.currentTimeMillis()));
        int data = blogService.addBlog(blog);

        result.put("success", 200);
        result.put("data",data);
        result.put("message","插入成功");

        return result;
    }

    static class BlogDesc {
        public int blogId;
        public String title;
        public String blogDesc;
    }

    @RequestMapping("/getBlogs")
    public List<BlogDesc> getAllBlogDesc() {
        List<Blog> blogs = blogService.getAllBlogDesc();
        List<BlogDesc> blogDescs = new ArrayList<>();
        for (Blog blog: blogs) {
            BlogDesc blogDesc = new BlogDesc();
            blogDesc.blogId = blog.getBlogId();
            blogDesc.title = blog.getTitle();
            blogDesc.blogDesc = blog.getBlogDesc();
            blogDescs.add(blogDesc);
        }
        return blogDescs;
    }

    static class BlogDetail {
        public int blogId;
        public String title;
        public Timestamp postTime;
        public int userId;
        public String content;
        public int likes;
        public int isYourBlog;
    }

    @RequestMapping("/getBlog")
    public BlogDetail getAllBlogDesc(int blogId,HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserSession userSession = (UserSession) session.getAttribute("userSession");
        Blog blog = blogService.getBlogById(blogId);
        BlogDetail blogDetail = new BlogDetail();
        blogDetail.blogId = blog.getBlogId();
        blogDetail.title = blog.getTitle();
        blogDetail.postTime = blog.getPostTime();
        blogDetail.content = blog.getContent();
        blogDetail.likes = blog.getLikes();
        blogDetail.userId = blog.getUserId();
        blogDetail.isYourBlog = userSession.getUserId() == blog.getUserId() ? 1 : 0;
        return blogDetail;
    }

    private Map<Integer,Set<Integer>> likeMap = new HashMap<>();
    @RequestMapping("/likes")
    public int incLikes(int blogId,HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserSession userSession = (UserSession) session.getAttribute("userSession");
        Set<Integer> likeUsers = likeMap.get(blogId);
        if (likeUsers == null) {
            int ret = blogService.incLikes(blogId);
            Integer integer = new Integer(blogId);
            likeUsers = new HashSet<>();
            likeUsers.add(userSession.getUserId());
            likeMap.put(integer,likeUsers);
            return ret;
        } else {
            if (likeUsers.contains(userSession.getUserId())) {
                return blogService.getLikes(blogId);
            } else {
                return blogService.incLikes(blogId);
            }
        }
    }

    static class UpdateBlog {
        public int blogId;
        public String title;
        public String content;
    }

    @RequestMapping("/updateBlog")
    public Map<String,Object> updateBlog(@RequestBody UpdateBlog updateBlog, HttpServletRequest request) {
        Map<String,Object> result = new HashMap<>();
        HttpSession session = request.getSession(false);
        UserSession userSession = (UserSession) session.getAttribute("userSession");
        Blog beforeBlog = blogService.getBlogById(updateBlog.blogId);
        System.out.println(beforeBlog);
        if (beforeBlog.getUserId() != userSession.getUserId()) {
            result.put("success",200);
            result.put("data",0);
            result.put("message","不是你的博客，不能修改");
            return result;
        }

        if (beforeBlog.getContent().equals(updateBlog.content)) {
            result.put("success",200);
            result.put("data",0);
            result.put("message","没有修改");
            return result;
        }

        int ret = blogService.updateBlog(updateBlog.content,updateBlog.title,updateBlog.blogId);
        result.put("success",200);
        result.put("data",ret);
        result.put("message","修改成功");
        return result;
    }

    @RequestMapping("/deleteBlog")
    public Map<String,Object> deleteBlog(int blogId,HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        UserSession userSession = (UserSession) session.getAttribute("userSession");
        int blogOwner = blogService.getUserIdByBlogId(blogId);

        int userId = userSession.getUserId();
        Map<String,Object> result = new HashMap<>();
        if (blogOwner != userId) {
            result.put("success",200);
            result.put("data",0);
            result.put("message","不是你的博客");
            return result;
        }

        int ret = blogService.deleteBlog(blogId, userId);

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(new Runnable() {
            @Override
            public void run() {

                int i = 0;
                while (true) {
                    if (Index.forwardIndex.get(i).getBlogId() == blogId) {
                        break;
                    }
                    i++;
                }
                synchronized (Index.forwardIndex) {
                    Index.forwardIndex.get(i).setBlogId(-1);
                }
            }
        });
        if (ret == 1) {
            result.put("success",200);
            result.put("data",ret);
            result.put("message","删除成功");
        } else {
            result.put("success",200);
            result.put("data",ret);
            result.put("message","删除失败");
        }
        return result;
    }

    static class authorBlogs {
        public String username;
        public String github;
        public List<BlogDesc> blogDescs;
    }

    @Autowired
    private UserService userService;

    @RequestMapping("/authorBlogs")
    public authorBlogs getauthorBlogs(int userId) {
        UserSession userSession = userService.getUserMessage(userId);
        authorBlogs authorBlogs = new authorBlogs();
        authorBlogs.username = userSession.getUsername();
        authorBlogs.github = userSession.getGithub();
        List<Blog> blogs = blogService.getBlogsByUserId(userId);
        List<BlogDesc> blogDescs = new ArrayList<>();
        for (Blog blog: blogs) {
            BlogDesc blogDesc = new BlogDesc();
            blogDesc.blogId = blog.getBlogId();
            blogDesc.title = blog.getTitle();
            blogDesc.blogDesc = blog.getBlogDesc();
            blogDescs.add(blogDesc);
        }
        authorBlogs.blogDescs = blogDescs;
        return authorBlogs;
    }
}
