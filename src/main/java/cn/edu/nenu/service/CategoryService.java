package cn.edu.nenu.service;

import cn.edu.nenu.config.orm.jpa.DynamicSpecifications;
import cn.edu.nenu.config.orm.jpa.SearchFilter;
import cn.edu.nenu.domain.Category;
import cn.edu.nenu.domain.Dictionary;
import cn.edu.nenu.repository.CategoryRepository;
import cn.edu.nenu.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * DictionaryService Class
 *
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-05-21 13:15
 */
@Service
public class CategoryService {


    @Autowired
    CategoryRepository categoryRepo;

    /**
     * 根据主键获取实体，常用
     */
    public Category findOne(long pkId){
        return categoryRepo.findOne(pkId);
    }

    /**
     * 保存一个实体，常用
     */
    public Category save(Category entity){
        return categoryRepo.save(entity);
    }

    /**
     * 批量保存实体  Set，List
     */
    public Iterable<Category> sava(Iterable<Category> entities){
        return categoryRepo.save(entities);
    }

    /**
     * 根据主键删除实体，常用
     */
    public void remove(Long pkId){
        categoryRepo.delete(pkId);
    }

    /**
     * 根据实体删除实体，不常用
     */
    public void remove(Category entity){
        categoryRepo.delete(entity);
    }

    /**
     * 批量删除实体，使用较少
     */
    public void remove(Iterable<Category> categorys){
        categoryRepo.delete(categorys);
    }

    /**
     * 根据字典类型获取字典集合
     */
    public List<Category> findByType(String type){
        return categoryRepo.findByTypeOrderBySort(type);
    }

    /**
     * 根据查询条件获取分页结果数据
     */
    public Page<Category> getEntityPage(Map<String, Object> filterParams, int pageNumber, int pageSize, String sortType){
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
        Specification<Category> spec = buildSpecification(filterParams);
        return categoryRepo.findAll(spec,pageRequest);
    }
    /**
     * 创建分页请求.
     */
    private PageRequest buildPageRequest(int pageNumber, int pageSize, String sortType) {
        Sort sort = null;
        if ("auto".equals(sortType)) {
            sort = new Sort(Sort.Direction.ASC, "sort");
        } else if ("sort".equals(sortType)) {
            sort = new Sort(Sort.Direction.ASC, "sort");
        }
        return new PageRequest(pageNumber - 1, pageSize, sort);
    }

    /**
     * 创建动态查询条件组合.
     */
    private Specification<Category> buildSpecification(Map<String, Object> filterParams) {

        Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
        //if (StringUtils.isNotBlank(id)) {
        //    filters.put("id", new SearchFilter("id", SearchFilter.Operator.EQ, id));
        //}
        Specification<Category> spec = DynamicSpecifications.bySearchFilter(filters.values(), Category.class);
        return spec;
    }


}
