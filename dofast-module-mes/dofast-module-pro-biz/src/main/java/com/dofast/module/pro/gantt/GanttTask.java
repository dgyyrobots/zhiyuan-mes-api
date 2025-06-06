package com.dofast.module.pro.gantt;

import lombok.Data;

import java.util.List;

@Data
public class GanttTask {
    private static final long serialVersionUID = 1L;

    private List<GanttData> data;

    private List<GanttLink> links;

    public List<GanttData> getData() {
        return data;
    }

    public void setData(List<GanttData> data) {
        this.data = data;
    }

    public List<GanttLink> getLinks() {
        return links;
    }

    public void setLinks(List<GanttLink> links) {
        this.links = links;
    }
}
