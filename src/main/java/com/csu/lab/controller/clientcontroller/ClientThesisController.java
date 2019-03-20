package com.csu.lab.controller.clientcontroller;

import com.csu.lab.pojo.Message;
import com.csu.lab.pojo.Thesis;
import com.csu.lab.service.ThesisService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/client/thesis")
public class ClientThesisController {

    String msg;

    @Autowired
    private ThesisService thesisService;

    // 分页成果信息列表
    @RequestMapping("/thesisList")
    public String getThesisListByPage(@RequestParam(defaultValue = "1") Integer page,
                                   Model model
                                   ) {

        PageHelper.startPage(page, 12);

        List<Thesis> thesisList=thesisService.getThesisList();

        PageInfo pageInfo = new PageInfo(thesisList);// 1 2 3 4

        model.addAttribute("pageInfo", pageInfo);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());

        model.addAttribute("thesisList",thesisList);

        model.addAttribute("page", page);

        System.out.println(model.getClass());

        return "client/thesis/index";
    }

    // 通过id获取成果信息
    @RequestMapping("/thesis")
    public String getThesisById(@RequestParam("id") int tid,Model model) {
        Thesis thesis = thesisService.queryThesisById(tid);
        model.addAttribute("thesis",thesis);

        return "client/thesis/thesis";
    }

    /********************************************************************************************************************************/
    /**
     * 下方写client代码
     */
    /**
     * 通过验证的用户下载论文
     */
    @GetMapping("/thesisContent/{tid}/{aid}")
    public void getThesisContent(@PathVariable Integer tid, @PathVariable Integer aid, HttpServletResponse response, HttpServletRequest request) {
        thesisService.getThesisContent(tid, aid, response, request);
    }

    /**
     * 通过验证的用户下载论文代码
     */
    @GetMapping("/thesisCode/{tid}/{aid}")
    public void getThesisCode(@PathVariable Integer tid, @PathVariable Integer aid, HttpServletResponse response, HttpServletRequest request) {
        thesisService.getThesisCode(tid, aid, response, request);
    }

    /**
     * 通过验证的用户下载论文数据集（考虑没有数据集的情况）
     */
    @GetMapping("/thesisData/{tid}/{aid}")
    public void getThesisData(@PathVariable Integer tid, @PathVariable Integer aid, HttpServletResponse response, HttpServletRequest request) {
        thesisService.getThesisData(tid, aid, response, request);
    }
}
