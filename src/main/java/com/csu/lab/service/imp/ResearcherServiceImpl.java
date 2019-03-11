package com.csu.lab.service.imp;

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
    private ResearcherMapper ResearcherMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Researcher> getResearcherList() {
        return ResearcherMapper.selectAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveResearcher(Researcher tutor) {
        logger.info("addTutor:{}", tutor);
        List<Researcher> researcherList = queryByProperty("aid", tutor.getAid());
        if (researcherList.isEmpty()) {
            if(ResearcherMapper.insert(tutor) != 1) {
                throw new ResearcherException(2, "Failure.");
            }
        } else {
            throw new ResearcherException(1, "Tutor Exist.");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateResearcher(Researcher researcher) {
        logger.info("updateTutor:{}", researcher);
        if(ResearcherMapper.updateByPrimaryKeySelective(researcher) != 1) {
            throw new ResearcherException(2, "Failure.");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteResearcher(Integer researcherId) {
        logger.info("deleteTutorById:{}", researcherId);
        if(ResearcherMapper.deleteByPrimaryKey(researcherId) != 1) {
            throw new ResearcherException(2, "Failure.");
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Researcher queryResearcherById(Integer researcherId) {
        logger.info("queryResearcherById:{}", researcherId);
        Researcher researcher = ResearcherMapper.selectByPrimaryKey(researcherId);
        if (researcher == null) {
            throw new ResearcherException(1, "Tutor No Found.");
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

        List<Researcher> researcherList = ResearcherMapper.selectByExample(example);

        return researcherList;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Researcher> queryResearcherListPaged(Integer personType, Integer page, Integer pageSize) {

        logger.info("queryResearcherListPaged");
        PageHelper.startPage(page, pageSize);

        List<Researcher> researcherList = queryByProperty("personType", personType);

        return researcherList;
    }

    @Override
    public List<Researcher> queryByProperty(String property, Object value) {
        Example example = new Example(Researcher.class);
        Example.Criteria criteria = example.createCriteria();

        // 设置条件
        criteria.andEqualTo(property, value);
        example.and(criteria);

        return ResearcherMapper.selectByExample(example);
    }

}
