package database.exp.aa.controller;

import com.alibaba.fastjson.JSONObject;
import database.exp.aa.service.aaServiceImplememt.SettingService;
import database.exp.aa.util.AaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/setting")
public class SettingController {
    @Autowired
    SettingService settingService;

    @PostMapping("/update-userInfo")
    public AaResponse<Map<String,Object>> updateUserInfoById(@RequestBody JSONObject parameters){
        return settingService.updateUserInfoById(parameters);
    }

    @PostMapping("/query-studentCourses")
    public AaResponse<Map<String,Object>> queryCoursesByStudentId(@RequestBody JSONObject parameters){
        return settingService.queryCoursesByStudentId(parameters);
    }

    @PostMapping("/add-course")
    public AaResponse<Map<String,Object>> addCourse(@RequestBody JSONObject parameters){
        return settingService.addCourse(parameters);
    }
}
