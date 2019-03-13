package com.csu.lab.service;

import com.csu.lab.pojo.Researcher;

import java.util.List;

public interface ResearcherService {

    public List<Researcher> getResearcherList();

    public void saveResearcher(Researcher researcher)throws Exception;

    public void updateResearcher(Researcher researcher);

    public void deleteResearcher(Integer researcherId);

    public Researcher queryResearcherById(Integer researcherId);

    // 根据对象进行用户查询
    public List<Researcher> queryResearcherList(Researcher researcher);

    // 分页查询
    public List<Researcher> queryResearcherListPaged(Integer personType, Integer page, Integer pageSize);

    // 根据条件查询
    public List<Researcher> queryByProperty(String property, Object value);

}
