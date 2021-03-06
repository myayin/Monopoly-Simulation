package cse3063f19p1_abinay_myayin_aaltay.game.config;

import com.google.gson.annotations.Expose;

/**
 * Exposable JSON config definition of
 * {@link cse3063f19p1_abinay_myayin_aaltay.game.square.LotGroup LotGroup}.
 */
public class LotGroupDefinition {

    @Expose
    public String color;

    @Expose
    public LotDefinition[] lots;

    public LotGroupDefinition(String color, LotDefinition...lots) {
        this.color = color;
        this.lots = lots;
    }

}