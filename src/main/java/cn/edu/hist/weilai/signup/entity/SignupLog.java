package cn.edu.hist.weilai.signup.entity;

import java.sql.Timestamp;
import java.util.Date;

public class SignupLog extends Entity {
    private String _id;
    private String name;
    private String ip;
    private String student_id;
    private String sessionId;
    private Timestamp signupTime;

    public SignupLog() {
    }

    public SignupLog(String name, String ip, String student_id, String sessionId) {
        this.name = name;
        this.ip = ip;
        this.student_id = student_id;
        this.sessionId = sessionId;
        this.signupTime = new Timestamp(new Date().getTime());
    }

    @Override
    public String get_id() {
        return _id;
    }

    @Override
    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Timestamp getSignupTime() {
        return signupTime;
    }

    public void setSignupTime(Timestamp signupTime) {
        this.signupTime = signupTime;
    }
}
