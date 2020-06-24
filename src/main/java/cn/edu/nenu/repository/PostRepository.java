package cn.edu.nenu.repository;

import cn.edu.nenu.config.orm.PlatformRepository;
import cn.edu.nenu.domain.Dictionary;
import cn.edu.nenu.domain.Post;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * DictionaryRepository Class
 *
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-05-21 13:17
 */
public interface PostRepository extends PlatformRepository<Post,Long> {


    @Query("from Post d where d.title   =?1 order by d.sort asc ")
    List<Post> findByTypeOrderBySort(String title);
    Post findFirstByOrderBySortDesc();
}
