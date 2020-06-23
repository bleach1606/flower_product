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

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum OrderStatus {

        NEW(1, "Trạng thái giỏ hàng"),
        WAIT(2, "Chờ xác nhận đặt hàng"),
        CONFIRM(3, "Xác nhận đặt hàng thành công"),
        PACKING(4, "Đơn hàng đang được đóng gói"),
        SHIPPING(5, "Đơn hàng đang được giao"),
        RECEIVED(6, "Đơn hàng đã hoàn thành"),
        CANCEL(7, "Hủy đơn hàng"),
        FAILED(8, "Đơn hàng bị huỷ");

        private int id;
        private String status;

        OrderStatus() {
        }

        OrderStatus(int id, String status) {
            this.id = id;
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public static Constant.OrderStatus findById(int i) {
            return Arrays.stream(Constant.OrderStatus.values()).filter(st -> st.getId() == i)
                    .findFirst().orElse(null);
        }
    }

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum TypeUser {
        NORMAL(1, "Người dùng thường"),
        FACEBOOK(2, "Người dùng Facebook"),
        GOOGLE(3, "Người dùng google");

        private int id;
        private String type;

        TypeUser() {
        }

        TypeUser(int id, String type) {
            this.id = id;
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

}
