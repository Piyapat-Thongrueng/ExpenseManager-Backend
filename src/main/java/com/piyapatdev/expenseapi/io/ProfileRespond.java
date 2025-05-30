package com.piyapatdev.expenseapi.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileRespond {

    private String profileId;

    private String email;

    private String name;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
