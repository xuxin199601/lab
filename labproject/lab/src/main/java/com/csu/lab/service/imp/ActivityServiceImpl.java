package com.csu.lab.service.imp;

import com.csu.lab.enums.ResultEnum;
import com.csu.lab.exception.ActivityException;
import com.csu.lab.mapper.ActivityMapper;
import com.csu.lab.pojo.Activity;
import com.csu.lab.service.ActivityService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@Service
public class ActivityServiceImpl implements ActivityService{

    private Logger logger = LoggerFactory.getLogger(ActivityServiceImpl.class);

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public List<Activity> getActivityList() {
        return activityMapper.selectAll();
    }

    /**
     * 保存项目信息
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveActivity(Activity activity) {
        logger.info("addActivity:{}", activity);
        List<Activity> activityList = queryByProperty("name", activity.getName());
        if (activityList.isEmpty()) {
            if(activityMapper.insert(activity) != 1) {
                throw new ActivityException(ResultEnum.ACTIVITY_SAVE_FAILURE);
            }
        } else {
            throw new ActivityException(ResultEnum.ACTIVITY_EXIST);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateActivity(Activity activity) {
        logger.info("updateActivity:{}", activity);
        if(activityMapper.updateByPrimaryKeySelective(activity) != 1) {
            throw new ActivityException(ResultEnum.ACTIVITY_UPDATE_FAILURE);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteActivity(Integer activityId) {
        if(activityMapper.deleteByPrimaryKey(activityId) != 1) {
            throw new ActivityException(ResultEnum.ACTIVITY_DELETE_FAILURE);
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Activity queryActivityById(Integer activityId) {
        Activity activity = activityMapper.selectByPrimaryKey(activityId);
        if (activity == null) {
            throw new ActivityException(ResultEnum.ACTIVITY_NO_FOUND);
        }
        return activity;
    }

    @Override
    public List<Activity> queryByProperty(String property, Object value) {
        Example example = new Example(Activity.class);
        Example.Criteria criteria = example.createCriteria();

        // 设置条件
        criteria.andEqualTo(property, value);
        example.and(criteria);

        return activityMapper.selectByExample(example);
    }
}
