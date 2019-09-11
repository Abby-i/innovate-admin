package com.innovate.modules.enterprise.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 项目信息表
 * 
 * @author mozhifan
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:19:50
 */
@Data
@TableName("ent_project_info")
public class EntProjectInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 项目信息主键
	 */
	@TableId
	private Long proInfoId;
	/**
	 * 项目名称
	 */
	private String proName;
	/**
	 * 项目登记
	 */
	private String proRegister;
	/**
	 * 项目来源
	 */
	private String proOrigin;
	/**
	 * 项目经费
	 */
	private String proOutlay;
	/**
	 * 项目类型
	 */
	private Integer proType;
	/**
	 * 项目介绍
	 */
	private String proIntroduce;

}
