package com.csu.lab.controller;

import com.csu.lab.customConst.CustomConstant;
import com.csu.lab.pojo.Account;
import com.csu.lab.pojo.Message;
import com.csu.lab.pojo.Researcher;
import com.csu.lab.service.ResearcherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/server/tutor")
public class TutorsController {

    String msg;

    private static final Logger LOGGER = LoggerFactory.getLogger(TutorsController.class);

    @Autowired
    private ResearcherService researcherService;

    // 跳转到导师管理界面
    @RequestMapping("/tutorList")
    public String tutorList(@RequestParam(name = "person_type", defaultValue = "0")Integer personType,
                            @RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize,
                            @RequestParam(name = "value", required = false) String value,
                            Model model,
                            HttpSession session) {

        session.setAttribute("error", msg);
        msg = null;

        PageHelper.startPage(pageNum,pageSize);

        List<Researcher> researcherList;
        if (value != null){
            model.addAttribute("key", value);
            researcherList = researcherService.queryTutorByProperty("name", value);
        }else {
            researcherList = researcherService.getResearcherList(personType);
        }

        PageInfo pageInfo = new PageInfo(researcherList,10);

        model.addAttribute("pageInfo",pageInfo);
        //获得当前页
        model.addAttribute("pageNum",pageInfo.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize",pageInfo.getPageSize());
        //是否是第一页
        model.addAttribute("isFirstPage",pageInfo.isIsFirstPage());
        //获得总页数
        model.addAttribute("totalPages",pageInfo.getPages());
        //是否是最后一页
        model.addAttribute("isLastPage",pageInfo.isIsLastPage());
        return "server/user/tutorManage";
    }

    // 跳转到新增导师页面
    @RequestMapping("/addTutor")
    public String toAddPage() {
        return "server/user/addTutor";
    }

    // 跳转到修改导师页面
    @RequestMapping("/editTutor")
    public String toEditPage(@RequestParam("id")Integer aid,
                             Model model) {
        Researcher researcher = researcherService.queryResearcherById(aid);
        model.addAttribute("researcher", researcher);
        return "server/user/addTutor";
    }

    // 保存添加的导师信息，跳转到导师管理界面
    @RequestMapping(value = "/saveTutor", method = RequestMethod.POST)
    public String saveTutor(Researcher researcher,
                              @RequestParam("file") MultipartFile file,
                              Model model) throws Exception {
        if (file.isEmpty()) {
            msg = "修改失败";
        }

        String fileName = file.getOriginalFilename();
        String filePath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + CustomConstant.IMAGE_SAVE_PATH;

        String imgPath = filePath + fileName;
        System.out.println(imgPath);

        model.addAttribute("image", "file:" + imgPath);
        researcher.setImage(imgPath);
        File dest = new File(imgPath);
        file.transferTo(dest);

        int result = researcherService.saveResearcher(researcher);
        if (result == 1) {
            msg = "添加成功";
        } else {
            msg = "添加失败";
        }
        return "redirect:/server/tutor/tutorList";
    }


    // 保存修改的导师信息，跳转到导师管理界面
    @RequestMapping(value = "/saveTutor", method = RequestMethod.PUT)
    public String saveEditTutor(Researcher researcher,
                                @RequestParam(value = "file", required = false)MultipartFile file,
                                @RequestParam(value = "image", required = false) String image,
                                Model model) throws Exception {

        // 只修改文本信息，不含头像修改
        if (file.isEmpty() && (image != null)) {
            int result = researcherService.updateResearcher(researcher);
            if (result == 1) {
                msg = "修改成功";
            } else {
                msg = "修改失败";
            }
            return "redirect:/server/tutor/tutorList";
        }

        String fileName = file.getOriginalFilename();
        String filePath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + CustomConstant.IMAGE_SAVE_PATH;

        String imgPath = filePath + fileName;
        System.out.println(imgPath);

        model.addAttribute("image", "file:" + imgPath);
        researcher.setImage(imgPath);
        File dest = new File(imgPath);
        file.transferTo(dest);

        int result = researcherService.updateResearcher(researcher);
        if (result == 1) {
            msg = "修改成功";
        } else {
            msg = "修改失败";
        }
        return "redirect:/server/tutor/tutorList";
    }

    // 删除导师信息，跳转到导师管理界面
    @RequestMapping(value = "/deleteTutor")
    public String delTutor(@RequestParam("id")Integer rid) {
        int result = researcherService.deleteResearcher(rid);
        if (result == 1) {
            msg = "删除成功";
        } else {
            msg = "删除失败";
        }
        return "redirect:/server/tutor/tutorList";
    }


    // 文件上传
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file,
                         Model model) {
        if (file.isEmpty()) {
            return "redirect:/server/tutor/tutorList";
        }

        String fileName = file.getOriginalFilename();
        String filePath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + CustomConstant.IMAGE_SAVE_PATH;

        String imgPath = filePath + fileName;
        System.out.println(imgPath);
        model.addAttribute("image", imgPath);
        File dest = new File(imgPath);
        try {
            file.transferTo(dest);
            LOGGER.info("上传成功");
            return "server/user/addTutor";
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        return "redirect:/server/tutor/tutorList";
    }


    /********************************************************************************************************************************/
    /**
     * 下方写client代码
     */

}
