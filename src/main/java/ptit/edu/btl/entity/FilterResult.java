package ptit.edu.btl.entity;

import java.util.List;

public class FilterResult {
    private List<Object> data;
    private Long total;
    private int size;
    private int page;

    public FilterResult(List<Object> data, Long total, int size, int page) {
        this.data = data;
        this.total = total;
        this.size = size;
        this.page = page;
    }

    public FilterResult() {
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
