/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
public class Memory {
//    [Memory_id] [int] IDENTITY(1,1) NOT NULL,
//	[Memory_size] [int] NOT NULL,
//	[deleted] [bit] NOT NULL,
    private int memoryId;
    private int memorySize;
    private int deleted;

    public Memory() {
    }

    public Memory(int memoryId, int memorySize, int deleted) {
        this.memoryId = memoryId;
        this.memorySize = memorySize;
        this.deleted = deleted;
    }

    public int getMemoryId() {
        return memoryId;
    }

    public void setMemoryId(int memoryId) {
        this.memoryId = memoryId;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Memory{" + "memoryId=" + memoryId + ", memorySize=" + memorySize + ", deleted=" + deleted + '}';
    }
}
