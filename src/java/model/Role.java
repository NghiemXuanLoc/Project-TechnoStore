/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
public class Role {
//    [Role_id] [int] IDENTITY(1,1) NOT NULL,
//	[Role_name] [nvarchar](20) NOT NULL,
//	[deleted] [bit] NOT NULL,
    
    private int roleId;
    private String roleName;
    private int deleted;

    public Role() {
    }

    public Role(int roleId, String roleName, int deleted) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.deleted = deleted;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Role{" + "roleId=" + roleId + ", roleName=" + roleName + ", deleted=" + deleted + '}';
    }
    
}
