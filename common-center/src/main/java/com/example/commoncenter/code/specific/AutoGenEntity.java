package com.example.commoncenter.code.specific;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutoGenEntity {

    private List<String> colNames;

    private List<String> colTypes;

    private List<Object> colComment;


}
