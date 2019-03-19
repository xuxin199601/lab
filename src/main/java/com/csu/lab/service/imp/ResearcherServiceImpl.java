package com.csu.lab.service.imp;

import com.csu.lab.enums.ResultEnum;
import com.csu.lab.exception.ResearcherException;
import com.csu.lab.mapper.ResearcherMapper;
import com.csu.lab.pojo.Researcher;
import com.csu.lab.service.ResearcherService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ResearcherServiceImpl implements ResearcherService {

    private Logger logger = LoggerFactory.getLogger(ResearcherServiceImpl.class);

    @Autowired
    private ResearcherMapper researcherMapper;

    @Override
    public List<Researcher> getResearcherList(Integer personType) {
        List<Researcher> researcherList = queryByProperty("personType", personType);
        return researcherList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveResearcher(Researcher researcher) {
        logger.info("addResearcher:{}", researcher);
        Researcher tempResearcher = new Researcher();
        tempResearcher.setAid(researcher.getAid());
        Researcher exitsResearcher = researcherMapper.selectOne(tempResearcher);
        if (exitsResearcher == null) {
            if(researcherMapper.insert(researcher) != 1) {
                throw new ResearcherException(ResultEnum.RESEARCHER_SAVE_FAILURE);
            }
        } else {
            throw new ResearcherException(ResultEnum.TUTOR_EXIST);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateResearcher(Researcher researcher) {
        logger.info("updateResearcher:{}", researcher);
        researcherMapper.updateByPrimaryKeySelective(researcher);
        if(researcherMapper.updateByPrimaryKeySelective(researcher) != 1) {
            throw new ResearcherException(ResultEnum.RESEARCHER_UPDATE_FAILURE);
        }
    }

    @Override
    public void deleteResearcher(Integer researcherId) {
        logger.info("deleteResearcherById:{}", researcherId);
        if(researcherMapper.deleteByPrimaryKey(researcherId) != 1) {
            throw new ResearcherException(ResultEnum.RESEARCHER_DELETE_FAILURE);
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Researcher queryResearcherById(Integer researcherId) {
        logger.info("queryResearcherById:{}", researcherId);
        Researcher researcher = researcherMapper.selectByPrimaryKey(researcherId);
        if (researcher == null) {
            throw new ResearcherException(ResultEnum.RESEARCHER_NO_FOUND);
        }
        return researcher;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Researcher> queryResearcherList(Researcher researcher) {
        Example example = new Example(Researcher.class);
        Example.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmptyOrWhitespace(researcher.getName())) {
            criteria.andLike("Researchername", "%" + researcher.getName() + "%");
        }

        List<Researcher> researcherList = researcherMapper.selectByExample(example);

        return researcherList;
    }

    @Override
    public List<Researcher> queryByProperty(String property, Object value) {
        Example example = new Example(Researcher.class);
        Example.Criteria criteria = example.createCriteria();

        // 设置条件
        criteria.andEqualTo(property, value);
        example.and(criteria);

        return researcherMapper.selectByExample(example);

    }

    @Override
    public List<Researcher> queryTutorByProperty(String property, Object value) {
        Example example = new Example(Researcher.class);
        Example.Criteria criteria = example.createCriteria();

        // 设置条件
        criteria.andEqualTo("personType", 0);
        criteria.andLike(property, "%" + value + "%");
        example.and(criteria);

        return researcherMapper.selectByExample(example);
    }

    @Override
    public List<Researcher> queryStudentByProperty(String property, Object value) {
        Example example = new Example(Researcher.class);
        Example.Criteria criteria = example.createCriteria();

        // 设置条件
        criteria.andEqualTo("personType", 1);
        criteria.andLike(property, "%" + value + "%");
        example.and(criteria);

        return researcherMapper.selectByExample(example);
    }

    @Override
    public List<Researcher> queryAllStudent() {
        Example example = new Example(Researcher.class);
        Example.Criteria criteria = example.createCriteria();

        // 设置条件
        criteria.andEqualTo("personType", 1);

        Example.Criteria criteria2 = example.createCriteria();
        criteria2.andEqualTo("personType", 2);
        example.or(criteria2);

        Example.Criteria criteria3 = example.createCriteria();
        criteria3.andEqualTo("personType", 3);
        example.or(criteria3);

        return researcherMapper.selectByExample(example);
    }
}
