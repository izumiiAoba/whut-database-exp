package database.exp.aa.service.aaServiceImplememt;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import database.exp.aa.mapper.CourseMapper;
import database.exp.aa.mapper.RecordMapper;
import database.exp.aa.mapper.UserMapper;
import database.exp.aa.pojo.Course;
import database.exp.aa.pojo.Record;
import database.exp.aa.pojo.StudentCourseMap;
import database.exp.aa.pojo.User;
import database.exp.aa.service.aaServiceInterface.SettingServiceInterface;
import database.exp.aa.util.AaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class SettingService implements SettingServiceInterface {
    @Autowired
    UserMapper userMapper;
    @Autowired
    CourseMapper courseMapper;

    @Override
    public AaResponse<Map<String, Object>> updateUserInfoById(JSONObject parameters) {
        int res = userMapper.updateUserById(
            (int)parameters.get("id"),
            (String)parameters.get("newUsername"),
            (String)parameters.get("newPassword")
        );
        if(res == 1){
            //修改成功
            User u = userMapper.queryUserById((int)parameters.get("id"));
            Map<String,Object> data = new ImmutableMap.Builder<String,Object>()
                .put("user",u)
                .build();
            return AaResponse.createBySuccess(data);
        }else {
            //修改失败
            return AaResponse.createByErrorMessage("用户信息修改失败");
        }
    }

    @Override
    public AaResponse<Map<String, Object>> queryCoursesByStudentId(JSONObject parameters) {
        List<StudentCourseMap> res = courseMapper.getAllCoursesByStudentId((int)parameters.get("studentId"));
        Iterator<StudentCourseMap> it = res.iterator();
        List<Course> courseList = new ArrayList<Course>();

        while (it.hasNext()){
            courseList.add(it.next().getCourse());
        }

        Map<String,Object> data = new ImmutableMap.Builder<String,Object>()
            .put("courseList",courseList)
            .build();

        return AaResponse.createBySuccess(data);
    }

    @Override
    public AaResponse<Map<String, Object>> addCourse(JSONObject parameters) {
        int res = courseMapper.insertCourse(
            (String)parameters.get("courseName"),
            (String)parameters.get("teacher"),
            (int)parameters.get("dayIndex"),
            (int)parameters.get("courseIndex")
        );
        if(res == 1){
            //插入成功
            return AaResponse.createBySuccessMessage("添加成功");
        }else {
            //插入失败
            return AaResponse.createByErrorMessage("添加失败");
        }
    }
}
