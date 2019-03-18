package com.csu.lab.service;

import com.csu.lab.pojo.Laboratory;

import java.util.List;

public interface LaboratoryService {

    public List<Laboratory> getLaboratoryList();

    public int updateLaboratory(Laboratory laboratory);


}
