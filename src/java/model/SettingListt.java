/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class SettingListt {
    private int settingId;
    private String type;
    private int value;
    private int order;
    private String description;
    private int status;

    public SettingListt() {
    }

    public SettingListt(int settingId, String type, int value, int order, String description, int status) {
        this.settingId = settingId;
        this.type = type;
        this.value = value;
        this.order = order;
        this.description = description;
        this.status = status;
    }
    
    public SettingListt( String type, int value, int order, String description, int status) {
        this.type = type;
        this.value = value;
        this.order = order;
        this.description = description;
        this.status = status;
    }
    
    public SettingListt(int value) {
        this.value = value;
    }
    
    public SettingListt(String type) {
        this.type = type;
    }

    public int getSettingId() {
        return settingId;
    }

    public void setSettingId(int settingId) {
        this.settingId = settingId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
