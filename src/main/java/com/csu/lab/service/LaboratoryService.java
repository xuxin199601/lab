package com.csu.lab.service;

import com.csu.lab.pojo.Laboratory;

import java.util.List;

public interface LaboratoryService {

    public List<Laboratory> getLaboratoryList();

    public void saveLaboratory(Laboratory laboratory)throws Exception;

    public void updateLaboratory(Laboratory laboratory);

    public void deleteLaboratory(Integer laboratoryId);

    public Laboratory queryLaboratoryById(Integer laboratoryId);

    // 分页查询
    public List<Laboratory> queryLaboratoryListPaged(Integer page, Integer pageSize);

    // 根据条件查询
    public List<Laboratory> queryByProperty(String property, Object value);

}
