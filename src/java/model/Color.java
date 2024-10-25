/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
public class Color {
//    [Color_id] [int] IDENTITY(1,1) NOT NULL,
//	[Color_name] [nvarchar](50) NOT NULL,
//	[deleted] [bit] NOT NULL,
    
    private int colorId;
    private String colorName;
    private int deleted;

    public Color() {
    }

    public Color(int colorId, String colorName, int deleted) {
        this.colorId = colorId;
        this.colorName = colorName;
        this.deleted = deleted;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Color{" + "colorId=" + colorId + ", colorName=" + colorName + ", deleted=" + deleted + '}';
    }
}
