package ptit.edu.btl.constant;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Arrays;

public class Constant {
    public static final Long ACTIVE = 1L;
    public static final Long UN_ACTIVE = 0L;

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum Sex {

        MALE(1, "Nam"),
        FEMALE(2, "Nữ");

        private int id;
        private String sex;

        Sex() {
        }

        Sex(int id, String sex) {
            this.id = id;
            this.sex = sex;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public static Constant.Sex findById(int i) {
            return Arrays.stream(Constant.Sex.values()).filter(st -> st.getId() == i)
                    .findFirst().orElse(null);
        }
    }

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum Role {

        CUSTOMER(1, "Khách hàng"),
        STAFF(2, "Nhân viên"),
        MANAGE(3, "Quản lý"),
        ADMIN(4, "Quản trị viên");

        private int id;
        private String role;

        Role() {
        }

        Role(int id, String role) {
            this.id = id;
            this.role = role;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public static Role findById(int i) {
            return Arrays.stream(Role.values()).filter(st -> st.getId() == i)
                    .findFirst().orElse(null);
        }
    }

}
