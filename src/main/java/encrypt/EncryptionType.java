package encrypt;

/**
 * Created by hongyu.zhuang
 * on 5/9/14 11:45 AM
 */
public enum EncryptionType {
    MD5("MD5"),
    SHA1("SHA-1");

    String type;

    EncryptionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
