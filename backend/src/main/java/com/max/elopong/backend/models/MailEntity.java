package com.max.elopong.backend.models;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MailEntity {

    private String to;

    private String subject;

    private String text;
}
