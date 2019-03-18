package com.csu.lab.service.imp;

import com.csu.lab.enums.ResultEnum;
import com.csu.lab.exception.DirectionException;
import com.csu.lab.mapper.DirectionMapper;
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

    private Logger logger = LoggerFactory.getLogger(DirectionServiceImpl.class);

    @Autowired
    private DirectionMapper directionMapper;

    @Override
    public List<Direction> getDirectionList() {
        return directionMapper.selectAll();
    }

    /**
     * 保存研究方向信息
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveDirection(Direction direction) {
        logger.info("addDirection:{}", direction);
        List<Direction> directionList = queryByProperty("resDirection", direction.getResDirection());
        if (directionList.isEmpty()) {
            if(directionMapper.insert(direction) != 1) {
                throw new DirectionException(ResultEnum.DIRECTION_SAVE_FAILURE);
            }
        } else {
            throw new DirectionException(ResultEnum.DIRECTION_EXIST);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateDirection(Direction direction) {
        logger.info("updateDirection:{}", direction);
        if(directionMapper.updateByPrimaryKeySelective(direction) != 1) {
            throw new DirectionException(ResultEnum.DIRECTION_UPDATE_FAILURE);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteDirection(Integer directionId) {
        logger.info("deleteDirectionById:{}", directionId);
        if(directionMapper.deleteByPrimaryKey(directionId) != 1) {
            throw new DirectionException(ResultEnum.DIRECTION_DELETE_FAILURE);
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Direction queryDirectionById(Integer directionId) {
        logger.info("queryDirectionById:{}", directionId);
        Direction direction = directionMapper.selectByPrimaryKey(directionId);
        if (direction == null) {
            throw new DirectionException(ResultEnum.DIRECTION_NO_FOUND);
        }
        return direction;
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
}
