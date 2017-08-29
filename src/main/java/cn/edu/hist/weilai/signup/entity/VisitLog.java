package cn.edu.hist.weilai.signup.entity;

import java.sql.Timestamp;
import java.util.Date;

public class VisitLog extends Entity {

    private String _id;
    private String host;
    private String ip;
    private String uri;
    private String sessionId;
    private Timestamp visitTime;
    public VisitLog() {
    }

    public VisitLog(String host,String ip, String uri, String sessionId) {
        this.host = host;
        this.ip = ip;
        this.uri = uri;
        this.sessionId = sessionId;
        this.visitTime = new Timestamp(new Date().getTime());
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String get_id() {
        return _id;
    }

    @Override
    public void set_id(String _id) {
        this._id = _id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Timestamp getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Timestamp visitTime) {
        this.visitTime = visitTime;
    }
}
