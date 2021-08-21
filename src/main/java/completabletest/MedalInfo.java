package completabletest;

/**
 * @author dingyawu
 * @version 1.0
 * @date created in 2021-07-05 14:57
 */
public class MedalInfo {
  private String code;

  private String msg;

  public MedalInfo(String code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public String getCode() {
    return code;
  }

  public MedalInfo setCode(String code) {
    this.code = code;
    return this;
  }

  public String getMsg() {
    return msg;
  }

  public MedalInfo setMsg(String msg) {
    this.msg = msg;
    return this;
  }
}
