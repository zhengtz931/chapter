package cn.edu.nenu.controller;

import cn.edu.nenu.config.Constants;
import cn.edu.nenu.config.HttpServlet;
import cn.edu.nenu.domain.Dictionary;
import cn.edu.nenu.domain.Storage;
import cn.edu.nenu.domain.User;
import cn.edu.nenu.service.UserService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import static cn.edu.nenu.config.Constants.PAGE_SIZE;

/**
 * UserController Class
 *
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-03-04 22:54
 */
@CommonsLog
@Controller
@RequestMapping("/user")
public class UserController {

    private static final int PAGE_SIZE = 20;

    @Autowired
    public UserService userService;

    /**
     * 列表页面，涉及到分页
     * @param pageNumber
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("")
    public String list(@RequestParam(value = "sortType", defaultValue = "auto") String sortType,
                       @RequestParam(value = "page", defaultValue = "1") int pageNumber, Model model, ServletRequest request){
        Map<String, Object> searchParams = HttpServlet.getParametersStartingWith(request, "s_");
        Page<User> users = userService.getPage(pageNumber,PAGE_SIZE,searchParams,sortType);
        model.addAttribute("users",users);
        model.addAttribute("PAGE_SIZE", PAGE_SIZE);
        model.addAttribute("searchParams", HttpServlet.encodeParameterStringWithPrefix(searchParams, "s_"));

        return "user/list"; //视图名，视图路径
    }

    /**
     * 进入新增页面
     */
    @GetMapping(value = "/create")
    public String createForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("action", "create");
        return "user/form";
    }

    /**
     * 新增页面，提交保存
     * @author zhangtz, 2020.06.23
     */
    @PostMapping(value = "/create")
    public String create(@Valid User newDict, RedirectAttributes redirectAttributes)
    {

        userService.save(newDict);
        redirectAttributes.addFlashAttribute("message", "创建数据字典成功");
        return "redirect:/user/";
    }



    /**
     * 根据主键ID获取实体，获取详细信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public User get(@PathVariable("id")Long id){
        return userService.findOne(id);
    }

    /**
     * 进入创建用户页面
     * @param model
     * @return
     */
    //@GetMapping("/create")
    //public String createForm(Model model){
    //    return "user/form";
    //}
    //@GetMapping("/create")
    //public ModelAndView createFrm(Model model){
    //    return new ModelAndView("user/form");
    //}

    /**
     * 填写用户信息后保存信息到数据库
     *
     * form表单标签属性name的值
     * user.username
     * user.password
     * user.createdAt
     * <input type="text" name="user.username" value=""/>
     *
     *
     * @param attributes
     * @return
     */


    //@PostMapping
    //public String createJSON(@RequestBody String json, RedirectAttributes attributes){
    //    //对json进行发序列化，变成参数对象
    //    return "redirect:/user"; //视图路径
    //}
    //@PostMapping
    //public String createRequest(HttpServletRequest request, RedirectAttributes attributes){
    //    String username = request.getParameter("username");
    //    User newUser = new User();
    //    newUser.setUsername(username);
    //
    //    userService.save(newUser);
    //    return "redirect:/user"; //视图路径
    //}
    /**
     * 进入编辑页面
     */
    @GetMapping(value = "update/{id}")
    public String updateForm(@PathVariable("id") Long pkId, Model model){
        User user = userService.findOne(pkId);
        model.addAttribute("user",user);
        model.addAttribute("action", "update");
        return "user/form";
    }

    /**
     * 页面编辑后，保存
     */
    @PostMapping(value = "update")
    public String update(@Valid User user, RedirectAttributes redirectAttributes){
        Long pkId = user.getId();
        User newDict = userService.findOne(pkId);
        newDict.setCreatedAt(user.getCreatedAt());
        newDict.setName(user.getName());
        newDict.setPassword(user.getPassword());
        newDict.setUsername(user.getUsername());
        newDict.setSex(user.getSex());
        newDict.setStatus(user.getStatus());
        userService.save(newDict);
        redirectAttributes.addFlashAttribute("message", "更改数据字典信息成功");
        return "redirect:/user/";
    }

    /**
     * 根据ID删除单个字典
     */
    @GetMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Long pkId, RedirectAttributes redirectAttributes) {
        String message = "删除字典成功";
        try {
            userService.remove(pkId);
        }catch (Exception e){
            message = "删除字典失败，该字典被使用";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/user/";
    }

    /**
     * 批量删除
     */
    @PostMapping(value = "delete")
    public String deleteBatch(ServletRequest request,RedirectAttributes redirectAttributes){
        String[] chkIds = request.getParameterValues("chkIds");
        for (String id:chkIds){
            userService.remove(Long.valueOf(id));
        }
        return "redirect:/user/";
    }

}
