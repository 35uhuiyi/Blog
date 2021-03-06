package com.ny.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    private Long id;
    private String name;

    private List<Blog> blogs = new ArrayList<>();
}
