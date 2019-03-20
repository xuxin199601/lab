package com.csu.lab.service;

import com.csu.lab.pojo.Activity;

import java.util.List;

public interface ActivityService {

    List<Activity> getActivityList();

    int saveActivity(Activity activity)throws Exception;

    int updateActivity(Activity activity);

    int deleteActivity(Integer activityId);

    Activity queryActivityById(Integer activityId);

    // 根据条件查询
    List<Activity> queryByProperty(String property, Object value);

}
