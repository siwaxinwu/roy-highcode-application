package completabletest;

/**
 * @author dingyawu
 * @version 1.0
 * @date created in 2021-07-05 14:55
 */
public class UserInfo {
  private String code;

  private String name;

  private Integer age;

  public UserInfo(String code, String name, Integer age) {
    this.code = code;
    this.name = name;
    this.age = age;
  }

  public String getCode() {
    return code;
  }

  public UserInfo setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public UserInfo setName(String name) {
    this.name = name;
    return this;
  }

  public Integer getAge() {
    return age;
  }

  public UserInfo setAge(Integer age) {
    this.age = age;
    return this;
  }
}
