package com.csu.lab.service;

import com.csu.lab.pojo.Direction;

import java.util.List;

public interface DirectionService {

    public List<Direction> getDirectionList();

    int addDirection(Direction direction);

    public int updateDirection(Direction direction);

    public Integer deleteDirection(Integer directionId);

    public Direction queryDirectionById(Integer directionId);

    // 根据条件查询
    public List<Direction> queryByProperty(String property, Object value);

}
