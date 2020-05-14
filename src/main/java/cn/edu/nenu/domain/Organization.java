package cn.edu.nenu.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Data
@Accessors
@Table(name = " T_ORGANIZATION")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
@Column(length = 128,nullable = false)
    private String name;
@Column(nullable = false)
    private float sort;
    @Column(nullable = false)
    private float pId;
    @Column(length = 240,unique = true)
    private String autoCode;
}
