package cn.edu.nenu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * OrganizationController Class
 *
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-05-07 15:19
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController {

    //final
    //OrganizationService organizationService;
    //
    //public OrganizationController(OrganizationService organizationService) {
    //    this.organizationService = organizationService;
    //}


    /**
     * 1. 机构基本的操作，增加，修改，删除，详情，列表，分页
     * 2. 机构节点的移动
     *         - 节点排序，只改变了sort的值，其他不变
     *         - 节点移动，由A位置->移动到B位置，A的子节点移动，到B后生成新的节点，
     *                  变化的是：A节点的父节点变化了，A节点和A节点的子节点的AutoCode变化了
     *                  无变化的是：A节点和A节点的子节点的ID不变
     *                  - 节点平行移动 00010002->00020003 层级不变 2->2
     *                  - 节点上行移动 00010002->0004 层级减少 2->1
     *                  - 节点下行移动 00010002->000200010001 层级增加2->3
     *         - 节点删除，A节点删除后，
     *                  - 其以A节点为根节点的所有下级节点均被删除
     *                  - A节点被删除后，下级所有节点被移动到指定的tmp节点中等待其他处理。
     *
     * 3.结构数据列表：
     *      - 全部加载，页面根据数据结构自动构建树形结构，需要使用迭代方法（递归调用）
     *      - 懒加载，一层又一层的加载数据，不涉及递归调用了，
     *      - 数据加载方式的选择：根据数据量选择，
     *              - 如果数据量小，变化不大，采用全部加载方式，只调用一次数据库即可
     *              - 数据量大，变化大，一般采用懒加载方式，缺点是需要多次调用数据库。异步请求。
     *
     */

    /**
     * 列表页面
     * @param model
     * @return
     */
    //@GetMapping("/list")
    //public String list(Model model){
    //    //参数接收，默认值，默认机构，
    //    Integer pId =0; //如无法从参数中获取，为null时则需要设定默认值
    //
    //    //List<Organization> organizationList = organizationService.getSubList(pId);
    //    //model.addAttribute("list",organizationList);
    //    return "organization/list";
    //}

    /**
     * 新增
     * @param json
     * @return
     */
    //@PostMapping("")
    //public String save(String json){
    //    //前端接收的参数中应包含哪些内容：上级机构ID，pId
    //    Integer pId = 1;
    //    String currentOrganizationName = "吉林省";
    //    //*****************以上变量是参数传递过来的**********************************************//
    //    //根据pId获取改pId下级机构的最大排序值
    //    //float currentLevelMaxSort = organizationService.getSubMaxSort(pId);
    //    //String currentLevelAutoCode = organizationService.getSubMaxAutoCode(pId);
    //    //Organization organization = new Organization();
    //    //organization.setName(currentOrganizationName)
    //    //        .setSort(currentLevelMaxSort)
    //    //        .setPId(pId)
    //    //        .setAutoCode(currentLevelAutoCode);
    //    //organizationService.save(organization);
    //
    //    return "organization/list";
    //}

    /**
     * 编辑
     * @param json
     * @return
     */
    //@PostMapping
    //public String update(String json){
    //    Integer id=1;
    //    String currentOrganizationName="吉林省";
    //
    //    //Organization organization = organizationService.get(id);
    //    //organization.setName(currentOrganizationName);
    //    //organizationService.update(organization);//有时可以和save方法公用同一方法。
    //    return "organization/list";
    //}

    /**
     * 删除
     * @param id
     * @return
     */
    //@PostMapping("/delete/{id}")
    //public String delete(@PathVariable("id")Integer id){
    //    if (id!=null){//在路径接收参数时增加正则表达式校验id不能为空，此处就可以删除了
    //        //organizationService.delete(id);
    //    }
    //    return "organization/list";
    //}
}
