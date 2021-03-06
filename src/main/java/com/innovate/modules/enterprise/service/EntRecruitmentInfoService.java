package com.innovate.modules.enterprise.service;


import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.enterprise.entity.EntRecruitmentInfoEntity;

import java.util.Map;

/**
 * 招聘信息表
 *
 * @author mozhifan
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:19:50
 */
public interface EntRecruitmentInfoService extends IService<EntRecruitmentInfoEntity> {

    /**
     * 招聘信息表 --》 id 查招聘信息 + 企业信息 -- > 列表
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 招聘信息表 --》 id 查招聘信息 + 企业信息
     * @return
     */
    R entRecruitmentInfoById(Long recruitmentInfoId);

    /**
     * 更新招聘信息---审核
     * @param params
     * @return
     */
    R updateRecExamine(Map<String, Object> params);

}

