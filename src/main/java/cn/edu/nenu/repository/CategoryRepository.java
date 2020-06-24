package cn.edu.nenu.repository;

import cn.edu.nenu.config.orm.PlatformRepository;
import cn.edu.nenu.domain.Category;
import cn.edu.nenu.domain.Dictionary;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author zhengtz
 * 20200624
 */
public interface CategoryRepository extends PlatformRepository<Category,Long> {
    @Query("from Category d where d.name=?1 order by d.sort asc ")
    List<Category> findByTypeOrderBySort(String name);

    Category findFirstByOrderBySortDesc();
}
