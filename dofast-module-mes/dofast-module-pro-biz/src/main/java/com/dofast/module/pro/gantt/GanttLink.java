package com.dofast.module.pro.gantt;

import lombok.Data;

@Data
public class GanttLink {
    private String id;

    private String source;

    private String target;

    private Long type;

    private String color;
}
