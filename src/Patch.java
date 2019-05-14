import java.util.Random;

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

    /**
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

         //heat is calculated as logarithmic function of solar-luminosity
         //where a absorbed-luminosity of 1 yields a heating of 80 degrees C
         //and an absorbed-luminosity of .5 yields a heating of approximately 30 C
         //and a absorbed-luminosity of 0.01 yields a =heating of approximately -273 C
         if( luminosity > 0){
             heat = 72 * (Math.log(luminosity)) + 80 ;
         }else{
             heat = 80;
         }
         temperature = ( heat + temperature )/2 ;
     }

     /**
        This method is used to decide whether the daisy in this patch live, die or reproduce
        In each time tick, the patch would check the daisy's age and decide whether it lives on
        if the daisy in this patch could live, then calculate the probability of reproduction to a neighbor patch
      */
     public boolean checkSurviability(){

         Random random = new Random();
         double seed_threshold = 0.0;
         boolean canReproduce = false;  //if this patch would sprout a daisy to its neighbor

         // check if there is a daisy here
         if( daisy!= null){
             daisy.setAge( daisy.getAge() + 1);

             // if the daisy's age is smaller than max age, it would live on
             // and calculate the probability of reproduction
             if( daisy.getAge() <= Params.AGE_MAX ){

                 // The seed threshold is calculated according to the temperature in this patch
                 // This parabola has a peak value of 1 -- the maximum growth factor possible at an optimum
                 // temperature of 22.5 degrees C
                 // and drops to zero at local temperatures of 5 degrees C and 40 degrees C. [the x-intercepts]
                    seed_threshold = ((0.1457 * temperature) - (0.0032 * (temperature * temperature)) - 0.6443);
                    if (random.nextDouble() < seed_threshold ){
                        canReproduce = true;
                    }else{
                        canReproduce = false;
                    }
             }else{
                 // or the daisy would die because of age
                 daisy = null;
                 canReproduce = false;
             }
         }else{
            canReproduce = false;
         }
         return canReproduce;
     }
}
