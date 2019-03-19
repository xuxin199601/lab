package com.csu.lab.service;

import com.csu.lab.pojo.Message;
import com.csu.lab.pojo.Thesis;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public List<Thesis> queryByProperty(String property, String value);

    /**
     * 通过验证的用户下载论文
     */
    public void getThesisContent(Integer tid, Integer aid, HttpServletResponse response, HttpServletRequest request);

    /**
     * 通过验证的用户下载论文代码
     */
    public void getThesisCode(Integer tid, Integer aid, HttpServletResponse response, HttpServletRequest request);

    /**
     * 通过验证的用户下载论文数据集（考虑没有数据集的情况）
     */
    public void getThesisData(Integer tid, Integer aid, HttpServletResponse response, HttpServletRequest request);

}
