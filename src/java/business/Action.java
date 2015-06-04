/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.Serializable;

/**
 *
 * @author K G
 */
public class Action implements Serializable {

    private String actionName;
    private String description;
    private String usingAPIS;

    public Action() {
    }

    public Action(String actionName, String description, String usingAPIS) {
        this.actionName = actionName;
        this.description = description;
        this.usingAPIS = usingAPIS;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsingAPIS() {
        return usingAPIS;
    }
    
    public void setUsingAPIS(String usingAPIS) {
        this.usingAPIS = usingAPIS;
    }
}
