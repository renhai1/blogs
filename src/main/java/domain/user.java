package domain;
/**
 * @className:user
 * @description:生成用户对象
 * @author: renhai
 * @create: 2021-08-17 16:58
 */
public class user {
    private int Id;
    private String Name;
    private String pas;
    private String email;
    private String status;
    private String photoAddress;
    private String code;


    public int getId() {
        return Id;
    }

    public String getPhotoAddress() {
        return photoAddress;
    }

    public void setPhotoAddress(String photoAddress) {
        this.photoAddress = photoAddress;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPas() {
        return pas;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setPas(String pas) {
        this.pas = pas;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return java.lang.String
     * @Author renhai
     * @Description 讲对象转成字符串编于测试代码
     * @Date 16:59 2021/8/17
     * @Param []
     **/
    @Override
    public String toString() {
        return "user{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", pas='" + pas + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", photoAddress='" + photoAddress + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
