package com.csu.lab.service;

import com.csu.lab.pojo.Relationship;

import java.util.List;

public interface RelationshipService {

    public List<Relationship> getRelationshipList();

    public void saveRelationship(Relationship relationship)throws Exception;

    public void updateRelationship(Relationship relationship);

    public void deleteRelationship(Integer relationshipId);

    public Relationship queryRelationshipById(Integer relationshipId);

    // 分页查询
    public List<Relationship> queryRelationshipListPaged(Integer page, Integer pageSize);

    // 根据条件查询
    public List<Relationship> queryByProperty(String property, Object value);

}
