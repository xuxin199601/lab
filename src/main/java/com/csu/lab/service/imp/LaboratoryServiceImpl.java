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
    public List<Laboratory> getLaboratoryList() {
        return laboratoryMapper.selectAll();
    }

    @Override
    public int updateLaboratory(Laboratory laboratory) {

        int result = laboratoryMapper.updateByPrimaryKeySelective(laboratory);

        return result;
    }
}
