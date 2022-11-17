package com.giousa.tools.lambda.completable2;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AIRecommendDTO implements Serializable {

    private List<RecommendDetail> questions;
    private List<RecommendDetail> answers;
    private List<RecommendDetail> chats;

}
