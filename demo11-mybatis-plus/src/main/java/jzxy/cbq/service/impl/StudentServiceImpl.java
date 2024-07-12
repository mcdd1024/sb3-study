package jzxy.cbq.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jzxy.cbq.entity.Student;
import jzxy.cbq.mapper.StudentMapper;
import jzxy.cbq.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * @author: cbq1024
 * @description: StudentServiceImpl
 * @since 2024/7/12 下午3:08
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
