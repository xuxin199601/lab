package com.csu.lab.service.imp;

import com.csu.lab.enums.ResultEnum;
import com.csu.lab.exception.LaboratoryException;
import com.csu.lab.mapper.LaboratoryMapper;
import com.csu.lab.pojo.Laboratory;
import com.csu.lab.service.LaboratoryService;
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
public class LaboratoryServiceImpl implements LaboratoryService{

    private Logger logger = LoggerFactory.getLogger(LaboratoryServiceImpl.class);

    @Autowired
    private LaboratoryMapper laboratoryMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Laboratory> getLaboratoryList() {
        return laboratoryMapper.selectAll();
    }

    /**
     * 保存实验室信息
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveLaboratory(Laboratory laboratory) {
        logger.info("addLaboratory:{}", laboratory);
        List<Laboratory> laboratoryList = queryByProperty("resDirection", laboratory.getLid());
        if (laboratoryList.isEmpty()) {
            if(laboratoryMapper.insert(laboratory) != 1) {
                throw new LaboratoryException(ResultEnum.LABORATORY_SAVE_FAILURE);
            }
        } else {
            throw new LaboratoryException(ResultEnum.LABORATORY_EXIST);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateLaboratory(Laboratory laboratory) {
        logger.info("updateLaboratory:{}", laboratory);
        if(laboratoryMapper.updateByPrimaryKeySelective(laboratory) != 1) {
            throw new LaboratoryException(ResultEnum.LABORATORY_UPDATE_FAILURE);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteLaboratory(Integer laboratoryId) {
        logger.info("deleteLaboratoryById:{}", laboratoryId);
        if(laboratoryMapper.deleteByPrimaryKey(laboratoryId) != 1) {
            throw new LaboratoryException(ResultEnum.LABORATORY_DELETE_FAILURE);
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Laboratory queryLaboratoryById(Integer laboratoryId) {
        logger.info("queryLaboratoryById:{}", laboratoryId);
        Laboratory laboratory = laboratoryMapper.selectByPrimaryKey(laboratoryId);
        if (laboratory == null) {
            throw new LaboratoryException(ResultEnum.LABORATORY_NO_FOUND);
        }
        return laboratory;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Laboratory> queryLaboratoryListPaged(Integer page, Integer pageSize) {

        logger.info("queryLaboratoryListPaged");
        PageHelper.startPage(page, pageSize);

        List<Laboratory> laboratoryList = laboratoryMapper.selectAll();

        return laboratoryList;
    }

    @Override
    public List<Laboratory> queryByProperty(String property, Object value) {
        Example example = new Example(Laboratory.class);
        Example.Criteria criteria = example.createCriteria();

        // 设置条件
        criteria.andEqualTo(property, value);
        example.and(criteria);

        return laboratoryMapper.selectByExample(example);
    }
}
