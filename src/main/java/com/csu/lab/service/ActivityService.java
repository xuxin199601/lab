package com.csu.lab.service;

import com.csu.lab.pojo.Activity;

import java.util.List;

public interface ActivityService {

    public List<Activity> getActivityList();

    public void saveActivity(Activity activity)throws Exception;

    public void updateActivity(Activity activity);

    public void deleteActivity(Integer activityId);

    public Activity queryActivityById(Integer activityId);

    // 根据对象进行分页查询用户
    public List<Activity> queryActivityListPaged(Integer page, Integer pageSize);

    // 根据条件查询
    public List<Activity> queryByProperty(String property, Object value);

}
