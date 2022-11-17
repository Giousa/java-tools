package com.giousa.tools.lambda.completable2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum AIRecommendType {
    AI_QUESTION("AI_QUESTION","AI推荐-问"),
    AI_ANSWER("AI_ANSWER","AI推荐-答"),
    AI_CHAT("AI_CHAT","AI推荐-聊"),
    ;

    private String type;

    private String desc;
}
