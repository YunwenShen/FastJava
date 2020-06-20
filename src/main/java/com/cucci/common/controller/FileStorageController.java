package com.cucci.common.controller;

import com.cucci.common.service.FileStorageService;
import com.cucci.common.vo.FileStorageEntity;
import com.cucci.common.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: shenyw
 * @Date: 2020/6/20 18:14
 */
@RestController
@RequestMapping("/file/storage")
@AllArgsConstructor
public class FileStorageController {

    private FileStorageService fileStorageService;

    @RequestMapping("/save")
    public Result save(@RequestParam("file") MultipartFile file) throws Exception {
        FileStorageEntity entity = new FileStorageEntity();
        entity.setInputStream(file.getInputStream());
        entity.setFileName(file.getOriginalFilename());
        return Result.createSuccess(fileStorageService.save(entity));
    }

    @RequestMapping("/delete")
    public Result delete(@RequestParam("url") String url) throws Exception {
        fileStorageService.delete(url);
        return Result.createSuccess("删除成功");
    }


}
