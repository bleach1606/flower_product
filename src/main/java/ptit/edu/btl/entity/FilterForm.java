package ptit.edu.btl.entity;

import org.springframework.util.StringUtils;

public class FilterForm {

    private boolean fiActive = true;
    private String key;
    private Integer page = 1;
    private Integer size = 20;
    private String sortBy = "name";
    private String order = "asc";

    public FilterForm() {
    }

    public FilterForm(boolean fiActive, String key, Integer page, Integer size, String sortBy, String order) {
        this.fiActive = fiActive;
        this.key = key;
        this.page = page;
        this.size = size;
        this.sortBy = sortBy;
        this.order = order;
    }

    public boolean isFiActive() {
        return fiActive;
    }

    public void setFiActive(boolean fiActive) {
        this.fiActive = fiActive;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public boolean isValidForLicenseQuery() {
        return !StringUtils.isEmpty(key);
    }
}
