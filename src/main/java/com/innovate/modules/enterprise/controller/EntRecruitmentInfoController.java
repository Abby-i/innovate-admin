package com.innovate.modules.enterprise.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.enterprise.entity.EntRecruitmentInfoEntity;
import com.innovate.modules.enterprise.service.EntRecruitmentInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 招聘信息表
 *
 * @author 莫智帆
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:19:50
 */
@RestController
@RequestMapping("enterprise/recruitment/info")
public class EntRecruitmentInfoController {
    @Autowired
    private EntRecruitmentInfoService entRecruitmentInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("enterprise:recruitment:info:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = entRecruitmentInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{recruitmentInfoId}")
    //@RequiresPermissions("enterprise:recruitment:info")
    public R info(@PathVariable("recruitmentInfoId") Long recruitmentInfoId){
        R r = entRecruitmentInfoService.entRecruitmentInfoById(recruitmentInfoId);
        return r;
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("enterprise:recruitment:info:save")
    public R save(@RequestBody EntRecruitmentInfoEntity entRecruitmentInfo){
		entRecruitmentInfoService.insert(entRecruitmentInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("enterprise:recruitment:info:update")
    public R update(@RequestBody EntRecruitmentInfoEntity entRecruitmentInfo){
		entRecruitmentInfoService.updateById(entRecruitmentInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("enterprise:recruitment:info:delete")
    public R delete(@RequestBody Long[] recruitmentInfoIds){
		entRecruitmentInfoService.deleteBatchIds(Arrays.asList(recruitmentInfoIds));

        return R.ok();
    }

}
