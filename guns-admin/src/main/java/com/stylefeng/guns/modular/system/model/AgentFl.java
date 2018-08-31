package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author pinkman
 * @since 2018-08-30
 */
@TableName("game_agent_fl")
public class AgentFl extends Model<AgentFl> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "AgentFl{" +
        "id=" + id +
        ", name=" + name +
        "}";
    }
}
