package com.csu.lab.service.imp;

import com.csu.lab.enums.ResultEnum;
import com.csu.lab.exception.ThesisException;
import com.csu.lab.mapper.ThesisMapper;
import com.csu.lab.pojo.Thesis;
import com.csu.lab.service.ThesisService;
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
public class ThesisServiceImpl implements ThesisService {

    private Logger logger = LoggerFactory.getLogger(ThesisServiceImpl.class);

    @Autowired
    private ThesisMapper thesisMapper;

    @Override
    public List<Thesis> getThesisList() {
        return thesisMapper.selectAll();
    }

    /**
     * 保存成果信息
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveThesis(Thesis thesis) {
        logger.info("addThesis:{}", thesis);
        List<Thesis> ThesisList = queryByProperty("name", thesis.getName());
        if (ThesisList.isEmpty()) {
            if (thesisMapper.insert(thesis) != 1) {
                throw new ThesisException(ResultEnum.THESIS_SAVE_FAILURE);
            }
        } else {
            throw new ThesisException(ResultEnum.THESIS_EXIST);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateThesis(Thesis thesis) {
        logger.info("updateThesis:{}", thesis);
        if (thesisMapper.updateByPrimaryKeySelective(thesis) != 1) {
            throw new ThesisException(ResultEnum.THESIS_UPDATE_FAILURE);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteThesis(Integer ThesisId) {
        logger.info("deleteThesisById:{}", ThesisId);
        if (thesisMapper.deleteByPrimaryKey(ThesisId) != 1) {
            throw new ThesisException(ResultEnum.THESIS_DELETE_FAILURE);
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Thesis queryThesisById(Integer ThesisId) {
        logger.info("queryThesisById:{}", ThesisId);
        Thesis Thesis = thesisMapper.selectByPrimaryKey(ThesisId);
        if (Thesis == null) {
            throw new ThesisException(ResultEnum.THESIS_NO_FOUND);
        }
        return Thesis;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Thesis> queryThesisListPaged(Integer page, Integer pageSize) {

        logger.info("queryThesisListPaged");
        PageHelper.startPage(page, pageSize);

        List<Thesis> ThesisList = thesisMapper.selectAll();

        return ThesisList;
    }

    @Override
    public List<Thesis> queryByProperty(String property, String value) {
        Example example = new Example(Thesis.class);
        Example.Criteria criteria = example.createCriteria();

        // 设置条件
        criteria.andLike(property, "%" + value + "%");
        example.and(criteria);

        return thesisMapper.selectByExample(example);
    }
}
