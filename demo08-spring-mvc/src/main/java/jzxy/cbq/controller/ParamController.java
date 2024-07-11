package jzxy.cbq.controller;

import jakarta.servlet.http.HttpServletRequest;
import jzxy.cbq.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author: cbq1024
 * @description: ParamController
 * @since 2024/7/11 下午3:44
 */
@RequestMapping("params")
@RestController
@Slf4j
public class ParamController {

    /**
     * 通过 HttpServletRequest 接收数据，
     * 可以接收 form 表单和地址上数据，接收不到 body 中的 json 数据
     * <p>
     * curl -X "GET" "http://localhost:8080/params/method1" \
     * --data-urlencode 'name=cbq-method01&age=22' \
     */
    @PostMapping("method1")
    public String method1(HttpServletRequest request) {
        // 参数名要与页面提交的参数名一致
        String name = request.getParameter("name");
        String age = request.getParameter("age");

        return "我叫 " + name + "，今年 " + age;
    }

    /**
     * 通过形参接收数据
     * 可以接收 form 表单和地址上数据，接收不到 body 中的 json 数据
     * <p>
     * curl -X "GET" "http://localhost:8080/params/method2?name=cbq-request-param&age=22" \
     * @RequestParam 注解可以设置是否必填和默认值
     */
    @PostMapping("method2")
    public String method2(@RequestParam(value = "name") String name,
                          @RequestParam(value = "age", defaultValue = "10") Integer age) {
        // 参数名要与页面提交的参数名一致
        log.info("name ===> {}", name);
        log.info("age ===> {}", age);
        return "我叫 " + name + "，今年 " + age;
    }

    /**
     * 接收对象数据
     * <p>
     * curl -X "GET" "http://localhost:8080/params/method3?name=name-instance&age=22" \
     */
    @PostMapping("method3")
    public String method3(Account account) {
        // 参数名要与页面提交的参数名一致
        log.info("account-instance ====> {}", account);
        return "我叫 " + account.getName() + "，今年 " + account.getAge();
    }

    /**
     * 接收数组
     * <p>
     * curl -X "GET" "http://localhost:8080/params/method4?balls=ball1,ball2,ball3" \
     */
    @GetMapping("method4")
    public String[] method4(@RequestParam("balls") String[] balls) {
        // 参数名要与页面提交的参数名一致
        log.info("balls ====> {}", Arrays.toString(balls));
        return balls;
    }

    /**
     * 接收 List
     * <p>
     * curl -X "GET" "http://localhost:8080/params/method5?list=item1,item2,item3" \
     */
    @GetMapping("method5")
    public List<String> method5(@RequestParam("list") List<String> list) {
        // 参数名要与页面提交的参数名一致
        log.info("list ====> {}", list);
        return list;
    }

    /**
     * 接收 JSON 数据，json 数据放到 body 中，需要使用 POST、PUT、DELETE 请求，GET 请求不能在 body 中存放数据
     * <p>
     * curl -X "GET" "http://localhost:8080/params/method6" \
     * -H 'Content-Type: application/json' \
     * -d '{
     *   "id": 1,
     *   "name": "张三",
     *   "age": 20,
     *   "jobs": [
     *     "job1",
     *     "job2",
     *     "job3"
     *   ],
     *   "address": {
     *     "name": "家庭住址",
     *     "zipCode": "454700"
     *   },
     *   "time": "2024-06-05 12:12:12"
     * }' \
     */
    @PostMapping("method6")
    public Account method6(@RequestBody Account account) {
        log.info("account-json ====> {}", account);
        return account;
    }

    /**
     * 接收 JSON 数据，json 数据放到 body 中，需要使用 POST、PUT、DELETE 请求，GET 请求不能在 body 中存放数据
     * <p>
     * curl -X "POST" "http://localhost:8080/params/method7" \
     * -H 'Content-Type: application/json' \
     * -d '{"id": 1}' \
     */
    @PostMapping("method7")
    public Map<String, String> method7(@RequestBody Map<String, String> map) {
        log.info("map ====> {}", map);
        return map;
    }

    /**
     * 接收路径参数，常用在 GET 请求根据 id 或者什么关键字获取数据的场景
     * <p>
     * curl -X "GET" "http://localhost:8080/params/method8/1" \
     */
    @GetMapping("method8/{id}")
    public Integer method8(@PathVariable("id") Integer id) {
        log.info("id ====> {}", id);
        return id;
    }

}

