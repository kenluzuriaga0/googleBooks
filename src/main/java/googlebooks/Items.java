/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package googlebooks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 *
 * @author kenlu
 */
public class Items {

    @SerializedName("volumeInfo")
    @Expose
    VolumeInfo VolumeInfoObject;

    // Getter Methods 
    public VolumeInfo getVolumeInfo() {
        return VolumeInfoObject;
    }

    // Setter Methods 
    public void setVolumeInfo(VolumeInfo volumeInfoObject) {
        this.VolumeInfoObject = volumeInfoObject;
    }

}
