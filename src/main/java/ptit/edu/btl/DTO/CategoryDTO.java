package ptit.edu.btl.DTO;


import lombok.Data;
import ptit.edu.btl.entity.Category;
import ptit.edu.btl.entity.FlowerProducts;

import java.util.List;

@Data
public class CategoryDTO {
    private int id;

    private String name;

    private String avatar;

    private Boolean active;

    private int type;

    private List<FlowerProducts> flowerProductsList;

    public CategoryDTO(Category category, List<FlowerProducts> flowerProductsList) {
        this.id = category.getId();
        this.name = category.getName();
        this.avatar = category.getAvatar();
        this.active = category.getActive();
        this.type = category.getType();
        this.flowerProductsList = flowerProductsList;
        this.type = category.getType();
    }
}
