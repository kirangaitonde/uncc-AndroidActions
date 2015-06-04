/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author K G
 */
public class API implements Serializable {

    private String apiName;
    private List<String> apiActions;

    public API() {
    }

    public API(String apiName, List<String> apiActions) {
        this.apiName = apiName;
        this.apiActions = apiActions;
    }

    public String getAPIName() {
        return apiName;
    }

    public void setAPIName(String apiName) {
        this.apiName = apiName;
    }

    public List<String> getAPIActions() {
        return apiActions;
    }

    public void setAPIActions(List<String> apiActions) {
        this.apiActions = apiActions;
    }
}
