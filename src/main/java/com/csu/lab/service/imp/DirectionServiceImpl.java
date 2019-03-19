package com.csu.lab.service.imp;

import com.csu.lab.enums.ResultEnum;
import com.csu.lab.exception.DirectionException;
import com.csu.lab.mapper.DirectionMapper;
import com.csu.lab.pojo.Account;
import com.csu.lab.pojo.Direction;
import com.csu.lab.service.DirectionService;
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
public class DirectionServiceImpl implements DirectionService{


    @Autowired
    private DirectionMapper directionMapper;

    @Override
    public List<Direction> getDirectionList() {
        return directionMapper.selectAll();
    }


    @Override
    public int addDirection(Direction direction) {
        int result = directionMapper.insert(direction);
        return result;
    }

    /**
     * 保存研究方向信息
     */
    @Override
    public List<Direction> saveDirection(Direction direction) {
        List<Direction> directionList = queryByProperty("resDirection", direction.getResDirection());
        return directionList;
    }

    @Override
    public List<Direction> queryByProperty(String property, Object value) {
        Example example = new Example(Direction.class);
        Example.Criteria criteria = example.createCriteria();

        // 设置条件
        criteria.andEqualTo(property, value);
        example.and(criteria);

        return directionMapper.selectByExample(example);
    }

    @Override
    public int updateDirection(Direction direction) {
        int result = directionMapper.updateByPrimaryKeySelective(direction);
        return result;
    }

    @Override
    public void deleteDirection(Integer directionId) {
        if(directionMapper.deleteByPrimaryKey(directionId) != 1) {
            throw new DirectionException(ResultEnum.DIRECTION_DELETE_FAILURE);
        }
    }

    @Override
    public Direction queryDirectionById(Integer directionId) {
        Direction direction = directionMapper.selectByPrimaryKey(directionId);
        return direction;
    }

}
