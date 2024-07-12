package jzxy.cbq.controller;

import jzxy.cbq.entity.Student;
import jzxy.cbq.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: cbq1024
 * @description: StudentController
 * @since 2024/7/12 下午3:19
 */
@RequestMapping("students")
@RestController
@Slf4j
@RequiredArgsConstructor
public class StudentController {
    private final StudentService service;

    /**
     * curl -X "POST" "http://localhost:8080/students" \
     * -H 'Content-Type: application/json' \
     * -d '{
     *   "id": 1,
     *   "classId": 1,
     *   "dormId": 1,
     *   "majorId": 1,
     *   "stuNo": "stuNo_ehwdk",
     *   "stuName": "stuName_szz7h",
     *   "stuSex": 1,
     *   "stuMobile": "stuMobile_xewyb",
     *   "status": 1,
     *   "isDelete": 0,
     *   "createId": 1,
     *   "updateId": 1,
     *   "remark": "remark_yl2le"
     * }' \
     */
    @PostMapping
    public Student save(@RequestBody Student student) {
        boolean saved = service.save(student);
        return saved ? student : null;
    }

    @GetMapping
    public List<Student> list() {
        return service.list();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        boolean remove = service.removeById(id);
        return remove ? "删除成功" : "删除失败";
    }

}
