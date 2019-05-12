public class Patch {

    // the daisy this patch holds
    private Daisy daisy;

    // The temperature of this patch
    private double temperature;

    // Constructor
    public Patch(Daisy daisy, double temperature) {
        this.daisy = daisy;
        this.temperature = temperature;
    }

    // getter and setter
    public Daisy getDaisy() {
        return daisy;
    }

    public void setDaisy(Daisy daisy) {
        this.daisy = daisy;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    /*
        This method could calculate the temperature of this patch
        The current temperature is based on the initial temperature and the luminosity it absorb
        The heat could be calculated from: heat = ( 1 - current albedo)* solar_luminosity
     */
     public void calTeperatrue(){

         // the luminosity this patch absorbed
         double luminosity = 0;

         double heat = 0;

         if( daisy!= null){
             luminosity = ( 1 - daisy.getAlbedo() ) * Params.SOLAR_LUMINOSITY;
             } else{
             luminosity = ( 1 - Params.SUR_ALBEDO ) * Params.SOLAR_LUMINOSITY;
         }

         // The heat will calculated according to the absorbed luminosity
         heat = 72 * (Math.log(luminosity)) + 80 ;

         temperature = ( heat + temperature )/2 ;
     }

     /*
        This method is used to decide whether the daisy in this patch live, die or reproduce
        In each time tick, the patch would check the daisy's age and decide whether it lives on
      */
     public void daisyLive(){
         if( daisy!= null){
             daisy.setAge( daisy.getAge() + 1);
             if( daisy.getAge() <= Params.AGE_MAX ){

             }else{
                 // or the daisy would die because of age
                 daisy = null;
             }
         }else{

         }
     }
}
