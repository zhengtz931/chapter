package cn.edu.nenu.domain;

import cn.edu.nenu.config.Constants;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Category Class
 *  栏目管理
 * @author <b>Oxidyc</b>, Copyright &#169; 2003
 * @version 1.0, 2020-04-30 15:45
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "T_CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //主键编码
    @Column(length = 64,nullable = false)
    private String name;//栏目名称
    private float sort;//排序
    @Column(nullable = false)
    private Constants.Status status;//属性状态
}
