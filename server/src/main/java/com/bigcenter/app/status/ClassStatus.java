package com.bigcenter.app.status;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ClassStatus {
    @JsonProperty("scheduled")
    SCHEDULED,

    @JsonProperty("using")
    USING,

    @JsonProperty("completed")
    COMPLETED
}
