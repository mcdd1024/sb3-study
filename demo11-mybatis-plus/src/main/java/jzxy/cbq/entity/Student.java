package jzxy.cbq.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: cbq1024
 * @description: Student
 * @since 2024/7/12 下午3:07
 */
@Data
@TableName(value = "tb_student")
public class Student implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer classId;
    private Integer dormId;
    private Integer majorId;
    private String stuNo;
    private String stuName;
    private Integer stuSex;
    private String stuMobile;
    private Integer status;
    @TableLogic(value = "0", delval = "1")
    private Integer isDelete;
    private Long createId;
    private Long updateId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String remark;

}
