package com.stouduo.qcb.Controller;

import com.stouduo.qcb.service.PicService;
import com.stouduo.qcb.util.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping("/pic")
public class PicController {
    @Autowired
    private PicService picService;

    @ResponseBody
    @GetMapping("/getPics")
    public RestResult getPics(@RequestParam(name = "pageSize", defaultValue = "9") int pageSize, @RequestParam(name = "curPage", defaultValue = "0") int curPage) {
        return RestResult.ok(picService.getPics(pageSize, curPage));
    }

    @ResponseBody
    @DeleteMapping("/delPic")
    public RestResult delPic(String ids) {
        picService.delPic(ids);
        return RestResult.ok("删除成功");
    }

    @ResponseBody
    @PostMapping("/uploadPic")
    public RestResult uploadPic(@RequestParam("pic") MultipartFile file) {
        try {
            picService.uploadPic(file);
            return RestResult.ok("上传成功");
        } catch (IOException e) {
            return RestResult.error("上传失败！");
        }
    }

    @GetMapping("/{id}")
    public void getPic(@PathVariable String id, HttpServletResponse response) {
        try (OutputStream outStream = response.getOutputStream()) {
            picService.getPic(id).writeTo(outStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
