package com.giousa.tools.lambda.completable2;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.List;

public class AIRecommendManager {

    public static ImmutablePair<AIRecommendType,List<RecommendDetail>> getQuestionRecommend() {
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<RecommendDetail> list = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            RecommendDetail recommendDetail = new RecommendDetail();
            recommendDetail.setTemplateName("name_"+i);
            recommendDetail.setTemplateCode("code"+i);
            list.add(recommendDetail);
        }

        return ImmutablePair.of(AIRecommendType.AI_QUESTION,list);
    }

    public static ImmutablePair<AIRecommendType,List<RecommendDetail>> getAnswerRecommend() {
        try {
            Thread.sleep(1100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<RecommendDetail> list = Lists.newArrayList();
        for (int i = 30; i < 40; i++) {
            RecommendDetail recommendDetail = new RecommendDetail();
            recommendDetail.setTemplateName("name_"+i);
            recommendDetail.setTemplateCode("code"+i);
            list.add(recommendDetail);
        }
        return ImmutablePair.of(AIRecommendType.AI_ANSWER,list);
    }

    public static ImmutablePair<AIRecommendType,List<RecommendDetail>> getChatRecommend() {
        try {
            Thread.sleep(1300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<RecommendDetail> list = Lists.newArrayList();
        for (int i = 70; i < 80; i++) {
            RecommendDetail recommendDetail = new RecommendDetail();
            recommendDetail.setTemplateName("name_"+i);
            recommendDetail.setTemplateCode("code"+i);
            list.add(recommendDetail);
        }
        return ImmutablePair.of(AIRecommendType.AI_CHAT,list);
    }
}
