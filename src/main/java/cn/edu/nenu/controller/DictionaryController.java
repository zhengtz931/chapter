package cn.edu.nenu.controller;

import cn.edu.nenu.config.Constants;
import cn.edu.nenu.config.HttpServlet;
import cn.edu.nenu.domain.Dictionary;
import cn.edu.nenu.service.DictionaryService;
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
import javax.validation.Valid;
import java.util.Map;

import static cn.edu.nenu.config.Constants.PAGE_SIZE;

/**
 * DictionaryController Class
 *
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-05-21 13:10
 */
@Controller
@RequestMapping("/dict")
public class DictionaryController {


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


    @Autowired
    DictionaryService dictService;

    @RequestMapping("")
    public String list(@RequestParam(value = "sortType", defaultValue = "auto") String sortType,
                       @RequestParam(value = "page", defaultValue = "1") int pageNumber, Model model, ServletRequest request){

        Map<String, Object> searchParams = HttpServlet.getParametersStartingWith(request, "s_");
        Page<Dictionary> dicts = dictService.getEntityPage(searchParams, pageNumber, PAGE_SIZE, sortType);
        model.addAttribute("dicts", dicts);
        model.addAttribute("sortType", sortType);
        model.addAttribute("PAGE_SIZE", PAGE_SIZE);
        model.addAttribute("searchParams", HttpServlet.encodeParameterStringWithPrefix(searchParams, "s_"));
        String path = "/WEB-INF/views/dict/list.jsp";
        return "dict/list";
    }

    @GetMapping(value = "create")
    public String createForm(Model model) {
        model.addAttribute("dict", new Dictionary());
        model.addAttribute("action", "create");
        return "dict/form";
    }
    @GetMapping(value = "update/{id}")
    public String updateForm(@PathVariable("id") Long pkId,Model model)
    {
        Dictionary dict = dictService.findOne(pkId);
        model.addAttribute("dict",dict);
        model.addAttribute("action","update");
        return "dict/form";
    }
    @PostMapping(value = "create")
    public String create(@Valid Dictionary newDict, RedirectAttributes redirectAttributes) {
        float sort = dictService.getMaxSort();
        newDict.setSort(sort+1);
        newDict.setStatus(Constants.Status.ENABLE);
        dictService.save(newDict);
        redirectAttributes.addFlashAttribute("message", "创建数据字典成功");
        return "redirect:/dict/";
    }
//更新操作
    @PostMapping(value = "update")
    public String update(@Valid Dictionary dict, RedirectAttributes redirectAttributes){
        Long pkId = dict.getId();
        Dictionary newDict = dictService.findOne(pkId);
        newDict.setType(dict.getType());
        newDict.setCode(dict.getCode());
        newDict.setName(dict.getName());

        dictService.save(newDict);
        redirectAttributes.addFlashAttribute("message", "更改数据字典信息成功");
        return "redirect:/dict/";
    }
//根据id删除
    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Long pkId, RedirectAttributes redirectAttributes) {
        String message = "删除字典成功";
        try {
            dictService.remove(pkId);
        }catch (Exception e){
            message = "删除字典失败，该字典被使用";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/dict/";
    }
//批量删除
    @PostMapping(value = "delete")
    public String deleteBatch(ServletRequest request,RedirectAttributes redirectAttributes){
        String[] chkIds = request.getParameterValues("chkIds");
        for (String id:chkIds){
            dictService.remove(Long.valueOf(id));
        }
        return "redirect:/dict/";
    }
}
