package com.csu.lab.service;

import com.csu.lab.pojo.Thesis;

import java.util.List;

public interface ThesisService {

    public List<Thesis> getThesisList();

    public void saveThesis(Thesis thesis)throws Exception;

    public void updateThesis(Thesis thesis);

    public void deleteThesis(Integer thesisId);

    public Thesis queryThesisById(Integer thesisId);

    // 分页查询
    public List<Thesis> queryThesisListPaged(Integer page, Integer pageSize);

    // 根据条件查询
    public List<Thesis> queryByProperty(String property, Object value);

}
