package com.csu.lab.service.imp;

import com.csu.lab.customConst.CustomConstant;
import com.csu.lab.enums.ResultEnum;
import com.csu.lab.exception.ThesisException;
import com.csu.lab.mapper.ThesisMapper;
import com.csu.lab.pojo.Message;
import com.csu.lab.pojo.Thesis;
import com.csu.lab.service.ThesisService;
import com.csu.lab.utils.CustomUtils;
import com.github.pagehelper.PageHelper;
import org.omg.CORBA.INTERNAL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.csu.lab.utils.FileUtil;

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
    public Integer saveThesis(Thesis thesis, List<MultipartFile> files) throws IOException {
        logger.info("addThesis:{}", thesis);
        List<Thesis> ThesisList = queryByProperty("name", thesis.getName());
        if (ThesisList.isEmpty()) {
            for (int i = 0; i < files.size(); ++i) {
                MultipartFile file = files.get(i);
                if (!file.isEmpty()) {
                    String filePath = "";
                    switch (i) {
                        case 0: {
                            filePath = CustomConstant.THESIS_CONTENT_SAVE_PATH;
                            break;
                        }
                        case 1: {
                            filePath = CustomConstant.THESIS_CODE_SAVE_PATH;
//                            thesis.setCode(newFilename);
                            break;
                        }
                        case 2: {
                            filePath = CustomConstant.THESIS_DATA_SAVE_PATH;
//                            thesis.setData(newFilename);
                            break;
                        }
                    }
                    String newFilename = CustomUtils.uploadFile(file, filePath);
                    switch (i) {
                        case 0: {
                            thesis.setContent(newFilename);
                            break;
                        }
                        case 1: {
                            thesis.setCode(newFilename);
                            break;
                        }
                        case 2: {
                            thesis.setData(newFilename);
                            break;
                        }
                    }


                } else if (file.getOriginalFilename().equals("")) {
                    switch (i) {
                        case 0: {
                            thesis.setContent("");
                            break;
                        }
                        case 1: {
                            thesis.setCode("");
                            break;
                        }
                        case 2: {
                            thesis.setData("");
                            break;
                        }
                    }
                } else {
                    switch (i) {
                        case 0: {
                            throw new ThesisException(ResultEnum.THESIS_CONTENT_FAILURE);
                        }
                        case 1: {
                            throw new ThesisException(ResultEnum.THESIS_CODE_FAILURE);
                        }
                        case 2: {
                            throw new ThesisException(ResultEnum.THESIS_DATA_FAILURE);
                        }
                    }
                }
            }

            if (thesisMapper.insert(thesis) != 1) {
                throw new ThesisException(ResultEnum.THESIS_SAVE_FAILURE);
            }
        } else {
            throw new ThesisException(ResultEnum.THESIS_EXIST);
        }
        return thesisMapper.insert(thesis);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer updateThesis(Thesis thesis, List<MultipartFile> files) throws IOException {
        logger.info("updateThesis:{}", thesis);
        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            if (!file.getOriginalFilename().equals("")) {
//                String deleteFile
                String deleteFilePath = "";
                String pathFile = "";
                switch (i) {
                    case 0: {
                        deleteFilePath = thesis.getContent();
                        pathFile = CustomConstant.THESIS_CONTENT_SAVE_PATH;
                        break;
                    }
                    case 1: {
                        deleteFilePath = thesis.getCode();
                        pathFile = CustomConstant.THESIS_CODE_SAVE_PATH;
                        break;
                    }
                    case 2: {
                        deleteFilePath = thesis.getData();
                        pathFile = CustomConstant.THESIS_DATA_SAVE_PATH;
                        break;
                    }
                }
                thesis.getContent();
                String newFilename = CustomUtils.uploadFile(file, pathFile);
                switch (i) {
                    case 0: {
                        thesis.setContent(newFilename);
                        break;
                    }
                    case 1: {
                        thesis.setCode(newFilename);
                        break;
                    }
                    case 2: {
//                        deleteFilePath = thesis.getData();
                        thesis.setData(newFilename);
                        break;

                    }
                }
//                file.setVideo(newFilename);
//            删除旧的文件
//            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + CustomConstant.VIDEO_SAVE_PATH;
//            String filename = path+""
//            String deleteFilePath = project.getVideo();
                CustomUtils.deleteFile(deleteFilePath);
            }
        }
        if (thesisMapper.updateByPrimaryKeySelective(thesis) != 1) {
            throw new ThesisException(ResultEnum.THESIS_UPDATE_FAILURE);
        }

        return thesisMapper.updateByPrimaryKeySelective(thesis);
    }

    /**
     * 删除
     *
     * @param ThesisId
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer deleteThesis(Integer ThesisId) {
        logger.info("deleteThesisById:{}", ThesisId);
        Thesis thesis = thesisMapper.selectByPrimaryKey(ThesisId);
        if (thesis == null) {
            return -1;
        }
        if (thesisMapper.deleteByPrimaryKey(ThesisId) != 1) {
            throw new ThesisException(ResultEnum.THESIS_DELETE_FAILURE);
        } else {
            CustomUtils.deleteFile(thesis.getContent());
            CustomUtils.deleteFile(thesis.getCode());
            CustomUtils.deleteFile(thesis.getData());
        }
        return thesisMapper.deleteByPrimaryKey(ThesisId);
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

    /**
     * 查询
     *
     * @param page
     * @param pageSize
     * @return
     */
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

    /**
     * 通过验证的用户下载论文
     */
    @Override
    public void getThesisContent(Integer tid, Integer aid, HttpServletResponse response, HttpServletRequest request) {

        //根据aid判断用户是否已经登录
        //【待补充】

        //根据tid获取论文的url
        Thesis thesis = thesisMapper.selectByPrimaryKey(tid);

        if (thesis.getContent() == null) {
            throw new ThesisException(ResultEnum.THESIS_NO_FOUND);
        }
        //根据url读取文件，并将文件流返回到前端
        FileUtil.downloadFile(thesis.getContent(), response);
    }

    /**
     * 通过验证的用户下载论文代码
     */
    @Override
    public void getThesisCode(Integer tid, Integer aid, HttpServletResponse response, HttpServletRequest request) {
        //根据aid判断用户是否已经登录
        //【待补充】

        //根据tid获取论文的url
        Thesis thesis = thesisMapper.selectByPrimaryKey(tid);

        if (thesis.getCode() == null) {
            throw new ThesisException(ResultEnum.THESIS_NO_FOUND);
        }
        //根据url读取文件，并将文件流返回到前端
        FileUtil.downloadFile(thesis.getCode(), response);
    }

    /**
     * 通过验证的用户下载论文数据集（考虑没有数据集的情况）
     */
    @Override
    public void getThesisData(Integer tid, Integer aid, HttpServletResponse response, HttpServletRequest request) {
        //根据aid判断用户是否已经登录
        //【待补充】

        //根据tid获取论文的url
        Thesis thesis = thesisMapper.selectByPrimaryKey(tid);

        if (thesis.getData() == null) {
            throw new ThesisException(ResultEnum.THESIS_NO_FOUND);
        }
        //根据url读取文件，并将文件流返回到前端
        FileUtil.downloadFile(thesis.getData(), response);
    }

}
