package com.csu.lab.service;

import com.csu.lab.pojo.Activity;

import java.util.List;

public interface ActivityService {

    List<Activity> getActivityList();

    void saveActivity(Activity activity)throws Exception;

    void updateActivity(Activity activity);

    void deleteActivity(Integer activityId);

    Activity queryActivityById(Integer activityId);

    // 根据条件查询
    List<Activity> queryByProperty(String property, Object value);

}
