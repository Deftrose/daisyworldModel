import java.io.FileWriter;
import java.io.IOException;

/**
 * Main Class bring each component together and print out the result of experiment as cvs file
 *
 * Use the preSetting method to preset some parameters and the main method would run the experiment and print it.
 */
public class Main{

    public static void main(String []args ) {

        // the MAX_TIME const would define how many time periods would this model
        // run in an experiment.
        final int MAX_TIME = 500;


        World daisyWorld = new World();

        try{
            FileWriter writer = new FileWriter("result.csv");

            for(int i=0 ; i< MAX_TIME ; i++ ){
                daisyWorld.tick();
                writer.append(String.valueOf(daisyWorld.getGlobalTemp() + "," +"\n"));
            }

            writer.close();
        } catch (IOException e){
            System.out.println("got an IOException" + e);
        }

    }

    /**
     * This method is used to preset the parameters. It could also been done manually.
     * However, that would require to change the value, run and repeat. This method would
     * make this process done multiple times with running once.
     *
     * In this way, the solar luminosity should be a fixed value
     * if need to get a experiment of changing solar luminosity
     * use method changingSolarSetting
     */
    private void paraSetting(double BLACK_DAISY, double WHIT_DAISY, double LUMINOSITY,
                             double SUR_ALBEDO, double BLK_ALBEDO, double WHITE_ALBEDO ){
        Params.BLACK_DAISY = BLACK_DAISY;
        Params.WHITE_DAISY = WHIT_DAISY;
        Params.SUR_ALBEDO = SUR_ALBEDO;
        Params.BLACK_ALBEDO = BLK_ALBEDO;
        Params.WHITE_ALBEDO = WHITE_ALBEDO;
        Params.SOLAR_LUMINOSITY = LUMINOSITY;
    }

    /**
     * This method is only used when you need to model a changing solar luminosity
     * This param LUMINOSITY is only the initial Luminosity and would change through the time
     * @param LUMINOSITY
     */
    private void changingSolarSetting(double BLACK_DAISY, double WHIT_DAISY, double LUMINOSITY,
                             double SUR_ALBEDO, double BLK_ALBEDO, double WHITE_ALBEDO ){
        Params.BLACK_DAISY = BLACK_DAISY;
        Params.WHITE_DAISY = WHIT_DAISY;
        Params.SUR_ALBEDO = SUR_ALBEDO;
        Params.BLACK_ALBEDO = BLK_ALBEDO;
        Params.WHITE_ALBEDO = WHITE_ALBEDO;
        Params.SOLAR_LUMINOSITY = LUMINOSITY;
    }
}