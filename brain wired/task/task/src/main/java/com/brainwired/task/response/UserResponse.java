package com.brainwired.task.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse<T> {
    private int statusCode;
    private String message;
    private T object;

}
