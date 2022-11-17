package com.giousa.tools.lambda.completable2;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class ZMainV2Test {

    public static void main(String[] args) {
        System.out.println("开始请求：");
        long startTime = System.currentTimeMillis();

        test01();
//        test02();

        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
        System.out.println("请求结束！");
    }


    private static void test01() {
        CompletableFuture<ImmutablePair<AIRecommendType, List<RecommendDetail>>> questionFuture = CompletableFuture.supplyAsync(() -> AIRecommendManager.getQuestionRecommend());
        CompletableFuture<ImmutablePair<AIRecommendType, List<RecommendDetail>>> answerFuture = CompletableFuture.supplyAsync(() -> AIRecommendManager.getAnswerRecommend());
        CompletableFuture<ImmutablePair<AIRecommendType, List<RecommendDetail>>> chatFuture = CompletableFuture.supplyAsync(() -> AIRecommendManager.getChatRecommend());

        AIRecommendDTO aiRecommendDTO = new AIRecommendDTO();
        Stream.of(questionFuture, answerFuture, chatFuture).forEach(it -> {
            ImmutablePair<AIRecommendType, List<RecommendDetail>> pair = null;
            try {
                pair = it.get();
                switch (pair.left) {
                    case AI_QUESTION:
                        aiRecommendDTO.setQuestions(pair.right);
                        break;

                    case AI_ANSWER:
                        aiRecommendDTO.setAnswers(pair.right);
                        break;

                    case AI_CHAT:
                        aiRecommendDTO.setChats(pair.right);
                        break;
                }
            } catch (Exception e) {
                System.out.println("查询异常");
            }

        });

        System.out.println("查询结果: " + JSON.toJSONString(aiRecommendDTO));

    }

    private static void test02() {

        ImmutablePair<AIRecommendType, List<RecommendDetail>> questionRecommend = AIRecommendManager.getQuestionRecommend();
        ImmutablePair<AIRecommendType, List<RecommendDetail>> answerRecommend = AIRecommendManager.getAnswerRecommend();
        ImmutablePair<AIRecommendType, List<RecommendDetail>> chatRecommend = AIRecommendManager.getChatRecommend();

        AIRecommendDTO aiRecommendDTO = new AIRecommendDTO();
        aiRecommendDTO.setQuestions(questionRecommend.right);
        aiRecommendDTO.setAnswers(answerRecommend.right);
        aiRecommendDTO.setChats(chatRecommend.right);

        System.out.println("查询结果: " + JSON.toJSONString(aiRecommendDTO));
    }

}
