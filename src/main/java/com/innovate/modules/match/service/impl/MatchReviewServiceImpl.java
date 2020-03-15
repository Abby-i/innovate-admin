package com.innovate.modules.match.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.innovate.entity.InnovateReviewGroupUserEntity;
import com.innovate.modules.innovate.service.InnovateReviewGroupUserService;
import com.innovate.modules.match.dao.MatchReviewDao;
import com.innovate.modules.match.entity.MatchInfoEntity;
import com.innovate.modules.match.entity.MatchReviewEntity;
import com.innovate.modules.match.entity.MatchTeacherEntity;
import com.innovate.modules.match.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @Email:1625017540@qq.com
 * @date 2018/11/19 18:43
 * @Version 1.0
 */
@Service
public class MatchReviewServiceImpl extends ServiceImpl<MatchReviewDao, MatchReviewEntity> implements MatchReviewService {
    @Autowired
    private MatchReviewService matchReviewService;
    @Autowired
    private MatchApplyService matchApplyService;
    @Autowired
    private MatchInfoService matchInfoService;
    @Autowired
    private MatchTeacherService matchTeacherService;
    @Autowired
    private InnovateReviewGroupUserService innovateReviewGroupUserService;

    @Override
    public List<MatchReviewEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public void remove(Map<String, Object> params) {
        baseMapper.remove(params);
    }

    @Override
    public Long queryCount(Map<String, Object> params){
        return baseMapper.queryCount(params);
    }

    @Override
    public Double queryScoreAvg(Map<String, Object> params) {
        return baseMapper.queryScoreAvg(params);
    }

    @Override
    public void reviewUser(Map<String,Object> params) {
        Long matchId = Long.parseLong(params.get("matchId").toString());
        Long groupId = Long.parseLong(params.get("groupId").toString());
        Long userId = Long.parseLong(params.get("userId").toString());
        String apply = params.get("apply").toString();
        String reApply = params.get("reApply").toString();
        MatchInfoEntity matchInfoEntity = matchInfoService.selectById(matchId);
        matchInfoEntity.setGroupId(groupId);
        matchInfoService.updateById(matchInfoEntity);
        List<MatchTeacherEntity> matchTeacherEntities = matchTeacherService.queryAll(params);
        List<InnovateReviewGroupUserEntity> innovateReviewGroupUserEntities = innovateReviewGroupUserService.queryAllGroupUser(groupId);
        matchReviewService.remove(params);
        MatchReviewEntity matchReviewEntity = null;
        for (int index = 0; index < innovateReviewGroupUserEntities.size(); index++) {
            for (int indexJ = 0; indexJ < matchTeacherEntities.size(); indexJ++) {
                if (innovateReviewGroupUserEntities.get(index).getUserId() != matchTeacherEntities.get(indexJ).getUserId()) {
                    matchReviewEntity = new MatchReviewEntity();
                    matchReviewEntity.setApply(apply);
                    matchReviewEntity.setMatchId(matchId);
                    matchReviewEntity.setUserId(innovateReviewGroupUserEntities.get(index).getUserId());
                    matchReviewService.insert(matchReviewEntity);
                }
            }
        }
        if (reApply.equals("false")) {
            matchApplyService.apply(params);
        }
    }

    @Override
    public void score(MatchReviewEntity matchReviewEntity) {
        Long matchId = matchReviewEntity.getMatchId();
        matchReviewService.updateById(matchReviewEntity);
        String apply = matchReviewEntity.getApply();
        Map<String, Object> params = new HashMap<>();
        params.put("matchId", matchId);
        params.put("apply", apply);
        params.put("roleId", 6);
        Long count = matchReviewService.queryCount(params);
        if (count == 0L){
            matchApplyService.apply(params);
            //计算平均分
            Double scoreAvg = matchReviewService.queryScoreAvg(params);
            MatchInfoEntity matchInfoEntity = matchInfoService.selectById(matchId);
            matchInfoEntity.setMatchScoreAvg(scoreAvg);
            matchInfoService.updateById(matchInfoEntity);
        }
    }

    @Override
    public MatchReviewEntity queryScore(Map<String,Object> params) {
        return baseMapper.queryScore(params);
    }
}
