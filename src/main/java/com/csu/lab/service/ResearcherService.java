package com.csu.lab.service;

import com.csu.lab.pojo.Researcher;

import java.util.List;

public interface ResearcherService {

    public List<Researcher> getResearcherList(Integer personType);

    public void saveResearcher(Researcher researcher)throws Exception;

    public void updateResearcher(Researcher researcher);

    public void deleteResearcher(Integer researcherId);

    public Researcher queryResearcherById(Integer researcherId);

    // 根据对象进行用户查询
    public List<Researcher> queryResearcherList(Researcher researcher);

    // 根据条件查询
    public List<Researcher> queryByProperty(String property, Object value);

    // 根据条件查询导师信息
    public List<Researcher> queryTutorByProperty(String property, Object value);

    // 根据条件查询学生信息
    public List<Researcher> queryStudentByProperty(String property, Object value);

}
