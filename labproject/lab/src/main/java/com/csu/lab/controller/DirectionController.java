package com.csu.lab.controller;

import com.csu.lab.pojo.Direction;
import com.csu.lab.pojo.Message;
import com.csu.lab.service.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/server/direction")
public class DirectionController {

    @Autowired
    private DirectionService directionService;

    // 分页研究方向信息列表
    @GetMapping("/directionList")
    public Message getStudentByPage(@RequestParam("page_index")Integer pageIndex,
                                    @RequestParam("page_size")Integer pageSize) {
        List<Direction> directionList = directionService.queryDirectionListPaged(pageIndex, pageSize);
        return Message.success().add(directionList);
    }

    // 通过id获取研究方向信息
    @GetMapping("/directionInfo")
    public Message getDirectionById(@RequestParam("did")Integer pid) {
        Direction direction = directionService.queryDirectionById(pid);
        return Message.success().add(direction);
    }

    // 添加研究方向
    @PostMapping("/addDirection")
    public Message addDirection(Direction direction) throws Exception {
        directionService.saveDirection(direction);
        return Message.success().add("添加成功");
    }

    // 修改研究方向信息
    @PutMapping("/modifyDirection")
    public Message modifyDirection(Direction direction) {
        directionService.updateDirection(direction);
        return Message.success().add("Success");
    }

    // 删除研究方向信息
    @DeleteMapping("/deleteDirection")
    public Message deleteDirection(@RequestParam("did")Integer rid) {
        directionService.deleteDirection(rid);
        return Message.success().add("Success");
    }

}
