package cn.wmap.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "polygon_web", schema = "webmap", catalog = "")
public class PolygonWeb {
    private int pid;
    private String name;
    private String location;
    private Timestamp time;
    private Integer updatetype;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid")
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "updatetype")
    public Integer getUpdatetype() {
        return updatetype;
    }

    public void setUpdatetype(Integer updatetype) {
        this.updatetype = updatetype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PolygonWeb that = (PolygonWeb) o;
        return pid == that.pid &&
                Objects.equals(name, that.name) &&
                Objects.equals(location, that.location) &&
                Objects.equals(time, that.time) &&
                Objects.equals(updatetype, that.updatetype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, name, location, time, updatetype);
    }
}
