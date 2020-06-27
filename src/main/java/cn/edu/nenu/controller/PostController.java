package cn.edu.nenu.controller;

import cn.edu.nenu.config.Constants;
import cn.edu.nenu.config.HttpServlet;
import cn.edu.nenu.domain.Category;
import cn.edu.nenu.domain.Dictionary;
import cn.edu.nenu.domain.Post;
import cn.edu.nenu.domain.User;
import cn.edu.nenu.repository.CategoryRepository;
import cn.edu.nenu.service.CategoryService;
import cn.edu.nenu.service.DictionaryService;
import cn.edu.nenu.service.PostService;
import cn.edu.nenu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static cn.edu.nenu.config.Constants.PAGE_SIZE;

/**
 * DictionaryController Class
 *
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-05-21 13:10
 */
@Controller
@RequestMapping("/post")
public class PostController {


    /**
     *  00-菜单 nav，menu
     *
     *  01-模块的名称 header、title
     *
     *  02-功能按钮 (新建、批量删除) button
     *
     *  02-查询条件搜索区域 （进行条件查询） search
     *
     *  02-数据列表 content, Data,list
     *
     *  02-分页信息 （上一页、当前页、下一页、每页记录数） page
     *
     *  00-版权 footer
     */

    /**
     *  Controller -> list() -> JSP页面（list.jsp）
     *  jsp页面
     *  service -> JPA分页如何实现的
     *  repository -> 方法如何写，规范是什么？
     *
     */

    /**
     *
     * 以小组为单位，提供github项目作业地址，readme.md文件中标注出来，
     * 小组成员分工，检查标准：以github的提交日志为准，
     * 在课程结束后两周时间完成作业。两周后批阅上成绩
     * 小组成员不允许超过4人。
     *
     */


    @Autowired
    PostService dictService;
    @Autowired
    CategoryService categoryService ;
    @Autowired
    UserService userService;
    /**
     * 列表页面
     */
    @RequestMapping("")
    public String list(@RequestParam(value = "sortType", defaultValue = "auto") String sortType,
                       @RequestParam(value = "page", defaultValue = "1") int pageNumber, Model model, ServletRequest request){

        Map<String, Object> searchParams = HttpServlet.getParametersStartingWith(request, "s_");
        Page<Post> dicts = dictService.getEntityPage(searchParams, pageNumber, PAGE_SIZE, sortType);
        model.addAttribute("dicts", dicts);
        model.addAttribute("sortType", sortType);
        model.addAttribute("PAGE_SIZE", PAGE_SIZE);
        model.addAttribute("searchParams", HttpServlet.encodeParameterStringWithPrefix(searchParams, "s_"));
        return "post/list";
    }

    /**
     * 进入新增页面
     */
    @GetMapping(value = "create")
    public String createForm(Model model) {

        List<Category> list = categoryService.findAll();
        model.addAttribute("categories",list);
        model.addAttribute("dict", new Post());
        model.addAttribute("action", "create");
        return "post/form";
    }
    /**
     * 新增页面，提交保存
     * @author zhangyj, 2020.05.21
     */
    @PostMapping(value = "create")
    public String create(@Valid Post newDict, String category, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
          User user =  userService.findByUsername(username);
        newDict.setCreator(user);


        Set<Category> list = newDict.getCategories();
        Category cate = categoryService.findOne(Integer.valueOf(category));
        Set<Category> categories = new HashSet<>();
        categories.add(cate);
        newDict.setCategories(categories);
        float sort = dictService.getMaxSort();
        newDict.setSort(sort+1);
        newDict.setStatus(Constants.Status.ENABLE);
        dictService.save(newDict);
        redirectAttributes.addFlashAttribute("message", "创建数据字典成功");
        return "redirect:/post/";
    }

    /**
     * 进入编辑页面
     */
    @GetMapping(value = "update/{id}")
    public String updateForm(@PathVariable("id") Long pkId, Model model){
        Post dict =  dictService.findOne(pkId);
        model.addAttribute("dict",dict);
        model.addAttribute("action", "update");
        return "post/form";
    }

    /**
     * 页面编辑后，保存
     */
    @PostMapping(value = "update")
    public String update(@Valid Post dict, RedirectAttributes redirectAttributes,Model model){
        Long pkId = dict.getId();
        Post newDict = dictService.findOne(pkId);
        List<Category> list = categoryService.findAll();
        model.addAttribute("categories",list);
        newDict.setContent(dict.getContent());
        newDict.setCreatedAt(dict.getCreatedAt());
        newDict.setCreator(dict.getCreator());
        newDict.setLastModifiedAt(dict.getLastModifiedAt());
        newDict.setTitle(dict.getTitle());

        dictService.save(newDict);
        redirectAttributes.addFlashAttribute("message", "更改数据字典信息成功");
        return "redirect:/post/";
    }

    /**
     * 根据ID删除单个字典
     */
    @GetMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Long pkId, RedirectAttributes redirectAttributes) {
        String message = "删除字典成功";
        try {
            dictService.remove(pkId);
        }catch (Exception e){
            message = "删除字典失败，该字典被使用";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/post/";
    }

    /**
     * 批量删除
     */
    @PostMapping(value = "delete")
    public String deleteBatch(ServletRequest request,RedirectAttributes redirectAttributes){
        String[] chkIds = request.getParameterValues("chkIds");
        for (String id:chkIds){
            dictService.remove(Long.valueOf(id));
        }
        return "redirect:/post/";
    }
}
