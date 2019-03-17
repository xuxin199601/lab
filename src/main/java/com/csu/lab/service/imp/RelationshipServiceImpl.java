package com.csu.lab.service.imp;

import com.csu.lab.mapper.RelationshipMapper;
import com.csu.lab.pojo.Relationship;
import com.csu.lab.service.RelationshipService;
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
public class RelationshipServiceImpl implements RelationshipService{

    private Logger logger = LoggerFactory.getLogger(RelationshipServiceImpl.class);

    @Autowired
    private RelationshipMapper relationshipMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Relationship> getRelationshipList() {
        return relationshipMapper.selectAll();
    }

    /**
     * 保存研究方向信息
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveRelationship(Relationship relationship) {
        logger.info("addRelationship:{}", relationship);
        relationshipMapper.insert(relationship);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateRelationship(Relationship relationship) {
        logger.info("updateRelationship:{}", relationship);
        relationshipMapper.updateByPrimaryKeySelective(relationship);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteRelationship(Integer relationshipId) {
        logger.info("deleteRelationshipById:{}", relationshipId);
        relationshipMapper.deleteByPrimaryKey(relationshipId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Relationship queryRelationshipById(Integer relationshipId) {
        logger.info("queryRelationshipById:{}", relationshipId);
        Relationship Relationship = relationshipMapper.selectByPrimaryKey(relationshipId);
        return Relationship;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Relationship> queryRelationshipListPaged(Integer page, Integer pageSize) {

        logger.info("queryRelationshipListPaged");
        PageHelper.startPage(page, pageSize);

        List<Relationship> relationshipList = relationshipMapper.selectAll();

        return relationshipList;
    }

    @Override
    public List<Relationship> queryByProperty(String property, Object value) {
        Example example = new Example(Relationship.class);
        Example.Criteria criteria = example.createCriteria();

        // 设置条件
        criteria.andEqualTo(property, value);
        example.and(criteria);

        return relationshipMapper.selectByExample(example);
    }
}
