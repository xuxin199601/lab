package com.csu.lab.service;

import com.csu.lab.pojo.Direction;

import java.util.List;

public interface DirectionService {

    public List<Direction> getDirectionList();

    public void saveDirection(Direction direction)throws Exception;

    public void updateDirection(Direction direction);

    public void deleteDirection(Integer directionId);

    public Direction queryDirectionById(Integer directionId);

    // 分页查询
    public List<Direction> queryDirectionListPaged(Integer page, Integer pageSize);

    // 根据条件查询
    public List<Direction> queryByProperty(String property, Object value);

}
