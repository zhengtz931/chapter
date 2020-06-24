package cn.edu.nenu.service;

import cn.edu.nenu.config.orm.jpa.DynamicSpecifications;
import cn.edu.nenu.config.orm.jpa.SearchFilter;
import cn.edu.nenu.domain.Dictionary;
import cn.edu.nenu.domain.User;
import cn.edu.nenu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * UserService Class
 *
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-03-04 22:54
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //@Autowired
    //private UserMapper userMapper;

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    /**
     * 根据主键ID获取实体对象
     * @param pkId
     * @return
     */


    public User findOne(Long pkId){
        return userRepository.findOne(pkId);
    }

    public void remove(Long pkId){
        userRepository.delete(pkId);
    }

    /**
     * 创建分页请求.
     */
    private PageRequest buildPageRequest(int pageNumber, int pageSize, String sortType) {
        Sort status = null;
        if ("auto".equals(sortType)) {
            status = new Sort(Sort.Direction.ASC, "status");
        } else if ("sort".equals(sortType)) {
            status = new Sort(Sort.Direction.ASC, "status");
        }
        return new PageRequest(pageNumber - 1, pageSize, status);
    }


    /**
     * 创建动态查询条件组合.
     */
    private Specification<User> buildSpecification(Map<String, Object> filterParams) {

        Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
        //if (StringUtils.isNotBlank(id)) {
        //    filters.put("id", new SearchFilter("id", SearchFilter.Operator.EQ, id));
        //}
        Specification<User> spec = DynamicSpecifications.bySearchFilter(filters.values(), User.class);
        return spec;
    }


    /**
     * 当前页面数据（当前页码，每页的记录数，查询参数）
     * @param pageNumber
     * @param pageSize
     * @param param
     * @return
     */
    public Page<User> getPage(int pageNumber, int pageSize, Map<String,Object> param,String sortType) {

        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
        Specification<User> spec = buildSpecification(param);
        return userRepository.findAll(spec,pageRequest);
    }

    /**
     * 持久化实体类
     * @param entity
     * @return
     */
    public User save(User entity) {

        /**
         * 使用了接口类，通用类中使用了泛型
         */
        return userRepository.save(entity);
    }

    /**
     * 批量持久化
     * @param entities
     * @return
     */
    public Collection save(Collection entities){
        return userRepository.save(entities);
    }
    public void delete(Long pkId){
        userRepository.delete(pkId);
    }
    public void delete(User entity){
        userRepository.delete(entity);
    }
}


/**
 *
 *
 * Spring MVC + Spring + JPA(Hibernate)
 *
 * Spring MVC + Spring + MyBatis
 *
 * Spring MVC + Spring + JPA(基本操作) + MyBatis（涉及性能或复杂业务处理）
 *
 * JSP、vue、React、H5（Thymeleaf）
 *
 * 前后端分离开发
 *   - Spring MVC @RestController，request and response JSON
 *   - 异步请求，Ajax，axios，
 *
 *
 *
 *   Spring Data JPA
 *   好处：仿照JPA接口的实现Mybatis进行自定义，save(collection entities)
 *
 *
 */






















