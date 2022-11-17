package com.giousa.tools.lambda.completable2;

import lombok.Data;

import java.io.Serializable;

@Data
public class RecommendDetail implements Serializable {

    private String templateName;

    private String templateCode;
}
